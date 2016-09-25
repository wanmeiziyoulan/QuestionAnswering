package com.quize.web;

import com.example.quizeanswer.QuizeEntity;
import com.google.gson.Gson;

public class SendDataThread {
	private String jsonData;
	private String dataUrl;
	public SendDataThread(QuizeEntity data, String url) {
		// TODO Auto-generated constructor stub
		jsonData=new Gson().toJson(data);//把数据转换成json格式上传
		dataUrl=url;
		new Task().start();//开始上传线程
	}
	class Task extends Thread {
		@Override
		public void run() {
			try {
				System.out.println("askjson"+jsonData);
				//jsondata="JsonData"+jsonData;
				boolean isPost=SendRequest.Send(dataUrl,jsonData);
				System.out.println("yonghuxinxi"+isPost);
				if(isPost==true){
					System.out.println("成功");
				}else{
					System.out.println("失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.run();
		}
	}
}
