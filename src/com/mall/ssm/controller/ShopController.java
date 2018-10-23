package com.mall.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.ssm.base.Code;
import com.mall.ssm.po.custom.ShopAddressCustom;
import com.mall.ssm.po.dto.MResult;
import com.mall.ssm.po.dto.xcx.R;
import com.mall.ssm.po.vo.ShopAddressVo;
import com.mall.ssm.service.redis.Redis;
import com.mall.ssm.service.shop.ShopAddressService;

@Controller
public class ShopController {
	
	@Autowired
	private ShopAddressService shopAddressService;	
	@Autowired
	private Redis<Object> redis;
	/**
	 * 保存
	 * @param request
	 * @param address
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/shipping-address/save", method = RequestMethod.POST)
	public @ResponseBody R<String> addShopAddress(HttpServletRequest request,ShopAddressVo address)
		throws Exception {
		R<String> r = new R<String>();
		
		if(address.getToken() == null || address.getToken().trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(address.getToken())){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		String openid = redis.get(address.getToken());
		
		if(openid == null || openid.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		try {
			address.getShopAddressCustom().setAppid(openid);
			/*转码*/
			String linkMan = address.getShopAddressCustom().getLinkMan();//收货人
			String addr = address.getShopAddressCustom().getAddress();//详细地址
			linkMan = java.net.URLDecoder.decode(linkMan,"UTF-8"); 
			addr = java.net.URLDecoder.decode(addr,"UTF-8"); 
			address.getShopAddressCustom().setLinkMan(linkMan);
			address.getShopAddressCustom().setAddress(addr);
			
			if(address.getShopAddressCustom().getIsDefault()){
				address.getShopAddressCustom().setIsDefault(false);
				shopAddressService.updateDefaultAddress(address);
				address.getShopAddressCustom().setIsDefault(true);
			}
			shopAddressService.save(address);
			r.setMsg("success");
			r.setCode(Code.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	
	/**
	 * 更新
	 * @param request
	 * @param address
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/shipping-address/update", method = RequestMethod.POST)
	public @ResponseBody R<String> updateShopAddress(HttpServletRequest request,ShopAddressVo address)
		throws Exception {
		R<String> r = new R<String>();
		
		if(address.getToken() == null || address.getToken().trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(address.getToken())){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		String openid = redis.get(address.getToken());
		
		if(openid == null || openid.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		try {
			address.getShopAddressCustom().setAppid(openid);
			/*转码*/
			String linkMan = address.getShopAddressCustom().getLinkMan();//收货人
			if(linkMan != null){
				linkMan = java.net.URLDecoder.decode(linkMan,"UTF-8");
				address.getShopAddressCustom().setLinkMan(linkMan);
			}
			String addr = address.getShopAddressCustom().getAddress();//详细地址
			if(addr != null){
				addr = java.net.URLDecoder.decode(addr,"UTF-8"); 
				address.getShopAddressCustom().setAddress(addr);
			}
			if(address.getShopAddressCustom().getIsDefault()){
				address.getShopAddressCustom().setIsDefault(false);
				shopAddressService.updateDefaultAddress(address);
				address.getShopAddressCustom().setIsDefault(true);
			}
			shopAddressService.update(address);
			r.setMsg("success");
			r.setCode(Code.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	/**
	 * 列表
	 * @param request
	 * @param address
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/shipping-address/list", method = RequestMethod.POST)
	public @ResponseBody R<List<ShopAddressCustom>> listShopAddress(HttpServletRequest request,ShopAddressVo address)
		throws Exception {
		R<List<ShopAddressCustom>> r = new R<List<ShopAddressCustom>>();
		
		if(address.getToken() == null || address.getToken().trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(address.getToken())){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		String openid = redis.get(address.getToken());
		
		if(openid == null || openid.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		try {
			if(address.getShopAddressCustom() == null){
				ShopAddressCustom sc = new ShopAddressCustom();
				sc.setAppid(openid);
				address.setShopAddressCustom(sc);
			}
			address.getShopAddressCustom().setAppid(openid);
			address.setLimit(1000);
			address.setPage(1);
			address.setStart(0);
			List<ShopAddressCustom> data = shopAddressService.getAllList(address);
			r.setMsg("success");
			if(data.isEmpty()){
				r.setCode(Code.SHOPPING_ADDRESS_EMPTY);
			}else{
				r.setCode(Code.OK);
			}
			r.setData(data);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	
	/**
	 * 查询
	 * @param request
	 * @param address
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/shipping-address/default", method = RequestMethod.POST)
	public @ResponseBody R<ShopAddressCustom> defaultShopAddress(HttpServletRequest request,ShopAddressVo address)
		throws Exception {
		R<ShopAddressCustom> r = new R<ShopAddressCustom>();
		
		if(address.getToken() == null || address.getToken().trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(address.getToken())){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		String openid = redis.get(address.getToken());
		
		if(openid == null || openid.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		try {
			if(address.getShopAddressCustom() == null){
				ShopAddressCustom sc = new ShopAddressCustom();
				sc.setAppid(openid);
				address.setShopAddressCustom(sc);
			}
			address.getShopAddressCustom().setAppid(openid);
			address.getShopAddressCustom().setIsDefault(true);
			ShopAddressCustom data = shopAddressService.get(address);
			r.setMsg("success");
			if(data == null){
				r.setCode(Code.SHOPPING_ADDRESS_EMPTY);
			}else{
				r.setCode(Code.OK);
				data.getProvince();
				data.getCity();
				data.getCounty();
			}
			r.setData(data);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	@RequestMapping(value="/user/shipping-address/detail", method = RequestMethod.POST)
	public @ResponseBody R<ShopAddressCustom> detailShopAddress(HttpServletRequest request,ShopAddressVo address)
		throws Exception {
		R<ShopAddressCustom> r = new R<ShopAddressCustom>();
		
		if(address.getToken() == null || address.getToken().trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(address.getToken())){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		String openid = redis.get(address.getToken());
		
		if(openid == null || openid.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		try {
			if(address.getShopAddressCustom() == null){
				ShopAddressCustom sc = new ShopAddressCustom();
				sc.setAppid(openid);
				address.setShopAddressCustom(sc);
			}
			address.getShopAddressCustom().setAppid(openid);
			ShopAddressCustom data = shopAddressService.get(address);
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
	 * 删除
	 * @param request
	 * @param address
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/shipping-address/delete", method = RequestMethod.POST)
	public @ResponseBody R<String> deleteShopAddress(HttpServletRequest request,ShopAddressVo address)
		throws Exception {
		R<String> r = new R<String>();
		
		if(address.getToken() == null || address.getToken().trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(address.getToken())){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		try {
			if(address.getShopAddressCustom() == null || address.getShopAddressCustom().getId() == null){
				r.setCode(Code.ERROR);
				r.setMsg("id is invalid");
				return r;
			}
			shopAddressService.delete(address.getShopAddressCustom().getId());
			r.setMsg("success");
			r.setCode(Code.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
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
