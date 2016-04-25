package com.cutlitstudios.wordgame;

import com.cutlitstudios.wordgame.ActionResolver;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;




public class ActionResolverAndroid implements ActionResolver {
    Handler handler;
    Context context;

    public ActionResolverAndroid(Context context) {
        handler = new Handler();
        this.context = context;
    }

    public void showToast(final CharSequence text) {
        handler.post(new Runnable() {
            @Override
            public void run() {
            	final Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            	toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 250);
            	toast.show();
            	Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       toast.cancel(); 
                   }
            }, 500);
            }
        });
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
	public int getCoins()
	{
		SharedPreferences mPrefs;
        //Context context1=getApplicationContext();
        mPrefs = context.getSharedPreferences("label", 0);
        return mPrefs.getInt("coins", 5);
	}
	public void setCoins(int coins)
	{
		SharedPreferences mPrefs;
        //Context context1=getApplicationContext();
        mPrefs = context.getSharedPreferences("label", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt("coins", coins).commit();
	}

}