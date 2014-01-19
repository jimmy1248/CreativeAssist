package net.jimmy1248.creativeassist.tool;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ToolMode {
	
	
	public static void wandLeftClick(Tool tool,Player player,Location location){
		tool.setPos1(location);
		player.sendMessage("Pos1 Set.");
	}
	
	public static void wandRightClick(Tool tool,Player player,
			Location location){
		tool.setPos2(location);
		player.sendMessage("Pos2 Set.");
	}

	public static void replacerLeftClick(Tool tool, Player player,
			Location location) {
		Material material = location.getBlock().getType();
		tool.setBlock(material);
		player.sendMessage("Replacer set to " + material.name());	
	}

	public static void replacerRightClick(Tool tool, Player player,
			Location location) {
		ToolMode.setBlock(location, player, tool.getBlock(), null);
	}
		
		

	public static void spRightClick(Tool tool, Player player) {
		tool.changeSize();
		player.sendMessage("Super Pickaxe set to " + tool.getSize());
	}
	
	private static boolean setBlock(Location location, Player player ,
			Material material,Material mask){
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(location, true, null);
		if(claim == null){
			player.sendMessage("You can only use this tool in a claimed area.");
			return false;
		}
		String errormessage = claim.allowBuild(player);
		if(errormessage == null){	
			Block block = location.getBlock();
			if(mask == null || block.getType() == mask) 
				location.getBlock().setType(material);
		}
		else{
			player.sendMessage(errormessage);
			return false;
		}
		return true;
	}
	
	public static boolean checkPermissions(Tools tools, Player player, 
			Material material, Material mask) {
		int xStart,xEnd,yStart,yEnd,zStart,zEnd;
		int tmp1,tmp2;
		Tool tool = tools.getTool(player);
		//Check if the player has used the 'tool' on wand mode.
		if(tool == null || tool.getPos1() == null || tool.getPos2() == null){
			player.sendMessage("Possitions no set.");
			return true;
		}
	
		if((tmp1=tool.getPos1().getBlockX()) < 
				(tmp2=tool.getPos2().getBlockX())){
			xStart = tmp1;
			xEnd = tmp2;
		}else{
			xStart = tmp2;
			xEnd = tmp1;
		}
		if((tmp1=tool.getPos1().getBlockY()) < 
				(tmp2=tool.getPos2().getBlockY())){
			yStart = tmp1;
			yEnd = tmp2;
		}else{
			yStart = tmp2;
			yEnd = tmp1;
		}
		if((tmp1=tool.getPos1().getBlockZ()) < 
				(tmp2=tool.getPos2().getBlockZ())){
			zStart = tmp1;
			zEnd = tmp2;
		}else{
			zStart = tmp2;
			zEnd = tmp1;
		}
		
		if((xEnd-xStart)*(yEnd-yStart)*(zEnd-zStart)>50000){
			player.sendMessage("Your selection is more than 50000 blocks.");
			return true;
		}
		for(int x = xStart;x <= xEnd;x++){
			for(int y = yStart;y <= yEnd;y++){
				for(int z = zStart;z <= zEnd;z++){
					Location location = new Location(player.getWorld(), x, y, z);
					if(!ToolMode.setBlock(location, player, material, mask)) return true;
				}
			}
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public static Material getMaterial(String mat) {
		Material material;
		try{
			material = Material.getMaterial(Integer.parseInt(mat));
		}catch(NumberFormatException e){
			material = Material.getMaterial(mat.toUpperCase());			
		}
		return material;
	}
}

