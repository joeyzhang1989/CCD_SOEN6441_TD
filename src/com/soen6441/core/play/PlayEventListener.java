package com.soen6441.core.play;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public interface PlayEventListener {
	/**
	 * Method playWaveDidStart.
	 * @param play Play
	 */
	public void playWaveDidStart(Play play);
	/**
	 * Method playWaveDidSendAllCritter.
	 * @param play Play
	 */
	public void playWaveDidSendAllCritter(Play play);
	/**
	 * Method playAllCrittersDidKilled.
	 * @param play Play
	 */
	public void playAllCrittersDidKilled(Play play);
	/**
	 * Method playGameover.
	 * @param play Play
	 */
	public void playGameover(Play play);
	/**
	 * Method playSuccess.
	 * @param play Play
	 */
	public void playSuccess(Play play);
}
