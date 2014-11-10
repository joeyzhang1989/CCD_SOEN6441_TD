package test.soen6441.core.tower;

import static org.junit.Assert.*;

import org.junit.Test;

import com.soen6441.core.tower.BottleTower;
import com.soen6441.core.tower.Tower;
import com.soen6441.core.tower.TowerManager;
import com.soen6441.core.tower.TowerManagerFactory;

/**
 * This test responses to test methods related to Tower Class
 * @author Haiyang Sun
 *
 * @version $Revision: 1.0 $
 */

public class TowerTest {
	
	private static TowerManagerFactory newTMF = TowerManagerFactory.defaultFactory();
	private static TowerManager newTM = newTMF.getManager("BottleTower");
	private Tower tower = newTM.createTower();
	
	/**
	 * Check whether properties of the copy and original are same.
	 */
	@Test
	public void copyToTest() {
		
		tower.setLevel(1);
		tower.setSellPrice(100);
		
		Tower newTower = new BottleTower();
		tower.copyTo(newTower);
		
		assertEquals(newTower.getLevel(),tower.getLevel());
		assertEquals(newTower.getSellPrice(),tower.getSellPrice());
		
	}

}
