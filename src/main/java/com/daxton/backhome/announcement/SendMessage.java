package com.daxton.backhome.announcement;

import com.daxton.backhome.BackHome;
import com.daxton.backhome.config.LoadConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.io.File;
import java.util.List;
import java.util.Random;

public class SendMessage {

	public static void send(){
		//BackHome.backHome.getDataFolder() 等於 plugins/BackHome
		//要獲取一個文件你必須要先獲取他的位置。
		File file = new File(BackHome.backHome.getDataFolder(), "/announcement.yml");
		File file2 = new File("plugins/BackHome/announcement.yml");

		//再來用Bukkit的方法來打開這文件，就像你打開文字檔案可以用很多不同類型的編輯器一樣意思。
		//Bukkit有他專門的編輯器來處來.yml檔案。
		//編輯器名稱是FileConfiguration
		//後面的YamlConfiguration.loadConfiguration(file);   類似滑鼠左鍵選擇文件打開
		FileConfiguration aConfig = YamlConfiguration.loadConfiguration(file);
		//可以從文件獲取各種類型的資料
		int time = aConfig.getInt("announcement.long");

		boolean random = aConfig.getBoolean("announcement.random");

		String test = aConfig.getString("announcement.Test");

		List<String> aList = aConfig.getStringList("announcement.List");

		//這是定時執行程序的功能
		BukkitRunnable bukkitRunnable = new BukkitRunnable() {
			//這是用來輪流發送訊息用的
			int order = 0;
			@Override
			public void run() {
				//Bukkit.getOnlinePlayers()是獲取線上全部玩家的方法
				//因為你要發訊息給全部玩家
				for(Player player : Bukkit.getOnlinePlayers()){
					player.sendMessage(LoadConfig.aList.get(order));
				}
				BackHome.backHome.getLogger().info(LoadConfig.aList.get(order));
				//如果random是true就執行下面動作，如果是false就執行else的動作
				if(LoadConfig.random){
					int aSize = LoadConfig.aList.size();
					//會產生0~公告數量-1的隨機數
					order = (int)(Math.random()*aSize);
				}else {
					//每次執行就把位置+1
					order++;
					//如果大於或等於你的公告數量就從公告0開始
					if(order >= LoadConfig.aList.size()){
						order = 0;
					}
				}
			}
		};
		//delay是執行要延遲多就後執行
		//period是執行後要再多久後執行
		//時間要*20是因為，這邊是以tick為標準，20tick=1秒
		bukkitRunnable.runTaskTimer(BackHome.backHome, 0, LoadConfig.time* 20L);

	}


}
