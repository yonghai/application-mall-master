package com.mall.ssm.controller;

import java.io.File;
import java.io.IOException;
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

import com.mall.ssm.base.Code;
import com.mall.ssm.po.custom.ProductCategoryCustom;
import com.mall.ssm.po.custom.ProductCustom;
import com.mall.ssm.po.custom.ProductDetailCustom;
import com.mall.ssm.po.custom.PropertyCustom;
import com.mall.ssm.po.dto.MResult;
import com.mall.ssm.po.dto.MResultT;
import com.mall.ssm.po.dto.ProductDto;
import com.mall.ssm.po.dto.xcx.R;
import com.mall.ssm.po.result.SaveProduct;
import com.mall.ssm.po.vo.ProductCategoryVo;
import com.mall.ssm.po.vo.ProductDetailVo;
import com.mall.ssm.po.vo.ProductVo;
import com.mall.ssm.po.vo.PropertyVo;
import com.mall.ssm.service.product.ProductDetailService;
import com.mall.ssm.service.product.ProductService;
import com.mall.ssm.service.productcategory.ProductCategoryService;
import com.mall.ssm.service.property.PropertyService;
import com.mall.ssm.service.redis.Redis;
import com.mall.ssm.util.Configuration;
import com.mall.ssm.util.TokenProccessor;
import com.mall.ssm.util.Tool;

