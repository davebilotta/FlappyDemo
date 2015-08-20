package com.davebilotta.flappydemo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.davebilotta.flappydemo.FlappyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = FlappyDemo.HEIGHT;
		config.height = FlappyDemo.HEIGHT;
		config.title = FlappyDemo.TITLE;
		
		new LwjglApplication(new FlappyDemo(), config);
	}
}
