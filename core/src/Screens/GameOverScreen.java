package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameLluviaMenu;

public class GameOverScreen implements Screen {
	private final GameLluviaMenu game;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;
	private int opcionGame;

	public GameOverScreen(final GameLluviaMenu game, int opcion1) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.opcionGame = opcion1;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		font.setColor(Color.RED);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.draw(batch, "GAME OVER", 200, camera.viewportWidth/2);
		font.draw(batch, "Presiona R para reiniciar.\n Presiona S para volver al menu principal.\n Presiona F para salir.", 200, 200);
		batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.R)) {
			game.setScreen(new GameScreen(game, opcionGame, null));
			dispose();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			font.setColor(Color.WHITE);
			game.setScreen(new MainMenuScreen(game));
			
			dispose();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.F)) {
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
