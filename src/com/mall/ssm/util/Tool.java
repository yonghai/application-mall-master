package com.mall.ssm.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.mall.ssm.po.dto.MResult;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Tool {

	private static Logger log = Logger.getLogger(Tool.class);

	// 获取带分时间
	public static String getyyyyMMddHHmm() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(d);
	}

	// 获取带毫秒时间
	public static String getyyyyMMddHHmmss() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}

	// 获取带毫秒时间戳
	public static String getyyyyMMddHHmmssSSS() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(d);
	}

	// 获取日期
	public static String getyyyyMMdd() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(d);
	}

	// 获取日期
	public static String getyyyy_MM_dd() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	}

	// 前一天
	public static String getq_yyyy_MM_dd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long l = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
		return sdf.format(l);
	}

	// 前一月
	public static String getqy_yyyy_MM_dd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long l = System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 30;
		return sdf.format(l);
	}

	// 获取10000-100000的随机数
	public static int getRandom() {
		int max = 100000;
		int min = 10000;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}
	/**
     * 两个时间之间相差距离多少天
     * @param one 时间参数 1：
     * @param two 时间参数 2：
     * @return 相差天数
     */
    public static long getDistanceDays(String str1, String str2) throws Exception{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days=0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
   
    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }
    public static String getDistanceTimesFriendly(Date one,Date two) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long time1 = one.getTime();
		long time2 = two.getTime();
		long diff ;
		if(time1<time2) {
		    diff = time2 - time1;
		} else {
		    diff = time1 - time2;
		}
		day = diff / (24 * 60 * 60 * 1000);
		hour = (diff / (60 * 60 * 1000) - day * 24);
		min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
		String result = "";
        if(day>0){
        	result += day+"天";
        }
        if(hour>0){
        	result += hour+"小时";
        }
        if(min>0){
        	result += min+"分钟";
        }
        return result;
    }
 
    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

	// 获取ip--暂时不使用
	public static String getIps(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("127.0.0.1")) {
			log.error("获取客户ip失败：" + ip);
			ip = getUUID();
		}
		return ip;
	}

	// 获取ip
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Pounded-For");
		if (ip != null) {
			return ip;
		}

		ip = request.getHeader("x-forwarded-for");

		if (ip == null) {
			ip = request.getRemoteAddr();
			if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
				InetAddress address;
				try {
					address = InetAddress.getLocalHost();
					ip = address.getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}

			}
			return ip;
		} else {
			StringTokenizer tokenizer = new StringTokenizer(ip, ",");
			for (int i = 0; i < tokenizer.countTokens() - 1; i++) {
				tokenizer.nextElement();
			}

			ip = tokenizer.nextToken().trim();

			if (ip.equals("")) {
				ip = null;
			}
		}

		if (ip == null) {
			ip = "0.0.0.0";
		}

		return ip;
	}

	// 获取uuid
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	/*
	 * public static String MD5(String str){ String newstr=""; try { //确定计算方法
	 * MessageDigest md5= MessageDigest.getInstance("MD5"); BASE64Encoder
	 * base64en = new BASE64Encoder(); //加密后的字符串
	 * newstr=base64en.encode(md5.digest(str.getBytes("utf-8"))); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * return newstr; }
	 */
	public static void main(String[] args) {
		System.out.println(Tool.MD5("123456"));// e10adc3949ba59abbe56e057f20f883e
	}

	/**
	 * 对字符串md5加密(小写+字母)
	 * 
	 * @param str
	 *            传入要加密的字符串
	 * @return MD5加密后的字符串
	 */
	public static String MD5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 两个日期相减得到的天数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getDiffDays(Date beginDate, Date endDate) {

		if (beginDate == null || endDate == null) {
			throw new IllegalArgumentException("getDiffDays param is null!");
		}

		long diff = (endDate.getTime() - beginDate.getTime())
				/ (1000 * 60 * 60 * 24);

		int days = new Long(diff).intValue();

		return days;
	}

	/**
	 * 比较两个日期
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long compareDate(Date beginDate, Date endDate) {

		if (beginDate == null || endDate == null) {
			throw new IllegalArgumentException("compareDate param is null!");
		}

		long diff = (endDate.getTime() - beginDate.getTime());

		return diff;
	}

	/**
	 * 获得当前的起始时间
	 */
	public static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}

	/**
	 * 获得当前的结束时间
	 */
	public static Date getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			// System.out.println(urlNameString);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			// Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection
					.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 生成缩略图
	 * 
	 * @param photoPath
	 *            大图
	 * @param smallPath
	 *            小图
	 * @throws IOException
	 */
	public static void createSmallPhoto(File photoPath, File smallPath)
			throws IOException {
		BufferedImage src;
		src = javax.imageio.ImageIO.read(photoPath);// 读入文件
		int wideth = src.getWidth() / 3;
		int height = src.getHeight() / 3;
		BufferedImage tag = new BufferedImage(wideth, height,
				BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(src, 0, 0, wideth, height, null);// 绘制缩小后的图
		OutputStream out = new FileOutputStream(smallPath); // 输出到文件流
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(tag);
		out.close();
	}

	/**
	 * 移动文件
	 * 
	 * @param from
	 * @param to
	 * @param uid
	 * @return
	 * @throws IOException
	 */
	public static MResult mvFile(File from, File to, String uid) {
		MResult result = new MResult();
		if (!from.exists()) {
			result.setOk(false);
			result.setResult("文件不存在");
			return result;
		}
		if (!to.getParentFile().exists()) {
			to.getParentFile().mkdirs();
		}
		boolean ret = from.renameTo(to);
		if (ret) {
			result.setOk(true);
		} else {
			result.setOk(false);
			result.setResult("移动文件失败");
		}
		return result;
	}

	public static String toRealPath(String url) {
		if (url != null) {
			return url.replace(Configuration.BaseUrl, "/").trim();
		}
		return "";
	}
	
	
	public static String replaceToServerUrl(String url) {
		url = url.replaceAll("http://www.localhost:8080/mall/", Configuration.BaseUrl)
					.replaceAll("https://www.localhost:8080/mall/", Configuration.BaseUrl);
		return url;
	}
	
	
	public static String toRealUrl(String url) {
		if (url != null && url.trim().length() > 0) {
			if (url.startsWith(Configuration.BaseUrl)
					|| url.startsWith(Configuration.BaseUrl.replaceAll("https",
							"http"))) {
				return url;
			}
			url = Configuration.BaseUrl + url;
			url.replaceAll("//", "/");
		}
		return url;
	}

	/**
	 * 访问网络
	 * 
	 * @param path
	 * @param params
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String request(String path, String params, String encoding,
			String method) throws Exception {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoInput(true);// 是否打开输出流? true|false
			connection.setDoOutput(true);// 是否打开输入流true|false
			connection.setRequestMethod(method);// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			out.writeBytes(params);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			connection.disconnect();// 关闭连接
		}
	}

	/**
	 * 推送通知
	 */
	public static String request_Json(String path, String params,
			String encoding) throws Exception {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoInput(true);// 是否打开输出流? true|false
			connection.setDoOutput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			// 设置接收类型否则返回415错误
			// conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
			connection.setRequestProperty("accept", "application/json");
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			out.writeBytes(params);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			connection.disconnect();// 关闭连接
		}
	}

	public static String request(String path, String params, String encoding)
			throws Exception {
		return request(path, params, encoding, "POST");
	}
	public static boolean sameDate(Date d1, Date d2) {  
	    if(null == d1 || null == d2)  
	        return false;  
	    Calendar cal1 = Calendar.getInstance();  
	    cal1.setTime(d1);  
	    cal1.set(Calendar.HOUR_OF_DAY, 0);  
	    cal1.set(Calendar.MINUTE, 0);  
	    cal1.set(Calendar.SECOND, 0);  
	    cal1.set(Calendar.MILLISECOND, 0);  
	    Calendar cal2 = Calendar.getInstance();  
	    cal2.setTime(d2);  
	    cal2.set(Calendar.HOUR_OF_DAY, 0);  
	    cal2.set(Calendar.MINUTE, 0);  
	    cal2.set(Calendar.SECOND, 0);  
	    cal2.set(Calendar.MILLISECOND, 0);  
	    return  cal1.getTime().equals(cal2.getTime());  
	}
	
	/**
	 * IpUtils工具类方法
	 * 获取真实的ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
	    if(ip!=null && ip.trim().length()>0  && !"unKnown".equalsIgnoreCase(ip)){
	         //多次反向代理后会有多个ip值，第一个ip才是真实ip
	    	int index = ip.indexOf(",");
	        if(index != -1){
	            return ip.substring(0,index);
	        }else{
	            return ip;
	        }
	    }
	    ip = request.getHeader("X-Real-IP");
	    if(ip!=null && ip.trim().length()>0 && !"unKnown".equalsIgnoreCase(ip)){
	       return ip;
	    }
	    return request.getRemoteAddr();
	}
	

	/**
	 * StringUtils工具类方法
	 * 获取一定长度的随机字符串，范围0-9，a-z
	 * @param length：指定字符串长度
	 * @return 一定长度的随机字符串
	 */
	public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
   }
	
	
	
	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean strIsEmpty(String str) {
		if (str != null && str.trim().length() > 0) {
			return false;
		}
		return true;
	}

	public static String httpRequest(String requestUrl,
			String requestMethod, String outputStr) {

		String result = null;

		StringBuffer buffer = new StringBuffer();

		try {

			// 创建SSLContext对象，并使用我们指定的信任管理器初始化

			TrustManager[] tm = { new MyX509TrustManager() };

			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");

			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上述SSLContext对象中得到SSLSocketFactory对象

			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);

			javax.net.ssl.HttpsURLConnection httpUrlConn = (javax.net.ssl.HttpsURLConnection) url
					.openConnection();

			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);

			httpUrlConn.setDoInput(true);

			httpUrlConn.setUseCaches(false);

			// 设置请求方式（GET/POST）

			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))

				httpUrlConn.connect();

			// 当有数据需要提交时

			if (null != outputStr) {

				OutputStream outputStream = httpUrlConn.getOutputStream();

				// 注意编码格式，防止中文乱码

				outputStream.write(outputStr.getBytes("UTF-8"));

				outputStream.close();

			}

			// 将返回的输入流转换成字符串

			InputStream inputStream = httpUrlConn.getInputStream();

			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");

			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;

			while ((str = bufferedReader.readLine()) != null) {

				buffer.append(str);

			}

			bufferedReader.close();

			inputStreamReader.close();

			// 释放资源

			inputStream.close();

			inputStream = null;

			httpUrlConn.disconnect();
			result = buffer.toString();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public static class MyX509TrustManager implements X509TrustManager {

		public void checkClientTrusted(
				java.security.cert.X509Certificate[] arg0, String arg1)
				throws java.security.cert.CertificateException {
		}

		public void checkServerTrusted(
				java.security.cert.X509Certificate[] arg0, String arg1)
				throws java.security.cert.CertificateException {
		}

		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}
}
