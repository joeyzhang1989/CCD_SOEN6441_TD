package test.soen6441.core.critter;
/**
 * This class test the critter manager and checks if the xml file is read correctly, and the multiplier is working accordingly.
 * 
 * @author JeanRaymondDaher
 *
 * @version $Revision: 1.1 $
 */
import static org.junit.Assert.*;

import org.junit.Test;

import com.soen6441.core.critter.Critter;
import com.soen6441.core.critter.CritterManager;
import com.soen6441.core.critter.CritterManagerFactory;
import com.soen6441.core.critter.CritterMultiplier;

public class CritterManagerTest {

	@Test
	public void testGenerateCritter() {
		CritterMultiplier x= new CritterMultiplier();
		x.setCritterName("Critter 1");
		x.setHpMultiplier(2);
		x.setRewardMultiplier(2.2);
		x.setSpeedMultiplier(3.2);
		
		Critter test=CritterManagerFactory.defaultFactory().getManager("Critter 1").generateCritter(x);
		assertEquals(test.getTotalHp(), 100);
	}

}
