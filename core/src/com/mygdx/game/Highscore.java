package com.mygdx.game;

public class Highscore {
	
	static private Highscore singleton = null;
	private int higherScore;
	
	 private Highscore() { }
	 
	 static public Highscore getHighscore() {
		 
         if (singleton == null) {
        	 singleton = new Highscore();
         }
         return singleton;
     }
	 
	 public int getHigherScore() {
			return higherScore;
		}

		public void setHigherScore(int higherScore) {
			this.higherScore = higherScore;
		}
		

}
