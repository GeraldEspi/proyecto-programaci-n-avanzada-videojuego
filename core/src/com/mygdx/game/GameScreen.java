package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private TipoTarro tarro;
	private TipoObstaculo obstaculo;
	private Texture escenario;
	private int opcion;  
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game, int opcion) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
	    this.opcion=opcion;  
	    // camera
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 1280, 720);
        batch = new SpriteBatch();
        // creacion del tarro
        if(opcion==1) {
        escenario = new Texture(Gdx.files.internal("atmosfera.png"));
        tarro = new Tarro();
        tarro.crear();
	      
        // creacion de la lluvia
        obstaculo = new Lluvia();
        obstaculo.crear();
        }
        else {
        escenario = new Texture(Gdx.files.internal("campo.png"));
        tarro = new Canasto();
        tarro.crear();
    	      
        // creacion de la lluvia
        obstaculo = new CampoTrigo();
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
		
		batch.draw(escenario,0,0);
		//dibujar textos
		font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 650);
		font.draw(batch, "Vidas : " + tarro.getVidas(), camera.viewportWidth/2, 650);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth-250, 650);
		
		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();        
			// caida de la lluvia 
	       if (!obstaculo.actualizarMovimiento(tarro)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<tarro.getPuntos())
	    		  game.setHigherScore(tarro.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game,opcion));
	    	  dispose();
	       }
		}
		
		tarro.dibujar(batch);
		obstaculo.actualizarDibujoObjeto(batch);
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			pause();
		}
		
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
	  escenario.dispose();
      tarro.destruir();
      obstaculo.destruir();

	}

}
