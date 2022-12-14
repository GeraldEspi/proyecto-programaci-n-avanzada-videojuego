package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.MainMenuScreen;

	public class GameLluviaMenu extends Game {

		private SpriteBatch batch;
		private BitmapFont font;

		public void create() {
			batch = new SpriteBatch();
			font = new BitmapFont(Gdx.files.internal("fuente.fnt"), Gdx.files.internal("fuente.png"),false);
			this.setScreen(new MainMenuScreen(this));
		}

		public void render() {
			super.render(); // important!
		}

		public void dispose() {
			batch.dispose();
			font.dispose();
		}

		public SpriteBatch getBatch() {
			return batch;
		}

		public BitmapFont getFont() {
			return font;
		}

	
		

	}