@Controller
public class ProductController {
	@Autowired
	private Redis<Object> redis;
	@Autowired
	private ProductService productService;	
	@Autowired
	private ProductDetailService productDetailService;	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private ProductCategoryService productCategoryService;
	
	
	/**
	 * 小程序调用
	 * @param swiper
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/shop/goods/list",method = RequestMethod.POST)
	public @ResponseBody R<List<ProductCustom>> goodsList(ProductVo product) throws Exception {
		R<List<ProductCustom>> r = new R<List<ProductCustom>>();
		try {
			List<ProductCustom> data = productService.getAllList(product);
			/*
			 * 补全图片url
			 */
			for (ProductCustom p : data) {
				String pic = p.getPic();
				pic = Tool.toRealUrl(pic);
				p.setPic(pic);
			}
			r.setCode(Code.OK);
			r.setMsg("success");
			r.setData(data);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@RequestMapping(value="/shop/goods/detail",method = RequestMethod.POST)
	public @ResponseBody R<ProductDto> detailGoodList(ProductVo product) throws Exception {
		R<ProductDto> r = new R<ProductDto>();
		try {
			if(product!=null && product.getProductCustom()!=null&&product.getProductCustom().getId()>0){

				ProductDto p = new ProductDto();
				ProductCustom pc = productService.get(product);
				
				String viewStr = redis.get("goods_view_"+pc.getId());
				int view = 0;
				if(viewStr == null){
					viewStr = "1";
					view = Integer.parseInt(viewStr);
					redis.add("goods_view_"+pc.getId(),viewStr);
				}else{
					try {
						view = Integer.parseInt(viewStr);
						view++;
						redis.add("goods_view_"+pc.getId(),view+"");
					} catch (RuntimeException e1) {}
				}
				
				int buy = 0;
				String buyStr = redis.get("goods_buy_"+pc.getId());
				if(viewStr == null){
				}else{
					try {
						buy = Integer.parseInt(buyStr);
					} catch (RuntimeException e1) {}
				}
				
				pc.setNumberOrders(buy);
				pc.setView(view);
				/*
				 * 为商品图补全地址
				 */
				String pic = pc.getPic();
				pc.setPic(Tool.toRealUrl(pic));
				/*
				 * 分类
				 */
				List<ProductCategoryCustom> categorys = productCategoryService.getAllList(null);
				
				/*
				 * 属性
				 */
				List<PropertyCustom> propertys = new ArrayList<PropertyCustom>();
				String propertyid = pc.getPropertyIds();
				if(propertyid!=null && propertyid.trim().length()>0){
					PropertyVo pv = new PropertyVo();
					PropertyCustom prc = new PropertyCustom();
					try {
						int pid = Integer.parseInt(propertyid);
						prc.setId(pid);
						pv.setPropertyCustom(prc);
						PropertyCustom property =propertyService.getFull(pv);
						propertys.add(property);
					} catch (RuntimeException e) {
					}
					
				}
				
				/*
				 * 商品详情
				 */
				ProductDetailVo pdv = new ProductDetailVo();
				ProductDetailCustom productDetailCustom = new ProductDetailCustom();
				productDetailCustom.setId(product.getProductCustom().getId());
				pdv.setProductDetailCustom(productDetailCustom);
				ProductDetailCustom pdc = productDetailService.get(pdv);
				
				List<String> pics = new ArrayList<String>();
				if((pdc.getPic1()+"").length()>0 && !(pdc.getPic1()+"").toUpperCase().equals("NULL")){
					pics.add(Tool.toRealUrl(pdc.getPic1()+""));
				}
				if((pdc.getPic2()+"").length()>0 && !(pdc.getPic2()+"").toUpperCase().equals("NULL")){
					pics.add(Tool.toRealUrl(pdc.getPic2()+""));
				}
				if((pdc.getPic3()+"").length()>0 && !(pdc.getPic3()+"").toUpperCase().equals("NULL")){
					pics.add(Tool.toRealUrl(pdc.getPic3()+""));
				}
				p.setBasicInfo(pc);
				p.setCategorys(categorys);
				String content = Tool.replaceToServerUrl(pdc.getContent());
				p.setContent(content);
				p.setPics(pics);
				p.setProperties(propertys);
				
				r.setCode(Code.OK);
				r.setMsg("success");
				r.setData(p);
			}else{
				r.setCode(Code.ERROR);
				r.setMsg("缺少参数id");
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@RequestMapping(value="/shop/goods/price",method = RequestMethod.POST)
	public @ResponseBody R<ProductCustom> goodPrice(ProductVo product) throws Exception {
		R<ProductCustom> r = new R<ProductCustom>();
		try {
			if(product!=null && product.getProductCustom()!=null&&product.getProductCustom().getId()>0){
				ProductCustom pc = productService.get(product);
				if(pc == null){
					r.setCode(Code.ERROR);
					r.setMsg("no result");
					return r;
				}
				/*
				 * 为商品图补全地址
				 */
				String pic = pc.getPic();
				pc.setPic(Tool.toRealUrl(pic));
				
				r.setCode(Code.OK);
				r.setMsg("success");
				r.setData(pc);
			}else{
				r.setCode(Code.ERROR);
				r.setMsg("缺少参数id");
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 保存产品
	 * @param request
	 * @param notice
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/product/save", method = RequestMethod.POST)
	public @ResponseBody MResult addProduct(HttpServletRequest request,ProductVo product,String token)
		throws Exception {
		MResult result = new MResult(true,"");
		//1判断用户是否是重复提交
		boolean b = isRepeatSubmit(request,token);
        if(b==true){
        	//重复提交处理
        	result.setOk(false);
        	result.setResult("请不要重复提交");
        	return result;
        }
        
		/*
		 * 2.检查值是否为空
		 */
		if(product == null || product.getProductCustom() == null){
			result.setOk(false);
			result.setResult("缺少参数");
		}
		if(product.getProductCustom().getName()==null||product.getProductCustom().getName().trim().equals("")){
			result.setOk(false);
			result.setResult("Lack of parameter name!");
		}else if(product.getProductCustom().getPic()==null||product.getProductCustom().getPic().trim().equals("")){
			result.setOk(false);
			result.setResult("Lack of parameter pic!");
		}else if(product.getProductCustom().getCid()==null||product.getProductCustom().getCid().trim().equals("")){
			result.setOk(false);
			result.setResult("Lack of parameter cid!");
		}else if(product.getProductCustom().getStores()==null){
			result.setOk(false);
			result.setResult("Lack of parameter stores!");
		}else if(product.getProductCustom().getMinPrice()==null){
			result.setOk(false);
			result.setResult("Lack of parameter minPrice!");
		}else if(product.getProductCustom().getOriginalPrice()==null){
			result.setOk(false);
			result.setResult("Lack of parameter originalPrice!");
		}else if(product.getProductCustom().getCommission()==null){
			result.setOk(false);
			result.setResult("Lack of parameter commission!");
		}else if(product.getProductCustom().getOrderBy()==null){
			result.setOk(false);
			result.setResult("Lack of parameter orderBy!");
		}
		if(!result.isOk()){
			return result;
		}
		
		try {
			/*
			 * 3.将图片从临时文件夹移动到指定目录
			 */
			String tmpUrl = product.getProductCustom().getPic();
			MResultT<SaveProduct> r = MoveFileFromTemp(tmpUrl,false);
			if(!r.isOk()){
				result.setOk(false);
				result.setResult(r.getResult().getMsg());
				return result;
			}
			//设置产品商品展示图片地址
			product.getProductCustom().setPic(r.getResult().getUrl());
			product.getProductCustom().setPublished(Configuration.CaoGao);
			product.getProductCustom().setDateAdd(Tool.getyyyyMMddHHmmss());
			product.getProductCustom().setDateUpdate(Tool.getyyyyMMddHHmmss());
			product.getProductCustom().setCommissionType(0);
			product.getProductCustom().setMinScore(0.00d);
			product.getProductCustom().setNumberFav(0);
			product.getProductCustom().setNumberGoodReputation(0);
			product.getProductCustom().setNumberOrders(0);
			product.getProductCustom().setView(0);
			
			
			/*
			 * 展示图
			 */
			List<String> urls = new ArrayList<String>();
			r = MoveFileFromTemp(product.getPic1(),false);
			if(r.isOk()){
				urls.add(r.getResult().getUrl());
			}
			r = MoveFileFromTemp(product.getPic2(),false);
			if(r.isOk()){
				urls.add(r.getResult().getUrl());
			}
			r = MoveFileFromTemp(product.getPic3(),false);
			if(r.isOk()){
				urls.add(r.getResult().getUrl());
			}
			
			String content = product.getContent()+"";
			ProductDetailCustom pd = new ProductDetailCustom();
			pd.setId(0);
			pd.setContent(content);
			if(urls.size()>0){
				pd.setPic1(urls.get(0));
				urls.remove(0);
			}
			if(urls.size()>0){
				pd.setPic2(urls.get(0));
				urls.remove(0);
			}
			if(urls.size()>0){
				pd.setPic3(urls.get(0));
				urls.remove(0);
			}
			ProductDetailVo productDetailVo = new ProductDetailVo();
			productDetailVo.setProductDetailCustom(pd);
			
			/*
			 * 4.保存商品信息
			 */
			productService.save(product);
			productDetailService.save(productDetailVo);
			
			/*
			 * 5.清除token
			 */
			request.getSession().removeAttribute("token");
			
			result.setOk(true);
			result.setResult("提交成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setResult(e.getMessage());
		}
		return result;
	}
	/**
	 * 查找用户发放的产品
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/product/listPage",method = RequestMethod.POST)
	public @ResponseBody MResult listProducts(ProductVo notice) throws Exception {
		MResult rs = new MResult();
		List<ProductCustom> scs;
		try {
			if(notice.getProductCustom().getPage()<1){
				notice.getProductCustom().setPage(1);
			}
			if(notice.getProductCustom().getLimit()<1){
				notice.getProductCustom().setLimit(10);
			}
			scs = productService.getListByPage(notice);
			long count = productService.getCount(notice);
			
			Map<Object, Object> r = new HashMap<Object, Object>();
			/*
			 * 补全图片url
			 */
			for (ProductCustom p : scs) {
				String pic = p.getPic();
				pic = Tool.toRealUrl(pic);
				p.setPic(pic);
			}
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

	
	/**
	 * 产品发布
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/product/fabu",method = RequestMethod.POST)
	public @ResponseBody MResult fabu(ProductVo product) throws Exception {
		MResult rs = new MResult();
		try {
			if(product.getProductCustom().getId()>0){
				product.getProductCustom().setPublished(Configuration.ShangXian);
				product.getProductCustom().setMinPrice(null);
				product.getProductCustom().setOriginalPrice(null);
				product.getProductCustom().setCommission(null);
				product.getProductCustom().setCommissionType(null);
				product.getProductCustom().setStores(null);
				product.getProductCustom().setOrderBy(null);
				product.getProductCustom().setMinScore(null);
				product.getProductCustom().setNumberFav(null);
				product.getProductCustom().setNumberGoodReputation(null);
				product.getProductCustom().setNumberOrders(0);
				product.getProductCustom().setView(null);
				productService.update(product);
				
				rs.setOk(true);
				rs.setResult("修改成功");
			}else{
				rs.setOk(false);
				rs.setResult("缺少参数id");
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		
		return rs;
	}
	
	/**
	 * 产品下架
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/product/xiajia",method = RequestMethod.POST)
	public @ResponseBody MResult xiajia(ProductVo product) throws Exception {
		MResult rs = new MResult();
		try {
			if(product.getProductCustom().getId()>0){
				product.getProductCustom().setPublished(Configuration.XiaJia);
				product.getProductCustom().setMinPrice(null);
				product.getProductCustom().setOriginalPrice(null);
				product.getProductCustom().setCommission(null);
				product.getProductCustom().setCommissionType(null);
				product.getProductCustom().setStores(null);
				product.getProductCustom().setOrderBy(null);
				product.getProductCustom().setMinScore(null);
				product.getProductCustom().setNumberFav(null);
				product.getProductCustom().setNumberGoodReputation(null);
				product.getProductCustom().setNumberOrders(null);
				product.getProductCustom().setView(null);
				productService.update(product);
				
				rs.setOk(true);
				rs.setResult("修改成功");
			}else{
				rs.setOk(false);
				rs.setResult("缺少参数id");
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		
		return rs;
	}
	
	
	/**
	 * 删除产品
	 */
	@RequestMapping("/product/delete")
	public @ResponseBody MResult deleteProduct(HttpServletRequest request,ProductVo product)throws Exception{
		MResult rs = new MResult();
		
		try {
			if(product.getProductCustom()!=null&&product.getProductCustom().getId()!=0){
				/*
				 * 1.把图片文件删除
				 */
				try {
					ProductCustom pc = productService.get(product);
					ProductDetailCustom pdc = new ProductDetailCustom();
					pdc.setId(product.getProductCustom().getId());
					ProductDetailVo pdv = new ProductDetailVo();
					pdv.setProductDetailCustom(pdc);
					pdc = productDetailService.get(pdv);
					
					List<String> urls = new ArrayList<String>();
					urls.add(pc.getPic());
					urls.add(pdc.getPic1());
					urls.add(pdc.getPic2());
					urls.add(pdc.getPic3());
					
					deleteFile(urls);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
				
				
				/*
				 * 2.从数据库中删除
				 */
				productDetailService.delete(product.getProductCustom().getId());
				productService.delete(product.getProductCustom().getId());
				
				/*
				 * redis删除购买记录
				 */
				List<String> listKeys = new ArrayList<String>();
				listKeys.add("goods_buy_"+product.getProductCustom().getId());
				listKeys.add("goods_view_"+product.getProductCustom().getId());
				redis.delete(listKeys);
				
				rs.setOk(true);
				rs.setResult("ok");
				
			}else{
				rs.setOk(false);
				rs.setResult("缺少参数id");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		return rs;
	}
	/**
	 * 查询产品信息
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/product/select")
	public ModelAndView findProduct(HttpServletRequest request,ProductVo product) throws Exception {
		MResult rs = new MResult();
		try {
			if(product!=null && product.getProductCustom()!=null&&product.getProductCustom().getId()>0){
				try {
					ProductDto p = new ProductDto();
					ProductCustom pc = productService.get(product);
					/*
					 * 为商品图补全地址
					 */
					String pic = pc.getPic();
					pc.setPic(Tool.toRealUrl(pic));
					List<ProductCategoryCustom> categorys = productCategoryService.getAllList(null);
					List<PropertyCustom> propertys =propertyService.getAllList(null);
					
					ProductDetailVo pdv = new ProductDetailVo();
					ProductDetailCustom productDetailCustom = new ProductDetailCustom();
					productDetailCustom.setId(product.getProductCustom().getId());
					pdv.setProductDetailCustom(productDetailCustom);
					ProductDetailCustom pdc = productDetailService.get(pdv);
					
					List<String> pics = new ArrayList<String>();
					if((pdc.getPic1()+"").length()>0){
						pics.add(Tool.toRealUrl(pdc.getPic1()+""));
					}
					if((pdc.getPic2()+"").length()>0){
						pics.add(Tool.toRealUrl(pdc.getPic2()+""));
					}
					if((pdc.getPic3()+"").length()>0){
						pics.add(Tool.toRealUrl(pdc.getPic3()+""));
					}
					p.setBasicInfo(pc);
					p.setCategorys(categorys);
					p.setContent(pdc.getContent());
					p.setPics(pics);
					p.setProperties(propertys);
					rs.setOk(true);
					rs.setResult(p);
				} catch (Exception e) {
					e.printStackTrace();
					rs.setOk(true);
					rs.setResult("参数id格式不正确");
				}
				
			}else{
				rs.setOk(false);
				rs.setResult("缺少参数id");
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edit-good");
		if(rs.isOk()){
			String token = TokenProccessor.getInstance().makeToken();//创建令牌
			request.getSession().setAttribute("token", token);
			modelAndView.addObject("token", token);
		}
		modelAndView.addObject("result", rs);
		return modelAndView;
	}
	
	/**
	 * 产品更新
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/product/update",method = RequestMethod.POST)
	public @ResponseBody MResult update(HttpServletRequest request,ProductVo product,String token) throws Exception {
		MResult result = new MResult(true,"");
		//1判断用户是否是重复提交
		boolean b = isRepeatSubmit(request,token);
        if(b==true){
        	//重复提交处理
        	result.setOk(false);
        	result.setResult("请不要重复提交");
        	return result;
        }
        
		/*
		 * 2.检查值是否为空
		 */
		if(product == null || product.getProductCustom() == null){
			result.setOk(false);
			result.setResult("缺少参数");
		}
		if(product.getProductCustom().getName()==null||product.getProductCustom().getName().trim().equals("")){
			result.setOk(false);
			result.setResult("Lack of parameter name!");
		}else if(product.getProductCustom().getCid()==null||product.getProductCustom().getCid().trim().equals("")){
			result.setOk(false);
			result.setResult("Lack of parameter cid!");
		}else if(product.getProductCustom().getStores()==null){
			result.setOk(false);
			result.setResult("Lack of parameter stores!");
		}else if(product.getProductCustom().getMinPrice()==null){
			result.setOk(false);
			result.setResult("Lack of parameter minPrice!");
		}else if(product.getProductCustom().getOriginalPrice()==null){
			result.setOk(false);
			result.setResult("Lack of parameter originalPrice!");
		}else if(product.getProductCustom().getCommission()==null){
			result.setOk(false);
			result.setResult("Lack of parameter commission!");
		}else if(product.getProductCustom().getOrderBy()==null){
			result.setOk(false);
			result.setResult("Lack of parameter orderBy!");
		}
		if(!result.isOk()){
			return result;
		}
		
		try {
			
			/*
			 * 3.是否展示图变更
			 */
			String tmpUrl = product.getProductCustom().getPic();
			if(tmpUrl!=null&&tmpUrl.trim().length()>0){
				//展示图变更
				String old = product.getOldPic();
				replaceFile(tmpUrl, old);
			}
			/*
			 * 4.三个轮播图有变更
			 */
			ProductDetailCustom pd = new ProductDetailCustom();
			pd.setPic1(null);
			pd.setPic2(null);
			pd.setPic3(null);
			
			tmpUrl = product.getPic1();
			if(tmpUrl!=null&&tmpUrl.trim().length()>0){
				//三个轮播图有变更
				String old = product.getOldPic1();
				if(old!=null&&old.length()>0&&old.equals("null")){
					MResultT<SaveProduct> r = MoveFileFromTemp(tmpUrl,false);
					if(r.isOk()){
						pd.setPic1(r.getResult().getUrl());
					}
				}else{
					replaceFile(tmpUrl, old);
				}
			}
			tmpUrl = product.getPic2();
			if(tmpUrl!=null&&tmpUrl.trim().length()>0){
				//三个轮播图有变更
				String old = product.getOldPic2();
				if(old!=null&&old.length()>0&&old.equals("null")){
					MResultT<SaveProduct> r = MoveFileFromTemp(tmpUrl,false);
					if(r.isOk()){
						pd.setPic2(r.getResult().getUrl());
					}
				}else{
					replaceFile(tmpUrl, old);
				}
			}
			tmpUrl = product.getPic3();
			if(tmpUrl!=null&&tmpUrl.trim().length()>0){
				//三个轮播图有变更
				String old = product.getOldPic3();
				if(old!=null&&old.length()>0&&old.equals("null")){
					MResultT<SaveProduct> r = MoveFileFromTemp(tmpUrl,false);
					if(r.isOk()){
						pd.setPic3(r.getResult().getUrl());
					}
				}else{
					replaceFile(tmpUrl, old);
				}
			}
			
			/*
			 * 5.数据保存
			 */

			//设置产品商品展示图片地址
			product.getProductCustom().setPic(null);
			product.getProductCustom().setPublished(Configuration.CaoGao);
			product.getProductCustom().setDateUpdate(Tool.getyyyyMMddHHmmss());
			/**
			 * 4.保存商品信息
			 */
			
			String content = product.getContent()+"";
			
			pd.setId(product.getProductCustom().getId());
			pd.setContent(content);

			ProductDetailVo productDetailVo = new ProductDetailVo();
			productDetailVo.setProductDetailCustom(pd);
			
			productService.update(product);
			productDetailService.update(productDetailVo);
			
			/*
			 * 5.清除token
			 */
			request.getSession().removeAttribute("token");
			
			result.setOk(true);
			result.setResult("提交成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setResult(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping(value="/product/listPropertyAndCategory")
	public @ResponseBody MResult listProductCategoryAndProperty(HttpServletRequest request) throws Exception {
		MResult rs = new MResult();
		try {
			List<ProductCategoryCustom> list1 = productCategoryService.getAllList(null);
			List<PropertyCustom> list2 = propertyService.getAllList(null);
			List<Object> list = new ArrayList<Object>();
			if(list1.size()==0){
				rs.setOk(false);
				rs.setResult("请先添加产品或商品分类");
			}else if(list2.size()==0){
				rs.setOk(false);
				rs.setResult("请先添加产品或商品属性");
			}else{
				list.add(list1);
				list.add(list2);
				
				rs.setOk(true);
				rs.setResult(list);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		return rs;
	}
	
	/**
	 * 将图片从临时目录移动 创建略缩图
	 * @param tmpUrl
	 * @param context
	 * @param request
	 * @param createThunb
	 * @return
	 * @throws IOException
	 */
	private MResultT<SaveProduct> MoveFileFromTemp(String tmpUrl,boolean createThunb) throws IOException{
		MResultT<SaveProduct> r = new MResultT<SaveProduct>();
		SaveProduct result = new SaveProduct();
		
		if(tmpUrl == null || tmpUrl.trim().length() == 0){
			r.setOk(false);
			result.setMsg("未找到图片"+tmpUrl);
			r.setResult(result);
			return r;
		}
		
		tmpUrl = Tool.toRealPath(tmpUrl);
		String basePath = Configuration.BaseImgDir;
		String savePath = Configuration.PRODUCT_PATH;
		
		//产品文件夹
		File dir = new File(basePath,savePath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
        //图片文件定位
    	File img = new File(basePath,tmpUrl);
    	//target
		File newFile = new File(dir,img.getName());
		
		//移动文件
		MResult res = Tool.mvFile(img, newFile, null);
		if(!res.isOk()){
			r.setOk(false);
			r.setResult(result);
			return r;
		}
		
		String url = "/"+savePath+"/"+img.getName().replaceAll("//", "/").trim();
		result.setUrl(url);
		
		if(createThunb){
			//创建略缩图
			Tool.createSmallPhoto(newFile, new File(dir,"_"+img.getName()));
			String thumb = "/"+savePath+"/_"+img.getName().replaceAll("//", "/").trim();
			result.setThumb(thumb);
		}
		
		r.setResult(result);
		r.setOk(true);
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
	private void replaceFile(String tmpUrl, String old) {
		tmpUrl = Tool.toRealPath(tmpUrl);
		//图片文件定位
		String basePath = Configuration.BaseImgDir;
		String savePath = Configuration.PRODUCT_PATH;
		File saveDir = new File(basePath,savePath);
		if(!saveDir.exists()){
			saveDir.mkdirs();
		}
		File img = new File(basePath,tmpUrl);
		if(old!=null&&old.length()>0&&!old.equals("null")){
			String targetUrl = Tool.toRealPath(old);
			File target = new File(basePath,targetUrl);
			
			if(target.exists()){
				target.delete();
			}
			
			/*移动图片和重新创建略缩图*/
			Tool.mvFile(img, target, null);
		}else{
			throw new RuntimeException("The old pic url is not exist!");
		}
	}
	
	/**
	 * 删除产品商品时删除对应的残余的文件
	 * @param context
	 * @param path
	 * @param urls
	 */
	private void deleteFile(List<String> urls) {
		if(urls == null || urls.size()==0)return;
		
		String basePath = Configuration.BaseImgDir;
		
		for (String tmpUrl : urls) {
			if(tmpUrl!=null && tmpUrl.trim().length()>0){
				//图片文件定位
				tmpUrl = Tool.toRealPath(tmpUrl);
				File img = new File(basePath,tmpUrl);
				if(img.exists()){
					img.delete();
				}
			}
		}
		
	}
	
}
