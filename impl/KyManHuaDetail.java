package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Cartoon;

public class KyManHuaDetail {

	public static List<Cartoon> getChapter(String url){
		List<Cartoon> cartoons = new ArrayList<>();
		String result = BaiTianSpider.newnewcraw(url);
		Document doc = Jsoup.parse(result);
		Elements imgs = doc.select(".imgBox img");
		int i=1;
		for(Element img:imgs){
			Cartoon cartoon = new Cartoon();
			cartoon.setName(i+"");
			i=i+1;
			cartoon.setUrl(img.attr("src").replace("&amp;", "&"));
			cartoons.add(cartoon);
		}
		return cartoons;
	}

}
