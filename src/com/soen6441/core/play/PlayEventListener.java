package com.soen6441.core.play;

public interface PlayEventListener {
	public void playWaveDidStart(Play play);
	public void playWaveDidSendAllCritter(Play play);
	public void playAllCrittersDidKilled(Play play);
	public void playGameover(Play play);
	public void playSuccess(Play play);
}
