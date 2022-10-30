package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Anguila extends TipoObjetoMovi{
	  private int velx = 400;
	  
	  public Anguila() {
		   
		   super(new Texture(Gdx.files.internal("anguila.png")),
				   Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
	   }
	   
	  public Rectangle getArea(){
			return player.getBoundingRectangle();
		}
		
	  public void dibujar(SpriteBatch batch) {
		 if (!herido)  
		   player.draw(batch);
		 else {
		
		   player.setX(player.getX()+MathUtils.random(-5,5));
		   player.draw(batch);
		   tiempoHerido--;
		   if (tiempoHerido<=0) herido = false;
		 }
	   } 
	   
	  public void setVelo(int newVelo) 
	   {
		   this.velx = newVelo;
	   }
	   
	  public void actualizarMovimiento() { 
		   // movimiento desde mouse/touch
		   /*if(Gdx.input.isTouched()) {
			      Vector3 touchPos = new Vector3();
			      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			      camera.unproject(touchPos);
			      bucket.x = touchPos.x - 64 / 2;
			}*/
		   //movimiento desde teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))  player.setX(player.getX()-velx * Gdx.graphics.getDeltaTime());
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) player.setX(player.getX()+velx/2 * Gdx.graphics.getDeltaTime());
		   if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) player.setY(player.getY()+velx * Gdx.graphics.getDeltaTime());
		   if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) player.setY(player.getY()-velx * Gdx.graphics.getDeltaTime());
		   //Sprite
		   if((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) 
				   && Gdx.input.isKeyPressed(Input.Keys.SPACE)) player.setY(player.getY()+velx * Gdx.graphics.getDeltaTime()*2);
		   if((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) 
				   && Gdx.input.isKeyPressed(Input.Keys.SPACE)) player.setY(player.getY()-velx * Gdx.graphics.getDeltaTime()*2);
		   
		   // que no se salga de los bordes izq y der
		   if(player.getX() < 0) player.setX(0);
		   if(player.getX() > 1200  - 64) player.setX(1200 - 64);
		   if(player.getY() < 0) player.setY(0);
		   if(player.getY() > 640 - 64) player.setY(640 - 64);
	   }
	   
	   
}

 


