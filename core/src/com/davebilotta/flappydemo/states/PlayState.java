package com.davebilotta.flappydemo.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davebilotta.flappydemo.FlappyDemo;

public class PlayState extends State {

	private Texture bird;
	
	protected PlayState(GameStateManager gsm) {
		super(gsm);
		bird = new Texture("bird.png");
		
		cam.setToOrtho(false,FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);
	}

	@Override
	public void handleInput() {
		// Aug 19, 2015 9:04:27 PM
		
	}

	@Override
	public void update(float dt) {
		// Aug 19, 2015 9:04:27 PM
		
	}

	@Override
	public void render(SpriteBatch sb) {
		// Aug 19, 2015 9:04:27 PM
		sb.setProjectionMatrix(cam.combined); // adjust SpriteBatch to coordinate system of camera 
		sb.begin();
		sb.draw(bird, 50, 50);
		sb.end();
		
	}

	@Override
	public void dispose() {
		// Aug 19, 2015 9:04:27 PM
		bird.dispose();
	}

}
