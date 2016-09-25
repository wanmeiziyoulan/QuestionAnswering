package com.quize.web;

import com.example.quizeanswer.AnsEntity;
import com.google.gson.Gson;

public class SendAnsThread {
	private String jsonData;
	private String dataUrl;
	public SendAnsThread(AnsEntity data, String url) {
		// TODO Auto-generated constructor stub
		jsonData=new Gson().toJson(data);
		dataUrl=url;
		new Task().start();
	}
	class Task extends Thread {
		@Override
		public void run() {
			try {
				System.out.println("json"+jsonData);
				//jsondata="JsonData"+jsonData;
				boolean isPost=SendRequest.Send(dataUrl,jsonData);
				System.out.println("yonghuxinxi"+isPost);
				if(isPost==true){
					System.out.println("³É¹¦");
				}else{
					System.out.println("Ê§°Ü");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.run();
		}
	}

}
