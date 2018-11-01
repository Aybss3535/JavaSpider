package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Chapter;

public class WangYiChapter {

	public static List<Chapter> getChapter(String url){
		List<Chapter> chapters = new ArrayList<>();
		String result = BaiTianSpider.newnewcraw(url);
		Document doc = Jsoup.parse(result);
		doc.setBaseUri(url);
		Elements as = doc.select(".m-chapter-item a");
		for(Element a:as){
			Chapter chapter = new Chapter();
			chapter.setTitle(a.attr("title"));
			chapter.setUrl(a.absUrl("href"));
			chapters.add(chapter);
		}
		return chapters;
	}
}
