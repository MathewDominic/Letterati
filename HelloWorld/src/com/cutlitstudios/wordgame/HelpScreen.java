package com.cutlitstudios.wordgame;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

 
public class HelpScreen implements Screen {
 
  final Drop game;
  Texture bigmenu;
  TextureRegion menu;
  int touchFlag=0;
 
	OrthographicCamera camera;
	
 
	public HelpScreen(final Drop gam) throws IOException {
		game = gam;
 
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 1280);
		bigmenu = new Texture(Gdx.files.internal("Pic3.png"));
		menu = new TextureRegion(bigmenu, 0, 0, 800, 1280);
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
		game.batch.draw(menu,0,0);
		game.batch.end();
		/*if (game.actionResolver.getSignedInGPGS()) game.actionResolver.getLeaderboardGPGS();
		else game.actionResolver.loginGPGS(); */

		 
		if (Gdx.input.isTouched()) {
			try 
			{
				if(touchFlag==0)
				{
					bigmenu = new Texture(Gdx.files.internal("1first.png"));
					menu = new TextureRegion(bigmenu, 0, 0, 800, 1280);
				}
				else if(touchFlag==1)
				{
					bigmenu = new Texture(Gdx.files.internal("2second.png"));
					menu = new TextureRegion(bigmenu, 0, 0, 800, 1280);
				}
				else if(touchFlag==2)
				{
					bigmenu = new Texture(Gdx.files.internal("3third.png"));
					menu = new TextureRegion(bigmenu, 0, 0, 800, 1280);
				}
				else if(touchFlag==3)
				{
					bigmenu = new Texture(Gdx.files.internal("4fourth.png"));
					menu = new TextureRegion(bigmenu, 0, 0, 800, 1280);
				}
				else if(touchFlag==4)
				{
					bigmenu = new Texture(Gdx.files.internal("5fifth.png"));
					menu = new TextureRegion(bigmenu, 0, 0, 800, 1280);
				}
				else if(touchFlag==5)
				{
					bigmenu = new Texture(Gdx.files.internal("6sixth.png"));
					menu = new TextureRegion(bigmenu, 0, 0, 800, 1280);
				}
				else if(touchFlag==6)
				{
					game.setScreen(new GameScreen(game));
				}
				touchFlag++;
				//
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