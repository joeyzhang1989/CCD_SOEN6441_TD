package test.soen6441.core.play;

import static org.junit.Assert.*;

import org.junit.Test;

import com.soen6441.core.play.Play;

public class PlaySingletonTest {

	Play play1 = Play.currentPlay();
	
	
	@Test
	public void sameTest() {
		Play play2 = Play.currentPlay();
		assertSame(play1,play2);
		
	}
	
	@Test
	public void destroyTest() {
		
		Play.destroy();
		Play play3 = Play.currentPlay();
		assertNotSame(play1,play3);
	}

}
