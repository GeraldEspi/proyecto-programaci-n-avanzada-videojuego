package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Tarro {
	   private Rectangle bucket;
	   private Texture bucketImage;
	   private Sound sonidoHerido;
	   private Sound sonidoHeal;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 400;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   private int time; int counter;
	   //---

	   //---
	   
	   public Tarro(Texture tex, Sound ss, Sound ss2) {
		   bucketImage = tex;
		   sonidoHerido = ss;
		   sonidoHeal = ss2;
	   }
	   
		public int getVidas() {
			return vidas;
		}
	
		public int getPuntos() {
			return puntos;
		}
		public Rectangle getArea() {
			return bucket;
		}
		public void sumarPuntos(int pp) {
			if(pp > 0)
			puntos+=pp;
			if(pp < 0)
			puntos = puntos + pp;
		}
		
		public int getTime() {
			return time;
		}

		
	
	   public void crear() {

		      bucket = new Rectangle();
		      bucket.x = 800 / 2 - 64 / 2;
		      bucket.y = 20;
		      bucket.width = 25;
		      bucket.height = 18;
	   }
	   public void dañar() {
		  vidas--;
		  herido = true;
		  tiempoHerido=tiempoHeridoMax;
		  sonidoHerido.play();
	   }
	   public void dibujar(SpriteBatch batch) {
		 if (!herido)  
		   batch.draw(bucketImage, bucket.x, bucket.y);
		 else {
	
		   batch.draw(bucketImage, bucket.x, bucket.y+ MathUtils.random(-5,5));
		   tiempoHerido--;
		   if (tiempoHerido<=0) herido = false;
		 }
	   } 
	   
	   
	   public void curar() {
		   vidas++;
		   sonidoHeal.play();
	}
	   
	public void temporizador(){ 
		time++;
		
	}
	

	public void actualizarMovimiento() { 
		   // movimiento desde mouse/touch
		   /*if(Gdx.input.isTouched()) {
			      Vector3 touchPos = new Vector3();
			      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			      camera.unproject(touchPos);
			      bucket.x = touchPos.x - 64 / 2;
			}*/
		   //Movimiento desde teclado Formato flechas
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.UP)) bucket.y += velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) bucket.y -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.SPACE) ) bucket.x -= velx * Gdx.graphics.getDeltaTime()*2;
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.SPACE) ) bucket.x += velx * Gdx.graphics.getDeltaTime()*2;
		   if(Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.SPACE) ) bucket.y += velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.SPACE) ) bucket.y -= velx * Gdx.graphics.getDeltaTime();
		   
		   // WASD format
		   if(Gdx.input.isKeyPressed(Input.Keys.A)) bucket.x -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.D)) bucket.x += velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.W)) bucket.y += velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.S)) bucket.y -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SPACE) ) bucket.x -= velx * Gdx.graphics.getDeltaTime()*2;
		   if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SPACE) ) bucket.x += velx * Gdx.graphics.getDeltaTime()*2;
		   if(Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.SPACE) ) bucket.y += velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.SPACE) ) bucket.y -= velx * Gdx.graphics.getDeltaTime();
		   
		   
		   // Habilidad especial
		   
		   if(Gdx.input.isKeyPressed(Input.Keys.F)) 
		  
		   // que no se salga de los bordes izq y der
		   if(bucket.x > 0) bucket.x = 0;
		   if(bucket.x < 0) bucket.x = 0;
		   if(bucket.x > 800 - 64) bucket.x = 800 - 64;
		   if(bucket.y < 0) bucket.y = 0;
		   if(bucket.y > 480 - 64) bucket.y = 480 - 64;
	   }
	    

	public void destruir() {
		    bucketImage.dispose();
	   }
	
   public boolean estaHerido() {
	   return herido;
   }
	   
}
