package com.davebilotta.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davebilotta.flappydemo.FlappyDemo;

public class MenuState extends State {
	
	private Texture background;
	private Texture playButton;

	public MenuState(GameStateManager gsm) { 
		super(gsm);
		background = new Texture("bg.png");
		playButton = new Texture("playbtn.png");
		
	}
	@Override
	public void handleInput() {
		// Aug 19, 2015 8:26:55 PM
		if (Gdx.input.justTouched()) {
			gsm.set(new PlayState(gsm));
			dispose();
		}
	}

	@Override
	public void update(float dt) {
		// Aug 19, 2015 8:26:55 PM
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		// Aug 19, 2015 8:26:55 PM
		sb.begin();
		
		sb.draw(background,0,0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
		sb.draw(playButton, (FlappyDemo.WIDTH /2 - playButton.getWidth()/2), (FlappyDemo.HEIGHT/2 - playButton.getHeight()/2));
		
		sb.end();
				
	}
	@Override
	public void dispose() {
		// Aug 19, 2015 9:03:07 PM
		background.dispose();
		playButton.dispose();
	}


}
