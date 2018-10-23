package com.mall.ssm.controller;

import java.io.File;
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
import com.mall.ssm.po.custom.SwiperCustom;
import com.mall.ssm.po.dto.MResult;
import com.mall.ssm.po.dto.xcx.R;
import com.mall.ssm.po.vo.SwiperVo;
import com.mall.ssm.service.swiper.SwiperService;
import com.mall.ssm.util.Configuration;
import com.mall.ssm.util.TokenProccessor;
import com.mall.ssm.util.Tool;

@Controller
public class SwiperController {
	
	@Autowired
	private SwiperService swiperService;	
	
	/**
	 * 小程序调用
	 * @param swiper
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/banner/list",method = RequestMethod.POST)
	public @ResponseBody R<List<SwiperCustom>> bannerList(SwiperVo swiper) throws Exception {
		R<List<SwiperCustom>> r = new R<List<SwiperCustom>>();
		try {
			if(swiper.getSwiperCustom() == null){
				SwiperCustom sc = new SwiperCustom();
				sc.setPublished(Configuration.ShangXian);
				swiper.setSwiperCustom(sc);
			}
			List<SwiperCustom> data = swiperService.getAllList(swiper);
			/*
			 * 补全图片url
			 */
			for (SwiperCustom s : data) {
				String thumb = s.getThumb();
				String url = s.getUrl();
				url = Tool.toRealUrl(url);
				thumb = Tool.toRealUrl(thumb);
				s.setUrl(url);
				s.setThumb(thumb);
			}
			
