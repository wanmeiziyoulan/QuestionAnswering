package com.quize.web;

public class Constant {
	
	//获取前10个提问（从数据库导出），不包含答案个数字段
	public static String searchQuizeUrl="http://115.29.151.149:8051/api/json/searchQuize";  
	//获取前10个回答----其中的quizeId项是answer对应的quize的id号
	public static String searchAnswerUrl="http://115.29.151.149:8051/api/json/searchAnswer";
	//获取问题，包含答案个数字段	
	public static String quizeListUrl="http://115.29.151.149:8051/api/json/quizeList";  
	//用id号查询提问问题(比如quizeId=1)，包括问题信息和所有答案         
	public static String quizeIdUrl="http://115.29.151.149:8051/api/json/quizeId=";
	//提交问题（向数据库导入）
	public static String outQuizeUrl="http://115.29.151.149:8051/api/json/quize";
	//提交答案
	public static String outAnswerUrl="http://115.29.151.149:8051/api/json/answer";                                 

	
	 public static final String DESCRIPTOR = "com.umeng.share";
		
	 /**
	  * 关于分享平台
	  */
		private static final String TIPS = "请移步官方网站 ";
		private static final String END_TIPS = ", 查看相关说明.";
		public static final String TENCENT_OPEN_URL = TIPS + "http://wiki.connect.qq.com/android_sdk使用说明" 
														+ END_TIPS;
		public static final String PERMISSION_URL = TIPS + "http://wiki.connect.qq.com/openapi权限申请" 
														+ END_TIPS;
		
		public static final String SOCIAL_LINK = "http://www.umeng.com/social";
		public static final String SOCIAL_TITLE = "友盟社会化组件帮助应用快速整合分享功能";
		public static final String SOCIAL_IMAGE = "http://www.umeng.com/images/pic/banner_module_social.png";
		
		public static final String SOCIAL_CONTENT = "友盟社会化组件（SDK）让移动应用快速整合社交分享功能，我们简化了社交平台的接入，为开发者提供坚实的基础服务：（一）支持各大主流社交平台，" + 
													"（二）支持图片、文字、gif动图、音频、视频；@好友，关注官方微博等功能" +
													"（三）提供详尽的后台用户社交行为分析。http://www.umeng.com/social";

}
