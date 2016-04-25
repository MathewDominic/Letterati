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
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


public class GameScreen implements Screen, InputProcessor 
{
	  final Drop game;
	  
	  private GameStage stage;
	  private Label scoreLabel;
	 
		Texture blockImage,wand,star, submit, submit2, bg, unavail, selectedblk,wordblk,wrongblk,b1,b2,b3, progress, pborder, delete;
		TextureRegion pbar,border, delete_reg;
		Sound click, wrong;
		OrthographicCamera camera;
		Rectangle bucket;
		Array<Rectangle> raindrops;
		long lastDropTime,gameStart;
		int reviveTime,randAch,oneTime=1,touchcount, timeElapsed,i, j, row, col, r,tx,ty,tx2,ty2,f,change,take,multiplier,timeLeft, timeMax,point,ttx2,tty2,displayTime,wordScore;
		float chumma;
		String[] ach = new String[8];
		int selected[][] = new int[4][4];
		int flag[]=new int[20],p,q,score,word,wordCnt,ttx,sec;
		private int act[][],avl[][],vis[][],tmp[][], newavail[][];
		int val[]={1,3,3,2,1,
				4,2,4,1,8,
				5,1,3,1,1,
				3,10,1,1,1,
				1,4,4,8,4,10};
		char distribution[]={'A','A','A','A','A','A','A','A','A','B',
				'B','C','C','D','D','D','D','E','E','E', 
				'E','E','E','E','E','E','E','E','E','F',
				'F','G','G','G','H','H','I','I','I','I',
				'I','I','I','I','I','J','K','L','L','L',
				'L','M','M','N','N','N','N','N','N','O',
				'O','O','O','O','O','O','O','P','P','Q',
				'R','R','R','R','R','R','S','S','S','S',
				'T','T','T','T','T','T','U','U','U','U',
				'V','V','W','W','X','Y','Y','Z'};
		int index[][];
		int shade[][];
		int shcol=-1;
		int gridcoords, delx, dely;
		int startx, starty, posx, posy;
		public enum GameState
		{
			Ready, Running, Paused, GameOver, Revive
		}
		public enum whatTouch
		{
			Down,Up
		}
		GameState state;
		whatTouch touch;
		char maze[][],tc;
		
		String l,ts,message;
		
		Color limegreen= new Color(0x7fab2a);
		//MyInputProcessor inputProcessor;
		

