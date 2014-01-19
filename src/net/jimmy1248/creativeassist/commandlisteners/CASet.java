package net.jimmy1248.creativeassist.commandlisteners;

import net.jimmy1248.creativeassist.tool.ToolMode;
import net.jimmy1248.creativeassist.tool.Tools;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CASet implements CommandExecutor {

	private Tools tools;

	public CASet(Tools tools) {
		this.tools = tools;
	}

	@SuppressWarnings({ })
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		Material material, mask = null;
		Player player = (Player)sender;
		switch (args.length){
		case 2:{
			mask = ToolMode.getMaterial(args[1]);
			if(mask == null){
				player.sendMessage("Invalid material.");
				return true;
			}
		}
		case 1:{
			material = ToolMode.getMaterial(args[0]);
			if(material == null){
				player.sendMessage("Invalid material.");
				return true;
			}
			return ToolMode.checkPermissions(tools, player, material, mask);
		}
		}
		return true;
	}

}
