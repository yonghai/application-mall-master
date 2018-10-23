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

import com.mall.ssm.po.custom.PropertyCustom;
import com.mall.ssm.po.custom.PropertyItemCustom;
import com.mall.ssm.po.dto.MResult;
import com.mall.ssm.po.vo.PropertyItemVo;
import com.mall.ssm.po.vo.PropertyVo;
import com.mall.ssm.service.property.PropertyItemService;
import com.mall.ssm.service.property.PropertyService;
import com.mall.ssm.util.Tool;

@Controller
public class PropertyController {
	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private PropertyItemService propertyItemService;	
	
	/**
	 * 保存属性
	 * @param request
	 * @param property
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/property/save", method = RequestMethod.POST)
	public @ResponseBody MResult addProperty(HttpServletRequest request,PropertyVo property,String options,String token)
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
		if(token == null || token.trim().length() == 0 || property == null || property.getPropertyCustom() == null ||(property.getPropertyCustom().getName()+"").length()==0||(options+"").length()==0 ){
			result.setOk(false);
			result.setResult("缺少参数token或者options或者name");
			return result;
		}
		try {
			/*
			 * 3.数据库保存
			 */
			property.getPropertyCustom().setId(0);
			property.getPropertyCustom().setUid(null);
			property.getPropertyCustom().setUpdateTime(Tool.getyyyyMMddHHmmss());
			if(property.getPropertyCustom().getOrderBy()==null){
				property.getPropertyCustom().setOrderBy(10);
			}
			
			propertyService.save(property);
			
			property.getPropertyCustom().setId(null);
			int id = propertyService.get(property).getId();
			
			List<PropertyItemVo> list = new ArrayList<PropertyItemVo>();
			
			for(String opt : options.split(",")){
				PropertyItemVo item = new PropertyItemVo();
				PropertyItemCustom c = new PropertyItemCustom();
				c.setId(0);
				c.setName(opt);
				c.setOrderBy(10);
				c.setUpdateTime(Tool.getyyyyMMddHHmmss());
				c.setPropertyId(id);
				item.setPropertyItemCustom(c);
				list.add(item);
			}
			propertyItemService.inserBatch(list);
			
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
	 * 查找用户发放的属性
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/property/listPage",method = RequestMethod.POST)
	public @ResponseBody MResult listPropertys(PropertyVo property) throws Exception {
		MResult rs = new MResult();
		List<PropertyCustom> scs;
		try {
			if(property.getPropertyCustom().getPage()<1){
				property.getPropertyCustom().setPage(1);
			}
			if(property.getPropertyCustom().getLimit()<1){
				property.getPropertyCustom().setLimit(10);
			}
			scs = propertyService.getListByPage(property);
			long count = propertyService.getCount(property);
			
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
	 * 查找所有属性
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/property/listAll",method = RequestMethod.POST)
	public @ResponseBody MResult listPropdertys(PropertyVo property) throws Exception {
		MResult rs = new MResult();
		List<PropertyCustom> scs;
		try {
			if(property.getPropertyCustom().getPage()<1){
				property.getPropertyCustom().setPage(1);
			}
			if(property.getPropertyCustom().getLimit()<1){
				property.getPropertyCustom().setLimit(10);
			}
			scs = propertyService.getAllList(null);
			
			rs.setOk(true);
			rs.setResult(scs);
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		
		return rs;
	}
	
	/**
	 * 删除属性
	 */
	@RequestMapping("/property/delete")
	public @ResponseBody MResult deleteProperty(HttpServletRequest request,PropertyVo property)throws Exception{
		MResult rs = new MResult();
		
		try {
			if(property.getPropertyCustom()!=null&&property.getPropertyCustom().getId()!=0){
				/*
				 * 从数据库中删除
				 */
				propertyItemService.deleteByPropertyId(property.getPropertyCustom().getId());
				propertyService.delete(property.getPropertyCustom().getId());
				
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
	 * 查询属性信息
	 * @param property
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/property/select")
	public ModelAndView findProperty(HttpServletRequest request,PropertyVo property) throws Exception {
		MResult rs = new MResult();
		try {
			if(property!=null && property.getPropertyCustom()!=null&&property.getPropertyCustom().getId()>0){
				try {
					PropertyCustom s = propertyService.getFull(property);
					if(s==null){
						rs.setOk(false);
						rs.setResult("查询结果为空");
					}else{
						rs.setOk(true);
						rs.setResult(s);
					}
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
		modelAndView.setViewName("view-property");
		modelAndView.addObject("result", rs);
		return modelAndView;
	}
	
//	/**
//	 * 属性更新
//	 * @param req
//	 * @param resp
//	 * @return
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@RequestMapping(value="/notice/update",method = RequestMethod.POST)
//	public @ResponseBody MResult update(HttpServletRequest request,PropertyVo notice,String token) throws Exception {
//		MResult rs = new MResult();
//		/*
//		 * 1.检查值是否为空
//		 */
//		if(token == null || token.trim().length() == 0 || notice == null || notice.getPropertyCustom() == null || notice.getPropertyCustom().getId() == 0|| notice.getPropertyCustom().getTitle().trim().length() == 0){
//			rs.setOk(false);
//			rs.setResult("缺少参数token或者id或者title");
//			return rs;
//		}
//		try {
//			/*
//			 * 数据库数据更新
//			 */
//			notice.getPropertyCustom().setUid(null);
//			notice.getPropertyCustom().setContent(notice.getPropertyCustom().getContent()+"");
//			notice.getPropertyCustom().setUpdatetime(Tool.getyyyyMMddHHmmss());
//			notice.getPropertyCustom().setPublished(Configuration.CaoGao);
//			notice.getPropertyCustom().setType(notice.getPropertyCustom().getContent().length()>0?1:0);
//
//			propertyService.update(notice);
//			
//			rs.setOk(true);
//			rs.setResult("更新成功");
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//			rs.setOk(false);
//			rs.setResult(e.getMessage());
//		}
//		
//		return rs;
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
	
	
	
	
	
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
