package com.mall.ssm.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.ssm.po.Photo;
import com.mall.ssm.po.custom.PhotoCustom;
import com.mall.ssm.po.custom.UserCustom;
import com.mall.ssm.po.dto.MResult;
import com.mall.ssm.po.dto.UploadMsg;
import com.mall.ssm.po.vo.PhotoVo;
import com.mall.ssm.service.photo.PhotoService;
import com.mall.ssm.util.CommonUtils;
import com.mall.ssm.util.Configuration;
import com.mall.ssm.util.Tool;

@Controller
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	/**
	 * 删除图片
	 */
	@RequestMapping("/photo/delete")
	public @ResponseBody MResult deletePhoto(HttpServletRequest request,PhotoVo photo)throws Exception{
		MResult rs = new MResult();
		
		try {
			if(photo!=null && photo.getPhotoCustom()!=null&& (photo.getPhotoCustom().getPhotoAddress()+"")!=null&& (photo.getPhotoCustom().getPhotoAddress()+"").length()>0){
				
				/*
				 * 从数据库中删除
				 */
				photoService.delete(photo.getPhotoCustom().getId());
				
				rs.setOk(true);
				rs.setResult("ok");
				
				/*
				 * 从物理磁盘中删除
				 */
				String url = photo.getPhotoCustom().getPhotoAddress();
				if(rs.isOk()){
					String basePath = request.getSession().getServletContext().getRealPath("/");
					url = Tool.toRealPath(url);
		            //创建路径,图片文件定位
		        	File img = new File(basePath,url);
					if(img.exists()){
						if(!img.delete()){
							Thread.sleep(1000);
							img.delete();
						}
					}
					//图片略缩图定位
					File _img = new File(img.getParent(),"_"+img.getName());
					if(_img.exists()){
						if(!_img.delete()){
							Thread.sleep(1000);
							_img.delete();
						}
					}
				}
			}else{
				rs.setOk(false);
				rs.setResult("缺少参数id或者photoAddress");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		return rs;
	}
	
	/**
	 * 返回图片结果集
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/photos")
	public ModelAndView photos(PhotoVo photo)throws Exception{
		MResult rs = new MResult();
		List<PhotoCustom> ps;
		try {
			ps = photoService.getAllList(null);
			/*
			 * 补全图片url
			 */
			for (PhotoCustom s : ps) {
				String thumb = s.getSmallPhoto();
				String url = s.getPhotoAddress();
				url = Tool.toRealUrl(url);
				thumb = Tool.toRealUrl(thumb);
				s.setPhotoAddress(url);
				s.setSmallPhoto(thumb);
			}
			rs.setOk(true);
			rs.setResult(ps);
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setOk(false);
			rs.setResult(e.getMessage());
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("result",rs);
		modelAndView.setViewName("photos");
		
		return modelAndView;
	}
	
	
	/**
	 * 上传图片
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/image/upload", method = RequestMethod.POST)
	public @ResponseBody UploadMsg image(MultipartFile file, HttpServletRequest request){
		UploadMsg msg = new UploadMsg();
		//原始名称
		try {
			String originalFilename = file.getOriginalFilename();
			//上传图片
			if(file!=null && originalFilename!=null&&originalFilename.length()>0){
				//存储图片的物理路径
				String basePath = Configuration.BaseImgDir;
				String savePath = Configuration.DEFAULT_PHOTOS_PATH;
				
				if(request.getParameter("filePath")!=null){
					savePath += "/"+request.getParameter("filePath");
				}
				
				//新图片名称
				String newFileName = CommonUtils.uuid()+originalFilename.substring(originalFilename.lastIndexOf("."));
				File dir = new File(basePath,savePath);
				String dirname=Tool.getyyyyMMdd();
				
				//新图片
				File newFileDir = new File(dir,dirname);
				if(!newFileDir.exists()){
					newFileDir.mkdirs();
				}
				
				//网络路径
				String accessPath = "/"+savePath+"/"+dirname+"/";
				accessPath = accessPath.replace("//", "/");
				
				/*生成原始文件*/
				File newFile = new File(newFileDir,newFileName);
				file.transferTo(newFile);
				
				/*生成略缩图*/
				File thumb = new File(newFile.getParent(),"_"+newFileName);
				Tool.createSmallPhoto(newFile, thumb);
				
				String photoType = file.getContentType();
				String photoAddress=accessPath+newFileName;//相片地址
				UserCustom user = (UserCustom) request.getSession().getAttribute("user");
				String username=(user!=null&&user.getName()!=null&&user.getName().length()>0)?user.getName():"未知";//	相片上传用户名
				String smallPhoto=accessPath+"_"+newFileName;//相片缩略图地址
				
				/*保存信息到数据库*/
				Photo p = new Photo(0,newFileName,file.getSize()+"",photoType,Tool.getyyyyMMddHHmmss(),photoAddress,username,smallPhoto);
				PhotoCustom pc = new PhotoCustom();
				CommonUtils.populate(pc, CommonUtils.describe(p));
				PhotoVo pv = new PhotoVo();
				pv.setPhotoCustom(pc);
				photoService.save(pv);
				msg.setCode(1);
				
				/*
				 * 补充图片地址信息
				 */
				String pa = p.getPhotoAddress();
				String sp = p.getSmallPhoto();
				pa = Tool.toRealUrl(pa);
				sp = Tool.toRealUrl(sp);
				p.setPhotoAddress(pa);
				p.setSmallPhoto(sp);
				
            	msg.setMsg(p);
			}else{
				msg.setCode(0);
            	msg.setMsg("文件上传失败");
			}
		} catch (Exception e) {
			msg.setCode(0);
        	msg.setMsg(e.getMessage());
        	e.printStackTrace();
		}
		
		return msg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
