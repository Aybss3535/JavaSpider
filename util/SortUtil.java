package com.tencent.util;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tencent.entity.Cartoon;
import com.tencent.entity.Chapter;

public class SortUtil {
	public static void Cartoon1234sort(List<Chapter> chapters){
		Collections.sort(chapters, new Comparator<Chapter>(){

			@Override
			public int compare(Chapter o1, Chapter o2) {
				String o1url = o1.getUrl();
				String o2url = o2.getUrl();
				String o1sub = o1url.substring(o1url.lastIndexOf("/")+1, o1url.indexOf(".html"));
				String o2sub = o2url.substring(o2url.lastIndexOf("/")+1, o2url.indexOf(".html"));
				int o1int = Integer.parseInt(o1sub);
				int o2int = Integer.parseInt(o2sub);
				return o1int<o2int?-1:(o1int==o1int?0:1);
			}
			
		});
	}
	
	public static void DongManSort(List<Chapter> chapters){
		Collections.sort(chapters, new Comparator<Chapter>(){

			@Override
			public int compare(Chapter arg0, Chapter arg1) {
				String arg0Num = arg0.getUrl().substring(arg0.getUrl().indexOf("episode_no=")+11);
				String arg1Num = arg1.getUrl().substring(arg1.getUrl().indexOf("episode_no=")+11);
				int arg0Int = Integer.parseInt(arg0Num);
				int arg1Int = Integer.parseInt(arg1Num);
				return arg0Int<arg1Int?-1:(arg0Int==arg1Int?0:1);
			}
			
		});
	}
	
	public static void ComicoolSort(List<Chapter> chapters){
		Collections.sort(chapters, new Comparator<Chapter>(){

			@Override
			public int compare(Chapter arg0, Chapter arg1) {
				String url0 = arg0.getUrl();
				String url1 = arg1.getUrl();
				int num0 = Integer.parseInt(url0.substring(url0.indexOf("ep_id")+6, url0.lastIndexOf("&")));
				int num1 = Integer.parseInt(url1.substring(url1.indexOf("ep_id")+6, url1.lastIndexOf("&")));
				return num0<num1?-1:(num0>num1?1:0);
			}
			
		});
	}
	
	public static void JpgSort(File[] files){
		Arrays.sort(files, new Comparator<File>(){

			@Override
			public int compare(File o1, File o2) {
					int o1int = Integer.parseInt(o1.getName().substring(0,o1.getName().indexOf(".")));
				int o2int = Integer.parseInt(o2.getName().substring(0,o2.getName().indexOf(".")));

				return o1int<o2int?-1:(o1int>o2int?1:0);
			}
			
		});
	}
	
	public static void DmzjSort(List<Chapter> chapters){
		Collections.sort(chapters, new Comparator<Chapter>() {

			@Override
			public int compare(Chapter o1, Chapter o2) {
				String o1title = o1.getTitle();
				String o2title = o2.getTitle();
				int o1int = Integer.parseInt(o1title.substring(o1title.indexOf("第")+1,o1title.indexOf("话")));
				int o2int = Integer.parseInt(o2title.substring(o2title.indexOf("第")+1,o2title.indexOf("话")));
				return o1int<o2int?-1:(o1int>o2int?1:0);
			}
		});
	}
}
