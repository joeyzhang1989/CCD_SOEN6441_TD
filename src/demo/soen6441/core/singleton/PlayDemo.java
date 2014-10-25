package demo.soen6441.core.singleton;

import com.soen6441.core.play.Play;

/**
 * @author chenglong
 * @version $Revision: 1.0 $
 */
public class PlayDemo {
	
	public void func1(){
		Play play = Play.currentPlay();
		play.setCoins(200);
		System.out.println("func1 " + play.getCoins());
		play.earnCoins(20);
		System.out.println("func1 " + play.getCoins());
	}
	
	public void func2(){
		Play play = Play.currentPlay();
		System.out.println("func2 " + play.getCoins());
		play.spendCoins(30);
		System.out.println("func2 " + play.getCoins());
		
	}
	
	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		
		PlayDemo demo = new PlayDemo();
		demo.func1();
		System.out.println("------------");
		System.out.println("Singleton works like a global varible");
		System.out.println("------------");
		demo.func2();
	}

}
