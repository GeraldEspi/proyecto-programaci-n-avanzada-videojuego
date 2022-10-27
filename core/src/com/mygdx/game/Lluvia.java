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
	private rainDropsPos gotasPos;
	private rainDropsType gotasTipo;
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
		this.gotaMuyBuena = gotaMuyBuena;
		gotasPos = new rainDropsPos();
		gotasTipo = new rainDropsType();
	}
	
	public void crear() {
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
	      gotasPos.addGota(raindrop);
	      // ver el tipo de gota
	      
	      int numRand = MathUtils.random(1,100);
	      if (numRand <= 60)	    	  
	    	  gotasTipo.addGotaType(2);
	      if (numRand > 60 &&  numRand < 98)
	    	  gotasTipo.addGotaType(1);
	      if (numRand >= 98)	
	    	  gotasTipo.addGotaType(3);
	      
	      lastDropTime = TimeUtils.nanoTime();
	   }
	
   public void actualizarMovimiento(Tarro tarro) { 
	   // generar gotas de lluvia 
	   
	   if(TimeUtils.nanoTime() - lastDropTime + (tarro.getTime())> 100000000) crearGotaDeLluvia();
	  
	   
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i=0; i < gotasPos.getSizeArray(); i++ ) {
		  Rectangle raindrop = gotasPos.getGota(i);
	      raindrop.y -= 300 * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(raindrop.y + 64 < 0) {
	    	  gotasPos.removerGota(i); 
	    	  gotasTipo.removerGotaType(i);
	      }
	      if(raindrop.overlaps(tarro.getArea())) { //la gota choca con el tarro
	    	if(gotasTipo.getGotaType(i)==1) { // gota dañina
	    	  tarro.sumarPuntos(-3);
	    	  tarro.dañar();
	    	  gotasPos.removerGota(i);  
	    	  gotasTipo.removerGotaType(i);
	    	  
	      	}if(gotasTipo.getGotaType(i)==2) { // gota a recolectar
	    	  tarro.sumarPuntos(1);
	          dropSound.play();
	          gotasPos.removerGota(i); 
	          gotasTipo.removerGotaType(i);
	      	}
	      	
	      	if(gotasTipo.getGotaType(i)==3) { // Super gota bendecida por jeováh
		    	  tarro.sumarPuntos(5);
		          dropSound.play();
		          tarro.curar();
		          gotasPos.removerGota(i); 
		          gotasTipo.removerGotaType(i);
	      	}
	      }
	   }   
   }
   
   public void actualizarDibujoLluvia(SpriteBatch batch) { 
	
	  for (int i=0; i < gotasPos.getSizeArray(); i++ ) {
		  Rectangle raindrop = gotasPos.getGota(i);
		  if(gotasTipo.getGotaType(i)==1) // gota mala
	         batch.draw(gotaMala, raindrop.x, raindrop.y); 
		  if(gotasTipo.getGotaType(i) == 2) // gota normal
			 batch.draw(gotaBuena, raindrop.x, raindrop.y); 
		  if(gotasTipo.getGotaType(i) == 3) // super gota 
			 batch.draw(gotaMuyBuena, raindrop.x, raindrop.y); 
	   }
   }
   public void destruir() {
	      dropSound.dispose();
	      rainMusic.dispose();
   }
   
}
