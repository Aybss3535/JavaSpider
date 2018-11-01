package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.entity.Announcement;

public class JwzxSpider {

	public static List<Announcement> getAllAnnouncement(String url){
		List<Announcement> anns = new ArrayList<Announcement>();
		String result=BaiTianSpider.newnewcraw(url);
		Document doc=Jsoup.parse(result);
		doc.setBaseUri(url);
		Elements trs=doc.select("#newsList tbody tr");
	    for(Element tr:trs){
	    	Announcement ann=new Announcement();
	    	Elements tds=tr.getElementsByTag("td");
	    	ann.setDate(tds.get(1).text());
	    	Element a = tds.first().getElementsByTag("a").first();
	    	ann.setUrl(a.absUrl("href"));
	    	ann.setTitle(a.text());
	    	anns.add(ann);
	    }
	    return anns;
	    
	}

}
