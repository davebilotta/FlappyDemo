package com.davebilotta.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.davebilotta.flappydemo.FlappyDemo;
import com.davebilotta.flappydemo.sprites.Bird;
import com.davebilotta.flappydemo.sprites.Tube;

public class PlayState extends State {
	
	private static final int TUBE_SPACING = 125; // spacing in between tubes (not including tubes themselves)
    private static final int TUBE_COUNT = 4;   // how many tubes total a game will have
	private static final int CAMERA_OFFSET = 80; 
	private Bird bird;
	private Texture bg;
	private Tube tube;
	private Texture ground;
	private Vector2 groundPos1, groundPos2;
	private static final int GROUND_Y_OFFSET = -30;
	private Sound die;
	private Sound coin;
	private int score;
	private BitmapFont font;
	
	private Array<Tube> tubes;
	
	protected PlayState(GameStateManager gsm) {
		super(gsm);
		bird = new Bird(50, 300);
		score = 0;
		
		cam.setToOrtho(false,FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);
		bg = new Texture("bg.png");
		ground = new Texture("ground.png"); 
		groundPos1 = new Vector2((cam.position.x - cam.viewportWidth/2), GROUND_Y_OFFSET);
		groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(), GROUND_Y_OFFSET);
		
		tubes = new Array<Tube>();
		for (int i = 1; i < (TUBE_COUNT + 1); i++) {
			tubes.add(new Tube (i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
		}
		
		coin = Gdx.audio.newSound(Gdx.files.internal("coin.ogg"));
		die = Gdx.audio.newSound(Gdx.files.internal("die.ogg"));
		
		font = new BitmapFont();
		font.setColor(Color.WHITE);
	}

	@Override
	public void handleInput() {
		// Aug 19, 2015 9:04:27 PM
		if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			bird.jump();
		}
	}

	@Override
	public void update(float dt) {
		// Aug 19, 2015 9:04:27 PM
		handleInput();
		updateGround();
		bird.update(dt);
				
		cam.position.x = bird.getPosition().x + CAMERA_OFFSET; // offset a little in front of bird
		
		
		for (Tube tube : tubes) { 
			// if tube is off left of screen 
			if (cam.position.x - (cam.viewportWidth/2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
				tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
			}
			
			// check collisions 
			if (tube.collides(bird.getBounds())) {
				//gsm.set(new PlayState(gsm));
				gsm.set(new MenuState(gsm));
			}
				
		}
		
		if (bird.getPosition().y <= (ground.getHeight() + GROUND_Y_OFFSET)) {
			//die.play();
			//gsm.set(new PlayState(gsm));
			gsm.set(new MenuState(gsm));
			
		}
		
		cam.update();
		
		for (Tube tube: tubes) { 
			if (!tube.collided) {
				Rectangle tubeCollider = new Rectangle(tube.getPosTopTube().x + (tube.TUBE_WIDTH/2), 0, 1, 1000);
			
				if (tubeCollider.overlaps(bird.getBounds())) { 
					tube.collided = true;
					score++;
					coin.play(0.5f);
					log("Score is now " + score);
			
			}
		}
	}
	}

	@Override
	public void render(SpriteBatch sb) {
		// Aug 19, 2015 9:04:27 PM
		sb.setProjectionMatrix(cam.combined); // adjust SpriteBatch to coordinate system of camera 
		sb.begin();
		sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
		sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
		
		for (Tube tube : tubes) { 
			sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
			sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
		}
		sb.draw(ground,groundPos1.x, groundPos1.y);
		sb.draw(ground,groundPos2.x, groundPos2.y);
		
		//font.draw(sb, "Score: " + score, 100, FlappyDemo.HEIGHT - 100);
		sb.end();
		
	}

	private void updateGround() {
		if ((cam.position.x - cam.viewportWidth/2) > groundPos1.x + ground.getWidth()) {
			groundPos1.add((ground.getWidth() * 2),0);
		}
		if ((cam.position.x - cam.viewportWidth/2) > groundPos2.x + ground.getWidth()) {
			groundPos2.add((ground.getWidth() * 2),0);
		}
	}
	
	@Override
	public void dispose() {
		// Aug 19, 2015 9:04:27 PM
		bg.dispose();
		bird.dispose();
		ground.dispose();
		die.dispose();
		coin.dispose();
		font.dispose();
		
		// For loop like this causes a nested iterator crash
		//	for (Tube tube: tubes) { 
		//	
		for (int i = 0; i < tubes.size; i++) { 
			tubes.get(i).dispose();
		}
		//}
		
	}
	
	public void log(String message) { 
		System.out.println(message);
	}

}
