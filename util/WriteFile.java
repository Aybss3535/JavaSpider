package com.tencent.util;

import java.io.File;
import java.io.FileOutputStream;


public class WriteFile {

	public static void WriteUsername(){
		File file = new File("D:\\username.txt");
		byte[] data= new byte[3];
		try{
			if(!file.exists()){
			file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			for(int  i=65;i<91;i++){
				for(int j=65;j<91;j++){
					for(int k=65;k<91;k++){
						data[0]=(byte)i;
						data[1]=(byte)j;
						data[2]=(byte)k;
						fos.write(data);
						fos.write("\r\n".getBytes());
					}
				}
			}
			fos.close();
			System.out.println("ÊäÈë³É¹¦");
		}catch(Exception e){
			
		}
		
	}

}
