package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class rainDropsType {
	
	private Array<Integer> rainType;
	
	public rainDropsType() 
	{
		rainType = new Array<Integer>();
	}
	
	public void addGotaType(int type) 
	{
		rainType.add(type);
	}
	
	public int getSizeArray() 
	{
		return rainType.size;
	}
	
	public int getGotaType(int i) 
	{
		return rainType.get(i);
	}
	
	public void removerGotaType(int i) 
	{
		rainType.removeIndex(i);
	}

}
