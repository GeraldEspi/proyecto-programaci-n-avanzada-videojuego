package TemplateMethod;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ParallaxBackground;


public abstract class TemplateCrearParallax {
	

	protected ParallaxBackground parallaxBackground;
	protected Array<Texture> textures;
	
	public TemplateCrearParallax() {
		textures = new Array<Texture>();
	}
	
	public ParallaxBackground crearNivel() {
		
		crearArrayImagenes();
		crearParrallax();
		return parallaxBackground;
	}
	
	public abstract void crearArrayImagenes();
	
	public abstract void crearParrallax();
	

}
