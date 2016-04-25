package com.cutlitstudios.wordgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
 /*
public class GameScreen implements Screen {
  final Drop game;
 
	Texture dropImage;
	Texture bucketImage;
	//Sound dropSound;
	//Music rainMusic;
	OrthographicCamera camera;
	Rectangle bucket;
	Array<Rectangle> raindrops;
	long lastDropTime,gameStart;
	int dropsGathered, timeElapsed;
 
	public GameScreen(final Drop gam) {
		this.game = gam;
 
		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
 
		// load the drop sound effect and the rain background "music"
		//dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		//rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		//rainMusic.setLooping(true);
 
		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
 
		// create a Rectangle to logically represent the bucket
		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
		bucket.y = 20; // bottom left corner of the bucket is 20 pixels above
						// the bottom screen edge
		bucket.width = 64;
		bucket.height = 64;
 
		// create the raindrops array and spawn the first raindrop
		raindrops = new Array<Rectangle>();
		spawnRaindrop();
		gameStart = TimeUtils.nanoTime();
 
	}
 
	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800 - 64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}
 
	@Override
	public void render(float delta) {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
 
		// tell the camera to update its matrices.
		camera.update();
 
		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);
		timeElapsed=(int) ((TimeUtils.nanoTime() - gameStart)/1000000000);
		if(timeElapsed==60)
		{
			game.setScreen(new MainMenuScreen(game));
			dispose();
		}
		// begin a new batch and draw the bucket and
		// all drops
		game.batch.begin();
		game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);
		timeElapsed=(int) ((TimeUtils.nanoTime() - gameStart)/1000000000);
		game.font.draw(game.batch, "Time Left: " + (60-timeElapsed), 600, 480);
		game.batch.draw(bucketImage, bucket.x, bucket.y);
		for (Rectangle raindrop : raindrops) {
			game.batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		game.batch.end();
 
		// process user input
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			bucket.x += 200 * Gdx.graphics.getDeltaTime();
 
		// make sure the bucket stays within the screen bounds
		if (bucket.x < 0)
			bucket.x = 0;
		if (bucket.x > 800 - 64)
			bucket.x = 800 - 64;
 
		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnRaindrop();
 
		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we play back
		// a sound effect as well.
		Iterator<Rectangle> iter = raindrops.iterator();
		while (iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0)
				iter.remove();
			if (raindrop.overlaps(bucket)) {
				dropsGathered++;
				//dropSound.play();
				iter.remove();
			}
		}
	}
 
	@Override
	public void resize(int width, int height) {
	}
 
	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
		//rainMusic.play();
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
		dropImage.dispose();
		bucketImage.dispose();
		//dropSound.dispose();
		//rainMusic.dispose();
	}
 
}*/



public class GameScreen implements Screen {
	  final Drop game;
	 
		Texture blockImage,wand,star, submit, bg, unavail, selectedblk,wordblk,wrongblk;
		OrthographicCamera camera;
		Rectangle bucket;
		Array<Rectangle> raindrops;
		long lastDropTime,gameStart;
		int dropsGathered, timeElapsed,i, j, row, col, r,tx,ty,tx2,ty2,f,change,take,multiplier,timeLeft, timeMax,point;
		float chumma;
		int selected[][] = new int[4][4];
		int flag[]=new int[20],p,q,score,word,wordCnt;
		int act[][],avl[][],vis[][],tmp[][];
		int val[]={1,3,3,2,1,
				4,2,4,1,8,
				5,1,3,1,1,
				3,10,1,1,1,
				1,4,4,8,4,10};
		char maze[][],tc;
		String l,ts,message;
		
		Color limegreen= new Color(0x7fab2a);

