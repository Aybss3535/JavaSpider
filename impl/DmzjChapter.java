package com.tencent.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Chapter;

public class DmzjChapter {

	public static List<Chapter> getChapter(String url){
		List<Chapter> chapters = new ArrayList<>();
		String result=BaiTianSpider.craw(url);
		Document doc=Jsoup.parse(result);
		Elements as=doc.select(".list_con_li li a");
		doc.setBaseUri(url);
		for(Element a:as){
			Chapter chapter = new Chapter();
			chapter.setUrl(a.absUrl("href"));
			Elements spans = a.getElementsByTag("span");
			chapter.setTitle(spans.get(1).text());
			chapters.add(chapter);
		}
		chapters = new ArrayList<>(new HashSet<Chapter>(chapters));
		return chapters;
	}

}
