package com.davebilotta.flappydemo.states;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {

	private Stack<State> states;
	
	public GameStateManager() { 
		states = new Stack<State>();
	}
	
	public void push(State state) {
		states.push(state);
	}
	
	/* Not returning anything when popping, so void */
	public void pop() {
		states.pop().dispose();
	}
	
	public void set(State state) { 
		states.pop().dispose();
		states.push(state);
	}
	
	public void update(float dt) {
		states.peek().update(dt);
	}
	
	public void render(SpriteBatch sb) { 
		states.peek().render(sb);
	}
}
