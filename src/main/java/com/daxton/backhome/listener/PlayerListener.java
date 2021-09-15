package com.daxton.backhome.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
//監聽最基本的要加上implements Listener
//因為要在主類註冊監聽這個物件，類似USB有很多廠商在做，但必須符合基本的USB規格，電腦腦才能使用。
//implements Listener的意思就是，PlayerListener是符合Listener規格的，因為監聽只能監聽符合Listener規格的物件。
public class PlayerListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event){

	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event){

	}

	@EventHandler
	public void onDeath(EntityDeathEvent event){

	}

}
