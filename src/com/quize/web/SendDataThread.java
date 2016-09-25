package com.quize.web;

import com.example.quizeanswer.QuizeEntity;
import com.google.gson.Gson;

public class SendDataThread {
	private String jsonData;
	private String dataUrl;
	public SendDataThread(QuizeEntity data, String url) {
		// TODO Auto-generated constructor stub
		jsonData=new Gson().toJson(data);//������ת����json��ʽ�ϴ�
		dataUrl=url;
		new Task().start();//��ʼ�ϴ��߳�
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
					System.out.println("�ɹ�");
				}else{
					System.out.println("ʧ��");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.run();
		}
	}
}
