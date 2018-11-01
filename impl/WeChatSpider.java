package com.tencent.impl;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WeChatSpider {

	public static String craw(String url){
		
//		PhantomJSDriver driver = new PhantomJSDriver();
		WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();   	
        JavascriptExecutor js = (JavascriptExecutor)driver;
        for(int i=1;i<260;i++){
        	js.executeScript("scrollTo(0,"+700*i+")");	
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        String result=driver.getPageSource();
        driver.close();
        return result;
        
	}
}
