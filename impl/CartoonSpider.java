package com.tencent.impl;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.tencent.entity.Cartoon;

public class CartoonSpider {

	public static List<Cartoon> getCartoon(String url){
		PhantomJSDriver driver = new PhantomJSDriver();
//		WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();   	
        JavascriptExecutor js = (JavascriptExecutor)driver;
        for(int i=1;i<280;i++){
        	js.executeScript("scrollTo(0,"+500*i+")");	
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        String result=driver.getPageSource();
        driver.close();
//        System.out.println(result);
        Document doc=Jsoup.parse(result);
        doc.setBaseUri(url);
        Elements lis=doc.select("#mainView li");
        List<Cartoon> cartoons = new ArrayList<>();
        int i=1;
        for(Element li:lis){
        	Elements imgs=li.getElementsByTag("img");
        	for(Element img:imgs){
        		if((!img.absUrl("src").contains("operation"))&&img.absUrl("src")!=""){
        			Cartoon cartoon = new Cartoon();
        		cartoon.setName(i+"");
        		i=i+1;
        		cartoon.setUrl(img.absUrl("src"));
        		cartoons.add(cartoon);
        		}
        		
        	}
        }
		return cartoons;
	}
}
