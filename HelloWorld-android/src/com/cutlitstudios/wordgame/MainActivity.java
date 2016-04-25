package com.cutlitstudios.wordgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cutlitstudios.wordgame.Drop;
/*import com.google.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.games.Games; */




public class MainActivity extends AndroidApplication 
{
	ActionResolverAndroid actionResolverAndroid;
	private static final String AD_UNIT_ID = "ca-app-pub-6916351754834612/3101802628";
	private static final String AD_UNIT_ID_BANNER = "ca-app-pub-6916351754834612/9855033027";
	private static final String AD_UNIT_ID_INTERSTITIAL = "ca-app-pub-6916351754834612/3808499421";
	//protected AdView adView;
	protected View gameView;
	//private InterstitialAd interstitialAd;
	
	

	Handler handler;
    Context context;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
	{
    	handler = new Handler();
        this.context = context; 
        super.onCreate(savedInstanceState);
        actionResolverAndroid = new ActionResolverAndroid(this);        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        cfg.useAccelerometer = true;
        cfg.useCompass = true;
        
  
   
       	//ads
       
       
        initialize(new Drop(actionResolverAndroid), cfg);
        
    }
	public void showToast(final CharSequence text)
	{
	        handler.post(new Runnable() 
	        {
	            @Override
	            public void run() 
	            {
	                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	            }
	        });
	}

	@Override
	public void onStart(){
		super.onStart();
		
	}

	@Override
	public void onStop(){
		super.onStop();
		
	}

	@Override
	public void onActivityResult(int request, int response, Intent data) {
		super.onActivityResult(request, response, data);
		
	}
	public int getHiScore()
	{
		SharedPreferences mPrefs;
        //Context context1=getApplicationContext();
        mPrefs = context.getSharedPreferences("label", 0);
        return mPrefs.getInt("hiScore", 0);
	}
	public void setHiScore(int hi)
	{
		SharedPreferences mPrefs;
        //Context context1=getApplicationContext();
        mPrefs = context.getSharedPreferences("label", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt("hiScore", hi).commit();
	} 
	
	/*@Override
	public boolean getSignedInGPGS() {
	
	}

	@Override
	

	@Override
	public void submitScoreGPGS(int score) {
		//Games.Leaderboards.submitScore(gameHelper.getApiClient(), "CgkI6574wJUXEAIQBw", score);
	}
	
	@Override
	public void unlockAchievementGPGS(String achievementId) {
	 // Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
	}
	
	@Override
	public void getLeaderboardGPGS() {
	  if (gameHelper.isSignedIn()) {
	    //startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), "CgkI2e3knOYaEAIQAA"), 100);
	  }
	  else if (!gameHelper.isConnecting()) {
	    loginGPGS();
	  }
	}

	@Override
	public void getAchievementsGPGS() {
	  if (gameHelper.isSignedIn()) {
	   // startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 101);
	  }
	  else if (!gameHelper.isConnecting()) {
	    loginGPGS();
	  }
	}
	
	@Override
	public void onSignInFailed() {
	}

	@Override
	public void onSignInSucceeded() {
	} */
}