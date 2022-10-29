package com.mygdx.game;

import java.util.Timer;
import java.util.TimerTask;

public class Timers {
	
	static int secondsPassed = 0;
	
	static Timer myTimer = new Timer();
	static TimerTask task = new TimerTask() {
		public void run() {
			secondsPassed++;
			System.out.print(secondsPassed);
			
		}
	
	};
	
	public static void start()
	{
		
		myTimer.scheduleAtFixedRate(task, 1000,1000);
		
	}
	
	public static void restart()
	{
		secondsPassed = 0;
	}
	
	public static void stop()
	{
		myTimer.cancel();
	}
	
	
	
	

}

