/**
 * 
 */
package test.soen6441.core.effect;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.soen6441.core.critter.Critter;
import com.soen6441.core.effect.DotEffect;

/**
 * This class tests two main method of DotEffect class.
 * 
 * @author Haiyang Sun
 *
 * @version $Revision: 1.0 $
 */
public class DotEffectTest {
	
	private static DotEffect effect1 = new DotEffect();
	private static DotEffect effect2 = new DotEffect();
	
	private static Critter critter = new Critter();
	
	public static final String TEST_EFFECT_NAME = "BurnningEffect";
	private static final double DALTA = 0;
	
	

	/**
	 * @throws Exception
	 * @throws java.lang.Exception
 	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		effect1.setType(TEST_EFFECT_NAME);
		effect2.setType(TEST_EFFECT_NAME);
		
		effect1.setDotDamage(10);
		effect2.setDotDamage(5);
		
		effect1.setDotCdTime(1);
		effect2.setDotCdTime(2);
		
		effect1.setDotTimes(5);
		effect2.setDotTimes(6);
	}

	/**
	 * Test method for {@link com.soen6441.core.effect.DotEffect#strongerThan(com.soen6441.core.effect.Effect)}.
	 */
	@Test
	public void testStrongerThan() {
		boolean result = effect1.strongerThan(effect2);
		assertTrue(result);
	}

	/**
	 * Test method for {@link com.soen6441.core.effect.DotEffect#start()}.
	 */
	@Test
	public void testStart() {
		
		critter.addEffect(effect1);
		DotEffect resultEffect = (DotEffect) critter.getEffect(TEST_EFFECT_NAME);
		
		assertEquals(effect1.getOn(), critter);
		assertEquals(effect1, resultEffect);
		assertEquals(resultEffect.getDotDamage(),10,DALTA);
		assertEquals(resultEffect.getDotCdTime(),1,DALTA);
		assertEquals(resultEffect.getDotTimes(),5);
		
	}

}
