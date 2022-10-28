package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Falcon extends TipoTarro {
	  
	  private int velx = 400;
	  Sound healSound;
	  
	   public Falcon() {
		   
		   super(new Texture(Gdx.files.internal("bucket.png")), Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
		   this.healSound = Gdx.audio.newSound(Gdx.files.internal("goodSound.wav"));
		   
	   }
	   
		public Rectangle getArea(){
			return bucket.getBoundingRectangle();
		}
		

	   public void dibujar(SpriteBatch batch) {
		 if (!herido)  
		   bucket.draw(batch);
		 else {
		
		   bucket.setY(bucket.getY()+MathUtils.random(-5,5));
		   bucket.draw(batch);
		   tiempoHerido--;
		   if (tiempoHerido<=0) herido = false;
		 }
	   } 
	   
	   @Override
	   public void curar(){
		   vidas++;
		   sumarPuntos(20);
		   healSound.play();
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
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) bucket.setX(bucket.getX()+velx * Gdx.graphics.getDeltaTime());
		   if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) bucket.setY(bucket.getY()+velx * Gdx.graphics.getDeltaTime());
		   if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) bucket.setY(bucket.getY()-velx * Gdx.graphics.getDeltaTime());
		   //Sprite
		   if((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) 
				   && Gdx.input.isKeyPressed(Input.Keys.SPACE)) bucket.setX(bucket.getX()-velx * Gdx.graphics.getDeltaTime()*2);
		   if((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) 
				   && Gdx.input.isKeyPressed(Input.Keys.SPACE)) bucket.setX(bucket.getX()+velx * Gdx.graphics.getDeltaTime()*2);
		   
		   // que no se salga de los bordes izq y der
		   if(bucket.getX() < 0) bucket.setX(0);
		   if(bucket.getX() > 1280  - 64) bucket.setX(1280 - 64);
		   if(bucket.getY() < 0) bucket.setY(0);
		   if(bucket.getY() > 640 - 64) bucket.setY(640 - 64);
	   }
	   
}
