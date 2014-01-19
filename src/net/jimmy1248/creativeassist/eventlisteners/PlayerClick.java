package net.jimmy1248.creativeassist.eventlisteners;

import net.jimmy1248.creativeassist.CreativeAssist;
import net.jimmy1248.creativeassist.tool.Tools;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;


public class PlayerClick implements Listener{

	@SuppressWarnings("unused")
	private CreativeAssist plugin;
	private Tools tools;
	
	public PlayerClick(CreativeAssist plugin, Tools tools) {
		this.plugin = plugin;
		this.tools = tools;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	void playerClickEvent(PlayerInteractEvent event){
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		if(item == null) return;
		if(item.getType() == Material.GOLD_AXE && 
				player.getInventory().getHeldItemSlot() == 0){
			if(event.getAction() == Action.LEFT_CLICK_AIR ||
					event.getAction() == Action.LEFT_CLICK_BLOCK){
				tools.onLeftCkick(player);
				event.setCancelled(true);
			}else if(event.getAction() == Action.RIGHT_CLICK_AIR ||
					event.getAction() == Action.RIGHT_CLICK_BLOCK){
				tools.onRightClick(player);
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	void onPlayerDropItemEvent(PlayerDropItemEvent event){
		Player player = event.getPlayer();
		if(event.getItemDrop().getItemStack().getType() == Material.GOLD_AXE && 
				player.getInventory().getHeldItemSlot() == 0){
			player.sendMessage(tools.onDrop(player));
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpenEvent(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        System.out.println("InventoryOpenEvent from player: " + player.getName());
    }

	@EventHandler
	void onPlayerJoin(PlayerJoinEvent event){
		tools.addTool(event.getPlayer());
	}

}