		InputStream in;
		HashSet hash;
		public GameScreen(final Drop gam) throws IOException {
			this.game = gam;
			
			stage = new GameStage();
			LabelStyle style = new LabelStyle();
			style.font = game.getFont();
			scoreLabel = new Label("100",style);
			scoreLabel.setFontScale(5);
			scoreLabel.setText("SCORE :100");
			stage.addActor(scoreLabel);
			scoreLabel.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(3000), Actions.fadeOut(1000)));

			
			state = GameState.Ready;
			row=4;
			col=4;
			score=0;
			change=0;
			take=0;
			wordCnt=0;
			multiplier=1;
			point=0;
			timeLeft=1;
			timeMax=150; //max time
			chumma=0;
			sec=0;
			displayTime=0;
			reviveTime=0;
			wordScore=0;
			act=new int[row][col];
			avl=new int[row][col];
			tmp=new int[row][col];
			vis=new int[row][col];
			maze=new char[row][col];
			shade=new int[row][col];
			index=new int[row][col];
			newavail=new int[row][col];
			startx=150;
			starty=850;
			posx=0;
			posy=0;
			l="";
			message="";
			tx2=-1;
			ty2=-1;
			word=0;
			f=0;
			touchcount=0;
			StringBuilder sb;
			for(i=0;i<row;i++)
			{
				for(j=0;j<col;j++)
					index[i][j]=-1;
			}
			//inputProcessor= new MyInputProcessor(); 
			Gdx.input.setInputProcessor(this);
			
			unavail = new Texture(Gdx.files.internal("unavailblock.png"));
			selectedblk = new Texture(Gdx.files.internal("selectedblock.png"));
			wordblk = new Texture(Gdx.files.internal("wordblock.png"));
			wrongblk = new Texture(Gdx.files.internal("wrongblock.png"));
			blockImage = new Texture(Gdx.files.internal("availblock.png"));

			click = Gdx.audio.newSound(Gdx.files.internal("music/click.mp3"));
			wrong = Gdx.audio.newSound(Gdx.files.internal("music/wrong.mp3"));
			
			/*unavail = new Texture(Gdx.files.internal("unavailblock - Copy.png"));
			selectedblk = new Texture(Gdx.files.internal("selectedblock - Copy.png"));
			wordblk = new Texture(Gdx.files.internal("wordblock - Copy.png"));
			wrongblk = new Texture(Gdx.files.internal("wrongblock - Copy.png"));
			blockImage = new Texture(Gdx.files.internal("availblock - Copy.png"));*/
			ach[0]="Form a 7 letter word";
			ach[1]="Form more than 50 words";
			ach[2]="Score more than 1000 points";

			
			progress = new Texture(Gdx.files.internal("Progress Bar.png"));
			pborder = new Texture(Gdx.files.internal("Progress border.png"));
			
			pbar= new TextureRegion(progress,0,0,762,53);
			border= new TextureRegion(pborder,0,0,762,53);
			wand = new Texture(Gdx.files.internal("wand.jpg"));
			star = new Texture(Gdx.files.internal("star.jpg"));
			submit = new Texture(Gdx.files.internal("Submit.png"));
			submit2 = new Texture(Gdx.files.internal("Submit2.png"));
			delete = new Texture(Gdx.files.internal("Delete3.png"));
			bg = new Texture(Gdx.files.internal("Background.png"));
			
			b1 = new Texture(Gdx.files.internal("Trials/Trial 2/1.png"));
			b2 = new Texture(Gdx.files.internal("Trials/Trial 2/2.png"));
			b3 = new Texture(Gdx.files.internal("Trials/Trial 2/3.png"));
			
			Sprite s_submit = new Sprite(submit);
			s_submit.setColor(0, 0, 0, 0.2f);
			
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
			initMaze();
			
	 
		}
	    public void initMaze()
	    {
	    	int i,j;
	    	for(i=0;i<4;i++)
			{	for(j=0;j<4;j++)
					{
						act[i][j]=1;
						if(i==0||j==0||i==3||j==3)
						{
							avl[i][j]=1;
							newavail[i][j]=2;
						}
						else
						{
							newavail[i][j]=0;
						}
					}
			}
			
			for(i=0;i<4;i++)
			{	for(j=0;j<4;j++)
					{
						shade[i][j]=0;
					}
			}
			
			for(i=0;i<row;i++)
			{
				for(j=0;j<col;j++)
				{
					if(act[i][j]==1)
					{
						r=randInt(0,97);
						//maze[i][j]=((char) (r+65));
						maze[i][j]=(char) distribution[r];
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
			if(oneTime==1)
			{
				achievementsConfig();
				oneTime=0;
			}
			// tell the camera to update its matrices.
			camera.update();
			chumma+=0.006*multiplier;
			// tell the SpriteBatch to render in the
			// coordinate system specified by the camera.
			game.batch.setProjectionMatrix(camera.combined);
			game.batch.begin();
			if(state == GameState.Paused)
			{
				for(int i=0;i<800;i+=512)
				{	for(int j=0;j<1280;j+=512)
					{
						game.batch.draw(bg, i, j);
					}
				}
				game.font.draw(game.batch, "Tap to resume" , 150, 700);
				/*if (Gdx.input.isKeyPressed(Keys.BACK))
				{
					state=GameState.Running;
				}
				*/
				Gdx.input.setCatchBackKey(true); 
				
				game.batch.end();
			}
			
			// game over screen
			else if(state == GameState.Revive)
			{
				for(int i=0;i<800;i+=512)
				{	for(int j=0;j<1280;j+=512)
					{
						game.batch.draw(bg, i, j);
					}
				}
				if(reviveTime<=0)
					state=GameState.GameOver;
				else
					reviveTime--;
				int coins=game.actionResolver.getCoins();
				game.font.draw(game.batch, "You have "+coins+" coins!" , 100, 800);
				game.font.draw(game.batch, "Revive for 1 coin!" , 100, 500);
				game.batch.end();
			}
			else if(state == GameState.GameOver)
			{
				for(int i=0;i<800;i+=512)
				{	for(int j=0;j<1280;j+=512)
					{
						game.batch.draw(bg, i, j);
					}
				}
				int hiScore=game.actionResolver.getHiScore();
				int coins=game.actionResolver.getCoins();
				if(score > hiScore)
				{
					game.actionResolver.setHiScore(score);
				}
				game.font.draw(game.batch, "Game Over" , 175, 1000);
				game.font.draw(game.batch, "High score is " +game.actionResolver.getHiScore(), 100, 700);
				game.font.draw(game.batch, "Your score is "+score , 100, 400);
				
				game.batch.end();
			}
			else
			{
				sec++;
				//timeElapsed=(int) ((TimeUtils.nanoTime() - gameStart)/1000000000);
				if (Gdx.input.isKeyPressed(Keys.BACK))
				{	
					state = GameState.Paused;
				}
	
				
										
				timeLeft=(int) (timeMax-sec/100-chumma);	
				
				for(int i=0;i<800;i+=512)
				{	for(int j=0;j<1280;j+=512)
					{
						game.batch.draw(bg, i, j);
					}
				}
				game.font.setScale((float) 0.7);
				game.font.draw(game.batch, ""+score , 550, 1100);
				delx=600;
				dely=87;
				game.batch.draw(delete,delx,dely);
				
				//game.font.draw(game.batch, " "+l, 0, 280);
				char[] chars=l.toCharArray();
				int msglen=l.length();
			
			
				
				//game.font.draw(game.batch, " "+message, 300, 380);
				game.batch.draw(border,28,1136);
				//timeElapsed=(int) ((TimeUtils.nanoTime() - gameStart)/1000000000);
				game.font.draw(game.batch, "Time Left: " + timeLeft, 50, 1100);
				game.font.setScale((float) 0.9);
				game.font.draw(game.batch, "x" + multiplier, 100, 180);
				updateProgressBar(timeLeft);
				if(timeLeft <= 0)
				{
					state = GameState.Revive;
					reviveTime = 125;
					
				}
				int temp1;
				for(int h=0;h<msglen;h++)
				{
					//game.batch.draw(wordblk, startx+(h*128), starty);
					game.font.setScale((float) 0.8);
					//game.font.draw(game.batch, " "+chars[h], startx+(128*h)+32, starty+100);
					if(msglen<6)
					game.font.draw(game.batch, " "+chars[h], startx+(75*h)+32, starty+100);
					else if(msglen<10)
						game.font.draw(game.batch, " "+chars[h], startx-100+(75*h)+32, starty+100);
					else
						game.font.draw(game.batch, " "+chars[h], startx-200+(75*h)+32, starty+100);
				}
				int k,m;
				int xx=0, yy=-128,xi=-1,xj=0;
				int i,j;
				int spx=144,spy=503;
				spy=303;
				spx=142;
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
						{
							game.batch.draw(selectedblk, spx+xx, spy+yy);	
						}	
						else
						{
							game.batch.draw(blockImage, spx+xx, spy+yy);
							
						}
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
				gridcoords= blockImage.getWidth()*4;
				Rectangle grid = new Rectangle(spx, spy, gridcoords, gridcoords);
				//game.font.setColor(196/255f,139/255f,58/255f, 1);//orange
				for(i=0;i<row;i++)
				{
					for(j=0;j<col;j++)
					{	
						temp1=j;
						if(selected[i][j]==0)
						{
							if(avl[i][j]==1)
								game.font.setColor(190/255f,135/255f,56/255f, 1);//orange190,135,56
							else
								game.font.setColor(100/255f,100/255f,100/255f,1);// white
						}
						else
						{
								game.font.setColor(1,1,1,1);// white
						}
						game.font.draw(game.batch, "" + maze[i][j], (j*128)+34+spx, ((4-i)*128)+spy-30);
						game.font.setScale((float) 0.3);
						game.font.draw(game.batch, "" + val[(int)maze[i][j]-65], (j*128)+10+spx, ((4-i)*128)+spy-40);
						game.font.setScale((float) 0.9);	
					}
				}
				game.font.setColor(1,1,1,1);//Back to white
				
				//game.batch.draw(submit, 303, 347);
				game.batch.draw(submit, 336, 87);
				
				// process user input
				if (Gdx.input.isTouched())
				{
					touch=whatTouch.Down;
					Vector3 touchPos = new Vector3();
					touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
					camera.unproject(touchPos);
					tx=((int) (4-((touchPos.y-spy)/128)));		
					ty=((int) (((touchPos.x-spx)/128)));
					
					//submit
					
					if(touchPos.x > 336 && touchPos.x < 464 && touchPos.y < 215 && touchPos.y > 87) 
					
					{
						
						game.batch.draw(submit2, 336, 87);
						resetMaze();
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
									 if(f==false)  //check if any letter is inaccessible
									 {
										 if(touchcount==1)
										 wrong.play();
										 if(Gdx.input.isPeripheralAvailable(Peripheral.Vibrator))
										 {
									         Gdx.input.vibrate(100);
									         game.actionResolver.showToast(maze[p][q]+ " is not accessible");
										 }
										 word=0;
										 //System.out.println(":(");
									 }
									 if(word!=0)
										 {
											 if(Gdx.input.isPeripheralAvailable(Peripheral.Vibrator))										      
										       //  Gdx.input.vibrate(100);
											 avl[p][q]=1;
										 }
										 
									 for(int x = 0;x<row;x++)
									 {
											Arrays.fill(vis[x], 0);
									 }
								}
							}
							if(word==0)
								break;
						}
						game.font.setScale((float) 0.7);
						if(word==1)
						{
							if(!(hash.contains(l.toLowerCase())) && !(l.equals("")))  //not a word
							{
								Gdx.input.vibrate(100);
								game.actionResolver.showToast("Not a valid word");
							}
							else
							{	
								//message=message.concat("Word");
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
												//game.actionResolver.showToast("Hi!");
												score+=point*multiplier;
												wordScore=point*multiplier;
												game.actionResolver.showToast("+"+wordScore+" points");
												displayTime=1;
												timeMax+=point;
												point=0;
												wordCnt++;
												if(l.length()>=5 && randAch==0)
												{
													game.actionResolver.showToast("Mission Accomplished! You earn 1 coin!");
													randAch=-1;
													int coins=game.actionResolver.getCoins();
													coins++;
													game.actionResolver.setCoins(coins);
												}
												if(wordCnt==10 && randAch==1)
												{
													game.actionResolver.showToast("Mission Accomplished! You earn 1 coin!");
													randAch=-1;
													int coins=game.actionResolver.getCoins();
													coins++;
													game.actionResolver.setCoins(coins);
												}
												if(score>50 && randAch==2)
												{
													game.actionResolver.showToast("Mission Accomplished! You earn 1 coin!");
													randAch=-1;
													int coins=game.actionResolver.getCoins();
													coins++;
													game.actionResolver.setCoins(coins);
												}
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
							
						}
						if(displayTime<=10)
						{
							game.font.draw(game.batch, "+"+wordScore, 650,1100);
							displayTime++;
							if(displayTime==10)
								displayTime=0;
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
					//else if(touchPos.x > spx && touchPos.x < (spx+512) && touchPos.y >spy && touchPos.y < (spy+512))	
					
					// HIT DELETE BUTTON
					
					else if(touchPos.x > delx && touchPos.x < (delx+128) && touchPos.y >dely && touchPos.y < (dely+128))
					{	touchcount++;
						if(l.length()>0 && touchcount==1)
						{
							resetMaze();
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
						}
					}
					else if (grid.contains(touchPos.x, touchPos.y))  //click on letter
					{
						touchcount++;
						if(touchcount==1 || (tx!=tx2 && ty!=ty2))
							click.play();
						//if(selected[tx][ty]==0)
						{
						
						
						if(avl[tx][ty]==1)
						{
							
							game.batch.draw(selectedblk, (ty*128)+spx, ((3-tx)*128)+spy);
						}
						else
						{
							game.batch.draw(wrongblk, (ty*128)+spx, ((3-tx)*128)+spy);
							game.font.setColor(42/255f,171/255f,127/255f,1);//lime green
							//game.font.draw(game.batch, "Unavailable", 150,150);	
							game.font.setColor(1,1,1,1);
							
						}
						game.font.draw(game.batch, "" + maze[tx][ty], (ty*128)+spx+34, ((4-tx)*128)+spy-30);								
						if(change==1)
						{
							r=randInt(0,97);
							maze[tx][ty]=(char) distribution[r];
							change=0;
						}
						else if(take==1)
						{
							avl[tx][ty]=1;
							take=0;
						}
						else if(tx==tx2 && ty==ty2 && touchcount>1)
						{ 
							
						}
						//IF CLICK ON ALREADY SELECTED
						
						else if(selected[tx][ty]==1 && index[tx][ty]>-1 && l.length()>0)
						{
							/*StringBuilder sb = new StringBuilder(l);
							sb.deleteCharAt(index[tx][ty]);
							l = sb.toString();
							selected[tx][ty]=0;
							for(i=0;i<row;i++)
							{	for(j=0;j<col;j++)
								{
									if(index[i][j]>index[tx][ty])
										index[i][j]--;
								}
							}
							index[tx][ty]=-1;
							if(newavail[tx][ty]==1)
							{
								newavail[tx][ty]=0;
							}*/
							
						}
						
						else
						{
							tc=maze[tx][ty];
							ts=Character.toString(tc);
							l=l.concat(ts);
							tmp[tx][ty]=1;
							word=1;
							index[tx][ty]=l.length()-1;
							selected[tx][ty]=1;
							if(avl[tx][ty]==1)
								makeNeighboursAvailabe(tx,ty);
						}
					
					}
						
						
				}
				
				
					
					tx2=tx;
					ty2=ty;
				}
				
				
				
				game.batch.end();
			}
			
			//stage.draw();
	        //stage.act(delta);
		
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
		public void hide() 
		{
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
		}
		
		public void updateProgressBar(int time)
		{
			float progressLength;
			TextureRegion barLength;
			progressLength=(pbar.getRegionWidth())*((float)time/150);
			if(progressLength>pbar.getRegionWidth()) 
				progressLength=pbar.getRegionWidth();
			barLength = new TextureRegion(pbar, 0, 0, (int)progressLength, pbar.getRegionHeight());
			//barLength = new TextureRegion(pbar, 0, 0, pbar.getRegionWidth(), pbar.getRegionHeight());
			game.batch.draw(barLength,28,1136);
		//	game.font.draw(game.batch, ""+progressLength+"  "+pbar.getRegionWidth(), 28, 1190);
		}
		public void resetMaze()
		{
			int m,k;
			for(m=0;m<4;m++)
			{
				for (k=0;k<4;k++)
				{ selected[m][k]=0;
					shade[m][k]=0;
					index[m][k]=-1;
					if(m==0||k==0||m==3||k==3)
					{
						newavail[m][k]=2;
					}
					else
					{
						newavail[m][k]=0;
					}
				}
			}
		}
		public void makeNeighboursAvailabe(int tx, int ty)
		{
			if(tx+1 <4 && avl[tx+1][ty]!=1)
			{
				newavail[tx+1][ty]=1;
				avl[tx+1][ty]=1;
				if(selected[tx+1][ty]==1)
					makeNeighboursAvailabe(tx+1,ty);
			}
			if(tx-1 >=0 && avl[tx-1][ty]!=1)
			{
				newavail[tx-1][ty]=1;
				avl[tx-1][ty]=1;
				if(selected[tx-1][ty]==1)
					makeNeighboursAvailabe(tx-1,ty);
			}
			if(ty+1 <4 && avl[tx][ty+1]!=1)
			{
				newavail[tx][ty+1]=1;
				avl[tx][ty+1]=1;
				if(selected[tx][ty+1]==1)
					makeNeighboursAvailabe(tx,ty+1);
			}
			if( ty-1 >=0 && avl[tx][ty-1]!=1)
			{
				newavail[tx][ty-1]=1;
				avl[tx][ty-1]=1;
				if(selected[tx][ty-1]==1)
					makeNeighboursAvailabe(tx,ty-1);
			}
			
		}
		public void fadeOut(Texture picture, int x, int y)
		{
			
		}

		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			int sty=1280-starty;
			touch=whatTouch.Down;
			touchcount=0;
			// TODO Auto-generated method stub
			if(state==GameState.Paused)
			{
				if(screenY<500)
				{
					dispose();
					game.setScreen(new MainMenuScreen(game));	
				}
				else
					state=GameState.Running;
			}
			
			// game over config
			else if(state == GameState.Revive)
			{
				int coins=game.actionResolver.getCoins();
				if(coins>0)
				{
					sec=sec-5000;
					coins=game.actionResolver.getCoins();
					coins--;
					game.actionResolver.setCoins(coins);
					state=GameState.Running;
					reviveTime=125;
				}
				//else
				//{
					//state=GameState.GameOver;
				//}
					
			}
			else if(state == GameState.GameOver)
			{
				/*if(screenY>500)
				{
					sec=sec-5000;
					int coins=game.actionResolver.getCoins();
					coins--;
					game.actionResolver.setCoins(coins);
					state=GameState.Running;
				}
				else*/
				//{
					dispose();
					game.setScreen(new MainMenuScreen(game));
				//}
			}
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
		public void achievementsConfig()
		{
			randAch = randInt(0,2);
			game.actionResolver.showToast(ach[randAch]);
		}
	 
	}