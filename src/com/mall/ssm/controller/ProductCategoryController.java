package com.mall.ssm.controller;

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
import com.mall.ssm.po.custom.SwiperCustom;
import com.mall.ssm.po.dto.MResult;
import com.mall.ssm.po.dto.xcx.R;
import com.mall.ssm.po.vo.ProductCategoryVo;
import com.mall.ssm.po.vo.SwiperVo;
import com.mall.ssm.service.productcategory.ProductCategoryService;
import com.mall.ssm.util.Configuration;
import com.mall.ssm.util.TokenProccessor;
import com.mall.ssm.util.Tool;

@Controller
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService productCategoryService;	
	
	
	/**
	 * 小程序调用
	 * @param swiper
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/shop/goods/category/all",method = RequestMethod.POST)
	public @ResponseBody R<List<ProductCategoryCustom>> categoryList(ProductCategoryVo category ) throws Exception {
		R<List<ProductCategoryCustom>> r = new R<List<ProductCategoryCustom>>();
		try {
			List<ProductCategoryCustom> data = productCategoryService.getAllList(category);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 保存产品分类
	 * @param request
	 * @param category
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/product/category/save", method = RequestMethod.POST)
	public @ResponseBody MResult addProductCategory(HttpServletRequest request,ProductCategoryVo category,String token)
		throws Exception {
		MResult result = new MResult();
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
		if(token == null || token.trim().length() == 0 || category == null || category.getProductCategoryCustom() == null ||(category.getProductCategoryCustom().getName()+"").trim().length()== 0 ){
			result.setOk(false);
			result.setResult("缺少参数name");
			return result;
		}
		try {
			/*
			 * 3.数据库保存
			 */
			if(category.getProductCategoryCustom().getOrderBy() == null){
				category.getProductCategoryCustom().setOrderBy(10);
			}
			category.getProductCategoryCustom().setId(0);
			category.getProductCategoryCustom().setUid(null);
			
			productCategoryService.save(category);
			result.setOk(true);
			result.setResult("提交成功");
			
			/*
			 * 3.清除token
			 */
			request.getSession().removeAttribute("token");
		} catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setResult(e.getMessage());
		}
		return result;
	}
	/**
	 * 查找用户发放的产品分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/product/category/listPage",method = RequestMethod.POST)
	public @ResponseBody MResult listProductCategorys(ProductCategoryVo category) throws Exception {
		MResult rs = new MResult();
		List<ProductCategoryCustom> scs;
		try {
			if(category.getProductCategoryCustom().getPage()<1){
				category.getProductCategoryCustom().setPage(1);
			}
			if(category.getProductCategoryCustom().getLimit()<1){
				category.getProductCategoryCustom().setLimit(10);
			}
			scs = productCategoryService.getListByPage(category);
			long count = productCategoryService.getCount(category);
			
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
	
	
	/**
	 * 删除产品分类
	 */
	@RequestMapping("/product/category/delete")
	public @ResponseBody MResult deleteProductCategory(HttpServletRequest request,ProductCategoryVo category)throws Exception{
		MResult rs = new MResult();
		
		try {
			if(category.getProductCategoryCustom()!=null&&category.getProductCategoryCustom().getId()!=0){
				
				/*
				 * 从数据库中删除
				 */
				productCategoryService.delete(category.getProductCategoryCustom().getId());
				
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
	 * 查询产品分类信息
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/product/category/select")
	public ModelAndView findProductCategory(HttpServletRequest request,ProductCategoryVo category) throws Exception {
		MResult rs = new MResult();
		try {
			if(category!=null && category.getProductCategoryCustom()!=null&&category.getProductCategoryCustom().getId()>0){
				try {
					ProductCategoryCustom s = productCategoryService.get(category);
					if(s==null){
						rs.setOk(false);
						rs.setResult("查询结果为空");
					}else{
						rs.setOk(true);
						rs.setResult(s);
					}
				} catch (Exception e) {
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
		modelAndView.setViewName("edit-product-category");
		if(rs.isOk()){
			String token = TokenProccessor.getInstance().makeToken();//创建令牌
			System.out.println("生成的token："+token);
			request.getSession().setAttribute("token", token);
			modelAndView.addObject("token", token);
		}
		modelAndView.addObject("result", rs);
		return modelAndView;
	}
	
	/**
	 * 产品分类更新
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/product/category/update",method = RequestMethod.POST)
	public @ResponseBody MResult update(HttpServletRequest request,ProductCategoryVo category,String token) throws Exception {
		MResult rs = new MResult();
		/*
		 * 1.检查值是否为空
		 */
		if(token == null || token.trim().length() == 0 || category == null || category.getProductCategoryCustom() == null || category.getProductCategoryCustom().getId() == 0|| (category.getProductCategoryCustom().getName()+"").trim().length() == 0){
			rs.setOk(false);
			rs.setResult("缺少参数token或者id或者name");
			return rs;
		}
		try {
			/*
			 * 数据库数据更新
			 */
			if(category.getProductCategoryCustom().getOrderBy() == null){
				category.getProductCategoryCustom().setOrderBy(10);
			}
			category.getProductCategoryCustom().setUid(null);

			productCategoryService.update(category);
			
			rs.setOk(true);
			rs.setResult("更新成功");
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		
		return rs;
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
