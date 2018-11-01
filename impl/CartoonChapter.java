package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Chapter;

public class CartoonChapter {

	public static List<Chapter> getChapter(String url){
		String result = BaiTianSpider.craw(url);
		Document doc=Jsoup.parse(result);
		doc.setBaseUri(url);
		Elements lis=doc.select("#chapter li");
		List<Chapter> chapters = new ArrayList<>();
		for(Element li:lis){
			Elements ps=li.getElementsByTag("p");
			for(Element p:ps){
				Elements spans=p.getElementsByTag("span");
				for(Element span:spans){
					Elements as=span.getElementsByTag("a");
					for(Element a:as){
						Chapter chapter= new Chapter();
						chapter.setTitle(a.attr("title"));
						chapter.setUrl(a.absUrl("href"));
						chapters.add(chapter);
					}
				}
			}
		}
		return chapters;
	}

}
