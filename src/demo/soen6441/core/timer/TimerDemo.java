package demo.soen6441.core.timer;

import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;

public class TimerDemo implements TimerListener{
	
	private Timer timer;
	
	public TimerDemo(){
		timer = new Timer();
		timer.setRepeats(true);
		timer.setDelay(5000);
		timer.setRepeatTimes(3);
		timer.setTimerListener(this);
		timer.start();
//		timer.startImmediately();
		System.out.println("0");
	}

	@Override
	public void timerTick(Timer timer) {
		System.out.println(timer.getCurrentRepeatTimes());
	}


	public static void main(String[] args) {
		new TimerDemo();
		
		while(true){}
	}
}
