package com.mall.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.mall.ssm.base.Code;
import com.mall.ssm.base.IntegralOperateType;
import com.mall.ssm.base.IntegralType;
import com.mall.ssm.base.OrderStatus;
import com.mall.ssm.po.custom.CouponsCustom;
import com.mall.ssm.po.custom.IntegralCustom;
import com.mall.ssm.po.custom.IntegralDetailCustom;
import com.mall.ssm.po.custom.OrderCustom;
import com.mall.ssm.po.custom.OrderItemCustom;
import com.mall.ssm.po.custom.ProductCustom;
import com.mall.ssm.po.custom.PropertyCustom;
import com.mall.ssm.po.custom.PropertyItemCustom;
import com.mall.ssm.po.custom.ReputationCustom;
import com.mall.ssm.po.dto.CreateOrder;
import com.mall.ssm.po.dto.GoodsDto;
import com.mall.ssm.po.dto.MResult;
import com.mall.ssm.po.dto.OrderListDto;
import com.mall.ssm.po.dto.OrderListPic;
import com.mall.ssm.po.dto.orderdetail.Logistics;
import com.mall.ssm.po.dto.orderdetail.OrderDetailDto;
import com.mall.ssm.po.dto.reputation.Reputation2;
import com.mall.ssm.po.dto.reputation.ReputationDto;
import com.mall.ssm.po.dto.xcx.R;
import com.mall.ssm.po.vo.CouponsVo;
import com.mall.ssm.po.vo.IntegralDetailVo;
import com.mall.ssm.po.vo.IntegralVo;
import com.mall.ssm.po.vo.OrderItemVo;
import com.mall.ssm.po.vo.OrderVo;
import com.mall.ssm.po.vo.ProductVo;
import com.mall.ssm.po.vo.PropertyVo;
import com.mall.ssm.po.vo.ReputationVo;
import com.mall.ssm.service.coupons.CouponsService;
import com.mall.ssm.service.integral.IntegralService;
import com.mall.ssm.service.order.OrderService;
import com.mall.ssm.service.order.ReputatioService;
import com.mall.ssm.service.product.ProductService;
import com.mall.ssm.service.property.PropertyService;
import com.mall.ssm.service.redis.Redis;
import com.mall.ssm.util.Tool;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private Redis<Object> redis;
	@Autowired
	private ProductService productService;	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private CouponsService couponsService;
	@Autowired
	private ReputatioService reputatioService;
	@Autowired
	private IntegralService integralService;
	/**
	 * 插入评论
	 * @param request
	 * @param postJsonString
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/order/reputation", method = RequestMethod.POST)
	public @ResponseBody R<String> reputation(HttpServletRequest request,String postJsonString)
		throws Exception {
		R<String> r = new R<String>();
		try {
			if(postJsonString!=null){
				postJsonString = java.net.URLDecoder.decode(postJsonString,"UTF-8");
			}
			ReputationDto reputationDto = JSONObject.parseObject(postJsonString, ReputationDto.class);
			if(reputationDto.getToken() == null || reputationDto.getToken().trim().length() == 0){
				r.setCode(Code.ERROR);
				r.setMsg("token is invalid");
				return r;
			}
			if(!redis.exist(reputationDto.getToken())){
				r.setCode(Code.ERROR);
				r.setMsg("token is invalid");
				return r;
			}
			String openid = redis.get(reputationDto.getToken());
			List<ReputationVo> data = new ArrayList<ReputationVo>();
			for (Reputation2 re : reputationDto.getReputations()) {
				ReputationCustom rc = new ReputationCustom();
				rc.setAppid(openid);
				rc.setDataAdd(Tool.getyyyyMMddHHmmss());
				rc.setGoodsId(re.getGoodsId());
				rc.setId(0);
				rc.setRemark(re.getRemark());
				rc.setReputation(re.getReputation());
				ReputationVo rv = new ReputationVo();
				rv.setReputationCustom(rc);
				data.add(rv);
			}
			if(data.size()>0){
				/*
				 * 1.插入评论
				 */
				reputatioService.insertBatch(data);
				
				
				/*
				 * 2.改变订单状态
				 */
				OrderCustom order = new OrderCustom();
				order.setOrderNumber(reputationDto.getOrderId());
				OrderVo ov = new OrderVo();
				ov.setOrderCustom(order);
				order = orderService.getOrderSimple(ov);
				if(order == null){
					r.setMsg("find no data");
					r.setCode(Code.ERROR);
					return r;
				}
				if(order.getStatus() == OrderStatus.daipingjia){
					order.setStatus(OrderStatus.yiwancheng);
					ov.setOrderCustom(order);
					orderService.updateOrder(ov);
					
					
					//增加积分
					String product_ids = order.getProductIds();
					try {
						int score = 0;
						for (String _id : product_ids.split(",")) {
							int id = Integer.parseInt(_id);
							ProductVo productVo = new ProductVo();
							ProductCustom pc = new ProductCustom();
							pc.setId(id);
							productVo.setProductCustom(pc);
							pc = productService.get(productVo);
							score += pc.getCommission();
						}
						if(score>0){
							IntegralDetailVo iv = new IntegralDetailVo();
							IntegralDetailCustom ic = new IntegralDetailCustom();
							ic.setAppid(openid);
							iv.setIntegralDetailCustom(ic);
							
							
							IntegralVo ivv = new IntegralVo();
							IntegralCustom icc = new IntegralCustom();
							icc.setId(openid);
							ivv.setIntegralCustom(icc);
							icc = integralService.getIntegral(ivv);
							if(icc == null){
								icc = new IntegralCustom();
								icc.setAvailable(0);
								icc.setTotal(0);
								icc.setId(openid);
								ivv.setIntegralCustom(icc);
								integralService.insertIntegral(ivv);
							}
							
							ic.setAppid(openid);
							ic.setOperation(score);
							ic.setAvailable(icc.getAvailable());
							ic.setOperationtype(IntegralOperateType.ADD);
							ic.setType(IntegralType.Buy);
							ic.setTime(Tool.getyyyyMMddHHmmss());
							iv.setIntegralDetailCustom(ic);
							integralService.insertIntegralDetail(iv);
							icc.setTotal(icc.getTotal()+ic.getOperation());
							icc.setAvailable(icc.getAvailable()+ic.getOperation());
							ivv.setIntegralCustom(icc);
							integralService.updateIntegral(ivv);
							
						}
					} catch (RuntimeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					r.setMsg("success");
					r.setCode(Code.OK);
				}else{
					r.setMsg("订单状态异常");
					r.setCode(Code.ERROR);
					return r;
				}
			}else{
				r.setMsg("empty data");
				r.setCode(Code.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		return r;
	}
	
	
	@RequestMapping(value="/shop/goods/reputation", method = RequestMethod.POST)
	public @ResponseBody R<List<ReputationCustom>> goods_reputation(HttpServletRequest request,ReputationVo reputationVo)throws Exception {
		R<List<ReputationCustom>> r = new R<List<ReputationCustom>>();
		List<ReputationCustom> list = new ArrayList<ReputationCustom>();
		if(reputationVo == null ||reputationVo.getReputationCustom()==null|| reputationVo.getReputationCustom().getGoodsId()==null){
			r.setData(list);
			r.setCode(Code.OK);
			r.setMsg("invalid param goodsId");
			return r;
		}
		try {
			int goodsId = reputationVo.getReputationCustom().getGoodsId();
			reputationVo.setReputationCustom(new ReputationCustom());
			reputationVo.getReputationCustom().setGoodsId(goodsId);
			list = reputatioService.getAllList(reputationVo);
			for (ReputationCustom reputationCustom : list) {
				reputationCustom.getUser();
			}
			r.setData(list);
			r.setCode(Code.OK);
			r.setMsg("success");
		} catch (Exception e) {
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@RequestMapping(value="/shop/reputations", method = RequestMethod.POST)
	public @ResponseBody MResult reputations(HttpServletRequest request,ReputationVo reputationVo)throws Exception {
		MResult rs = new MResult();
		List<ReputationCustom> scs;
		
		try {
			if(reputationVo.getReputationCustom().getPage()<1){
				reputationVo.getReputationCustom().setPage(1);
			}
			if(reputationVo.getReputationCustom().getLimit()<1){
				reputationVo.getReputationCustom().setLimit(10);
			}
			scs = reputatioService.getListByPage(reputationVo);
			for (ReputationCustom r : scs) {
				r.getUser();
				r.getProductCustom();
			}
			long count = reputatioService.getCount(reputationVo);
			
			Map<Object, Object> r = new HashMap<Object, Object>();
			
			r.put("data",scs);  
			r.put("recordsTotal", count);  
			r.put("recordsFiltered", count);  
			r.put("draw", System.currentTimeMillis());
			
			rs.setOk(true);
			rs.setResult(r);
		} catch (Exception e) {
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		return rs;
	}
	@RequestMapping(value="/reputation/delete")
	public @ResponseBody MResult reputation_delete(HttpServletRequest request,ReputationVo reputationVo)throws Exception {
		MResult rs = new MResult();
		try {
			if(reputationVo == null ||reputationVo.getReputationCustom() == null || reputationVo.getReputationCustom().getId() == null){
				rs.setOk(false);
				rs.setResult("invaild param id");
			}
			reputatioService.delete(reputationVo.getReputationCustom().getId());
			rs.setOk(true);
			rs.setResult("删除成功");
		} catch (Exception e) {
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		return rs;
	}
	/**
	 * 付款成功
	 * @param request
	 * @param orderVo
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/order/payOk", method = RequestMethod.POST)
	public @ResponseBody R<String> payOk(HttpServletRequest request,OrderVo orderVo,String token)
		throws Exception {
		R<String> r = new R<String>();
		if(orderVo.getOrderCustom()==null || orderVo.getOrderCustom().getOrderNumber() == null || orderVo.getOrderCustom().getOrderNumber().trim().length()==0){
			r.setCode(Code.ERROR);
			r.setMsg("invalid param orderNumber");
			return r;
		}
		try {
			OrderCustom order = orderService.getOrderSimple(orderVo);
			if(order.getStatus() == OrderStatus.daifukuan){
				order.setStatus(OrderStatus.daifahuo);
				orderVo.setOrderCustom(order);
				orderService.updateOrder(orderVo);
				
				//购买量+1
				String ids = order.getProductIds();
				for(String id : ids.split(",")){
					String buyStr = redis.get("goods_buy_"+id);
					int buy = 0;
					if(buyStr == null){
						buyStr = "1";
						buy = Integer.parseInt(buyStr);
						redis.add("goods_buy_"+id,buyStr);
					}else{
						try {
							buy = Integer.parseInt(buyStr);
							buy++;
							redis.add("goods_buy_"+id,buy+"");
						} catch (RuntimeException e1) {}
					}
					
				}
				
				
				
			}
			r.setMsg("success");
			r.setCode(Code.OK);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		return r;
	}
	/**
	 * 确认收货
	 * @param request
	 * @param orderVo
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/order/delivery", method = RequestMethod.POST)
	public @ResponseBody R<String> deliveryOrder(HttpServletRequest request,OrderVo orderVo,String token)
		throws Exception {
		R<String> r = new R<String>();
		if(orderVo.getOrderCustom()==null || orderVo.getOrderCustom().getOrderNumber() == null || orderVo.getOrderCustom().getOrderNumber().trim().length()==0){
			r.setCode(Code.ERROR);
			r.setMsg("invalid param orderNumber");
			return r;
		}
		try {
			OrderCustom order = orderService.getOrderSimple(orderVo);
			if(order.getStatus() == OrderStatus.daishouhuo){
				order.setStatus(OrderStatus.daipingjia);
				orderVo.setOrderCustom(order);
				orderService.updateOrder(orderVo);
			}
			r.setMsg("success");
			r.setCode(Code.OK);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		return r;
	}
	/**
	 * 查余额
	 * @param request
	 * @param orderVo
	 * @param token
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value="/user/amount", method = RequestMethod.POST)
//	public @ResponseBody R<String> amountOrder(HttpServletRequest request,OrderVo orderVo,String token)
//		throws Exception {
//		R<String> r = new R<String>();
//		if(orderVo.getOrderCustom()==null || orderVo.getOrderCustom().getOrderNumber() == null || orderVo.getOrderCustom().getOrderNumber().trim().length()==0){
//			r.setCode(Code.ERROR);
//			r.setMsg("invalid param orderNumber");
//			return r;
//		}
//		try {
//			OrderCustom order = orderService.getOrderSimple(orderVo);
//			if(order.getStatus() == OrderStatus.daifukuan){
//				order.setStatus(OrderStatus.daifahuo);
//				orderVo.setOrderCustom(order);
//				orderService.updateOrder(orderVo);
//			}
//			r.setMsg("success");
//			r.setCode(Code.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			r.setMsg(e.getMessage());
//			r.setCode(Code.ERROR);
//		}
//		return r;
//	}
	
	
	/**
	 * 
	 * @param request
	 * @param orderVo
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/order/close", method = RequestMethod.POST)
	public @ResponseBody R<String> closeOrder(HttpServletRequest request,OrderVo orderVo,String token)
		throws Exception {
		R<String> r = new R<String>();
		
		if(orderVo.getOrderCustom()==null || orderVo.getOrderCustom().getOrderNumber() == null || orderVo.getOrderCustom().getOrderNumber().trim().length()==0){
			r.setCode(Code.ERROR);
			r.setMsg("invalid param orderNumber");
			return r;
		}
		
		try {
			orderService.deleteOrder(orderVo.getOrderCustom().getOrderNumber());
			r.setMsg("success");
			r.setCode(Code.OK);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	/**
	 * 详细订单
	 * @param request
	 * @param orderVo
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/order/detail", method = RequestMethod.POST)
	public @ResponseBody R<OrderDetailDto> orderDetail(HttpServletRequest request,OrderVo orderVo,String token)
		throws Exception {
		R<OrderDetailDto> r = new R<OrderDetailDto>();
		OrderDetailDto data = new OrderDetailDto();
		
		if(orderVo.getOrderCustom()==null || orderVo.getOrderCustom().getOrderNumber() == null || orderVo.getOrderCustom().getOrderNumber().trim().length()==0){
			r.setCode(Code.ERROR);
			r.setMsg("invalid param orderNumber");
			return r;
		}
		
		try {
			OrderCustom order = orderService.getOrderWithItems(orderVo.getOrderCustom().getOrderNumber());
			if(order == null){
				r.setCode(Code.ERROR);
				r.setMsg("found no data");
				return r;
			}
			List<OrderItemCustom> items = order.getOrderdetails();
			data.setGoods(items);
			order.setOrderdetails(null);
			data.setOrderInfo(order);
			if(order.getLogistics()>0){
				Logistics logistics = new Logistics(order.getTrackingNumber()==null?"暂无":order.getTrackingNumber(),order.getLinkMan(),order.getMobile(),order.getAddress());
				data.setLogistics(logistics);
				
				//暂无实现物流信息
				//LogisticsTraces logisticsTraces = new LogisticsTraces("暂无物流信息","");
				//data.setLogisticsTraces(logisticsTraces);
			}
			
			r.setMsg("success");
			r.setCode(Code.OK);
			r.setData(data);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	@RequestMapping(value="/order/detail2")
	public ModelAndView orderDetail2(HttpServletRequest request,OrderVo orderVo)
		throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("orderdetail");
		
		R<OrderDetailDto> r = new R<OrderDetailDto>();
		OrderDetailDto data = new OrderDetailDto();
		
		if(orderVo.getOrderCustom()==null || orderVo.getOrderCustom().getOrderNumber() == null || orderVo.getOrderCustom().getOrderNumber().trim().length()==0){
			r.setCode(Code.ERROR);
			r.setMsg("invalid param orderNumber");
			request.setAttribute("result", r);
			return modelAndView;
		}
		
		try {
			OrderCustom order = orderService.getOrderWithItems(orderVo.getOrderCustom().getOrderNumber());
			if(order == null){
				r.setCode(Code.ERROR);
				r.setMsg("found no data");
				request.setAttribute("result", r);
				return modelAndView;
			}
			List<OrderItemCustom> items = order.getOrderdetails();
			data.setGoods(items);
			order.setOrderdetails(null);
			data.setOrderInfo(order);
			if(order.getLogistics()>0){
				Logistics logistics = new Logistics(order.getTrackingNumber()==null?"暂无":order.getTrackingNumber(),order.getLinkMan(),order.getMobile(),order.getAddress());
				data.setLogistics(logistics);
				
				//暂无实现物流信息
				//LogisticsTraces logisticsTraces = new LogisticsTraces("暂无物流信息","");
				//data.setLogisticsTraces(logisticsTraces);
			}
			
			r.setMsg("success");
			r.setCode(Code.OK);
			r.setData(data);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		request.setAttribute("result", r);
		
		return modelAndView;
	}
	/**
	 * 列表
	 * @param request
	 * @param orderVo
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/order/list", method = RequestMethod.POST)
	public @ResponseBody R<OrderListDto> orderList(HttpServletRequest request,OrderVo orderVo,String token)
		throws Exception {
		R<OrderListDto> r = new R<OrderListDto>();
		OrderListDto orderList = new OrderListDto();
		
		if(token == null || token.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(token)){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		String appid = redis.get(token);
		
		if(appid == null || appid.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(orderVo.getOrderCustom()==null){
			OrderCustom oc = new OrderCustom();
			oc.setStatus(OrderStatus.daifukuan);
			orderVo.setOrderCustom(oc);
		}
		orderVo.getOrderCustom().setAppid(appid);
		
		try {
			orderVo.getOrderCustom().setPage(1);
			orderVo.getOrderCustom().setStart(0);
			orderVo.getOrderCustom().setLimit(1000);
			List<OrderCustom> list = orderService.getOrderListByPage(orderVo);
			List<List<OrderListPic>> goodsMap = new ArrayList<List<OrderListPic>>();
			for (OrderCustom o : list) {
				List<OrderListPic> pics = new ArrayList<OrderListPic>();
				for(String pic : o.getProductPics().split(",")){
					pic = Tool.toRealUrl(pic);
					pics.add(new OrderListPic(pic));
				}
				o.setProductPics("");
				goodsMap.add(pics);
				
				//将时间格式后的.0去掉,此步可以省略或者修改格式
				if(o.getDateAdd()!=null){
					if(o.getDateAdd().endsWith(".0")){
						try {
							String dateAdd = o.getDateAdd().substring(0, o.getDateAdd().length()-2);
							o.setDateAdd(dateAdd);
						} catch (RuntimeException e) {}
					}
				}
			}
			orderList.setGoodsMap(goodsMap);
			orderList.setOrderList(list);
			r.setMsg("success");
			r.setCode(Code.OK);
			r.setData(orderList);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	/**
	 * 生成订单
	 * @param request
	 * @param orderVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/order/create", method = RequestMethod.POST)
	public @ResponseBody R<CreateOrder> create(HttpServletRequest request,OrderVo orderVo)
		throws Exception {
		R<CreateOrder> r = new R<CreateOrder>();
		CreateOrder data = new CreateOrder();
		data.setIsNeedLogistics(1);//默认需要物流
		if(orderVo.getToken() == null || orderVo.getToken().trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(orderVo.getToken())){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		String openid = redis.get(orderVo.getToken());
		
		
		if(orderVo.getGoodsJsonStr()!=null){
			orderVo.setGoodsJsonStr(java.net.URLDecoder.decode(orderVo.getGoodsJsonStr(),"UTF-8"));
		}
		if(orderVo.getAddress()!=null){
			orderVo.setAddress(java.net.URLDecoder.decode(orderVo.getAddress(),"UTF-8"));
		}
		if(orderVo.getLinkMan()!=null){
			orderVo.setLinkMan(java.net.URLDecoder.decode(orderVo.getLinkMan(),"UTF-8"));
		}
		if(orderVo.getRemark()!=null){
			orderVo.setRemark(java.net.URLDecoder.decode(orderVo.getRemark(),"UTF-8"));
		}
		OrderCustom order = new OrderCustom();
		List<OrderItemVo> items = new ArrayList<OrderItemVo>();
		
		try {
		////"[{"goodsId":5,"number":1,"propertyChildIds":"6:13,","logisticsType":0, "inviter_id":0}]"
			List<GoodsDto> goods = JSONObject.parseArray(orderVo.getGoodsJsonStr(), GoodsDto.class);
			String uid = Tool.getUUID();
			
			String goodIds = "";
			String pics = "";
			Double amount = 0.00d;
			for (GoodsDto good : goods) {
				ProductVo pv = new ProductVo();
				ProductCustom pc = new ProductCustom();
				pc.setId(good.getGoodsId());
				pv.setProductCustom(pc);
				pc = productService.get(pv);
				
				goodIds+=good.getGoodsId()+",";
				pics+=pc.getPic()+",";
				if(good.getNumber()>pc.getStores()){
					r.setCode(Code.ERROR);
					r.setMsg("["+pc.getName()+"]购买数量超出了库存!");
					return r;
				}
				amount += pc.getMinPrice() * good.getNumber();
				
				OrderItemVo orderitem = new OrderItemVo();
				OrderItemCustom itemCustom = new OrderItemCustom();
				String propertys = good.getPropertyChildIds();
				String property = "";
				if(propertys !=null &&propertys.trim().length()>0){//商品属性
					try {
						String [] proNames = propertys.split(",")[0].split(":");
						if(proNames.length>1){
							PropertyVo prov = new PropertyVo();
							PropertyCustom proc = new PropertyCustom();
							String idStr = proNames[0];
							proc.setId(Integer.parseInt(idStr));
							prov.setPropertyCustom(proc);
							proc = propertyService.getFull(prov);
							property += proc.getName()+":";
							int itemid = Integer.parseInt(proNames[1]);
							
							for(PropertyItemCustom i : proc.getPropertyItemCustoms()){
								if(i.getId() == itemid){
									property += i.getName();
									break;
								}
							}
						}
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
					
				}
				itemCustom.setGoodsId(good.getGoodsId());
				itemCustom.setId(0);
				itemCustom.setOid(uid);
				itemCustom.setGoodsName(pc.getName());
				itemCustom.setPic(pc.getPic());
				itemCustom.setAmount(pc.getMinPrice());
				itemCustom.setProperty(property);
				itemCustom.setNumber(good.getNumber());
				
				
				
				//子订单
				orderitem.setOrderItemCustom(itemCustom);
				items.add(orderitem);
			}
			
			order.setProductIds(goodIds);
			order.setProductPics(pics);
			order.setTrackingNumber(null);
			order.setStatus(OrderStatus.daifukuan);
			order.setRemark(orderVo.getRemark());
			order.setOrderNumber(uid);
			order.setMobile(orderVo.getMobile());
			order.setLogistics(1);//默认需要物流
			order.setLinkMan(orderVo.getLinkMan());
			order.setAppid(openid);
			order.setAddress(orderVo.getAddress());
			order.setId(0);
			if(orderVo.getCouponId() == null){
				order.setDiscount(0);
			}else{
				CouponsVo cv = new CouponsVo();
				CouponsCustom cc = new CouponsCustom();
				cc.setId(orderVo.getCouponId());
				cv.setCouponsCustom(cc);
				cc = couponsService.get(cv);
				if(cc!=null){
					order.setDiscount(cc.getMoney());
					order.setCouponId(cc.getId());
				}
			}
			order.setAmount(amount);
			order.setAmountLogistics(0.00d);//默认免运费
			order.setAmountReal(order.getAmount()-order.getAmountLogistics()-order.getDiscount());
			order.setDateAdd(Tool.getyyyyMMddHHmmss());
			
			/*
			 * 事务
			 */
			orderVo.setOrderCustom(order);
			orderService.insertOrder(orderVo);//订单
			orderService.insertOrderItemBatch(items);//子订单
			r.setMsg("success");
			r.setCode(Code.OK);
			data.setOrderNumber(uid);
			data.setDateAdd(order.getDateAdd());
			data.setAmountLogistics(0.00d);
			data.setAmountTotle(order.getAmountReal());
			r.setData(data);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	@RequestMapping(value="/order/calculate", method = RequestMethod.POST)
	public @ResponseBody R<CreateOrder> calculateOrder(HttpServletRequest request,OrderVo orderVo)
		throws Exception {
		R<CreateOrder> r = new R<CreateOrder>();
		CreateOrder data = new CreateOrder();
		data.setIsNeedLogistics(1);//默认需要物流
		if(orderVo.getToken() == null || orderVo.getToken().trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(orderVo.getToken())){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		try {
			if(orderVo.getGoodsJsonStr()!=null){
				orderVo.setGoodsJsonStr(java.net.URLDecoder.decode(orderVo.getGoodsJsonStr(),"UTF-8"));
			}
		////"[{"goodsId":5,"number":1,"propertyChildIds":"6:13,","logisticsType":0, "inviter_id":0}]"
			List<GoodsDto> goods = JSONObject.parseArray(orderVo.getGoodsJsonStr(), GoodsDto.class);
			Double amount = 0.00d;
			for (GoodsDto good : goods) {
				ProductVo pv = new ProductVo();
				ProductCustom pc = new ProductCustom();
				pc.setId(good.getGoodsId());
				pv.setProductCustom(pc);
				pc = productService.get(pv);
				amount += pc.getMinPrice() * good.getNumber();
			}
			
			data.setAmountLogistics(0.00d);
			data.setAmountTotle(amount);
			
			r.setMsg("success");
			r.setCode(Code.OK);
			r.setData(data);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/order/fahuo")
	public @ResponseBody MResult fahuo(OrderVo order) throws Exception {
		MResult rs = new MResult();
		try {
			OrderVo o = new OrderVo();
			o.setOrderCustom(new OrderCustom());
			o.getOrderCustom().setOrderNumber(order.getOrderCustom().getOrderNumber());
			OrderCustom oc = orderService.getOrderSimple(o);
			if(oc == null){
				rs.setOk(false);
				rs.setResult("订单不存在");
				return rs;
			}
			if(oc.getStatus() == 0){
				order.getOrderCustom().setStatus(1);
				orderService.updateOrder(order);
				rs.setOk(true);
				rs.setResult("已发货");
			}else{
				rs.setOk(false);
				rs.setResult("订单异常,请联系客服");
				return rs;
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		
		return rs;
	}
	@RequestMapping(value="/order/delete")
	public @ResponseBody MResult delete(OrderVo order) throws Exception {
		MResult rs = new MResult();
		try {
			OrderVo o = new OrderVo();
			o.setOrderCustom(new OrderCustom());
			o.getOrderCustom().setOrderNumber(order.getOrderCustom().getOrderNumber());
			OrderCustom oc = orderService.getOrderSimple(o);
			if(oc == null){
				rs.setOk(false);
				rs.setResult("订单不存在");
				return rs;
			}
			orderService.deleteOrder(order.getOrderCustom().getOrderNumber());
			rs.setOk(true);
			rs.setResult("订单已删除");
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		
		return rs;
	}
	
	@RequestMapping(value="/order/listPage")
	public @ResponseBody MResult listProducts(OrderVo order) throws Exception {
		MResult rs = new MResult();
		List<OrderCustom> scs;
		try {
			if(order.getOrderCustom().getPage()<1){
				order.getOrderCustom().setPage(1);
			}
			if(order.getOrderCustom().getLimit()<1){
				order.getOrderCustom().setLimit(10);
			}
			
			if(order.getOrderCustom().getOrderNumber()!=null && order.getOrderCustom().getOrderNumber().trim().length()==0){
				order.getOrderCustom().setOrderNumber(null);
			}
			if(order.getOrderCustom().getAppid()!=null && order.getOrderCustom().getAppid().trim().length()==0){
				order.getOrderCustom().setAppid(null);
			}
			scs = orderService.getOrderListByPage(order);
			for (OrderCustom orderCustom : scs) {
				orderCustom.getUser();
			}
			long count = orderService.getCount(order);
			
			Map<Object, Object> r = new HashMap<Object, Object>();

			r.put("data",scs);  
			r.put("recordsTotal", count);  
			r.put("recordsFiltered", count);  
			r.put("draw", System.currentTimeMillis());
			
			rs.setOk(true);
			rs.setResult(r);
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		
		return rs;
	}
	
}
