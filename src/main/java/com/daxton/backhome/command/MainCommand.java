package com.daxton.backhome.command;

import com.daxton.backhome.BackHome;
import com.daxton.backhome.config.LoadConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		//先檢查args的長度，如果沒有檢查你第一個args[0]如果是空的就會錯誤
		if(args.length == 1){
			if(args[0].equals("reload")){
				LoadConfig.load();
				BackHome.backHome.getLogger().info("重新讀取設定");
				if(sender instanceof Player){
					Player player = (Player) sender;
					player.sendMessage("重新讀取設定");
				}
			}
			//檢查第一個值是test
			if(args[0].equals("test")){
				String testMessage = "測試訊息";
				//就跟BackHome裡面寫的在後台發訊息一樣，但其他地方要使用就要在開頭加上BackHome
				BackHome.backHome.getLogger().info(testMessage);
				//這邊是檢查如果執行指令的是玩家
				if(sender instanceof Player){
					//要把sender換成玩家，才能使用玩家的一些功能
					Player player = (Player) sender;
					//向執行指令的玩家發送測試訊息
					player.sendMessage(testMessage);
				}
			}
		}
		return false;
	}

}
