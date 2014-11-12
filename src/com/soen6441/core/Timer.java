package com.soen6441.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer implements ActionListener{
	private javax.swing.Timer timer;
	
	public Timer() {
		timer = new javax.swing.Timer(0, this);
		timer.setRepeats(false);
	}
	
	private int repeatTimes;
	private int currentRepeatTimes;
	
	public int getRepeatTimes() {
		return repeatTimes;
	}

	public void setRepeatTimes(int repeatTimes) {
		this.repeatTimes = repeatTimes;
	}

	public int getCurrentRepeatTimes() {
		return currentRepeatTimes;
	}


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
	
	
	

	public TimerListener getTimerListener() {
		return timerListener;
	}

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

	public boolean isRunning() {
		return timer.isRunning();
	}

	public void setDelay(int delay) {
		timer.setDelay(delay);
	}

	public int getDelay() {
		return timer.getDelay();
	}

	public void setRepeats(boolean flag) {
		timer.setRepeats(flag);
	}

	public boolean isRepeats() {
		return timer.isRepeats();
	}
	


}
