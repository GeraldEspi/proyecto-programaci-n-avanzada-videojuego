package Screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ObjetosJugables.TipoObjetoMovi;

public interface TipoObstaculo {
	public void crear();
	public void crearObjetoObstaculo();
	public boolean actualizarMovimiento(TipoObjetoMovi actor);
	public void actualizarDibujoObjeto(SpriteBatch batch);
	public void destruir();
	public void pausar();
	public void continuar();
}