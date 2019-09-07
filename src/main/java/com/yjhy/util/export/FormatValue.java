package com.yjhy.util.export;

import java.text.SimpleDateFormat;

public class FormatValue {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String format(String type,Object obj){
		try{
			if(obj==null){
				return "";
			}
			if("str".equals(type)){
				return obj.toString();
			}else if("guimo".equals(type)){
				return "yiban".equals(obj)?"一般规模":"小规模";
			}else if("sb".equals(type)){                          //申报
				return "yue".equals(obj)?"月报":"季报";
			}else if("shui".equals(type)){                          //地税、国税、金税盘
				String str = "";
				if(obj.toString().indexOf("ds")!=-1){
					str+="地税钥匙/";
				}
                if(obj.toString().indexOf("gs")!=-1){
					str+="国税钥匙/";
				}
                if(obj.toString().contains("js")){
					str+="金税盘/";
				}
				if(!"".equals(str)&&str.lastIndexOf("/")==str.length()-1){
					str = str.substring(0,str.lastIndexOf("/"));
					
				}
				return str;
			}else if("yes".equals(type)){
				return "1".equals(obj)?"是":"否";
			}else if("time".equals(type)){
				return sdf.format(obj);
			}else{
				return obj.toString();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String str = FormatValue.format("shui", ",gs,ds");
		System.out.println(str);
	}

}
