package net.jimmy1248.creativeassist.tool;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.jimmy1248.creativeassist.CreativeAssist;

public class Tools {
	@SuppressWarnings("unused")
	private CreativeAssist plugin;
	private HashMap<Player, Tool> tools;
	public Tools(CreativeAssist plugin) {
		this.plugin = plugin;
		tools = new HashMap<Player, Tool>();
	}
	
	public void addTool(Player player) {
		tools.put(player, new Tool());
	}
	public void delTool(Player player){
		tools.remove(player);
	}
	@SuppressWarnings("deprecation")
	public void onLeftCkick(Player player){
		Tool tool = tools.get(player);
		if(!tools.containsKey(player)){
			addTool(player);
		}
		tool = tools.get(player);
		Location location = player.getTargetBlock(null, 20).getLocation();
		switch (tool.getMode()) {
		//wand
		case 0:
			ToolMode.wandLeftClick(tool, player, location);
			break;
		//replacer
		case 1:
			ToolMode.replacerLeftClick(tool, player, location);
		}
		
		
		
	}
	@SuppressWarnings("deprecation")
	public void onRightClick(Player player){
		Tool tool;
		Location location = player.getTargetBlock(null, 20).getLocation();
		if(!tools.containsKey(player)){
			addTool(player);
		}
		tool = tools.get(player);
		switch (tool.getMode()) {
		
		case 0:{ //wand
			ToolMode.wandRightClick(tool, player, location);
			break;
		}
		case 1:{ //replacer
			ToolMode.replacerRightClick(tool, player ,location);
			break;
		}
		case 2:{ //Super pickaxe
			ToolMode.spRightClick(tool,player);
			break;
		}
		}
	}
	public String onDrop(Player player){
		Tool tool;
		if(!tools.containsKey(player)){
			addTool(player);
		}
		tool = tools.get(player);
		tool.changeMode();
		tools.put(player, tool);
		return tool.getModeString();
	}
	public Tool getTool(Player player){
		return tools.get(player);
	}

}
