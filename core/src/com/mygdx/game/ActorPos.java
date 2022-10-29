package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class ActorPos {
	
	private Array<Rectangle> actorPosArray;
	
	public ActorPos() 
	{
		actorPosArray = new Array<Rectangle>();
	}
	
	public void addActor(Rectangle raidDrop) 
	{
		actorPosArray.add(raidDrop);
	}
	
	public int getSizeArray() 
	{
		return actorPosArray.size;
	}
	
	public Rectangle getActor(int i) 
	{
		return actorPosArray.get(i);
	}
	
	public void removeActor(int i) 
	{
		actorPosArray.removeIndex(i);
	}

}
