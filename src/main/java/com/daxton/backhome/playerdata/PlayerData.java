package com.daxton.backhome.playerdata;

import com.daxton.backhome.BackHome;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerData {
	//用來存放玩家的設定檔
	FileConfiguration config;
	//存放玩家
	Player player;
	//存放玩家的UUID
	UUID uuid;
	public PlayerData(Player player){
		//如果進來的參數跟物件的參數相同，就要加上this.代表物件的參數。
		this.player = player;
		uuid = player.getUniqueId();

		//在新建這物件時執行
		setConfig();
	}
	//建立設定檔和讀取設定檔
	public void setConfig(){
		//後面沒有.類型，就代表資料夾路徑
		File file1 = new File(BackHome.backHome.getDataFolder(), "/playerdata");
		//file1.exists()意思就是這個路徑有沒有這個資料夾，如果存在就會是有(true)
		//但我要在沒有的情況才建立這資料夾，所以我要在前面加個驚嘆號，來反轉結果 true變false，false變true
		//不存在(false)就變成true，就會執行裡面內容。
		if(!file1.exists()){
			//mkdir是建立資料夾，如果是多層資料夾可以用mkdirs，像是/playerdata/data1的情況。
			//mkdir只會在上一曾資料夾，存在的情況下才會建立。
			file1.mkdir();
		}
		File file2 = new File(BackHome.backHome.getDataFolder(), "/playerdata/"+uuid+".yml");
		if(!file2.exists()){
			try {
				//createNewFile()是建立文件用的，但建立文件會拋出個IOException例外，如果不懂，照做就好。
				//createNewFile()不會幫你建立上一層文件夾，所以要先有上一層文件夾才行。
				file2.createNewFile();
			}catch (IOException exception){
				exception.printStackTrace();
			}
		}

		//建立好文件後把檔案存放到物件的config裡面。
		config = YamlConfiguration.loadConfiguration(file2);

	}
	//儲存設定檔
	public void saveConfig(){
		File file2 = new File(BackHome.backHome.getDataFolder(), "/playerdata/"+uuid+".yml");
		try {
			config.save(file2);
		}catch (IOException exception){
			exception.printStackTrace();
		}
	}
	//儲存位置
	public void setHome(String key, Location location){
		config.set("home."+key, location);
	}
	//傳送到儲存位置
	public void home(String key){
		Location location = config.getLocation("home."+key);
		//要確定座標不是空值
		if(location != null){
			player.teleport(location);
		}
	}
	//儲存死亡位置
	public void setDeath(Location location){
		config.set("death", location);
	}
	//傳送到儲存的死亡位置
	public void toDeath(){
		Location location = config.getLocation("death");
		//要確定座標不是空值
		if(location != null){
			player.teleport(location);
		}
	}


}
