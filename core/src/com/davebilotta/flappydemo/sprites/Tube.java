package com.davebilotta.flappydemo.sprites;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tube {    
	public static final int TUBE_WIDTH = 52;        // width of texture
	private static final int FLUCTUATION = 130;     // can move up and down between 0 and 130
	private static final int TUBE_GAP = 100;        // difference between openings in tube 
	private static final int LOWEST_OPENING = 120;  // how low can bottom tube be?
	
	private Texture topTube, bottomTube;
	private Vector2 posTopTube, posBotTube;
	private Rectangle boundsTop, boundsBot;
	private Random rand;

	public Tube(float x) { 
		// TODO: Create a static texture that is used by all rather than having each Tube have a top/bottom tube  
		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");
		rand = new Random();
		
		posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
		posBotTube = new Vector2(x, (posTopTube.y - TUBE_GAP - bottomTube.getHeight()));
		
		boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
		boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
	}
	
	public Texture getTopTube() { 
		return topTube;
	}
	
	public Vector2 getPosTopTube() { 
		return posTopTube;
	}
	
	public Texture getBottomTube() {
		return bottomTube;
	}
	
	public Vector2 getPosBotTube() { 
		return posBotTube;
	}
	
	public void reposition(float x) { 
		posTopTube.set(x, (rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING));
		posBotTube.set(x, (posTopTube.y - TUBE_GAP - bottomTube.getHeight()));
		
		boundsTop.setPosition(posTopTube.x, posTopTube.y);
		boundsBot.setPosition(posBotTube.x, posBotTube.y);
	}
	
	public boolean collides(Rectangle player) { 
		return player.overlaps(boundsTop) || player.overlaps(boundsBot);
	}

	public void dispose() {
		// Aug 21, 2015 8:07:26 PM
		topTube.dispose();
		bottomTube.dispose();
		
	}
	
}