		InputStream in;
		HashSet hash;
		public GameScreen(final Drop gam) throws IOException {
			this.game = gam;
			row=4;
			col=4;
			score=0;
			change=0;
			take=0;
			wordCnt=0;
			multiplier=1;
			point=0;
			timeLeft=1;
			timeMax=150;
			chumma=0;
			act=new int[row][col];
			avl=new int[row][col];
			tmp=new int[row][col];
			vis=new int[row][col];
			maze=new char[row][col];
			l="";
			message="";
			tx2=-1;
			ty2=-1;
			word=0;
			f=0;
		/*	unavail = new Texture(Gdx.files.internal("unavailblock.png"));
			selectedblk = new Texture(Gdx.files.internal("selectedblock.png"));
			wordblk = new Texture(Gdx.files.internal("wordblock.png"));
			wrongblk = new Texture(Gdx.files.internal("wrongblock.png"));
			// load the images for the droplet and the bucket, 64x64 pixels each
			blockImage = new Texture(Gdx.files.internal("availblock.png"));*/
			
			
			//blockImage = new Texture(Gdx.files.internal("greenblk.png"));
			
			unavail = new Texture(Gdx.files.internal("unavailblock.png"));
			selectedblk = new Texture(Gdx.files.internal("selectedblock.png"));
			wordblk = new Texture(Gdx.files.internal("wordblock.png"));
			wrongblk = new Texture(Gdx.files.internal("wrongblock.png"));
			// load the images for the droplet and the bucket, 64x64 pixels each
			blockImage = new Texture(Gdx.files.internal("availblock.png"));
			
			
			wand = new Texture(Gdx.files.internal("wand.jpg"));
			star = new Texture(Gdx.files.internal("star.jpg"));
			submit = new Texture(Gdx.files.internal("Submit.png"));

			bg = new Texture(Gdx.files.internal("Background.png"));
			
			
			FileHandle file = Gdx.files.internal("data/eng_com.dic");
			in = file.read();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	        StringBuilder out = new StringBuilder();
	        String line;
	        hash = new HashSet();
	        while ((line = reader.readLine()) != null) 
	        {
	            hash.add(line);
	        }

			
			
			// load the drop sound effect and the rain background "music"
			//dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
			//rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
			//rainMusic.setLooping(true);
	 
			// create the camera and the SpriteBatch
			camera = new OrthographicCamera();
			//camera.setToOrtho(false, 800, 480);
			camera.setToOrtho(false, 800, 1280);
			gameStart = TimeUtils.nanoTime();
			for(int i=0;i<4;i++)
			{	for(int j=0;j<4;j++)
					{
						act[i][j]=1;
						if(i==0||j==0||i==3||j==3)
						{
							avl[i][j]=1;
						}
					}
			}
			
			
			for(i=0;i<row;i++)
			{
				for(j=0;j<col;j++)
				{
					if(act[i][j]==1)
					{
						r=randInt(0,25);
						maze[i][j]=((char) (r+65));
					}
					else
						maze[i][j]=' ';
				}
			}
	 
		}
	 
		public static int randInt(int min, int max) 
		{

		    Random rand = new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    return randomNum;
		}
		public static boolean isPossible(int act[][],int avl[][],int tmp[][],int p,int q,int vis[][],int r,int c)
		{
				int i,j;
				
				//System.out.print(p+ " ");
				//System.out.println(q);
				//System.out.println("ASF");
				//System.out.println("tmp="+tmp[p][q]);
				//System.out.println("avl="+avl[p][q]);
				//System.out.println(p+" "+q);
				if(act[p][q]==0)
				{
					//System.out.println("FALSE NOT act");
					return false;
				}
				if(avl[p][q]==1 && tmp[p][q]==1)
				{
					//System.out.println("TRUE AVL AND IN WORD");
					return true;
				}
				if(tmp[p][q]!=1)
				{
					//System.out.println("FALSE NOT IN WORD");
					return false;
				}
				if(vis[p][q]==1)
				{
					//System.out.println("FALSE visited");
					return false;
				}
				vis[p][q]=1;
				if(p-1>=0)
				{
				if(act[p-1][q]==1)
				{
						
					if(isPossible(act,avl,tmp,p-1,q,vis,r,c))
					{
						//System.out.println("TRUE");
						return true;
					}
				}
				}
				if(p+1<r)
				{
				if(act[p+1][q]==1)
				{
						
					if(isPossible(act,avl,tmp,p+1,q,vis,r,c))
					{
						//System.out.println("TRUE");
						return true;
					}
				}
				}
				if(q-1>=0)
				{
				if(act[p][q-1]==1)
				{
						
					if(isPossible(act,avl,tmp,p,q-1,vis,r,c))
					{
						//System.out.println("TRUE");
						return true;
					}
				}
				}
				if(q+1<c)
				{
				if(act[p][q+1]==1)
				{
						
					if(isPossible(act,avl,tmp,p,q+1,vis,r,c))
					{
						//System.out.println("TRUE");
						return true;
					}
				}
				}
				//System.out.println("FALSE");
				return false;
		}
	 
