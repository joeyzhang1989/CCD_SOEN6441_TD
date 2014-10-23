package test.soen6441.core.tower;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.soen6441.core.tower.Tower;
import com.soen6441.core.tower.TowerManager;
import com.soen6441.core.tower.TowerManagerFactory;

public class TowerManagerTest {
	
	private static TowerManagerFactory newTMF = TowerManagerFactory.currentManagerFactory();
	private static TowerManager newTM = newTMF.getManager("BottleTower");
	
	@Test
	public void createTowerTest() {
		Tower newTower = newTM.createTower();
		assertEquals(newTower.getLevel(),1);
	}
	
	
	@Test
	public void upgradeTowerTest() {
		
		Tower newTower = newTM.createTower();
		newTM.upgrade(newTower);
		assertEquals(newTower.getLevel(),2);
	}
	

}
