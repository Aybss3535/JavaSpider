package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Cartoon;

public class ComicoolDetail {

	public static List<Cartoon> getDetail(String url){
		int i=1;
		List<Cartoon> cartoons = new ArrayList<Cartoon>();
		String result=BaiTianSpider.newcraw(url);
		Document doc=Jsoup.parse(result);
		doc.setBaseUri(url);
		Elements imgs=doc.select(".read-area img");
		for(Element img:imgs){
			Cartoon cartoon = new Cartoon();
			cartoon.setName(i+"");
			i=i+1;
			cartoon.setUrl(img.absUrl("src"));
			cartoons.add(cartoon);
		}
		return cartoons;
	}

}
