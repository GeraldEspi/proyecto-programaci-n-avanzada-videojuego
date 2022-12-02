package Screens;

import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameLluviaMenu;
import com.mygdx.game.Highscore;
import com.mygdx.game.ParallaxBackground;

import Builder.CampoPeces;
import Builder.Lluvia;
import Builder.Builder;
import ObjetosJugables.Anguila;
import ObjetosJugables.Falcon;
import ObjetosJugables.TipoObjetoMovi;
import TemplateMethod.TemplateCrearParallax;
import TemplateMethod.TemplateParallaxLLuvia;
import TemplateMethod.TemplateParallaxPeces;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
	private Highscore highscore;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private TipoObjetoMovi player;
	private Builder obstaculo;
	private int opcionGame;
	private Stage stage;
	private ParallaxBackground parallaxBackground;
	private TemplateCrearParallax inicioNivel;
	 
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game, int opcion1, Highscore highscore ) {
		this.game = game;
		this.highscore = Highscore.getHighscore();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.opcionGame = opcion1;
        
    	
	    // camera
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 1280, 720);
        batch = new SpriteBatch();
        
        
        if(opcionGame == 1)
        {
        	this.inicioNivel = new TemplateParallaxLLuvia();
        	
        	
        	font.setColor(Color.BLACK); // color setteado
        	stage = new Stage(new ScreenViewport());
        	
        	parallaxBackground = inicioNivel.crearNivel();
           
            stage.addActor(parallaxBackground);
            
            player = new Falcon();
            player.crear();
            
            obstaculo = new Lluvia();
        	obstaculo.crear();
        }
        
        if(opcionGame == 2) 
        {
        	this.inicioNivel = new TemplateParallaxPeces();
        	
        	stage = new Stage(new ScreenViewport());
        	font.setColor(Color.BLACK); // color setteado
        	
        	parallaxBackground = inicioNivel.crearNivel();

            stage.addActor(parallaxBackground);
                            
            player = new Anguila();
            player.crear();

            obstaculo = new CampoPeces();
        	obstaculo.crear();
        }
        
	}

	@Override
	public void render(float delta) {
		
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();

		
		//dibujar textos
		font.draw(batch, "Puntos totales: " + player.getPuntos(), 5, 700);
		font.draw(batch, "Vidas : " + player.getVidas(), (camera.viewportWidth/2)-40, 700);
		font.draw(batch, "HighScore : " + highscore.getHigherScore(), camera.viewportWidth-305, 700);
		//_------------------------------------------------------------------------
		//----------------------------- ver herido
		if (!player.estaHerido()) {
			parallaxBackground.setSpeed(1);
	        player.actualizarMovimiento();        
			// caida de la lluvia 
	       if (!obstaculo.actualizarMovimiento(player)) {
	    	  //actualizar HigherScore
	    	  if (highscore.getHigherScore()<player.getPuntos())
	    		  highscore.setHigherScore(player.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game,opcionGame));
	    	  dispose();
	       }
		}
		
		
		
		if((player.estaHerido())) parallaxBackground.setSpeed(0);
		//-------------------------------------------
		
		player.dibujar(batch);
		obstaculo.actualizarDibujoObjeto(batch);
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){pause();} // Pause
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
	  obstaculo.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		obstaculo.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
	  stage.dispose();
      player.destruir();
      obstaculo.destruir();

	}

}
