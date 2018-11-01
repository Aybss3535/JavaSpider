package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Chapter;

public class KyManHuaChapter {

	public static List<Chapter> getChapter(String url){
		List<Chapter> chapters = new ArrayList<Chapter>();
		String result = BaiTianSpider.newnewcraw(url);
		Document doc = Jsoup.parse(result);
		Elements as = doc.select(".flag a");
//		System.out.println(as);
		for(Element a:as){
			Chapter chapter = new Chapter();
			String onclick = a.attr("onclick");
			String chapterid = onclick.substring(onclick.indexOf("differ")+8,onclick.indexOf(",")-1);
			String autoCode = onclick.substring(onclick.indexOf(",")+2,onclick.lastIndexOf("'"));
			chapter.setUrl("http://m.kymanhua.com/web/chapter_info.jsp?chapterId="+chapterid+"&autoCode="+autoCode);
			chapter.setTitle(a.getElementsByTag("div").first().text());
			chapters.add(chapter);
		}
		return chapters;
	}
}
