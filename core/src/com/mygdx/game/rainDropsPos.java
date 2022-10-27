package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class rainDropsPos {
	
	private Array<Rectangle> rainDropsPos;
	
	public rainDropsPos() 
	{
		rainDropsPos = new Array<Rectangle>();
	}
	
	public void addGota(Rectangle raidDrop) 
	{
		rainDropsPos.add(raidDrop);
	}
	
	public int getSizeArray() 
	{
		return rainDropsPos.size;
	}
	
	public Rectangle getGota(int i) 
	{
		return rainDropsPos.get(i);
	}
	
	public void removerGota(int i) 
	{
		rainDropsPos.removeIndex(i);
	}

}
