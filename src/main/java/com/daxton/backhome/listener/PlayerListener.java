package com.daxton.backhome.listener;

import com.daxton.backhome.manager.PlayerManger;
import com.daxton.backhome.playerdata.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

//監聽最基本的要加上implements Listener
//因為要在主類註冊監聽這個物件，類似USB有很多廠商在做，但必須符合基本的USB規格，電腦腦才能使用。
//implements Listener的意思就是，PlayerListener是符合Listener規格的，因為監聽只能監聽符合Listener規格的物件。
public class PlayerListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		//如果Map裡面找不到PlayerData
		if(!PlayerManger.playerDataMap.containsKey(uuid)){
			//建立一個新的PlayerData
			PlayerData playerData = new PlayerData(player);
			//把新建的PlayerData放到Mpa裡面
			//uuid是找到PlayerData的關鍵字，所以只有要辦法獲得玩家的uuid就能找到該PlayerData;
			PlayerManger.playerDataMap.put(uuid, playerData);
		}

	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		//從Map中獲取PlayerData
		PlayerData playerData = PlayerManger.playerDataMap.get(uuid);
		//在執行PlayerData裡面的儲存設定檔方法
		playerData.saveConfig();
		//在清除Map裡面的PlayerData方法，反正玩家不在，放著也沒用，只換占用記憶體。
		PlayerManger.playerDataMap.remove(uuid);
	}

	@EventHandler
	public void onDeath(EntityDeathEvent event){
		//獲取死亡的實體
		Entity entity = event.getEntity();
		//判斷死亡的實體是否是玩家
		if(entity instanceof Player){
			//把實體轉換成玩家
			Player player = (Player) entity;
			UUID uuid = player.getUniqueId();
			//從Map中獲取PlayerData
			PlayerData playerData = PlayerManger.playerDataMap.get(uuid);
			//獲取當時玩家的位置
			Location location = player.getLocation();
			//儲存到設定檔
			playerData.setDeath(location);

		}

	}

}
