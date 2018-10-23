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
import com.mall.ssm.po.custom.NoticeCustom;
import com.mall.ssm.po.custom.ProductCustom;
import com.mall.ssm.po.dto.MResult;
import com.mall.ssm.po.dto.xcx.R;
import com.mall.ssm.po.vo.NoticeVo;
import com.mall.ssm.po.vo.ProductVo;
import com.mall.ssm.service.notice.NoticeService;
import com.mall.ssm.util.Configuration;
import com.mall.ssm.util.TokenProccessor;
import com.mall.ssm.util.Tool;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;	
	
	/**
	 * 小程序调用
	 * @param swiper
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/list",method = RequestMethod.POST)
	public @ResponseBody R<List<NoticeCustom>> noticesList(NoticeVo product) throws Exception {
		R<List<NoticeCustom>> r = new R<List<NoticeCustom>>();
		try {
			if(product.getNoticeCustom()==null){
				NoticeCustom nc = new NoticeCustom();
				product.setNoticeCustom(nc);
			}
			product.getNoticeCustom().setPublished(Configuration.ShangXian);
			List<NoticeCustom> data = noticeService.getAllList(product);
			if(data.isEmpty()){
				r.setCode(Code.NOTFOUND);
			}else{
				r.setCode(Code.OK);
			}
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
	 * 详情
	 * @param request
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/detail",method = RequestMethod.POST)
	public @ResponseBody R<NoticeCustom> detailNotice(HttpServletRequest request,NoticeVo notice) throws Exception {
		R<NoticeCustom> rs = new R<NoticeCustom>();
		try {
			if(notice!=null && notice.getNoticeCustom()!=null&&notice.getNoticeCustom().getId()>0){
				NoticeCustom s = noticeService.get(notice);
				if(s==null){
					rs.setCode(Code.NOTFOUND);
					rs.setMsg("empty result");
				}else{
					rs.setCode(Code.OK);
					rs.setMsg("success");
					rs.setData(s);
				}
			}else{
				rs.setCode(Code.ERROR);
				rs.setMsg("缺少参数id");
			}
		} catch (Exception e) {
			rs.setCode(Code.ERROR);
			rs.setMsg(e.getMessage());
		}
		return rs;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 保存公告
	 * @param request
	 * @param notice
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/save", method = RequestMethod.POST)
	public @ResponseBody MResult addNotice(HttpServletRequest request,NoticeVo notice,String token)
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
		if(token == null || token.trim().length() == 0 || notice == null || notice.getNoticeCustom() == null ||notice.getNoticeCustom().getTitle()==null||notice.getNoticeCustom().getTitle().trim().length()==0 ){
			result.setOk(false);
			result.setResult("缺少参数token或者title");
			return result;
		}
		try {
			/*
			 * 3.数据库保存
			 */
			notice.getNoticeCustom().setId(0);
			notice.getNoticeCustom().setUid(null);
			notice.getNoticeCustom().setUpdatetime(Tool.getyyyyMMddHHmmss());
			notice.getNoticeCustom().setPublished(Configuration.CaoGao);
			notice.getNoticeCustom().setType(notice.getNoticeCustom().getContent().length()>0?1:0);
			
			noticeService.save(notice);
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
	 * 查找用户发放的公告
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/notice/listPage",method = RequestMethod.POST)
	public @ResponseBody MResult listNotices(NoticeVo notice) throws Exception {
		MResult rs = new MResult();
		List<NoticeCustom> scs;
		try {
			if(notice.getNoticeCustom().getPage()<1){
				notice.getNoticeCustom().setPage(1);
			}
			if(notice.getNoticeCustom().getLimit()<1){
				notice.getNoticeCustom().setLimit(10);
			}
			scs = noticeService.getListByPage(notice);
			long count = noticeService.getCount(notice);
			
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
	 * 公告发布
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/notice/fabu",method = RequestMethod.POST)
	public @ResponseBody MResult fabu(NoticeVo notice) throws Exception {
		MResult rs = new MResult();
		try {
			if(notice.getNoticeCustom().getId()>0){
				notice.getNoticeCustom().setUpdatetime(Tool.getyyyyMMddHHmmss());
				notice.getNoticeCustom().setPublished(Configuration.ShangXian);
				notice.getNoticeCustom().setType(null);
				notice.getNoticeCustom().setUid(null);
				noticeService.update(notice);
				
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
	 * 公告下架
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/notice/xiajia",method = RequestMethod.POST)
	public @ResponseBody MResult xiajia(NoticeVo notice) throws Exception {
		MResult rs = new MResult();
		try {
			if(notice.getNoticeCustom().getId()>0){
				notice.getNoticeCustom().setUpdatetime(Tool.getyyyyMMddHHmmss());
				notice.getNoticeCustom().setPublished(Configuration.XiaJia);
				notice.getNoticeCustom().setType(null);
				notice.getNoticeCustom().setUid(null);

				noticeService.update(notice);
				
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
	 * 删除公告
	 */
	@RequestMapping("/notice/delete")
	public @ResponseBody MResult deleteNotice(HttpServletRequest request,NoticeVo notice)throws Exception{
		MResult rs = new MResult();
		
		try {
			if(notice.getNoticeCustom()!=null&&notice.getNoticeCustom().getId()!=0){
				
				/*
				 * 从数据库中删除
				 */
				noticeService.delete(notice.getNoticeCustom().getId());
				
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
	 * 查询公告信息
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/select")
	public ModelAndView findNotice(HttpServletRequest request,NoticeVo notice) throws Exception {
		MResult rs = new MResult();
		try {
			if(notice!=null && notice.getNoticeCustom()!=null&&notice.getNoticeCustom().getId()>0){
				try {
					NoticeCustom s = noticeService.get(notice);
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
		modelAndView.setViewName("edit-notice");
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
	 * 公告更新
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/notice/update",method = RequestMethod.POST)
	public @ResponseBody MResult update(HttpServletRequest request,NoticeVo notice,String token) throws Exception {
		MResult rs = new MResult();
		/*
		 * 1.检查值是否为空
		 */
		if(token == null || token.trim().length() == 0 || notice == null || notice.getNoticeCustom() == null || notice.getNoticeCustom().getId() == 0|| notice.getNoticeCustom().getTitle().trim().length() == 0){
			rs.setOk(false);
			rs.setResult("缺少参数token或者id或者title");
			return rs;
		}
		try {
			/*
			 * 数据库数据更新
			 */
			notice.getNoticeCustom().setUid(null);
			notice.getNoticeCustom().setContent(notice.getNoticeCustom().getContent()+"");
			notice.getNoticeCustom().setUpdatetime(Tool.getyyyyMMddHHmmss());
			notice.getNoticeCustom().setPublished(Configuration.CaoGao);
			notice.getNoticeCustom().setType(notice.getNoticeCustom().getContent().length()>0?1:0);

			noticeService.update(notice);
			
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
