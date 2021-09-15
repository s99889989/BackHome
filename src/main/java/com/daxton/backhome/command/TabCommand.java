package com.daxton.backhome.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCommand implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
		//這是一個字的列表，可以塞進很多單字
		List<String> commandList = new ArrayList<>();
		//判斷args的數量，如果長度是1就執行下面的動作。
		if(args.length == 1){
			//把一個單字加進回傳列表
			commandList.add("test");
			commandList.add("reload");
			commandList.add("sethome");
			commandList.add("home");
			commandList.add("back");
		}
		//回傳單字列表
		return commandList;
	}

}
