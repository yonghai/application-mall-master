package com.mall.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.mall.ssm.base.Code;
import com.mall.ssm.po.custom.CouponsCustom;
import com.mall.ssm.po.dto.xcx.R;
import com.mall.ssm.po.vo.CouponsVo;
import com.mall.ssm.service.coupons.CouponsService;
import com.mall.ssm.service.redis.Redis;
import com.mall.ssm.util.Tool;

@Controller
public class CouponsController {
	
	@Autowired
	private CouponsService couponsService;	
	@Autowired
	private Redis<Object> redis;
	/**
	 * 小程序调用
	 * @param swiper
	 * @return
	 * @throws Exception
	 */
	
	/**
	 * 领取优惠券
	 */
	@RequestMapping(value="/discounts/fetch", method = RequestMethod.POST)
	public @ResponseBody R<String> discountsFetch(HttpServletRequest request)
		throws Exception {
		R<String> r = new R<String>();
		try {
			String id = request.getParameter("id");
			String token = request.getParameter("token");
			if(id == null || id.trim().length() == 0){
				r.setCode(Code.ERROR);
				r.setMsg("param id is empty!");
				return r;
			}
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
			
			CouponsVo couponsVo = new CouponsVo();
			couponsVo.setCouponsCustom(new CouponsCustom());
			couponsVo.getCouponsCustom().setId(Integer.parseInt(id));
			
			//获取优惠券信息
			CouponsCustom coupons= couponsService.getCouponsPublishedById(couponsVo);
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			
			Date target = simpleDateFormat.parse(coupons.getDateEnd());
			Date now = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
			long result =  Tool.compareDate(now,target);
			if(coupons == null || result<0){
				r.setCode(Code.Expire);
				r.setMsg("优惠券已过期");
				return r;
			}

			//获取优惠券的剩余数量 Expire
			String flag = coupons.getFlag();
			String remainStr = redis.get(flag);
			
			if(remainStr==null){
				r.setCode(Code.Remain0);
				r.setMsg("优惠券领完了");
				return r;
			}
			
			int remain = Integer.parseInt(remainStr);
			
			if(remain <= 0){
				r.setCode(Code.Remain0);
				r.setMsg("优惠券领完了");
				return r;
			}
			
			//获取用户id
			String openid = redis.get(token);
			
			//是否已经领用
			CouponsCustom cc = new CouponsCustom();
			cc.setMoney(coupons.getMoney());
			cc.setName(coupons.getName());
			cc.setMoneyHreshold(coupons.getMoneyHreshold());
			cc.setDateEnd(coupons.getDateEnd());
			cc.setOpenid(openid);
			cc.setFlag(coupons.getFlag());
			couponsVo.setCouponsCustom(cc);
			cc = couponsService.get(couponsVo);
			if(cc != null){
				r.setCode(Code.Repeat);
				r.setMsg("你领过了，别贪心哦~");
				return r;
			}
			
			
			try {
				delCounter(redis, flag);
				coupons.setOpenid(openid);
				couponsVo.setCouponsCustom(coupons);
				couponsService.insert(couponsVo);
			} catch (RuntimeException e) {
				e.printStackTrace();
				r.setCode(Code.Remain0);
				r.setMsg(e.getMessage());
				return r;
			}
			
			r.setCode(Code.OK);
			r.setMsg("success");
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR2);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	private synchronized void delCounter(Redis<Object> redis,String flag) throws RuntimeException{
		String remainStr = redis.get(flag);
		int remain = Integer.parseInt(remainStr);
		remain--;
		if(remain<0)
			throw new RuntimeException("优惠券领完了");
		redis.add(flag,remain+"");
	}
	/**
	 * 小程序首页优惠券列表
	 */
	@RequestMapping(value="/discounts/coupons", method = RequestMethod.POST)
	public @ResponseBody R<List<CouponsCustom>> discountsCoupons(HttpServletRequest request)
		throws Exception {
		R<List<CouponsCustom>> r = new R<List<CouponsCustom>>();
		try {
			CouponsVo couponsVo = new CouponsVo();
			couponsVo.setCouponsCustom(new CouponsCustom());
			couponsVo.getCouponsCustom().setPage(1);
			couponsVo.getCouponsCustom().setLimit(1000);
			List<CouponsCustom> coupons= couponsService.getCouponsPublished(couponsVo);
			
			for (CouponsCustom coupon : coupons) {
				if(coupon.getRemain() == 0){
					continue;
				}
				String storesRemain = redis.get(coupon.getFlag());
				if(storesRemain == null){
					redis.add(coupon.getFlag(), coupon.getRemain()+"");
					storesRemain = coupon.getRemain()+"";
				}
				
				int remain = Integer.parseInt(storesRemain);
				coupon.setRemain(remain);
				if(remain == 0){
					CouponsVo cv = new CouponsVo();
					cv.setCouponsCustom(new CouponsCustom());
					cv.getCouponsCustom().setRemain(0);
					couponsService.updateCouponsPublished(cv);
					redis.delete(coupon.getFlag());
				}
			}
			r.setCode(Code.OK);
			r.setMsg("success");
			r.setData(coupons);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	/**
	 * 我的优惠券
	 */
	@RequestMapping(value="/discounts/my", method = RequestMethod.POST)
	public @ResponseBody R<List<CouponsCustom>> myDiscounts(HttpServletRequest request,String token,int status)
		throws Exception {
		R<List<CouponsCustom>> r = new R<List<CouponsCustom>>();
		
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
		
		CouponsVo cv = new CouponsVo();
		CouponsCustom cc = new CouponsCustom();
		cc.setStatus(status);
		cc.setOpenid(appid);
		cv.setCouponsCustom(cc);
		
		try {
			List<CouponsCustom> list = couponsService.getAllList(cv);
			List<CouponsCustom> data = new ArrayList<CouponsCustom>();
			//查找有效期内
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			for (CouponsCustom c : list) {
				Date target = simpleDateFormat.parse(c.getDateEnd());
				Date now = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
				if(Tool.compareDate(now,target)>=0){
					c.setDateEnd(simpleDateFormat.format(target));
					data.add(c);
				}
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
	
	
	
	
	
	/**
	 * 发行优惠券
	 */
	@RequestMapping(value="/coupons/add", method = RequestMethod.POST)
	public ModelAndView coupons_add(HttpServletRequest request,CouponsCustom c)
		throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String id = Tool.getUUID();
		c.setFlag(id);
		
		try {
			int stores = Integer.parseInt(request.getParameter("demo0"));
			int money = Integer.parseInt(request.getParameter("demo1_1"));
			int moneyHreshold = Integer.parseInt(request.getParameter("demo1_2"));
			c.setStores(stores);
			c.setRemain(stores);
			c.setMoney(money);
			c.setType(0);
			c.setStatus(0);
			c.setMoneyHreshold(moneyHreshold);
			CouponsVo vo = new CouponsVo();
			vo.setCouponsCustom(c);
			couponsService.insertCoupons(vo);
			
			redis.add(c.getFlag(), c.getStores().toString());
			
			modelAndView.setViewName("coupons");
			
		} catch (RuntimeException e) {
			modelAndView.addObject("coupons", c);
			modelAndView.addObject("msg", "添加失败:"+e.getMessage());
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	/**
	 * 后台优惠券列表
	 */
	@RequestMapping(value="/coupons/get", method = RequestMethod.POST)
	public @ResponseBody R<Map<Object, Object>> coupons_get(CouponsVo couponsVo)
		throws Exception {
		R<Map<Object, Object>> r = new R<Map<Object, Object>>();
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(couponsVo.getCouponsCustom().getPage()<1){
				couponsVo.getCouponsCustom().setPage(1);
			}
			if(couponsVo.getCouponsCustom().getLimit()<1){
				couponsVo.getCouponsCustom().setLimit(20);
			}
			
			List<CouponsCustom> coupons= couponsService.getCouponsPublished(couponsVo);
			
			for (CouponsCustom coupon : coupons) {
				if(coupon.getRemain() == 0){
					continue;
				}
				String storesRemain = redis.get(coupon.getFlag());
				if(storesRemain == null){
					redis.add(coupon.getFlag(), coupon.getStores()+"");
					storesRemain = coupon.getStores()+"";
				}
				
				int remain = Integer.parseInt(storesRemain);
				coupon.setRemain(remain);
				if(remain == 0){
					CouponsVo cv = new CouponsVo();
					cv.setCouponsCustom(new CouponsCustom());
					cv.getCouponsCustom().setRemain(0);
					couponsService.updateCouponsPublished(cv);
					redis.delete(coupon.getFlag());
				}
			}
			r.setCode(Code.OK);
			r.setMsg("success");
			map.put("data",coupons);  
			map.put("recordsTotal", coupons.size());  
			map.put("recordsFiltered", coupons.size());  
			map.put("draw", System.currentTimeMillis());
			r.setData(map);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	
	
	
	
	/**
	 * 后台被领取的优惠券列表
	 */
	@RequestMapping(value="/coupons/fetchList", method = RequestMethod.POST)
	public @ResponseBody R<Map<Object, Object>> coupons_fetchList(CouponsVo couponsVo)
		throws Exception {
		R<Map<Object, Object>> r = new R<Map<Object, Object>>();
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(couponsVo.getCouponsCustom().getPage()<1){
				couponsVo.getCouponsCustom().setPage(1);
			}
			if(couponsVo.getCouponsCustom().getLimit()<1){
				couponsVo.getCouponsCustom().setLimit(20);
			}
			
			List<CouponsCustom> coupons= couponsService.getFetchListByPage(couponsVo);
			
			for (CouponsCustom coupon : coupons) {
				coupon.getWxUserCustom();
			}
			r.setCode(Code.OK);
			r.setMsg("success");
			map.put("data",coupons);  
			map.put("recordsTotal", coupons.size());  
			map.put("recordsFiltered", coupons.size());  
			map.put("draw", System.currentTimeMillis());
			r.setData(map);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	/**
	 * 删除指定种类的优惠券
	 */
	@RequestMapping(value="/coupons/deleteByFlag", method = RequestMethod.POST)
	public @ResponseBody R<String> deleteByFlag(CouponsVo couponsVo)
		throws Exception {
		R<String> r = new R<String>();
		try {
			if(couponsVo.getCouponsCustom()!=null && couponsVo.getCouponsCustom().getFlag()!=null && couponsVo.getCouponsCustom().getFlag().trim().length()>0){
				String flag = couponsVo.getCouponsCustom().getFlag();
				if(redis.exist(flag)){
					//删除redis数据
					redis.delete(flag);
				}
				couponsService.deleteCouponsAll(flag);
				r.setCode(Code.OK);
				r.setMsg("success");
			}else{
				r.setCode(Code.ERROR);
				r.setMsg("invalid param flag!");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
     * 判断客户端提交上来的令牌和服务器端生成的令牌是否一致
     * @param request
     * @return 
     *         true 用户重复提交了表单 
     *         false 用户没有重复提交表单
     */
    private boolean isRepeatSubmit(HttpServletRequest req,String token) {
        String client_token = token;
        //1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
        if(client_token==null){
            return true;
        }
        //取出存储在Session中的token
        String server_token = (String) req.getSession().getAttribute("token");
        //2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
        if(server_token==null){
            return true;
        }
        //3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if(!client_token.equals(server_token)){
            return true;
        }
        
        return false;
    }
	
}
