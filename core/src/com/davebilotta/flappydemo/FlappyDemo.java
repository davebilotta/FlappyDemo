package com.davebilotta.flappydemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davebilotta.flappydemo.states.GameStateManager;
import com.davebilotta.flappydemo.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	
	public static final String TITLE = "Flappy Bird";
	private GameStateManager gsm;
	private	SpriteBatch batch;

	private Music bkgMusic;
	
	@Override
	public void create () {
		Gdx.gl.glClearColor(1, 0, 0, 1);

		batch = new SpriteBatch();
		gsm = new GameStateManager();
		bkgMusic = Gdx.audio.newMusic(Gdx.files.internal("bkg.mp3"));
		bkgMusic.setLooping(true);
		bkgMusic.setVolume(0.1f);
		bkgMusic.play();
		
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // wipes screen
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		
	}
	
	@Override
	public void dispose() { 
		bkgMusic.dispose();
	}
}
