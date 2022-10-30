package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class ActorTipo {
	
	private Array<Integer> actorType;
	
	public ActorTipo() 
	{
		actorType = new Array<Integer>();
	}
	
	public void addActorTipo(int type) 
	{
		actorType.add(type);
	}
	
	public int getSizeArray() 
	{
		return actorType.size;
	}
	
	public int getActorTipo(int i) 
	{
		return actorType.get(i);
	}
	
	public void removeActorTipo(int i) 
	{
		actorType.removeIndex(i);
	}

}
