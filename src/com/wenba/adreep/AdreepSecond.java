//package com.wenba.adreep;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class AdreepSecond {
//	private static String urlRegex2 = ".*?<a href='(.*?)'target='_blank'>";
//	private static String urlRegex1 =".*?<option value=\".*?page=(.*?)\">.*?</option>";
//
////	public static List<String> getAdreep(String DUrl){
////		String html = ReadHtml.httpRequest(DUrl);
////		List<String> resultList = htmlFiter(html);
////		return resultList;
////	}
////	// 过滤掉无用的信息 (可修改部分)
////	public static List<String> htmlFiter(String html){
////		List<String> list = new ArrayList<>();
////		// 查找目标
////		Pattern p = Pattern.compile(urlRegex);
////		Matcher m = p.matcher(html);
////		while (m.find()) {
////			list.add(m.group(1));
////		}
////		return list;
////	}
//	/**
//	 * 测试
//	 * @param args
//	 * @throws UnsupportedEncodingException 
//	 */
//	public static void main(String[] args){
//		String url_t = "http://www.adreep.cn/xx/?page=1";
//		String url_f = "http://www.adreep.cn/xx/?page=";
//		
//		List<String> info_first = AdreepFirst.getAdreep(url_t,urlRegex1);//得到的是不同的页数，但是没有1
//		info_first.add("1");
//		
//		for (String url1 : info_first) {
//			String url = url_f + url1;   //此时抓到的是小学对应所有页面
//			List<String> info = new ArrayList<>();
//			info=AdreepFirst.getAdreep(url,urlRegex2);   //得到每个文章的链接
//			
//			System.out.println(info);
//		}
//	}
//}
