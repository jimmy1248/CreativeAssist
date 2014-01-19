package net.jimmy1248.creativeassist.tool;

import java.util.ArrayDeque;
import java.util.Deque;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Tool {
	private Material block;
	private Location pos1,pos2;
	private int mode,size;
	private Deque<BlockHistory> history;
	private static String[] modes = {"Wand","Replacer","Super Pickaxe"};
	public Tool() {
		this.mode = 0;
		this.size = 1;
		history = new ArrayDeque<BlockHistory>();
	}
	
	public void changeMode() {
		this.mode++;
		if(this.mode == modes.length) this.mode = 0;
	}
	public int getMode() {
		return mode;
	}
	public String getModeString() {
		return modes[mode];
		
	}
	public void setBlock(Material block) {
		this.block = block;
	}
	public Material getBlock() {
		return block;
	}
	public void setPos1(Location pos1) {
		this.pos1 = pos1;
	}
	public Location getPos1() {
		return pos1;
	}
	public void setPos2(Location pos2) {
		this.pos2 = pos2;
	}
	public Location getPos2() {
		return pos2;
	}
	void addToBlockHistory(Block[][][] blocks){
		history.push(new BlockHistory());;
	}

	public void changeSize() {
		size ++;
		if(size == 6)size = 1;
	}

	public int getSize() {
		return size;
	}
}
