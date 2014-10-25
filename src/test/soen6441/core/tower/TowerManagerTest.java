package test.soen6441.core.tower;

import static org.junit.Assert.*;

import org.junit.Test;

import com.soen6441.core.tower.Tower;
import com.soen6441.core.tower.TowerManager;
import com.soen6441.core.tower.TowerManagerFactory;

/**
 * This test responses to test method related to TowerManager class, which are createTower and upgradeTower.
 * @author Haiyang Sun
 *
 * @version $Revision: 1.0 $
 */

public class TowerManagerTest {
	
	private static TowerManagerFactory newTMF = TowerManagerFactory.currentManagerFactory();
	private static TowerManager newTM = newTMF.getManager("BottleTower");
	
	/**
	 * Check the level of the Tower created by TowerManager to test if it is created correctly.
	 */
	
	@Test
	public void createTowerTest() {
		Tower newTower = newTM.createTower();
		assertEquals(newTower.getLevel(),1);
	}
	
	/**
	 * Check the level of the Tower upgraded by TowerManager to test if it is upgraded correctly.
	 */
	
	@Test
	public void upgradeTowerTest() {
		
		Tower newTower = newTM.createTower();
		newTM.upgrade(newTower);
		assertEquals(newTower.getLevel(),2);
	}
	

}
