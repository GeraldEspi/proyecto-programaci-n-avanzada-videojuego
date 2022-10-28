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

public class Canasto extends TipoObjetoMovi{
	  private int velx = 400;
	  
	   public Canasto() {
		   
		   super(new Texture(Gdx.files.internal("Canasto.png")), Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
	   }
	   
		public Rectangle getArea(){
			return bucket.getBoundingRectangle();
		}
		

	   public void dibujar(SpriteBatch batch) {
		 if (!herido)  
		   bucket.draw(batch);
		 else {
		
		   bucket.setX(bucket.getX()+MathUtils.random(-5,5));
		   bucket.draw(batch);
		   tiempoHerido--;
		   if (tiempoHerido<=0) herido = false;
		 }
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
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))  bucket.setX(bucket.getX()-velx * Gdx.graphics.getDeltaTime());
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) bucket.setX(bucket.getX()+velx/2 * Gdx.graphics.getDeltaTime());
		   if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) bucket.setY(bucket.getY()+velx * Gdx.graphics.getDeltaTime());
		   if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) bucket.setY(bucket.getY()-velx * Gdx.graphics.getDeltaTime());
		   //Sprite
		   if((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) 
				   && Gdx.input.isKeyPressed(Input.Keys.SPACE)) bucket.setY(bucket.getY()+velx * Gdx.graphics.getDeltaTime()*2);
		   if((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) 
				   && Gdx.input.isKeyPressed(Input.Keys.SPACE)) bucket.setY(bucket.getY()-velx * Gdx.graphics.getDeltaTime()*2);
		   
		   // que no se salga de los bordes izq y der
		   if(bucket.getX() < 0) bucket.setX(0);
		   if(bucket.getX() > 1200  - 64) bucket.setX(1200 - 64);
		   if(bucket.getY() < 0) bucket.setY(0);
		   if(bucket.getY() > 640 - 64) bucket.setY(640 - 64);
	   }
	   
	   
}

 



