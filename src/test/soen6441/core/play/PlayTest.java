package test.soen6441.core.play;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.core.play.Play;

public class PlayTest {
	
	Play play = Play.currentPlay();
	@Before
	public void setUpOriginalCoins() {
		play.setCoins(1000);
	}

	@Test
	public void earnCoinTest() {
		int originalCoins = play.getCoins();
		play.earnCoins(1000);
		assertEquals(play.getCoins(), originalCoins+1000);
	}
	
	@Test
	public void spendCoinTest() {
		int originalCoins = play.getCoins();
		play.spendCoins(1000);
		assertEquals(play.getCoins(), originalCoins-1000);
	}
}
