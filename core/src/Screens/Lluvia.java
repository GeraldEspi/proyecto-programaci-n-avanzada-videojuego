package Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.ActorPos;
import com.mygdx.game.ActorTipo;

import ObjetosJugables.TipoObjetoMovi;

public class Lluvia implements TipoObstaculo{
	private ActorPos gotasPos;
	private ActorTipo gotasTipo;
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Texture gotaMuyBuena;
    private Texture granizo;
    private long seconds;//se usara despues
    private Sound getItemSound;
    private Music rainMusic;
   
	public Lluvia() {
		
		getItemSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        
		this.gotaBuena = new Texture(Gdx.files.internal("drop.png"));
		this.gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
		this.gotaMuyBuena= new Texture(Gdx.files.internal("VeryGoodDrop.png"));
		this.granizo= new Texture(Gdx.files.internal("hielo.png"));
	}
	
	@Override
	public void crear() {
		gotasPos = new ActorPos();
		gotasTipo = new ActorTipo();
		crearObjetoObstaculo();
	      // start the playback of the background music immediately
	    rainMusic.setLooping(true);
	    rainMusic.play();
	}
	
	@Override
	public void crearObjetoObstaculo() {
	      Rectangle raindrop = new Rectangle();
	      raindrop.x = MathUtils.random(0, 1580-32);
	      raindrop.y = 720;
	      // ver el tipo de gota


	      int numRand = MathUtils.random(1,100);
	      if (numRand <= 80)
	      {
	    	  gotasTipo.addActorTipo(2);
	    	  raindrop.width = 24;
		      raindrop.height = 24;
		      gotasPos.addActor(raindrop);
	      }
	      if (numRand > 80 &&  numRand < 97)
	      {
	    	  gotasTipo.addActorTipo(1);
	    	  raindrop.width = 32;
		      raindrop.height = 32;
		      gotasPos.addActor(raindrop);
	      }
	      if (numRand >= 97 && numRand<=98) 
	      {
	    	  gotasTipo.addActorTipo(3);
	    	  raindrop.width = 32;
		      raindrop.height = 32;
		      gotasPos.addActor(raindrop);
	      }	
	      if (numRand >= 99) 
	      {
	    	  gotasTipo.addActorTipo(4);
	    	  raindrop.width = 50;
	    	  raindrop.height = 50;
	    	  gotasPos.addActor(raindrop);
	      }	
	      	
	      
	      lastDropTime = TimeUtils.nanoTime();
	   }
	
	@Override
	public boolean actualizarMovimiento(TipoObjetoMovi player) { 
	   // generar gotas de lluvia 
	
	   
	   if(TimeUtils.nanoTime() - lastDropTime > 19990000) 
		   crearObjetoObstaculo();
	   
	  
	   
	   // revisar si las gotas cayeron al suelo o chocaron con el faclon
	   for (int i=0; i < gotasPos.getSizeArray(); i++ ) {
		  Rectangle raindrop = gotasPos.getActor(i);
	      raindrop.y -= 600 * Gdx.graphics.getDeltaTime();
	      raindrop.x -= 300 * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(raindrop.y + 32 < 0) {
	    	  gotasPos.removeActor(i); 
	    	  gotasTipo.removeActorTipo(i);
	      }
	      if(raindrop.overlaps(player.getArea())) { //la gota choca con el faclon
	    	if(gotasTipo.getActorTipo(i)==1) { // gota dañina
	    	  player.sumarPuntos(-20);
	    	  player.dañar();
	    	  
	    	  if(player.getPuntos()<=0) player.setPuntos(0);
	    	  if (player.getVidas()<=0)
	    		 return false; // si se queda sin vidas retorna falso /game over
	    	  gotasPos.removeActor(i);  
	    	  gotasTipo.removeActorTipo(i);
	      	}
	    	if(gotasTipo.getActorTipo(i)==2){ // gota a recolectar
	    	  player.sumarPuntos(1);
	          getItemSound.play();
	          gotasPos.removeActor(i); 
	          gotasTipo.removeActorTipo(i);
	      	}
	    	if(gotasTipo.getActorTipo(i)==3){ // Super gota bendecida por jeováh
	    		  player.sumarPuntos(20);
		          player.curar();
		          gotasPos.removeActor(i); 
		          gotasTipo.removeActorTipo(i);
	      	}
	      	
	      	if(gotasTipo.getActorTipo(i)==4) { // granizo
		    	  player.dañar(2);
		  
		    	  if (player.getVidas()<=0)
		    		 return false; // si se queda sin vidas retorna falso /game over
		    	  gotasPos.removeActor(i);  
		    	  gotasTipo.removeActorTipo(i);
		          
	      	}
	      	
	      	
	      }
	   } 
	  return true; 
   }
	
	@Override
	public void actualizarDibujoObjeto(SpriteBatch batch) { 
	   
	  for (int i=0; i < gotasPos.getSizeArray(); i++ ) {
		  Rectangle raindrop = gotasPos.getActor(i);
		  if(gotasTipo.getActorTipo(i)==1) // gota mala
	         batch.draw(gotaMala, raindrop.x, raindrop.y); 
		  if(gotasTipo.getActorTipo(i) == 2) // gota normal
			 batch.draw(gotaBuena, raindrop.x, raindrop.y); 
		  if(gotasTipo.getActorTipo(i) == 3) // super gota 
			 batch.draw(gotaMuyBuena, raindrop.x, raindrop.y); 
		  if(gotasTipo.getActorTipo(i) == 4) // super gota 
			 batch.draw(granizo, raindrop.x, raindrop.y);
	   }
   }
	
	@Override
	public void destruir() {
      getItemSound.dispose();
      rainMusic.dispose();
   }
	
	@Override
	public void pausar() {
	  rainMusic.stop();
   }
	
	@Override
	public void continuar() {
	  rainMusic.play();
   }

}
