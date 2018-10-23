package com.mall.ssm.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.ssm.po.custom.WxUserCustom;
import com.mall.ssm.po.dto.UploadImageMsg;
import com.mall.ssm.po.dto.UploadMsg;
import com.mall.ssm.service.order.OrderService;
import com.mall.ssm.service.xcx.WeixinService;
import com.mall.ssm.util.CommonUtils;
import com.mall.ssm.util.Configuration;
import com.mall.ssm.util.TokenProccessor;
import com.mall.ssm.util.Tool;

@Controller
public class PageNavigateController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private WeixinService weixinService;
	
	/**
	 * 跳转到登录界面
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request)throws Exception{
		String token = TokenProccessor.getInstance().makeToken();//创建令牌
		System.out.println("生成的token："+token);
		
		request.getSession().setAttribute("token", token);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("token", token);
		modelAndView.setViewName("login");
		
		return modelAndView;
	}
	/**
	 * 跳转到注册界面
	 */
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		
		return modelAndView;
	}
	/**
	 * 跳转到主页
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String todayStr = simpleDateFormat.format(today);
		String yesterdayStr = Tool.getq_yyyy_MM_dd();
		
		int order_today = orderService.getOrderByDate(todayStr).size();
		int order_yesterday = orderService.getOrderByDate(yesterdayStr).size();
		int view_today = weixinService.getLoginRecodeList(todayStr).size();
		int view_yesterday = weixinService.getLoginRecodeList(yesterdayStr).size();
		
		List<WxUserCustom> users = weixinService.getNewUsers(todayStr);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (WxUserCustom wxUserCustom : users) {
			String time = Tool.getDistanceTimesFriendly(df.parse(wxUserCustom.getAddtime()), today);
			wxUserCustom.setAddtime(time);
		}
		
		request.setAttribute("order_today", order_today);
		request.setAttribute("order_yesterday", order_yesterday);
		request.setAttribute("view_today", view_today);
		request.setAttribute("view_yesterday", view_yesterday);
		request.setAttribute("users", users);
		
		return modelAndView;
	}
	
	/**
	 * 批量上传图片到空间
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toUploadImages")
	public ModelAndView toUploadImages(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("upload");
		
		return modelAndView;
	}
	
	/**
	 * 跳转到增加轮播图界面
	 */
	@RequestMapping("/swiper/add")
	public ModelAndView navigateToAddSwiper(HttpServletRequest request)throws Exception{
		String token = TokenProccessor.getInstance().makeToken();//创建令牌
		System.out.println("生成的token："+token);
		
		request.getSession().setAttribute("token", token);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("token", token);
		modelAndView.setViewName("add-swiper");
		
		return modelAndView;
	}
	
	/**
	 * 跳转到轮播图界面
	 */
	@RequestMapping("/swiper/list")
	public ModelAndView navigateToSwiperList(HttpServletRequest request)throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("swipers");
		
		return modelAndView;
	}
	
	
	/**
	 * 跳转到公告界面
	 */
	@RequestMapping("/notice/list")
	public ModelAndView navigateToNoticeList(HttpServletRequest request)throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("notices");
		
		return modelAndView;
	}
	/**
	 * 跳转到增加公告界面
	 */
	@RequestMapping("/notice/add")
	public ModelAndView navigateToAddNotice(HttpServletRequest request)throws Exception{
		String token = TokenProccessor.getInstance().makeToken();//创建令牌
		System.out.println("生成的token："+token);
		
		request.getSession().setAttribute("token", token);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("token", token);
		modelAndView.setViewName("add-notice");
		
		return modelAndView;
	}
	
	/**
	 * 跳转到新增分类界面
	 */
	@RequestMapping("/product/category/add")
	public ModelAndView navigateToAddProductCategory(HttpServletRequest request)throws Exception{
		String token = TokenProccessor.getInstance().makeToken();//创建令牌
		System.out.println("生成的token："+token);
		
		request.getSession().setAttribute("token", token);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("token", token);
		modelAndView.setViewName("add-product-category");
		
		return modelAndView;
	}
	/**
	 * 跳转到产品分类界面
	 */
	@RequestMapping("/product/category/list")
	public ModelAndView navigateToProductCategoryList(HttpServletRequest request)throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("product-categorys");
		
		return modelAndView;
	}
	/**
	 * 跳转到新增属性界面
	 */
	@RequestMapping("/property/add")
	public ModelAndView navigateToAddProperty(HttpServletRequest request)throws Exception{
		String token = TokenProccessor.getInstance().makeToken();//创建令牌
		System.out.println("生成的token："+token);
		
		request.getSession().setAttribute("token", token);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("token", token);
		modelAndView.setViewName("add-property");
		
		return modelAndView;
	}
	/**
	 * 跳转到属性界面
	 */
	@RequestMapping("/property/list")
	public ModelAndView navigateToPropertyList(HttpServletRequest request)throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("propertys");
		
		return modelAndView;
	}
	
	
	/**
	 * 跳转到新增属性界面
	 */
	@RequestMapping("/product/add")
	public ModelAndView navigateToAddProduct(HttpServletRequest request)throws Exception{
		String token = TokenProccessor.getInstance().makeToken();//创建令牌
		System.out.println("生成的token："+token);
		
		request.getSession().setAttribute("token", token);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("token", token);
		modelAndView.setViewName("add-good");
		
		return modelAndView;
	}
	
	/**
	 * 跳转到产品界面
	 */
	@RequestMapping("/product/list")
	public ModelAndView navigateToProductList(HttpServletRequest request)throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("goods");
		
		return modelAndView;
	}
	
	
	/**
	 * 跳转到产品界面
	 */
	@RequestMapping("/order/list")
	public ModelAndView navigateToOrderList(HttpServletRequest request)throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("orders");
		
		return modelAndView;
	}
	
	/**
	 * 跳转到用户列表界面
	 */
	@RequestMapping("/user/list")
	public ModelAndView navigateToUserList(HttpServletRequest request)throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("users");
		
		return modelAndView;
	}
	
	/**
	 * 跳转到发行优惠券界面
	 */
	@RequestMapping("/coupons/new")
	public ModelAndView navigateToNewCoupons(HttpServletRequest request)throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("add-coupon");
		
		return modelAndView;
	}
	
	@RequestMapping("/coupons/list")
	public ModelAndView navigateToCouponsList(HttpServletRequest request)throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("coupons");
		
		return modelAndView;
	}
	
	@RequestMapping("/reputation/list")
	public ModelAndView navigateToReputationList(HttpServletRequest request)throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reputations");
		
		return modelAndView;
	}
	
	@RequestMapping("/coupons/fetchListPage")
	public ModelAndView navigateToFetchListPage(HttpServletRequest request)throws Exception{
		String flag = request.getParameter("flag");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("fetch-coupons");
		modelAndView.addObject("flag", flag);
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	/**
	 * 上传图片
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/api/upload/image", method = RequestMethod.POST)
	public @ResponseBody UploadMsg image(MultipartFile file, HttpServletRequest request){
		UploadMsg msg = new UploadMsg();
		//原始名称
		try {
			String originalFilename = file.getOriginalFilename();
			//上传图片
			if(file!=null && originalFilename!=null&&originalFilename.length()>0){
				//存储图片的物理路径
				String pic_path = Configuration.BaseImgDir;
				String savePath = Configuration.TEMP_PATH;//upload/temp
				
				if(request.getParameter("filePath")!=null){
					savePath += "/"+request.getParameter("filePath");
				}
				
				//新图片名称
				String newFileName = CommonUtils.uuid()+originalFilename.substring(originalFilename.lastIndexOf("."));
				File dir = new File(pic_path,savePath);
				String dirname=Tool.getyyyyMMdd();
				
				//新图片
				File newFileDir = new File(dir,dirname);
				if(!newFileDir.exists()){
					newFileDir.mkdirs();
				}
				
				/*生成原始文件*/
				File newFile = new File(newFileDir,newFileName);
				file.transferTo(newFile);
				
				//网络路径
				String accessPath = Configuration.BaseUrl+savePath+"/"+dirname+"/"+newFileName;
				
				UploadImageMsg msgr = new UploadImageMsg(accessPath,newFileName);
				
				msg.setCode(1);
            	msg.setMsg(msgr);
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
