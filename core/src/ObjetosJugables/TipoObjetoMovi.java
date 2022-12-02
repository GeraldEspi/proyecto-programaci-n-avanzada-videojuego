package ObjetosJugables;

import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Timers;

import strategy.StrategyObjMovi;

public abstract class TipoObjetoMovi {
	
	   protected Sprite player;
	   protected Texture playerSkin;
	   protected Texture playerEffectSkin;
	   protected Texture playerEspecialSkin;
	   protected Sound sonidoHerido;
	   protected Sound healSound;
	   protected StrategyObjMovi metodosObjMovi;
	   protected int vidas;
	   protected int velx;
	   protected int puntos = 0;
	   protected boolean herido = false;
	   protected int tiempoHeridoMax=50;
	   protected int tiempoHerido;
	   protected boolean activado;
	   protected int especialTime;
	 
	   
	   public TipoObjetoMovi(Texture tex, Texture tex2, Texture tex3, Sound ss) {
		   playerSkin = tex;
		   playerEffectSkin = tex2;
		   playerEspecialSkin = tex3;
		   sonidoHerido = ss;  
	   }
	   
	   public int getVidas() {
			return vidas;
	   }
	
		public int getPuntos() {
			return puntos;
		}
		
		public void setPuntos(int pts) {
			this.puntos=pts;
		}
		
		public void setStrategy(StrategyObjMovi strategyObje){
			this.metodosObjMovi = strategyObje;
		}
		
		public void setTextura(Texture tx) {
			this.playerSkin = tx;
			this.player = new Sprite(playerSkin);
			player.setCenterX(1280/2-64/2);
			player.setY(720/2);
		}
		
		public void sumarPuntos(int pp) {
			puntos+=pp;
		}
		
		public void restarPuntos(int pp) {
			puntos-=pp;
		}
		
		
	   public void crear(){
		   player= new Sprite(playerSkin);
		   player.setCenterX(1280/2-64/2);
		   player.setY(720/2);
	   }
	   public void curar() 
	   {
		   if(vidas < metodosObjMovi.getVidas()){
			   vidas = metodosObjMovi.curarVidas(this.vidas);
			   velx = metodosObjMovi.curarVelx();
			   activado = false;
			   player.setTexture(playerSkin);
			   healSound.play();
		   }
	   }
	   
	   public void dañar() 
	   {
		   this.vidas = metodosObjMovi.dañarVidas(this.vidas);
		   herido = true;
		   activado = false;
		   tiempoHerido=tiempoHeridoMax;
		   sonidoHerido.play();
	   }
	   
	   public void dañar(int a) 
	   {
			  vidas = metodosObjMovi.dañarVidas(this.vidas, a);
			  velx = metodosObjMovi.dañarVelx(this.velx);
			  herido = true;
			  player.setTexture(playerEffectSkin);
			  tiempoHerido=tiempoHeridoMax;
			  sonidoHerido.play();
	   }
	   
	   public boolean especial() 
	   {
		   player.setTexture(playerEspecialSkin);
		   activado = true;
		  
		   especialTime = Timers.getSeconds();
		   return activado;
	   }
	   
	   
	   public boolean desactivarEspecial() 
	   {
		   player.setTexture(playerSkin);
		   activado = false;
		   return activado;
	   }
	   
	   
	   
	   public void destruir() {
			    playerSkin.dispose();
	   }
	   
	   public boolean estaHerido() {
		   return herido;
	   }
	   
	 
	   public boolean especialActivado() {
		   return activado;
	   }
	   public abstract void dibujar(SpriteBatch batch);
	   
	   public abstract void actualizarMovimiento();
	   
	   public abstract Rectangle getArea();
	   	   
	   
}
