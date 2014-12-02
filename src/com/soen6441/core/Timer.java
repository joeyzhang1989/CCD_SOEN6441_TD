package com.soen6441.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *@author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class Timer implements ActionListener{
	private javax.swing.Timer timer;
	
	public Timer() {
		timer = new javax.swing.Timer(0, this);
		timer.setRepeats(false);
	}
	
	private int repeatTimes;
	private int currentRepeatTimes;
	
	/**
	 * Method getRepeatTimes.
	 * @return int
	 */
	public int getRepeatTimes() {
		return repeatTimes;
	}

	/**
	 * Method setRepeatTimes.
	 * @param repeatTimes int
	 */
	public void setRepeatTimes(int repeatTimes) {
		this.repeatTimes = repeatTimes;
	}

	/**
	 * Method getCurrentRepeatTimes.
	 * @return int
	 */
	public int getCurrentRepeatTimes() {
		return currentRepeatTimes;
	}


	/**
	 * Method actionPerformed.
	 * @param e ActionEvent
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (timer.isRepeats() && repeatTimes > 0){
			currentRepeatTimes ++;
			timerListener.timerTick(this);
			if (currentRepeatTimes == repeatTimes) {
				this.stop();
			}
		} else {
			timerListener.timerTick(this);
		}
		
	}
	
	private TimerListener timerListener;
	
	
	

	/**
	 * Method getTimerListener.
	 * @return TimerListener
	 */
	public TimerListener getTimerListener() {
		return timerListener;
	}

	/**
	 * Method setTimerListener.
	 * @param timerListener TimerListener
	 */
	public void setTimerListener(TimerListener timerListener) {
		this.timerListener = timerListener;
	}

	public void start() {
		timer.setInitialDelay(timer.getDelay());
		currentRepeatTimes = 0;
		timer.start();
	}
	
	public void startImmediately(){
		currentRepeatTimes = 0;
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}

	/**
	 * Method isRunning.
	 * @return boolean
	 */
	public boolean isRunning() {
		return timer.isRunning();
	}

	/**
	 * Method setDelay.
	 * @param delay int
	 */
	public void setDelay(int delay) {
		timer.setDelay(delay);
	}

	/**
	 * Method getDelay.
	 * @return int
	 */
	public int getDelay() {
		return timer.getDelay();
	}
	
	/**
	 * Method setTimeIntervalSecond.
	 * @param second double
	 */
	public void setTimeIntervalSecond(double second) {
		this.setDelay((int)(second * 1000));
	}
	
	
	/**
	 * Method setRepeats.
	 * @param flag boolean
	 */
	public void setRepeats(boolean flag) {
		timer.setRepeats(flag);
	}

	/**
	 * Method isRepeats.
	 * @return boolean
	 */
	public boolean isRepeats() {
		return timer.isRepeats();
	}
	


}
