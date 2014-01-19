package net.jimmy1248.creativeassist.commandlisteners;

import net.jimmy1248.creativeassist.tool.Tools;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CAWand implements CommandExecutor{
	private Tools tools;

	public CAWand(Tools tools) {
		this.tools = tools;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		if(sender instanceof Player){
			Player player = (Player)sender;	
			tools.addTool(player);
			player.getInventory().setItem(0, new ItemStack(Material.GOLD_AXE));
			player.sendMessage("You have been given a wand.");
			
		}else sender.sendMessage("This command can only be run by a player.");
		return true;
	}

}
