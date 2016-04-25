package com.cutlitstudios.wordgame;




import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 
public class Drop extends Game {
 
    SpriteBatch batch;
	BitmapFont font;
	public ActionResolver actionResolver;
	public Drop(ActionResolver actionResolver) 
	{
	    this.actionResolver = actionResolver;
	}
	public void create() 
	{
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.
		//font = new BitmapFont();
		font = new BitmapFont(Gdx.files.internal("data/Quicksand-Regular.fnt"),Gdx.files.internal("data/Quicksand-Regular_0.png"), false);
		//font.setScale((float) 0.9);
		
		font.setScale((float) 0.9);
		this.setScreen(new MainMenuScreen(this));
	}
	
	public BitmapFont getFont(){
		return font;
	}
 
	public void render()
	{
		super.render(); // important!
	}
 
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
 
}

