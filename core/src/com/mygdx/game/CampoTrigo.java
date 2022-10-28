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

public class CampoTrigo implements TipoObstaculo
{
		private rainDropsPos avePos;
		private rainDropsType aveTipo;
	    private long lastDropTime;
	    private Texture trigo;
	    private Texture cuervo;
	    private Texture paloma;
	    private Sound dropSound;
	    private Music cuervoSounds;
	   
		   
		public CampoTrigo() {
			
			dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	        
			cuervoSounds = Gdx.audio.newMusic(Gdx.files.internal("Cuervos.mp3"));
	        
			this.trigo = new Texture(Gdx.files.internal("trigo.png"));
			this.cuervo = new Texture(Gdx.files.internal("Cuervo.png"));
			this.paloma = new Texture(Gdx.files.internal("paloma.png"));
		}
		
		public void crear() {
			avePos = new rainDropsPos();
			aveTipo = new rainDropsType();
			crearObjetoObstaculo();
		      // start the playback of the background music immediately
		      cuervoSounds.setLooping(true);
		      cuervoSounds.play();
		}
		
		public void crearObjetoObstaculo() {
		      Rectangle raindrop = new Rectangle();
		      raindrop.y = MathUtils.random(0, 640-64);
		      raindrop.x = 1280;
		      raindrop.width = 64;
		      raindrop.height = 64;
		      avePos.addGota(raindrop);
		      int random=MathUtils.random(1,100);
		      // ver el tipo de objeto
		      if (random<=30)	    	  
		    	  aveTipo.addGotaType(1);
		      if(random>30 && random<=98)
		    	  aveTipo.addGotaType(2);
		      if(random>=99)
		    	  aveTipo.addGotaType(3);
		      
		      lastDropTime = TimeUtils.nanoTime();
	    }
		
	   public boolean actualizarMovimiento(TipoObjetoMovi canasta) { 
		   // generando objetos 
		   if(TimeUtils.nanoTime() - lastDropTime > 100000000) 
			   crearObjetoObstaculo();
		  
		   
		   // revisar si las gotas cayeron al suelo o chocaron con el tarro
		   for (int i=0; i < avePos.getSizeArray(); i++ ) {
			  Rectangle raindrop = avePos.getGota(i);
		      raindrop.x -= 300 * Gdx.graphics.getDeltaTime();
		      
		      //Movimiento en eje y de los cuervos, va aumentando segun el coefMov
		      if(aveTipo.getGotaType(i)==1)
		    	  raindrop.y+=MathUtils.random(-canasta.puntos*2,canasta.puntos*2)* Gdx.graphics.getDeltaTime();
		      
		      //
		      if(aveTipo.getGotaType(i)==3)
		    	  raindrop.y+=MathUtils.random(-700,700)* (Gdx.graphics.getDeltaTime());
		      
		      
		      //cae al suelo y se elimina
		      if(raindrop.x + 64 < 0) {
		    	  avePos.removerGota(i); 
		    	  aveTipo.removerGotaType(i);
		      }
		      if(raindrop.overlaps(canasta.getArea())) { //la gota choca con el tarro
		    	if(aveTipo.getGotaType(i)==1) { // gota dañina
		    	  canasta.dañar();
		    	  if (canasta.getVidas()<=0)
		    		 return false; // si se queda sin vidas retorna falso /game over
		    	  avePos.removerGota(i);  
		    	  aveTipo.removerGotaType(i);
		      	}
		    	if(aveTipo.getGotaType(i)==2) { // trigo
		    	  canasta.sumarPuntos(1);
		          dropSound.play();
		          avePos.removerGota(i);  
		    	  aveTipo.removerGotaType(i);
		      	}
		    	else {
		    		canasta.curar();
		    		avePos.removerGota(i);  
			    	aveTipo.removerGotaType(i);
		    		
		    	}
		      }
		   } 
		  return true; 
	   }
	   
	   public void actualizarDibujoObjeto(SpriteBatch batch) { 
		   
		  for (int i=0; i < avePos.getSizeArray(); i++ ) {
			  Rectangle raindrop = avePos.getGota(i);
			  if(aveTipo.getGotaType(i)==1) // gota dañina
		         batch.draw(cuervo, raindrop.x, raindrop.y); 
			  if(aveTipo.getGotaType(i)==2)
				 batch.draw(trigo, raindrop.x, raindrop.y); 
			  if(aveTipo.getGotaType(i)==3)
				 batch.draw(paloma, raindrop.x, raindrop.y);
		   }
	   }
	   public void destruir() {
	      dropSound.dispose();
	      cuervoSounds.dispose();
	   }
	   public void pausar() {
		  cuervoSounds.stop();
	   }
	   public void continuar() {
		  cuervoSounds.play();
	   }
 
}

