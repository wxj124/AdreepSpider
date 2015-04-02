package com.wenba.adreep;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdreepTest {
	private static String urlRegex_rank = "<li><a href='(.*?)/'>";    //得到/xx /zx的结果

	private static String urlRegex_se = "\\s<a href='(.*?)'target='_blank'>.*?</a>";  //得到每个页面下的文章的链接
	private static String urlRegex_f = ".*?<option value=\".*?page=(.*?)\">.*?</option>";//得到页面数字  

	private static String urlRegex1 = ".*?<h1>(.*?)</h1>";
	private static String urlRegex2 = ".*?<span>作者:(.*?)</span>";
	private static String urlRegex3 = ".*?<div id=\"c500\" class=\"content-ad\"></div>\\s*(.*?)<br />\\s*(.*?)<br />";

	public static List<String> getAdreep(String DUrl)
			throws UnsupportedEncodingException {
		String html = ReadHtml.httpRequest(DUrl);
		List<String> resultList = htmlFiter(html, DUrl);
		return resultList;
	}

	// 过滤掉无用的信息 (可修改部分)
	public static List<String> htmlFiter(String html, String durl)
			throws UnsupportedEncodingException {
		List<String> list = new ArrayList<>();
		// 查找目标
		Pattern p = Pattern.compile(urlRegex1);
		Pattern p2 = Pattern.compile(urlRegex2);
		Pattern p3 = Pattern.compile(urlRegex3);
		Matcher m = p.matcher(html);
		Matcher m2 = p2.matcher(html);
		Matcher m3 = p3.matcher(html);
		while (m.find() && m2.find() && m3.find()) {
			String content = "";
			content = m3.group(1);
			content += m3.group(2);
			AdreepConnection.insert(m.group(1), m2.group(1), content, durl);
		}
		return list;
	}
	/**
	 * 测试
	 * 
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String Url = "http://www.adreep.cn";
		String url_t1 = "/?page=1";
		String url_t2 = "/?page=";

		List<String> rank = AdreepFirst.getAdreep(Url, urlRegex_rank);
		for (String url_t3 : rank) {
			String url_t = Url + url_t3 + url_t1; // www.adreep.cn/xx/?page=1
			String url_f = Url + url_t3 + url_t2; // www.adreep.cn/xx/?page=

			List<String> info_first = AdreepFirst.getAdreep(url_t, urlRegex_f);// 得到的是不同的页数，但是没有1
			info_first.add("1");

			for (String url1 : info_first) {
				String url = url_f + url1; // 此时抓到的是不同年级对应所有页面page数字，如小学
				System.out.println(url);

				List<String> ever = AdreepFirst.getAdreep(url, urlRegex_se); // 得到每个文章的链接
				// System.out.println(ever);

				for (String url2 : ever) {
					String URL = Url + url2;
					System.out.println(URL);
					List<String> result = getAdreep(URL); // 解析每一个文章得到数据
					// System.out.println(result);
				}
			}

		}

		// String url_t = "http://www.adreep.cn/xx/?page=1";
		// String url_f = "http://www.adreep.cn/xx/?page=";
		// String Url = "http://www.adreep.cn";

	}
}
