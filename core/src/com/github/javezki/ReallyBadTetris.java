package com.github.javezki;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.github.javezki.screens.MainMenu;

public class ReallyBadTetris extends Game {

	@Override
	public void create() {
		// Set's the game to full screen
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		
		this.setScreen(new MainMenu());
	}

	@Override
	public void render() {
		super.render();	
	}

	@Override
	public void dispose() {
		
	}

}
