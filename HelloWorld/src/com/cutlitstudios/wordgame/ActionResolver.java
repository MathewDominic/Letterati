package com.cutlitstudios.wordgame;

public interface ActionResolver 
{
	  /*public boolean getSignedInGPGS();
	  public void loginGPGS();
	  public void submitScoreGPGS(int score);
	  public void unlockAchievementGPGS(String achievementId);
	  public void getLeaderboardGPGS();
	  public void getAchievementsGPGS();  
	  public void showToast(final CharSequence text);
	  public void onSignInFailed();
	  public void onSignInSucceeded();
	  //public void showOrLoadInterstital(); */
	  public void showToast(CharSequence text);
	  public int getHiScore();
	  public void setHiScore(int hi);
	  public int getCoins();
	  public void setCoins(int coins);
}
