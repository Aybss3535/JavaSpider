package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Chapter;

public class Cartoon1234Chapter {

	public static List<Chapter> getChapter(String url){
		List<Chapter> chapters = new ArrayList<Chapter>();
		String result = Cartoon1234Spider.craw(url);
		Document doc=Jsoup.parse(result);
		doc.setBaseUri(url);
		Elements lis=doc.select("#play_0 li a");
		for(Element li:lis){
			Chapter chapter = new Chapter();
			chapter.setTitle(li.attr("title"));
			chapter.setUrl(li.absUrl("href"));
			chapters.add(chapter);
		}
		return chapters;
	}

}
