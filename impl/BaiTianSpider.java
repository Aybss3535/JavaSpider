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
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);//�½�һ��ģ��ȸ�Chrome�������������ͻ��˶���

        webClient.getOptions().setThrowExceptionOnScriptError(false);//��JSִ�г����ʱ���Ƿ��׳��쳣, ����ѡ����Ҫ
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//��HTTP��״̬��200ʱ�Ƿ��׳��쳣, ����ѡ����Ҫ
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//�Ƿ�����CSS, ��Ϊ����Ҫչ��ҳ��, ���Բ���Ҫ����
        webClient.getOptions().setJavaScriptEnabled(true); //����Ҫ������JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//����Ҫ������֧��AJAX
        HtmlPage page = null;
        try {
            page = webClient.getPage(url);//���Լ�������ͼƬ���Ӹ�������ҳ
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            webClient.close();
        }

        webClient.waitForBackgroundJavaScript(30000);//�첽JSִ����Ҫ��ʱ,���������߳�Ҫ����30��,�ȴ��첽JSִ�н���

        String pageXml = page.asXml();//ֱ�ӽ�������ɵ�ҳ��ת����xml��ʽ���ַ���
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
