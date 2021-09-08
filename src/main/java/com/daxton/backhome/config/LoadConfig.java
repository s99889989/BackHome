package com.daxton.backhome.config;

import com.daxton.backhome.BackHome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.List;

public class LoadConfig {

	//把每個發送公告的值在這邊建立為公開(public)靜態的(static)。
	//這樣可以讓所有的物件來使用這邊的值
	public static int time;
	public static boolean random;
	public static String test;
	public static List<String> aList;

	public static void load(){
		//在把之前在SendMessage物件裡面的讀取檔案方法複製到這邊
		File file = new File(BackHome.backHome.getDataFolder(), "/announcement.yml");
		FileConfiguration aConfig = YamlConfiguration.loadConfiguration(file);

		//把儲存位置改到存到上面靜態的值
		time = aConfig.getInt("announcement.long");

		random = aConfig.getBoolean("announcement.random");

		test = aConfig.getString("announcement.Test");

		aList = aConfig.getStringList("announcement.List");
	}

}
