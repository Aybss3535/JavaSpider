package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Chapter;

public class DongManChapter {

	public static List<Chapter> getChapter(String url,int pageNum){
		List<Chapter> chapters = new ArrayList<Chapter>();
		for(int i=0;i<pageNum;i++){
			String result = BaiTianSpider.craw(url+(i+1));
			Document doc=Jsoup.parse(result);
			doc.setBaseUri(url);
			Elements as=doc.select("#_listUl li a");
			for(Element a:as){
			Chapter chapter = new Chapter();
			chapter.setUrl(a.absUrl("href"));
			Elements spans=a.getElementsByClass("thmb");
			for(Element span:spans){
			Elements imgs=span.getElementsByTag("img");
			for(Element img:imgs){
				chapter.setTitle(img.attr("alt"));
				chapters.add(chapter);
			}
			}
			}
		}
		return chapters;
	}

}