		/* (non-Javadoc)
		 * @see com.badlogic.gdx.Screen#render(float)
		 */
		/* (non-Javadoc)
		 * @see com.badlogic.gdx.Screen#render(float)
		 */
		@Override
		public void render(float delta) {
			// clear the screen with a dark blue color. The
			// arguments to glClearColor are the red, green
			// blue and alpha component in the range [0,1]
			// of the color to be used to clear the screen.
			Gdx.gl.glClearColor(0, 0, 0.2f, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	 
			// tell the camera to update its matrices.
			camera.update();
			chumma+=0.006*multiplier;
			// tell the SpriteBatch to render in the
			// coordinate system specified by the camera.
			game.batch.setProjectionMatrix(camera.combined);
			timeElapsed=(int) ((TimeUtils.nanoTime() - gameStart)/1000000000);
			if(timeLeft <= 0)
			{
				game.setScreen(new MainMenuScreen(game));
				dispose();
			}
					
			// begin a new batch and draw the bucket and
			// all drops
			game.batch.begin();
			timeLeft=(int) (timeMax-timeElapsed-chumma);	
			
			for(int i=0;i<800;i+=512)
			{	for(int j=0;j<1280;j+=512)
				{
					game.batch.draw(bg, i, j);
				}
			}
			
			game.font.draw(game.batch, "Score: "+score , 0, 480);
			//game.font.draw(game.batch, " "+l, 0, 280);
			char[] chars=l.toCharArray();
			int msglen=l.length();
			int startx=150, starty=150;
			
			//game.font.draw(game.batch, " "+message, 300, 380);
			timeElapsed=(int) ((TimeUtils.nanoTime() - gameStart)/1000000000);
			game.font.draw(game.batch, "Time Left: " + timeLeft, 50, 1100);
			game.font.draw(game.batch, "x" + multiplier, 100, 100);
			//game.font.draw(game.batch, "" + TimeUtils.nanoTime(), 100, 200);
			game.batch.draw(wand, 300, 100);
			game.batch.draw(star, 400, 100);
			int temp1;
			for(int h=0;h<msglen;h++)
			{
				game.batch.draw(wordblk, startx+(h*128), starty);
				game.font.draw(game.batch, " "+chars[h], startx+(128*h)+32, starty+100);		
			}
			int k,m;
			/*for(m=0;m<4;m++)
			{
				for (k=0;k<4;k++)
				selected[m][k]=0;
			}*/
			int xx=0, yy=-128,xi=-1,xj=0;
			int i,j;
			int spx=144,spy=503;
			for(int count=1;count<=16;count++)
			{
				if(count%4==1)
				{
					xx=0;
					yy=yy+128;
					xj=0;
					xi++;
				}	
				else
				{
					xx+=128;
					xj++;
				}
				if(avl[3-xi][xj]==1)
					{	
						if(selected[3-xi][xj]==1)
							game.batch.draw(selectedblk, spx+xx, spy+yy);
						else
							game.batch.draw(blockImage, spx+xx, spy+yy);
					}
				else
					{
					if(selected[3-xi][xj]==1)
						game.batch.draw(wrongblk, spx+xx, spy+yy);
					else
						game.batch.draw(unavail, spx+xx, spy+yy);
				//game.font.draw(game.batch, "" + maze[i][j], (j*128)+50+spx, ((4-i)*128)+spy-25);
					}
			}
			
	
			
			for(i=0;i<row;i++)
			{
				for(j=0;j<col;j++)
				{	temp1=j;
		/*		if(temp1==0)
					//game.font.setColor(149, 73, 40, 1);//red
				//game.font.setColor(58, 139, 196, 1);//red
					game.font.setColor(149/255f, 73/255f, 40/255f, 1);//red
				else if(temp1==1)
					game.font.setColor(196/255f,139/255f,58/255f, 1);//orange
				//game.font.setColor(58,139,196, 1);//orange
				else if(temp1==2)
					game.font.setColor(196/255f,185/255f,58/255f,1);//yellow
				//game.font.setColor(58/255f,185/255f,196/255f,1);//yellow
				else
					game.font.setColor(127/255f,171/255f,42/255f,1);//lime green
				//game.font.setColor(42/255f,171/255f,127/255f,1);//lime green
					//game.font.setColor( 0.49804,0.67059,0.16471,1);*/
					
				
			/*	if(selected[i][j]==1)
				{
					game.font.setColor(196/255f,185/255f,58/255f,1);//yellow
				}*/
			/*	else
				{
					game.font.setColor(127/255f,171/255f,42/255f,1);//lime green
				}
				*/
				game.font.draw(game.batch, "" + maze[i][j], (j*128)+34+spx, ((4-i)*128)+spy-30);
				}
			}
			game.font.setColor(1,1,1,1);//Back to white
			
			//game.batch.draw(submit, 303, 347);
			game.batch.draw(submit, 336, 287);
			
			// process user input
			if (Gdx.input.isTouched())
			//if( Gdx.input.justTouched()) 
			{
				Vector3 touchPos = new Vector3();
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);
				//tx=(int) (4-(touchPos.y/64));		//game.font.draw(game.batch, "" + touchPos.x, 500, 480);
				//ty=(int) ((touchPos.x/64));
				tx=((int) (4-((touchPos.y-spy)/128)));		//game.font.draw(game.batch, "" + touchPos.x, 500, 480);
				ty=((int) (((touchPos.x-spx)/128)));
				//game.font.draw(game.batch, "tx" + tx, 100, 1100);
				//game.font.draw(game.batch, "ty" + ty, 100, 1000);
				//game.font.draw(game.batch, "touch x" + touchPos.x, 100, 1100);
				//game.font.draw(game.batch, "touch y" + touchPos.y, 100, 1000);
				/*game.font.draw(game.batch, "touch y" + touchPos.y, 250, 1000);
				game.font.draw(game.batch, "touch x" + touchPos.x, 250, 1100);*/
				if(touchPos.x > 300 && touchPos.x < 364 && touchPos.y > 100 && touchPos.y <165)
					change=1;
				else if(touchPos.x > 400 && touchPos.x < 464 && touchPos.y > 100 && touchPos.y <165)
					take=1;	
				//else if(touchPos.x > 256 || touchPos.y >256)
				//To detect submit button touch
				//else if(touchPos.x > 303 && touchPos.x < 431 && touchPos.y < 575 && touchPos.y > 347)
				else if(touchPos.x > 336 && touchPos.x < 464 && touchPos.y < 415 && touchPos.y > 287)
				{
					for(m=0;m<4;m++)
					{
						for (k=0;k<4;k++)
						selected[m][k]=0;
					}
					message="";
					point=0;
					for(p=0;p<row;p++)
					{
						for(q=0;q<col;q++)
						{
							if(tmp[p][q]==1)
							{
								 point+=val[maze[p][q]-65];
								 boolean f = isPossible(act,avl,tmp,p,q,vis,row,col);
								 if(f==false)
								 {
									 word=0;
									 //System.out.println(":(");
								 }
								 if(word!=0)
									 avl[p][q]=1;
								 for(int x = 0;x<row;x++)
								 {
										Arrays.fill(vis[x], 0);
								 }
							}
						}
						if(word==0)
							break;
					}
					if(word==1)
					{
						if(hash.contains(l.toLowerCase()))
						{	
							//message=message.concat("Word");
							hash.remove(l.toLowerCase());
							for(p=0;p<row;p++)
							{
								for(q=0;q<col;q++)
								{
									if(tmp[p][q]==1)
									{
										
										//timeElapsed-=val[maze[p][q]-65];									
										r=randInt(0,25);
										maze[p][q]=((char) (r+65));
										if(f==0)
										{
											score+=point*multiplier;
											timeMax+=point;
											point=0;
											wordCnt++;
											if(wordCnt==2)
												multiplier++;
											if(wordCnt==4)
												multiplier++;
											if(wordCnt==7)
												multiplier++;
											if(wordCnt==11)
												multiplier++;
											if(wordCnt==16)
												multiplier++;
											if(wordCnt==22)
												multiplier++;
											if(wordCnt==29)
												multiplier++;
											if(wordCnt==37)
												multiplier++;
											if(wordCnt==46)
												multiplier++;
											if(wordCnt==56)
												multiplier++;
											if(wordCnt==67)
												multiplier++;
											if(wordCnt==79)
												multiplier++;
											ArrayList<Integer>  lista = new ArrayList<Integer>();
											lista.add(65);
											lista.add(69);
											lista.add(73);
											lista.add(79);
											lista.add(85);
											Random r = new Random();
											int rnd=lista.get(r.nextInt(lista.size()));
											maze[p][q]=((char) (rnd));
											f=1;
										}
										
									}
									
								}
							}
							f=0;
							//score+=l.length();
						}
						else
						{
							//message=message.concat("Not Word");
						}
					}
					l="";	
					for(p=0;p<row;p++)
					{
						Arrays.fill(tmp[p], 0);
						Arrays.fill(vis[p], 0);
						Arrays.fill(avl[p], 0);
					}
					avl[0][0]=1;
					avl[0][1]=1;
					avl[0][2]=1;
					avl[0][3]=1;
					avl[1][0]=1;
					avl[1][3]=1;
					avl[2][0]=1;
					avl[2][3]=1;
					avl[3][0]=1;
					avl[3][1]=1;
					avl[3][2]=1;
					avl[3][3]=1;
				}
				else if(touchPos.x > spx && touchPos.x < (spx+512) && touchPos.y >spy && touchPos.y < (spy+512))					
				{
					selected[tx][ty]=1;
					if(avl[tx][ty]==1)
					{
						game.batch.draw(selectedblk, (ty*128)+spx, ((3-tx)*128)+spy);
					}
					else
					{
						game.batch.draw(wrongblk, (ty*128)+spx, ((3-tx)*128)+spy);
					}
					game.font.draw(game.batch, "" + maze[tx][ty], (ty*128)+spx+34, ((4-tx)*128)+spy-30);
								
					if(change==1)
					{
						r=randInt(0,25);
						maze[tx][ty]=((char) (r+65));
						change=0;
					}
					else if(take==1)
					{
						avl[tx][ty]=1;
						take=0;
					}
					
					
					else if(tx==tx2 && ty==ty2)
					{ 
						
					}
					else
					{
						tc=maze[tx][ty];
						ts=Character.toString(tc);
						l=l.concat(ts);
						tmp[tx][ty]=1;
						word=1;
					}
				}
				
				tx2=tx;
				ty2=ty;
				
				
			}
			game.batch.end();
			
			/*if (Gdx.input.isKeyPressed(Keys.LEFT))
				bucket.x -= 200 * Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Keys.RIGHT))
				bucket.x += 200 * Gdx.graphics.getDeltaTime();*/
	 
	
	 
			// check if we need to create a new raindrop
			
	 
			// move the raindrops, remove any that are beneath the bottom edge of
			// the screen or that hit the bucket. In the later case we play back
			// a sound effect as well.
			/*Iterator<Rectangle> iter = raindrops.iterator();
			while (iter.hasNext()) {
				Rectangle raindrop = iter.next();
				raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
				if (raindrop.y + 64 < 0)
					iter.remove();
				if (raindrop.overlaps(bucket)) {
					dropsGathered++;
					//dropSound.play();
					iter.remove();
				}
			}*/
		}
	 
		@Override
		public void resize(int width, int height) {
		}
	 
		@Override
		public void show() {
			// start the playback of the background music
			// when the screen is shown
			//rainMusic.play();
			
			//game.actionResolver.showToast("Hi!");
			
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
			blockImage.dispose();
			//dropSound.dispose();
			//rainMusic.dispose();
		}
	 
	}