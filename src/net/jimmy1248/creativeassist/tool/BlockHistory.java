package net.jimmy1248.creativeassist.tool;

import org.bukkit.block.Block;

public class BlockHistory {
	private Block[][][] blocks;
	void setBlock(int x,int y,int z,Block block){
		blocks[x][y][z] = block;
	}
	public Block getBlocks(int x,int y,int z) {
		return blocks[x][y][z];
	}
}
