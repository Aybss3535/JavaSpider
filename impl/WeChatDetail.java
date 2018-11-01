package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Cartoon;

public class WeChatDetail {

	public static List<Cartoon> getCartoon(String url){
		String result=WeChatSpider.craw(url);
		Document doc=Jsoup.parse(result);
		doc.setBaseUri(url);
		Elements imgs=doc.select("#js_content img");	
		List<Cartoon> cartoons = new ArrayList<Cartoon>();
		int i=1;
		for(Element img:imgs){
			Cartoon cartoon = new Cartoon();
			cartoon.setName(i+"");
			i=i+1;
			cartoon.setUrl(img.absUrl("data-src"));
			cartoons.add(cartoon);
		}
		return cartoons;
	}

}
