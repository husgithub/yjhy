package com.yjhy.util;

public  class StatusCodes {
	
	//成功
	public static final String SYS_SUCCESS="101_101";
	
	//失败
	public static final String SYS_FAILURE= "101_102";
	
	//系统错误
	public static final String SYS_ERROR="101_103";
	
	//格式错误
	public static final String SYS_FORMAT_ERROR ="101_104";
	
	//用户已存在
	public static final String USER_EXIST="102_101";
	
	//注册成功
	public static final String USER_REG_SUCCESS="102_102";
	
	//用户名或密码错误
	public static final String USER_NAMEORPWD_ERROR = "102_103";
	
	//验证码错误
	public static final String VCODE_ERROR = "102_104";
	
	//已收藏
	public static final String USER_COLLECTED="102_106";
	
	//未收藏
	public static final String USER_NO_COLLECTED="102_107";
	
	
	//请求超时
	public static final String REQUEST_TIMEOUT ="104_408";
	
	//对方不在线
	public static final String MSG_OFFLINE = "104_409";
	

}
