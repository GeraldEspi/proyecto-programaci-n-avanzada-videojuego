package com.mygdx.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		Timers.start();
		config.setForegroundFPS(60);
		config.setTitle("Gather Matter");
		config.setWindowedMode(1280, 720);
		
		new Lwjgl3Application(new GameLluviaMenu(), config);
	}
}

