package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class TipoObjetoMovi {
	
	protected Sprite bucket;
	   protected Texture bucketImage;
	   protected Sound sonidoHerido;
	   protected int vidas = 3;
	   protected int puntos = 0;
	   protected boolean herido = false;
	   protected int tiempoHeridoMax=50;
	   protected int tiempoHerido;
	   
	   public TipoObjetoMovi(Texture tex, Sound ss) {
		   bucketImage = tex;
		   sonidoHerido = ss;
	   }
	   
	   public int getVidas() {
			return vidas;
	   }
	
		public int getPuntos() {
			return puntos;
		}
		public abstract Rectangle getArea();
		
		public void sumarPuntos(int pp) {
			puntos+=pp;
		}
		
	   public void crear(){
		   bucket= new Sprite(bucketImage);
		   bucket.setCenterX(1280/2-64/2);
		   bucket.setY(720/2);
	   }
	   
	   public void dañar() {
		  vidas--;
		  herido = true;
		  tiempoHerido=tiempoHeridoMax;
		  sonidoHerido.play();
	   }
	   
	   public void dañar(int a) 
	   {

			  vidas-=a;
			  herido = true;
			  tiempoHerido=tiempoHeridoMax;
			  sonidoHerido.play();
	   }
	   
	   public abstract void dibujar(SpriteBatch batch);
	   
	   public abstract void setVelo(int velo);
	   
	   public void curar() 
	   {
		   vidas++;  
	   }
	   
	   public abstract void actualizarMovimiento();
	    
	   public void destruir() 
	{
		    bucketImage.dispose();
	}
	
	   public boolean estaHerido() 
	{
	   return herido;
	}	   
}
