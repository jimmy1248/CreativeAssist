package net.jimmy1248.creativeassist.commandlisteners;

import net.jimmy1248.creativeassist.CreativeAssist;
import net.jimmy1248.creativeassist.tool.Tools;

public class CommandListeners {
	@SuppressWarnings("unused")
	private CreativeAssist plugin;

	public CommandListeners(CreativeAssist plugin, Tools tools) {
		this.plugin = plugin;
		
		plugin.getCommand("cawand").setExecutor(new CAWand(tools));
		plugin.getCommand("caset").setExecutor(new CASet(tools));
	}
}
