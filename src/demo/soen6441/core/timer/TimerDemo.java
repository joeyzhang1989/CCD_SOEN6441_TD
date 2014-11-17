package demo.soen6441.core.timer;

import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;

/**
 * @author Zhe Zhao
 */
public class TimerDemo implements TimerListener{
	
	private Timer timer;
	
	public TimerDemo(){
		timer = new Timer();
		//timer.setRepeats(true);
		timer.setDelay(5000);
		timer.setRepeatTimes(3);
		timer.setTimerListener(this);
		timer.start();
//		Thread.sleep(2000);
//		timer.stop();
		
//		timer.startImmediately();
		System.out.println("0");
	}

	/**
	 * Method timerTick.
	 * @param timer Timer
	 * @see com.soen6441.core.TimerListener#timerTick(Timer)
	 */
	@Override
	public void timerTick(Timer timer) {
		System.out.println(timer.getCurrentRepeatTimes());
	}


	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		new TimerDemo();
		
		while(true){}
	}
}
