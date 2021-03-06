/**
 * 
 */
package test.soen6441.core.effect;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.MotivateEffect;
import com.soen6441.core.tower.BottleTower;

/**
 * This class tests two main method of MotivateEffect class.
 * @author sunhaiyang
 *
 * @version $Revision: 1.0 $
 */
public class MotivateEffectTest {
	
	private static final double DALTA = 0;
	private static MotivateEffect effect1 = new MotivateEffect();
	private static MotivateEffect effect2 = new MotivateEffect();
	private static BottleTower tower = new BottleTower();
	/**
	 * @throws Exception
	 * @throws java.lang.Exception
 	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		effect1.setType("MotivateEffect");
		effect2.setType("MotivateEffect");
		
		effect1.setEnhanceRate(2.0);
		effect2.setEnhanceRate(1.0);
		
		AffectableValue damage = new AffectableValue(50.0);
		AffectableValue cdTime = new AffectableValue(2.0);
		AffectableValue range = new AffectableValue(3.0);
		tower.setDamage(damage);
		tower.setCdTime(cdTime);
		tower.setRange(range);
		
	}

	/**
	 * Test method for {@link com.soen6441.core.effect.MotivateEffect#strongerThan(com.soen6441.core.effect.Effect)}.
	 */
	@Test
	public void testStrongerThan() {
		
		boolean result = effect1.strongerThan(effect2);
		assertTrue(result);
	}

	/**
	 * Test method for {@link com.soen6441.core.effect.MotivateEffect#affect()}.
	 */
	@Test
	public void testAffect() {
		
		tower.reinforce(effect1.getEnhanceRate());
		
		double expectedDamage = tower.getDamage().getOriginalValue () * effect1.getEnhanceRate();
		double resultDamage = tower.getDamage().getAffectedValue();
		assertEquals(expectedDamage, resultDamage,DALTA);
		
		double expectedCdTime = tower.getCdTime().getOriginalValue () / effect1.getEnhanceRate();
		double resultCdTime = tower.getCdTime().getAffectedValue();
		assertEquals(expectedCdTime,resultCdTime,DALTA);
		
		double expectedRange = tower.getRange().getOriginalValue () * effect1.getEnhanceRate();
		double resultRange = tower.getRange().getAffectedValue();
		assertEquals(expectedRange, resultRange, DALTA);
		
	}

}
