package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Cartoon;

public class Cartoon1234Detail {

	public static List<Cartoon> getDetail(String url){
		int lastpage=0;
		List<Cartoon> cartoons = new ArrayList<Cartoon>();
		String result = Cartoon1234Spider.newnewcraw(url);
		Document doc=Jsoup.parse(result);
		Elements options=doc.select("#qTcms_select_i2 option");
		Element option=options.last();
		lastpage=Integer.parseInt(option.attr("value"));
		Elements imgs=doc.select("#qTcms_Pic_middle img");
		Cartoon cartoon = new Cartoon();
		cartoon.setName("1");
		cartoon.setUrl(imgs.last().absUrl("src"));
		cartoons.add(cartoon);
		for(int i=0;i<lastpage-1;i++){
			System.out.println("µ±Ç°Ò³Êý"+(i+2));
			String result1=Cartoon1234Spider.newnewcraw(url+"?p="+(i+2));
			Document doc1=Jsoup.parse(result1);
			Elements img1=doc1.select("#qTcms_Pic_middle img");
			Cartoon cartoon1 = new Cartoon();
			cartoon1.setName((i+2)+"");
			cartoon1.setUrl(img1.last().absUrl("src"));
			cartoons.add(cartoon1);
		}
		return cartoons;
	}

}
