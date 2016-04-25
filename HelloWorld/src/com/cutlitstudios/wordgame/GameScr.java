package com.cutlitstudios.wordgame;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameScr implements Screen
{
	private GameStage stage;
	Drop game;
	public GameScr(Drop game) throws IOException {
    	this.game = game;
        stage = new GameStage();
    }
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.95f, 0.757f, 0.453f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
        //stage.draw();
        //stage.act(delta);		
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
}
