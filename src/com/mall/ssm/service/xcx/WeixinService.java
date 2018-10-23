package com.mall.ssm.service.xcx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.wx.WxUserMapper;
import com.mall.ssm.po.WxUserLoginRecord;
import com.mall.ssm.po.custom.WxUserCustom;
import com.mall.ssm.po.pay.PayUtil;
import com.mall.ssm.po.pay.Payment;
import com.mall.ssm.po.pay.RequestPay;
import com.mall.ssm.po.pay.WxPayConfig;
import com.mall.ssm.po.result.AccessToken;
import com.mall.ssm.po.result.SendTempleteMsg;
import com.mall.ssm.po.result.TempleteResult;
import com.mall.ssm.po.vo.WxUserLoginRecordVo;
import com.mall.ssm.po.vo.WxUserVo;
import com.mall.ssm.util.Tool;
import com.mall.ssm.weixin.CustomHandler;

@Service
public class WeixinService extends CrudService<WxUserMapper, WxUserVo,WxUserCustom>{
	@Autowired
	private CustomHandler customHandler;
	@Autowired
	private WxPayConfig wxPayConfig;
	
	public CustomHandler getCustomHandler() {
		return customHandler;
	}

	public void setCustomHandler(CustomHandler customHandler) {
		this.customHandler = customHandler;
	}
	
	
	public WxPayConfig getWxPayConfig() {
		return wxPayConfig;
	}

	public void setWxPayConfig(WxPayConfig wxPayConfig) {
		this.wxPayConfig = wxPayConfig;
	}

	/**
	 * 登录
	 * @param js_code
	 * @return
	 * @throws Exception
	 */
	public String login(String js_code) throws Exception{
		String result = Tool.request(customHandler.getWxLoginUrl(),"appid="+customHandler.getAppid()+"&secret="+customHandler.getSecret()+"&js_code="+js_code+"&grant_type=authorization_code", "utf-8");
		return result;
	}
	
	
	
	
	/**
	 * 发送模板消息
	 */
	/*
	 * 1. 获取 access_token
	 */
	public AccessToken getAccess_token() throws Exception{
		String result = Tool.request(customHandler.getAccess_tokenUrl(),customHandler.getAccess_tokenParam()+"&appid="+customHandler.getAppid()+"&secret="+customHandler.getSecret(), "utf-8","GET");
		AccessToken token = JSONObject.parseObject(result,AccessToken.class);
		return token;
	}
	/*
	 * 2.发送模板消息
	 */
	public TempleteResult sendTempleteMsg(SendTempleteMsg msg,String token) throws Exception{
		String data = JSON.toJSONString(msg);
		String resultStr = Tool.httpRequest(customHandler.getSendTempleteUrl()+token, "POST",data.toString());
		TempleteResult result = JSONObject.parseObject(resultStr,TempleteResult.class);
		return result;
	}
	
	
	/**
	 * 调用微信支付
	 * @param request
	 * @param pay
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public Payment wxPay(HttpServletRequest request,RequestPay pay,String openid)throws Exception{
		/**
		 * 支付流程步骤：

			1）首先调用wx.login方法获取code，通过code获取openid；
			
			2）java后台调用统一下单支付接口（这里会进行第一次签名），用来获取prepay_id；
			
			3）java后台再次调用签名（这里会进行第二次签名），并返回支付需要用使用的参数；
			
			4）小程序前端wx.requestPayment方法发起微信支付；
			
			5）java后台接收来自微信服务器的通知并处理结果。
		 */
		Payment payment = new Payment();
			//生成的随机字符串
			String nonce_str = Tool.getRandomStringByLength(32);
			//商品名称
			String body = pay.getPayName()==null?"测试商品名称":pay.getPayName();
			//获取客户端的ip地址
			String spbill_create_ip = Tool.getIpAddr(request);
			int payMoney = pay.getMoney().intValue();
			//组装参数，用户生成统一下单接口的签名
			Map<String, String> packageParams = new HashMap<String, String>();
			packageParams.put("appid", customHandler.getAppid());
			packageParams.put("mch_id", wxPayConfig.getMch_id());
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("body", body);
			packageParams.put("out_trade_no", pay.getNextAction().getId());//商户订单号
			packageParams.put("total_fee", payMoney+"");//支付金额，这边需要转成字符串类型，否则后面的签名会失败
			packageParams.put("spbill_create_ip", spbill_create_ip);
			packageParams.put("notify_url", wxPayConfig.getNotify_url());//支付成功后的回调地址
			packageParams.put("trade_type", wxPayConfig.getTRADETYPE());//支付方式
			packageParams.put("openid", openid);
			   
	        	String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串 
	        
	        	//MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
	        	String mysign = PayUtil.sign(prestr, wxPayConfig.getKey(), "utf-8").toUpperCase();
	        
	        //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
	        String xml = "<xml>" + "<appid>" + customHandler.getAppid() + "</appid>" 
                    + "<body><![CDATA[" + body + "]]></body>" 
                    + "<mch_id>" + wxPayConfig.getMch_id() + "</mch_id>" 
                    + "<nonce_str>" + nonce_str + "</nonce_str>" 
                    + "<notify_url>" + wxPayConfig.getNotify_url() + "</notify_url>" 
                    + "<openid>" + openid + "</openid>" 
                    + "<out_trade_no>" + pay.getNextAction().getId() + "</out_trade_no>" 
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>" 
                    + "<total_fee>" + payMoney + "</total_fee>"
                    + "<trade_type>" + wxPayConfig.getTRADETYPE() + "</trade_type>" 
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";
	        
	        //System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);

	        //调用统一下单接口，并接受返回的结果
	        String result = PayUtil.httpRequest(wxPayConfig.getPay_url(), "POST", xml);
	        
	        //System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
	        
	        // 将解析结果存储在HashMap中   
	        Map map = PayUtil.doXMLParse(result);
	        
	        String return_code = (String) map.get("return_code");//返回状态码
	        
	        if(return_code=="SUCCESS"||return_code.equals(return_code)){   
	            String prepay_id = (String) map.get("prepay_id");//返回的预付单信息   
	            payment.setNonceStr(nonce_str);
	            payment.setPrepayId("prepay_id=" + prepay_id);
	            Long timeStamp = System.currentTimeMillis() / 1000;   
	            payment.setTimeStamp(timeStamp + "");
	            //拼接签名需要的参数
	            String stringSignTemp = "appId=" + customHandler.getAppid() + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=MD5&timeStamp=" + timeStamp;   
	            //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
	            String paySign = PayUtil.sign(stringSignTemp, wxPayConfig.getKey(), "utf-8").toUpperCase();
	            
	            payment.setPaySign(paySign);
	        }
	        
		
		return payment;
	}
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void insertLoginRecord(WxUserLoginRecordVo loginRecordVo){
		((WxUserMapper)super.dao).insertLoginRecord(loginRecordVo);
	}
	public List<WxUserLoginRecord> getLoginRecodeList(String dateStr){
		return ((WxUserMapper)super.dao).getLoginRecodeList(dateStr);
	}
	public List<WxUserCustom> getNewUsers(String dateStr){
		return ((WxUserMapper)super.dao).getNewUsers(dateStr);
	}
}
