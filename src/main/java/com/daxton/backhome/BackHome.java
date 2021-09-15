package com.daxton.backhome;

import com.daxton.backhome.announcement.SendMessage;
import com.daxton.backhome.command.MainCommand;
import com.daxton.backhome.command.TabCommand;
import com.daxton.backhome.config.CopyConfig;
import com.daxton.backhome.config.LoadConfig;
import com.daxton.backhome.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BackHome extends JavaPlugin {

	public static BackHome backHome;

	@Override
	public void onEnable() {

		backHome = this;

		this.getLogger().info("回家插件-1");
		backHome.getLogger().info("回家插件-2");

		Bukkit.getPluginCommand("backhome").setExecutor(new MainCommand());

		Bukkit.getPluginCommand("backhome").setTabCompleter(new TabCommand());

		//把做好的事件物件，註冊到監聽
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), backHome);

		CopyConfig.copy();

		LoadConfig.load();

		SendMessage.send();

	}

	@Override
	public void onDisable() {

	}
}
