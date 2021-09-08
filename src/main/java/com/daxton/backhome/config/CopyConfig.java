package com.daxton.backhome.config;

import com.daxton.backhome.BackHome;

public class CopyConfig {
	//只要這邊加上static就可以直接使用，不用在CopyConfig copyConfig = new CopyConfig()，在使用copyConfig.copy();
	public static void copy(){
		//這個方法是Bukkit內建的複製資源文件方法
		//他會把resources的內的.yml資源，複製到plugins/插件名稱/底下
		//後面的replace，就是問你要不要蓋過舊有的檔案
		// 通常是設false，不然每次重開都會把檔案蓋過去，之前的設定就白設了。
		BackHome.backHome.saveResource("announcement.yml", false);
	}
}
