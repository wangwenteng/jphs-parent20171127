package com.jinpaihushi.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * 连接api接口工具类
 * @author Administrator
 *
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil {
	private static  Log logger = LogFactory.getLog(HttpClientUtil.class);
	
	/**
	 * get方式请求
	 * @param url 请求地址 (含请求参数)
	 * @return  String
	 */
	public static String getStringData(String  url){
		return httpURLConnectionRequest("GET", url, null, "UTF-8", "UTF-8", 60000);
	}

	/**
	 * get方式请求
	 * @param url 请求地址 (含请求参数)
	 * @return  JSONObject
	 */
	public static JSONObject httpURLConnectionRequestGet(String url){
		JSONObject data_result = new JSONObject();
		String result = httpURLConnectionRequest("GET", url, null, "UTF-8", "UTF-8", 60000);
		if(result != null){
			data_result = JSONObject.fromObject(result);
		}else{
			data_result.put("msg", "接口没有数据返回！");//没有数据返回 服务器出故障
		}
		return data_result;
	}
	/**
	 * post方式 请求数据
	 * @param url   接口地址
	 * @param data json数据包
	 * @return   json
	 */
	public static JSONObject httpURLConnectionRequestPost(String url,String data ) {
		JSONObject data_result = new JSONObject();
		String result = httpURLConnectionRequest("POST", url, data, "UTF-8", "UTF-8", 60000);
		if(result != null){
			data_result = JSONObject.fromObject(result);
		}else{
			data_result.put("msg", "接口没有数据返回！");//没有数据返回 服务器出故障
		}
		return data_result;
	}
	
	public static String httpURLConnectionRequest(String requestWay,String url,String data,String requestCharacter, String responseCharacter,int timeout){
		StringBuffer sb_result = new StringBuffer();
		logger.info("请求接口地址：" + url);
		try {
			URL urls = new URL(url);
			HttpURLConnection connect = (HttpURLConnection) urls.openConnection();
			connect.setDoInput(true);
			connect.setDoOutput(true);
			connect.setConnectTimeout(timeout);
			connect.setReadTimeout(timeout);
			connect.setUseCaches(false);
			if(StringUtils.equalsIgnoreCase(requestWay, "GET")){
				connect.setRequestProperty("Content-Type", "text/xml");
				connect.setRequestProperty("charset",requestCharacter);
				connect.setRequestMethod("GET");
			}else if(StringUtils.equalsIgnoreCase(requestWay, "POST")){
				connect.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				connect.setRequestProperty("charset",requestCharacter);
				connect.setRequestMethod("POST");
				OutputStreamWriter out = new OutputStreamWriter(connect.getOutputStream(), requestCharacter);
				out.write(data);
				out.flush();
				out.close();
			}else{
				logger.error("httpURLConnectionRequest调用接口失败！请求方式错误!");
				return null;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream(), responseCharacter));
			for (String line = null; (line = in.readLine()) != null;)
				sb_result.append(line);
			in.close();
			in = null;
			urls = null;
		} catch (Exception e) {
			logger.error("httpURLConnectionRequest调用接口失败！错误信息：" + e.getMessage());
			return null;
		}
		return sb_result.toString();
	}
	
	/**
	 * 带证书验证https协议连接
	 * @param keyStore_path(String)   证书地址
	 * @param keyStore_password(String)  证书密码  
	 * @param host_post(String)  接口连接地址的端口号
	 * @param url(String)  完整的接口地址
	 * @param requestParam  Map<String, String>  参数名=参数值 的键值对形式
	 * @return   字符串(String)
	 */
	@SuppressWarnings("resource")
	public static String keyStoreRequestCall(String keyStore_path,String keyStore_password,String host_post,String url,Map<String, String> requestParam){
		HttpClient client = new DefaultHttpClient();
		String result= null;
		try {
			KeyStore trustStore =KeyStore.getInstance(KeyStore.getDefaultType());
			InputStream instream = new FileInputStream(keyStore_path);//此服务器上地址
//			InputStream instream = getResourceAsStream(keyStore_path);//此工程项目内
			trustStore.load(instream, keyStore_password.toCharArray());
			SSLSocketFactory socketFactory=new SSLSocketFactory(trustStore);
			socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme sch=new Scheme("https",Integer.parseInt(host_post), socketFactory);
			client.getConnectionManager().getSchemeRegistry().register(sch);
			HttpPost post = new HttpPost(url);
			List<NameValuePair> formParams = initNameValuePair(requestParam);
			HttpEntity entity = new UrlEncodedFormEntity(formParams, "utf-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			InputStream is=response.getEntity().getContent();
			BufferedReader br=new BufferedReader(new InputStreamReader(is,"utf-8"));
			StringBuilder sb=new StringBuilder();
			for(String temp=br.readLine();temp!=null;sb.append(temp),temp=br.readLine());
			result=sb.toString();
			logger.info(result);
		}catch (Exception e) {
//			e.printStackTrace();
			logger.error("带正式调用接口失败！异常信息：" + e.getMessage());
		}
		return result;
	}
	/**
	 *  post请求方式  响应结果为json格式的字符串
	 * @param url(String)   请求地址
	 * @param requestParam Map<String, String>  参数
	 * @return JSONObject
	 */
	public static JSONObject requestPostWay_json(String url, Map<String, String> requestParam) {
		JSONObject data_result = new JSONObject();
		String result = requestPostWay(url, requestParam, "UTF-8", "UTF-8", 60000);
		if(StringUtils.isNotEmpty(result)){
			data_result = JSONObject.fromObject(result);
		}else{
			data_result.put("msg", "调用接口出错了！");
		}
		return data_result;
	}
	/**
	 * post请求方式  响应结果为xml格式
	 * @param url(String)   请求地址
	 * @param requestParam Map<String, String>  参数
	 * @return JSONObject
	 */
	public static JSONObject requestPostWay_xml(String url, Map<String, String> requestParam) {
		JSONObject data_result = new JSONObject();
		String result = requestPostWay(url, requestParam, "UTF-8", "UTF-8", 60000);
		if(StringUtils.isNotEmpty(result)){
			data_result = getJSONObjectFromXml(result);
		}else{
			data_result.put("msg", "调用接口出错了！");
		}
		return data_result;
	}
	/**
	 * 读取项目内的文件
	 * @param resource
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private static InputStream getResourceAsStream(String resource) throws IOException {
		InputStream in = HttpClientUtil.class.getResourceAsStream(resource);
		if (in == null)
			throw new IOException("Could not find resource " + resource);
		return in;
	}
	/** 
	 * 将xml字符串转换为JSONObject对象 
	 * @param xmlFile xml字符串 
	 * @return JSONObject对象 
	 */
	private static JSONObject getJSONObjectFromXml(String xmlString) {  
	    XMLSerializer xmlSerializer = new XMLSerializer();  
	    JSON json = xmlSerializer.read(xmlString);  
	    return JSONObject.fromObject(json);
	}
	/**
	 * http或者https协议 -- Post请求方式
	 * @param url(String)   请求地址
	 * @param requestParam Map<String, String>  参数
	 * @param requestCharacter(String)   请求编码  "UTF-8"......
	 * @param responseCharacter(String)   响应编码  "UTF-8"......
	 * @param timeout  连接和读取超时   单位毫秒 
	 * @return  (String) 返回字符串类型的响应结果
	 */
	public static String requestPostWay(String url, Map<String, String> requestParam, String requestCharacter, String responseCharacter, int timeout) {
		HttpResponseWrapper httpResponseWrapper = null;
		try {
			HttpClient client = null;
			if (url.startsWith("https")) {
				client = createHttpsClient(timeout, timeout);
			} else {
				client = createHttpClient(timeout, timeout);
			}
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> formParams = initNameValuePair(requestParam);
			httpPost.setEntity(new UrlEncodedFormEntity(formParams, requestCharacter));
			HttpResponse httpResponse = client.execute(httpPost);
			httpResponseWrapper = new HttpResponseWrapper(client, httpResponse, httpPost);
			return httpResponseWrapper.getResponseString(responseCharacter);
		} catch (Exception e) {
			logger.error("requestPostWay方法请求异常信息：" + e.getMessage());
		} finally {
			httpResponseWrapper.close();
		}
		return null;
	}

	/**
	 * 获取http协议连接 HttpClient对象
	 * @param connectionTimeout  连接时间   毫秒
	 * @param soTimeout   读取时间  毫秒
	 * @return   HttpClient对象
	 */
	private static HttpClient createHttpClient(int connectionTimeout, int soTimeout) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);
		HttpConnectionParams.setSoTimeout(params, soTimeout);
		return httpClient;
	}

	/**
	 * 获取https协议连接 HttpClient对象
	 * @param connectionTimeout  连接时间   毫秒
	 * @param soTimeout   读取时间  毫秒
	 * @return
	 */
	private static HttpClient createHttpsClient(int connectionTimeout, int soTimeout) {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpParams params = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);
			HttpConnectionParams.setSoTimeout(params, soTimeout);
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { new TrustAnyTrustManager() }, null);
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
			return httpClient;
		} catch (Exception e) {
			logger.error("createHttpsClient失败了！错误信息：" + e.getMessage());
		}
		return null;
	}
	/**
	 * 请求的参数处理 
	 * @param params  Map<String, String> params 参数名=参数值  键值对
	 * @return  List<NameValuePair>   参数名=参数值  键值对
	 */
	private static List<NameValuePair> initNameValuePair(Map<String, String> params) {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		if (params != null && params.size() > 0) {
			List<String> keys = new ArrayList<String>(params.keySet());
			Collections.sort(keys);
			for (String key : keys) {
				formParams.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		return formParams;
	}

	/**
	 *
	 */
	public static class TrustAnyTrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		
		}
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		
		}
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	/**
	 * 请求响应对象类
	 *
	 */
	public static class HttpResponseWrapper {
		private HttpResponse httpResponse;
		private HttpClient httpClient;
		private HttpRequestBase httpRequest;
		
		public HttpResponseWrapper(HttpClient httpClient, HttpResponse httpResponse) {
			this.httpClient = httpClient;
			this.httpResponse = httpResponse;
		}
		
		public HttpResponseWrapper(HttpClient httpClient, HttpResponse httpResponse, HttpRequestBase httpRequest) {
			this(httpClient, httpResponse);
			this.httpRequest = httpRequest;
		}
		/**
		 * 将请求响应结果转字符串
		 * @param responseCharacter  响应编码
		 * @return
		 * @throws ParseException
		 * @throws IOException
		 */
		public String getResponseString(String responseCharacter) throws ParseException, IOException {
			HttpEntity entity = getEntity();
			String responseStr = EntityUtils.toString(entity, responseCharacter);
			if (entity.getContentType() == null) {
				responseStr = new String(responseStr.getBytes("iso-8859-1"), responseCharacter);
			}
			EntityUtils.consume(entity);
			return responseStr;
		}
		
		public int getStatusCode() {
			return httpResponse.getStatusLine().getStatusCode();
		}
		
		public HttpEntity getEntity() {
			return httpResponse.getEntity();
		}
		
		public void close() {
			if (httpRequest != null) {
				httpRequest.releaseConnection();
			}
			httpClient.getConnectionManager().shutdown();
		}
	}
}