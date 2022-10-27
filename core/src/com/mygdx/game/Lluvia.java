package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	private Array<Rectangle> rainDropsPos;
	private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Texture gotaMuyBuena;
    private Sound dropSound;
    private Music rainMusic;
    private int numero;
	   
	public Lluvia(Texture gotaBuena, Texture gotaMala,Texture gotaMuyBuena, Sound ss, Music mm ) {
		rainMusic = mm;
		dropSound = ss;
		this.gotaBuena = gotaBuena;
		this.gotaMala = gotaMala;
		this.gotaMuyBuena = gotaMuyBuena;;
	}
	
	public void crear() {
		rainDropsPos = new Array<Rectangle>();
		rainDropsType = new Array<Integer>();
		crearGotaDeLluvia();
	      // start the playback of the background music immediately
	      rainMusic.setLooping(true);
	      rainMusic.play();
	}
	
	private void crearGotaDeLluvia() {
	      Rectangle raindrop = new Rectangle();
	      raindrop.x = MathUtils.random(0, 800-64);
	      raindrop.y = 480;
	      raindrop.width = 64;
	      raindrop.height = 64;
	      rainDropsPos.add(raindrop);
	      // ver el tipo de gota
	      if (MathUtils.random(1,100)<50)	    	  
	         rainDropsType.add(1);
	      if (MathUtils.random(1,100)>25)
	    	 rainDropsType.add(2);
	      if (MathUtils.random(1,80) < 4)	
	    	 rainDropsType.add(3);
	      
	      lastDropTime = TimeUtils.nanoTime();
	   }
	
   public void actualizarMovimiento(Tarro tarro) { 
	   // generar gotas de lluvia 
	   
	   if(TimeUtils.nanoTime() - lastDropTime + (tarro.getTime())> 100000000) crearGotaDeLluvia();
	  
	   
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle raindrop = rainDropsPos.get(i);
	      raindrop.y -= 300 * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(raindrop.y + 64 < 0) {
	    	  rainDropsPos.removeIndex(i); 
	    	  rainDropsType.removeIndex(i);
	      }
	      if(raindrop.overlaps(tarro.getArea())) { //la gota choca con el tarro
	    	if(rainDropsType.get(i)==1) { // gota dañina
	    	  tarro.sumarPuntos(-3);
	    	  tarro.dañar();
	    	  rainDropsPos.removeIndex(i); 
	    	  rainDropsType.removeIndex(i);
	    	  
	      	}if(rainDropsType.get(i)==2) { // gota a recolectar
	    	  tarro.sumarPuntos(1);
	          dropSound.play();
	          rainDropsPos.removeIndex(i);
	          rainDropsType.removeIndex(i);
	      	}
	      	
	      	if(rainDropsType.get(i)==3) { // Super gota bendecida por jeováh
		    	  tarro.sumarPuntos(5);
		          dropSound.play();
		          tarro.curar();
		          rainDropsPos.removeIndex(i);
		          rainDropsType.removeIndex(i);
	      	}
	      }
	   }   
   }
   
   public void actualizarDibujoLluvia(SpriteBatch batch) { 
	
	  for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle raindrop = rainDropsPos.get(i);
		  if(rainDropsType.get(i)==1) // gota mala
	         batch.draw(gotaMala, raindrop.x, raindrop.y); 
		  if(rainDropsType.get(i) == 2) // gota normal
			 batch.draw(gotaBuena, raindrop.x, raindrop.y); 
		  if(rainDropsType.get(i) == 3) // super gota 
			 batch.draw(gotaMuyBuena, raindrop.x, raindrop.y); 
	   }
   }
   public void destruir() {
	      dropSound.dispose();
	      rainMusic.dispose();
   }
   
}