			if(data.size() == 0){
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
	 * 保存轮播图
	 * @param request
	 * @param swiper
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/swiper/save", method = RequestMethod.POST)
	public @ResponseBody MResult addSwiper(HttpServletRequest request,SwiperVo swiper,String token)
		throws Exception {
		MResult result = new MResult();
		/*
		 * 1.检查值是否为空
		 */
		if(token == null || token.trim().length() == 0 || swiper == null || swiper.getSwiperCustom()==null|| (swiper.getSwiperCustom().getUrl()+"").trim().length()==0){
			result.setOk(false);
			result.setResult("缺少参数token或者fileName");
			return result;
		}
		try {
			/*
			 * 2.将图片从临时文件夹移动到指定目录
			 */
			String tmpUrl = Tool.toRealPath(swiper.getSwiperCustom().getUrl());
			String basePath = Configuration.BaseImgDir;
			
            //图片文件定位
        	File img = new File(basePath,tmpUrl);
			String savePath = Configuration.SWIPER_PATH;
			File dir = new File(basePath,savePath);
			File newFile = new File(dir,img.getName());
			
			//移动文件
			result = Tool.mvFile(img, newFile, null);
			//创建略缩图
			Tool.createSmallPhoto(newFile, new File(dir,"_"+img.getName()));
			
			if(result.isOk()){
				/*
				 * 3.数据库保存
				 */
				String url = "/"+savePath+"/"+img.getName();
				String thumb = "/"+savePath+"/_"+img.getName();
				String content = swiper.getSwiperCustom().getContent()+"";
				
				swiper.getSwiperCustom().setId(0);
				swiper.getSwiperCustom().setUid(null);
				swiper.getSwiperCustom().setUrl(url);
				swiper.getSwiperCustom().setThumb(thumb);
				swiper.getSwiperCustom().setContent(content);
				swiper.getSwiperCustom().setUpdatetime(Tool.getyyyyMMddHHmmss());
				swiper.getSwiperCustom().setPublished(Configuration.CaoGao);
				swiper.getSwiperCustom().setType(content.length()>0?1:0);
				
				swiperService.save(swiper);
				result.setOk(true);
				result.setResult("提交成功");
			}else{
				if(img.exists()){
					img.delete();
				}
				if(newFile.exists()){
					newFile.delete();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setResult(e.getMessage());
		}
		return result;
	}
	/**
	 * 查找用户发放的轮播图
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/swiper/listPage",method = RequestMethod.POST)
	public @ResponseBody MResult listSwipers(SwiperVo swiper) throws Exception {
		MResult rs = new MResult();
		List<SwiperCustom> scs;
		try {
			if(swiper.getSwiperCustom().getPage()<1){
				swiper.getSwiperCustom().setPage(1);
			}
			if(swiper.getSwiperCustom().getLimit()<1){
				swiper.getSwiperCustom().setLimit(10);
			}
			scs = swiperService.getListByPage(swiper);
			long count = swiperService.getCount(swiper);
			
			Map<Object, Object> r = new HashMap<Object, Object>();
			
			/*
			 * 补全图片url
			 */
			for (SwiperCustom s : scs) {
				String thumb = s.getThumb();
				String url = s.getUrl();
				url = Tool.toRealUrl(url);
				thumb = Tool.toRealUrl(thumb);
				s.setUrl(url);
				s.setThumb(thumb);
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
	 * 轮播图发布
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/swiper/fabu",method = RequestMethod.POST)
	public @ResponseBody MResult fabu(SwiperVo swiper) throws Exception {
		MResult rs = new MResult();
		try {
			if(swiper.getSwiperCustom().getId()>0){
				swiper.getSwiperCustom().setUpdatetime(Tool.getyyyyMMddHHmmss());
				swiper.getSwiperCustom().setPublished(Configuration.ShangXian);
				swiper.getSwiperCustom().setType(null);
				swiper.getSwiperCustom().setUid(null);
				swiperService.update(swiper);
				
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
	 * 轮播图下架
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/swiper/xiajia",method = RequestMethod.POST)
	public @ResponseBody MResult xiajia(SwiperVo swiper) throws Exception {
		MResult rs = new MResult();
		try {
			if(swiper.getSwiperCustom().getId()>0){
				swiper.getSwiperCustom().setUpdatetime(Tool.getyyyyMMddHHmmss());
				swiper.getSwiperCustom().setPublished(Configuration.XiaJia);
				swiper.getSwiperCustom().setType(null);
				swiper.getSwiperCustom().setUid(null);

				swiperService.update(swiper);
				
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
	 * 删除图片
	 */
	@RequestMapping("/swiper/delete")
	public @ResponseBody MResult deleteSwiper(HttpServletRequest request,SwiperVo swiper)throws Exception{
		MResult rs = new MResult();
		
		try {
			if(swiper.getSwiperCustom()!=null&&swiper.getSwiperCustom().getId()!=0){
//				/*
//				 * 1.把图片文件删除
//				 */
//				try {
//					SwiperCustom sc = swiperService.get(swiper);
//					List<String> urls = new ArrayList<String>();
//					urls.add(sc.getUrl());
//					urls.add(sc.getThumb());
//					
//					String path = request.getSession().getServletContext().getRealPath("/");
//					String context = request.getContextPath();
//					deleteFile(context, path, urls);
//				} catch (RuntimeException e) {
//					e.printStackTrace();
//				}
				
				/*
				 * 2.从数据库中删除
				 */
				swiperService.delete(swiper.getSwiperCustom().getId());
				
				rs.setOk(true);
				rs.setResult("ok");
				
				/*
				 * 从物理磁盘中删除
				 */
				String url = swiper.getSwiperCustom().getUrl();
				if(rs.isOk()){
					String path = Configuration.BaseImgDir;
		            //创建路径,图片文件定位
					url = Tool.toRealPath(url);
					if(url != null){
						File img = new File(path,url);
						if(img.exists()){
							if(!img.delete()){
								Thread.sleep(1000);
								img.delete();
							}
						}
						
						//图片略缩图定位
						
						if(img != null){
							File _img = new File(img.getParent(),"_"+img.getName());
							if(_img.exists()){
								if(!_img.delete()){
									Thread.sleep(1000);
									_img.delete();
								}
							}
						}
					}
		        	
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
		return rs;
	}
	
	/**
	 * 查询轮播图信息
	 * @param swiper
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/swiper/select")
	public ModelAndView find(HttpServletRequest request,SwiperVo swiper) throws Exception {
		MResult rs = new MResult();
		try {
			if(swiper!=null && swiper.getSwiperCustom()!=null&&swiper.getSwiperCustom().getId()>0){
				try {
					SwiperCustom s = swiperService.get(swiper);
					if(s==null){
						rs.setOk(false);
						rs.setResult("查询结果为空");
					}else{
						rs.setOk(true);
						/*
						 * 补全URL地址
						 */
						String thumb = s.getThumb();
						String url = s.getUrl();
						url = Tool.toRealUrl(url);
						thumb = Tool.toRealUrl(thumb);
						s.setUrl(url);
						s.setThumb(thumb);
						
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
		if(rs.isOk()){
			String token = TokenProccessor.getInstance().makeToken();//创建令牌
			System.out.println("生成的token："+token);
			request.getSession().setAttribute("token", token);
			modelAndView.addObject("token", token);
		}
		modelAndView.addObject("result", rs);
		modelAndView.setViewName("edit-swiper");
		return modelAndView;
	}
	
	/**
	 * 轮播图更新
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/swiper/update",method = RequestMethod.POST)
	public @ResponseBody MResult update(HttpServletRequest request,SwiperVo swiper,String token,boolean modified) throws Exception {
		MResult rs = new MResult();
		/*
		 * 1.检查值是否为空
		 */
		if(token == null || token.trim().length() == 0 || swiper == null || swiper.getSwiperCustom() == null || swiper.getSwiperCustom().getId() == 0){
			rs.setOk(false);
			rs.setResult("缺少参数token或者id");
			return rs;
		}
		try {
			//新图片路径
			if(swiper.getFileName()!=null && swiper.getFileName().length()>0){
				/*
				 * 2.将图片从临时文件夹移动到指定目录
				 */
				String tmpUrl = Tool.toRealPath(swiper.getFileName());
				String basePath = Configuration.BaseImgDir;
	            //图片文件定位
	        	File img = new File(basePath,tmpUrl);
	        	
	        	if(swiper.getSwiperCustom().getUrl() == null || swiper.getSwiperCustom().getUrl().length()==0){
	        		rs.setOk(false);
	    			rs.setResult("缺少参数url");
	    			return rs;
	        	}
	        	String targetUrl = swiper.getSwiperCustom().getUrl();
	        	targetUrl = Tool.toRealPath(targetUrl);
				File target = new File(basePath,targetUrl);
				
				/*
				 * 先将要被替换的图片删除,消除物理垃圾
				 */
				if(target.exists()){
					target.delete();
				}
				
				/*移动图片和重新创建略缩图*/
				Tool.mvFile(img, target, null);
				String fn = target.getName();
				File thumb = new File(target.getParent(),"_"+fn);
				if(thumb.exists()){
					thumb.delete();
				}
				Tool.createSmallPhoto(target, thumb);
			}
			
			
			/*
			 * 3.数据库数据更新
			 */
			if(modified){
				swiper.getSwiperCustom().setUid(null);
				swiper.getSwiperCustom().setUrl(null);
				swiper.getSwiperCustom().setThumb(null);
				swiper.getSwiperCustom().setContent(swiper.getSwiperCustom().getContent()+"");
				swiper.getSwiperCustom().setUpdatetime(Tool.getyyyyMMddHHmmss());
				swiper.getSwiperCustom().setPublished(Configuration.CaoGao);
				swiper.getSwiperCustom().setType(swiper.getSwiperCustom().getContent().length()>0?1:0);

				swiperService.update(swiper);
			}
			
			rs.setOk(true);
			rs.setResult("更新成功");
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		
		return rs;
	}
	
	
	
	
	
//	/**
//	 * 删除产品商品时删除对应的残余的文件
//	 * @param context
//	 * @param path
//	 * @param urls
//	 */
//	private void deleteFile(String context,String path , List<String> urls) {
//		if(urls.size()==0)return;
//		for (String tmpUrl : urls) {
//			if(tmpUrl!=null && tmpUrl.trim().length()>0){
//				int index = tmpUrl.indexOf(context);
//				if(index>0){
//					tmpUrl = tmpUrl.substring(index+context.length());
//				}
//				
//				//图片文件定位
//				File img = new File(path,tmpUrl);
//				if(img.exists()){
//					img.delete();
//				}
//			}
//		}
//		
//	}
	
	
	
	
	
	
	
	
	
	
}
