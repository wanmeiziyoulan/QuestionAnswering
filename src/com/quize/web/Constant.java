package com.quize.web;

public class Constant {
	
	//��ȡǰ10�����ʣ������ݿ⵼�������������𰸸����ֶ�
	public static String searchQuizeUrl="http://115.29.151.149:8051/api/json/searchQuize";  
	//��ȡǰ10���ش�----���е�quizeId����answer��Ӧ��quize��id��
	public static String searchAnswerUrl="http://115.29.151.149:8051/api/json/searchAnswer";
	//��ȡ���⣬�����𰸸����ֶ�	
	public static String quizeListUrl="http://115.29.151.149:8051/api/json/quizeList";  
	//��id�Ų�ѯ��������(����quizeId=1)������������Ϣ�����д�         
	public static String quizeIdUrl="http://115.29.151.149:8051/api/json/quizeId=";
	//�ύ���⣨�����ݿ⵼�룩
	public static String outQuizeUrl="http://115.29.151.149:8051/api/json/quize";
	//�ύ��
	public static String outAnswerUrl="http://115.29.151.149:8051/api/json/answer";                                 

	
	 public static final String DESCRIPTOR = "com.umeng.share";
		
	 /**
	  * ���ڷ���ƽ̨
	  */
		private static final String TIPS = "���Ʋ��ٷ���վ ";
		private static final String END_TIPS = ", �鿴���˵��.";
		public static final String TENCENT_OPEN_URL = TIPS + "http://wiki.connect.qq.com/android_sdkʹ��˵��" 
														+ END_TIPS;
		public static final String PERMISSION_URL = TIPS + "http://wiki.connect.qq.com/openapiȨ������" 
														+ END_TIPS;
		
		public static final String SOCIAL_LINK = "http://www.umeng.com/social";
		public static final String SOCIAL_TITLE = "������ữ�������Ӧ�ÿ������Ϸ�����";
		public static final String SOCIAL_IMAGE = "http://www.umeng.com/images/pic/banner_module_social.png";
		
		public static final String SOCIAL_CONTENT = "������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ����Ǽ����罻ƽ̨�Ľ��룬Ϊ�������ṩ��ʵ�Ļ������񣺣�һ��֧�ָ��������罻ƽ̨��" + 
													"������֧��ͼƬ�����֡�gif��ͼ����Ƶ����Ƶ��@���ѣ���ע�ٷ�΢���ȹ���" +
													"�������ṩ�꾡�ĺ�̨�û��罻��Ϊ������http://www.umeng.com/social";

}
