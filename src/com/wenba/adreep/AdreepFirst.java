package com.wenba.adreep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdreepFirst {
		public static List<String> getAdreep(String DUrl,String urlreggex) {
			String html = ReadHtml.httpRequest(DUrl);
			List<String> resultList = htmlFiter(html,urlreggex);
			return resultList;
		}
		public static List<String> htmlFiter(String html,String urlRegex) {
			List<String> list = new ArrayList<String>();
			// 查找目标
			Pattern p = Pattern.compile(urlRegex);
			Matcher m = p.matcher(html);
			while (m.find()) {
				list.add(m.group(1));
			}
			return list;
		}  
}
