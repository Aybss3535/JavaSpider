package com.tencent.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CartoonDownload {

	public static void download(String url,String path){
		byte[] byimg = getImageFormNet(url);
		if(byimg!=null&&byimg.length>0){
			try {
				writeImgToDisk(byimg,path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static byte[] getImageFormNet(String strurl){
//		String cookie="uuid=72564C645A17492FB85BD85927812C6D; Hm_lvt_49d6c8b588d462203d2980ce4d84a366=1533100469,1533208032,1533355375,1533366473; Hm_lpvt_49d6c8b588d462203d2980ce4d84a366=1533374214; rw=w_629_32%2Cw_296_12";
		try {
			URL url =new URL(strurl);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
//			conn.setRequestProperty("Cookie", cookie);
			conn.setRequestProperty("accept", "image/webp,image/apng,image/*,*/*;q=0.8");
			conn.setRequestProperty("accept-encoding", "gzip, deflate, br");
			conn.setRequestProperty("accept-language", "en-US,en;q=0.8,la;q=0.6");
			conn.setRequestProperty("referer", strurl);
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
			byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
			return btImg;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] readInputStream(InputStream inStream) throws IOException{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while( (len=inStream.read(buffer)) != -1 ){
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
	public static void writeImgToDisk(byte[] Byimg,String path) throws Exception{
		File file = new File(path);
		FileOutputStream fops = new FileOutputStream(file);
		fops.write(Byimg);
		fops.flush();
		fops.close();
		System.out.println("已下载图片至"+path);
	}
	

}
