package net.jimmy1248.creativeassist;

import net.jimmy1248.creativeassist.commandlisteners.CommandListeners;
import net.jimmy1248.creativeassist.eventlisteners.PlayerClick;
import net.jimmy1248.creativeassist.tool.Tools;

import org.bukkit.plugin.java.JavaPlugin;

public class CreativeAssist extends JavaPlugin{
	@Override
	public void onEnable() {
		Tools tools = new Tools(this);
		new PlayerClick(this,tools);
		new CommandListeners(this,tools);
		
	}
}
