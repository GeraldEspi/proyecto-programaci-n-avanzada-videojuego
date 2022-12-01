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


public class SelectScreen implements Screen{
	final GameLluviaMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private int opcionGame;
	private int opcionPerso;
	
	public SelectScreen(final GameLluviaMenu game, int opcion) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.opcionGame = opcion;
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
		
		if(opcionGame == 1) {
			font.draw(batch, "Selecciona el ave que quieras utilizar.\n Nivel F. Falcon. \n Nivel C. Colibri. \n ESC. Volver", 100, camera.viewportHeight/2-50);
			batch.end();
			
			if (Gdx.input.isKeyPressed(Input.Keys.F)) {
				opcionPerso=1;
				game.setScreen(new GameScreen(game, opcionGame, opcionPerso));
				dispose();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.C)) {
				opcionPerso=2;
				game.setScreen(new GameScreen(game, opcionGame, opcionPerso));
				dispose();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
				font.setColor(Color.WHITE);
				game.setScreen(new MainMenuScreen(game));
				dispose();
			}
		}
		if(opcionGame == 2) {
			font.draw(batch, "Selecciona el pez que quieras utilizar.\n Nivel A. Anguila. \n Nivel Z. Anguilatroz. \n ESC. Volver", 100, camera.viewportHeight/2-50);
			batch.end();
			
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				opcionPerso=1;
				game.setScreen(new GameScreen(game, opcionGame, opcionPerso));
				dispose();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
				opcionPerso=2;
				game.setScreen(new GameScreen(game, opcionGame, opcionPerso));
				dispose();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
				font.setColor(Color.WHITE);
				game.setScreen(new MainMenuScreen(game));
				dispose();
			}
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
