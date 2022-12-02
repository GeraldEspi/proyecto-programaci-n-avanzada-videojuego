package TemplateMethod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ParallaxBackground;

public class TemplateParallaxPeces extends TemplateCrearParallax {
	
	public void crearArrayImagenes() {
		for(int i = 1; i <=4;i++){
            textures.add(new Texture(Gdx.files.internal("seaBackparallax/img"+i+".png")));
            textures.get(textures.size-1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        }
	}
	
	public void crearParrallax() {
		
		parallaxBackground = new ParallaxBackground(textures);
        parallaxBackground.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        parallaxBackground.setSpeed(1);
	}
	


}
