package com.quize.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class SendRequest {
	public static String get(String url) {//��url��get��ʽ��ȡ����
		String strResult = "none";
		HttpGet httpRequest = new HttpGet(url);
	
		try {

			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
		
				strResult = EntityUtils.toString(httpResponse.getEntity());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strResult;
	}
	public static boolean sendRequestJsonData(String url, String jsonDate)//post��ʽ��������
			throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		httpPost.setHeader("Content-Type", "application/json");
		
		String JsonData="'"+jsonDate+"'";
		System.out.println(JsonData);
		httpPost.setEntity(new StringEntity(JsonData, "utf-8"));
		HttpResponse response = httpClient.execute(httpPost);
		System.out.println("1");
		int code = response.getStatusLine().getStatusCode();
		System.out.println("2");
		if (code == 200) {
			String jsonData = EntityUtils.toString(response.getEntity());
			System.out.println("2"+jsonData);
			return true;
		} else {
			return false;
		}
	}
	public static boolean Send(String url,String json) {
		System.out.println("jsonΪ"+json);
		System.out.println("url:"+url);
		
		
		HttpPost httpRequest = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("JsonData", json));
		
		try {
			System.out.println("httprequest");
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);
			System.out.println("getStatusCode"+httpResponse.getStatusLine().getStatusCode());
			/**û��ӡ����**/
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				System.out.println("getStatusCode");
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				System.out.println("sendResult"+strResult);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
