package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Chapter;

public class ComicoolChapter {

	public static List<Chapter> getChapter(String url){
		String result=BaiTianSpider.newcraw(url);
		Document doc=Jsoup.parse(result);
		doc.setBaseUri(url);
		Elements as=doc.select(".detail-list a");
		List<Chapter> chapters = new ArrayList<Chapter>();
		for(Element a:as){
			Chapter chapter = new Chapter();
			chapter.setUrl(a.absUrl("href"));
			chapter.setTitle(a.getElementsByTag("p").first().text());
			chapters.add(chapter);
			
		}
		return chapters;
	}

}
