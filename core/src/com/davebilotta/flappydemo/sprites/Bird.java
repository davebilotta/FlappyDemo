package com.davebilotta.flappydemo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

	private static final int GRAVITY = -15;
	private static final int MOVEMENT = 100; // how far he moves on x axis
	private Vector3 position;
	private Vector3 velocity;
	private Rectangle bounds;
	private Sound flap;
	
	private Animation birdAnimation;
	
	private Texture birdTexture;
	
	public Bird(int x, int y) { 
		position = new Vector3(x, y, 0);
		velocity = new Vector3(0, 0, 0);
		//bird = new Texture("bird.png");
		birdTexture = new Texture("birdanimation.png");
		birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
		bounds = new Rectangle(x, y, birdTexture.getWidth() / 3, birdTexture.getHeight());
		flap = Gdx.audio.newSound(Gdx.files.internal("flap.ogg"));
	}
	
	public void update(float dt) { 
		birdAnimation.update(dt);
		if (position.y > 0) {
		velocity.add(0, GRAVITY, 0); // only add gravity to y axis
		}
		velocity.scl(dt);           // scale by delta time
		position.add(MOVEMENT * dt, velocity.y, 0);
		
		if (position.y < 0) {
			position.y = 0;
		}
		
		velocity.scl(1/dt);         // reverse scale so it can be re-done on next frame
		
		bounds.setPosition(position.x, position.y);
	}
	
	public void jump() { 
		velocity.y = 250;
		flap.play(0.5f);
	}
	
	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public TextureRegion getTexture() {
		return birdAnimation.getFrame();
	}

	public void setTexture(Texture bird) {
		this.birdTexture = bird;
	}
	
	public Rectangle getBounds() { 
		return bounds;
	}

	public void dispose() {
		// Aug 21, 2015 8:07:20 PM
		birdTexture.dispose();
		flap.dispose();
		
	}
}
