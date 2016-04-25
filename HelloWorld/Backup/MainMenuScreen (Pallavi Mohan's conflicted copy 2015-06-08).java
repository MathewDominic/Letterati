package com.cutlitstudios.wordgame;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

 
public class MainMenuScreen implements Screen {
 
  final Drop game;
 
	OrthographicCamera camera;
 
	public MainMenuScreen(final Drop gam) {
		game = gam;
 
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
 
	}
 
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//if (game.actionResolver.getSignedInGPGS()) game.actionResolver.getLeaderboardGPGS();
		//else game.actionResolver.loginGPGS();
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
 
		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Drop!!! ", 250, 300);
		game.font.draw(game.batch, "Tap anywhere to begin!", 250, 250);
		game.batch.end();
 
		if (Gdx.input.isTouched()) {
			try {
				game.setScreen(new GameScreen(game));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispose();
		}
	}
 
	@Override
	public void resize(int width, int height) {
	}
 
	@Override
	public void show() {
	}
 
	@Override
	public void hide() {
	}
 
	@Override
	public void pause() {
	}
 
	@Override
	public void resume() {
	}
 
	@Override
	public void dispose() {
	}
}