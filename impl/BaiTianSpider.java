package com.tencent.impl;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BaiTianSpider {

	public static String craw(String url){
		String result=null;
		try(
				CloseableHttpClient httpclient=HttpClientBuilder.create().build();
	            CloseableHttpResponse response= httpclient.execute(new HttpGet(url));
				){
			HttpEntity entity=response.getEntity();
			result=EntityUtils.toString(entity);
		}catch(Exception e){
			
		}
		return result;
	}
	public static String newcraw(String url){
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);//新建一个模拟谷歌Chrome浏览器的浏览器客户端对象

        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        HtmlPage page = null;
        try {
            page = webClient.getPage(url);//尝试加载上面图片例子给出的网页
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            webClient.close();
        }

        webClient.waitForBackgroundJavaScript(30000);//异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束

        String pageXml = page.asXml();//直接将加载完成的页面转换成xml格式的字符串
		return pageXml;
		
	}
	
	public static String newnewcraw(String url){
		PhantomJSDriver driver = new PhantomJSDriver();
//		WebDriver driver = new ChromeDriver();
        driver.get(url);
        String result=driver.getPageSource();
        driver.close();
		return result;
	}
	public static String newnewnewcraw(String url){
//		PhantomJSDriver driver = new PhantomJSDriver();
		WebDriver driver = new ChromeDriver();
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        for(int i=1;i<100;i++){
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
