package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.mygdx.game.*;


public class MainMenuScreen implements Screen {

	final GameLluviaMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private int opcion;

	public MainMenuScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.getData().setScale(1, 1);
		font.draw(batch, "Bienvenido  Gather Matter!!! ", 100, camera.viewportHeight/2+50);
		font.draw(batch, "Selecciona lo que quieres recolectar\n Nivel 1. Lluvia de Falcon\n Nivel 2. la Anguila de las algas\n ESC. Cerrar juego", 100, camera.viewportHeight/2-50);

		batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			opcion=1;
			game.setScreen(new SelectScreen(game, opcion));
			dispose();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			opcion=2;
			game.setScreen(new SelectScreen(game, opcion));
			dispose();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			dispose();
			System.exit(0);
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
