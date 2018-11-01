package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Cartoon;

public class WangYiDetail {

	public static List<Cartoon> getCartoon(String url){
		List<Cartoon> cartoons = new ArrayList<>();
		String result = BaiTianSpider.newnewnewcraw(url);
		Document doc = Jsoup.parse(result);
		doc.setBaseUri(url);
		Elements imgs = doc.select(".img-box img");
		int i=1;
		for(Element img:imgs){
			Cartoon cartoon = new Cartoon();
			cartoon.setName(i+"");
			i++;
			cartoon.setUrl(img.absUrl("src"));
			cartoons.add(cartoon);
		}
		return cartoons;
	}

}
