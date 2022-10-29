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

public class CampoPeces implements TipoObstaculo
{
		private ActorPos fishPos;
		private ActorTipo fishTipo;
	    private long lastDropTime;
	    private Texture alga;
	    private Texture pezTiburon;
	    private Texture pezBueno;
	    private Sound getItemSound;
	    private Music debajoDelMar;
	    private Sound healSound;
	    private Sound eatSound;
	    
	   
	   
		   
		public CampoPeces() {
			
			getItemSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
			eatSound =  Gdx.audio.newSound(Gdx.files.internal("queRico.mp3"));
			debajoDelMar = Gdx.audio.newMusic(Gdx.files.internal("underwater.mp3"));
			this.healSound = Gdx.audio.newSound(Gdx.files.internal("goodSound.mp3"));
			this.alga = new Texture(Gdx.files.internal("alga.png"));
			this.pezTiburon = new Texture(Gdx.files.internal("pezTiburon.png"));
			this.pezBueno = new Texture(Gdx.files.internal("pezBueno.png"));
		}
		
		public void crear() {
			fishPos = new ActorPos();
			fishTipo = new ActorTipo();
			crearObjetoObstaculo();
		      // start the playback of the background music immediately
		      debajoDelMar.setLooping(true);
		      debajoDelMar.play();
		}
		
		public void crearObjetoObstaculo() {
		      Rectangle raindrop = new Rectangle();
		      
		      raindrop.y = MathUtils.random(0, 640-64);
		      
		     
		      raindrop.x = 1280;
		      
		      raindrop.width = 64;
		      raindrop.height = 64;
		      fishPos.addActor(raindrop);
		      int random=MathUtils.random(1,100);
		      // ver el tipo de objeto
		      if (random<=30)	    	  
		    	  fishTipo.addActorTipo(1);
		      if(random>30 && random<=98)
		    	  fishTipo.addActorTipo(2);
		      if(random>=99)
		    	  fishTipo.addActorTipo(3);
		      
		      lastDropTime = TimeUtils.nanoTime();
	    }
		
	   public boolean actualizarMovimiento(TipoObjetoMovi player) { 
		   // generando objetos 
		   if(TimeUtils.nanoTime() - lastDropTime > 100000000) 
			   crearObjetoObstaculo();
		  
		   
		   // revisar si las gotas cayeron al suelo o chocaron con el tarro
		   for (int i=0; i < fishPos.getSizeArray(); i++ ) {
			  Rectangle raindrop = fishPos.getActor(i);
			  
			  
		     
			 
		       raindrop.x -= 300 * Gdx.graphics.getDeltaTime();
		      
		   
				  
		      
		      //Movimiento en eje y de los cuervos, va aumentando segun el coefMov
		      if(fishTipo.getActorTipo(i)==1)
		    	  raindrop.y+=MathUtils.random(-player.puntos*2,player.puntos*2)* Gdx.graphics.getDeltaTime();
		      
		      //
		      if(fishTipo.getActorTipo(i)==3)
		    	  raindrop.y+=MathUtils.random(-700,700)* (Gdx.graphics.getDeltaTime());
		      
		      
		      //cae al suelo y se elimina
		      if(raindrop.x + 64 < 0) {
		    	  fishPos.removeActor(i); 
		    	  fishTipo.removeActorTipo(i);
		      }
		      if(raindrop.overlaps(player.getArea())) { //Pez tiburon malvado
		    	if(fishTipo.getActorTipo(i)==1) { // te muerde y hace daño, se muere en el intento
		    	  player.dañar();
		    	  if (player.getVidas()<=0)
		    		 return false; // si se queda sin vidas retorna falso /game over
		    	  fishPos.removeActor(i);  
		    	  fishTipo.removeActorTipo(i);
		      	}
		    	if(fishTipo.getActorTipo(i)==2) { // trigo
		    	  player.sumarPuntos(1);
		          getItemSound.play();
		          fishPos.removeActor(i);  
		    	  fishTipo.removeActorTipo(i);
		      	}
		    	else {
		    		player.curar();
		    		healSound.play();
		    		eatSound.play();
		    		fishPos.removeActor(i);  
			    	fishTipo.removeActorTipo(i);
		    		
		    	}
		      }
		   } 
		  return true; 
	   }
	   
	   public void actualizarDibujoObjeto(SpriteBatch batch) { 
		   
		  for (int i=0; i < fishPos.getSizeArray(); i++ ) {
			  Rectangle raindrop = fishPos.getActor(i);
			  if(fishTipo.getActorTipo(i)==1) // gota dañina
		         batch.draw(pezTiburon, raindrop.x, raindrop.y); 
			  if(fishTipo.getActorTipo(i)==2)
				 batch.draw(alga, raindrop.x, raindrop.y); 
			  if(fishTipo.getActorTipo(i)==3)
				 batch.draw(pezBueno, raindrop.x, raindrop.y);
		   }
	   }
	   public void destruir() {
	      getItemSound.dispose();
	      debajoDelMar.dispose();
	   }
	   public void pausar() {
		  debajoDelMar.stop();
	   }
	   public void continuar() {
		  debajoDelMar.play();
	   }
 
}

