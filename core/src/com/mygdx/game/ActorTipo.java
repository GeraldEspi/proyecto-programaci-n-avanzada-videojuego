package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class ActorTipo {
	
	private Array<Integer> rainType;
	
	public ActorTipo() 
	{
		rainType = new Array<Integer>();
	}
	
	public void addActorTipo(int type) 
	{
		rainType.add(type);
	}
	
	public int getSizeArray() 
	{
		return rainType.size;
	}
	
	public int getActorTipo(int i) 
	{
		return rainType.get(i);
	}
	
	public void removeActorTipo(int i) 
	{
		rainType.removeIndex(i);
	}

}
