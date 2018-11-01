package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Cartoon;

public class BaiTianDetail extends BaiTianSpider {

	public static List<Cartoon> getDetail(String url){
		String result=BaiTianSpider.newnewnewcraw(url);
		Document doc=Jsoup.parse(result);
		doc.setBaseUri(url);
		Elements imgs=doc.select("#_imageList img");
		if(imgs!=null){
			List<Cartoon> cartoons = new ArrayList<>(); 
			int i=1;
			for(Element img:imgs){
				Cartoon cartoon = new Cartoon();
				cartoon.setName(i+"");
				i=i+1;
				cartoon.setUrl(img.absUrl("src"));
				cartoons.add(cartoon);
			}
			return cartoons;
		}else{
			return null;
		}
	}

}
