package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.tencent.entity.Cartoon;

public class DmzjCartoon {

	public static List<Cartoon> getCartoon(String url){
		List<Cartoon> cartoons = new ArrayList<Cartoon>();
		String newUrl = url+"#@page=";
		String result1=BaiTianSpider.newcraw(newUrl+"1");
		Document doc1 = Jsoup.parse(result1);
		Elements img1 = doc1.select(".comic_wraCon img");
		Cartoon cartoon1 = new Cartoon();
		cartoon1.setName("1");
		cartoon1.setUrl(img1.first().absUrl("src"));
		cartoons.add(cartoon1);
		Elements options = doc1.select("#page_select option");
		String page = options.last().text();
		int n = Integer.parseInt(page.substring(page.indexOf("第")+1, page.indexOf("页")));
		for(int i=2;i<=n;i++){
			System.out.println("当前页面"+i);
			String result = BaiTianSpider.newcraw(newUrl+i);
			Document doc=Jsoup.parse(result);
			Elements img = doc.select(".comic_wraCon img");
			Cartoon cartoon = new Cartoon();
			cartoon.setName(i+"");
			cartoon.setUrl(img.first().absUrl("src"));
			cartoons.add(cartoon);
		}
		return cartoons;
	}

}
