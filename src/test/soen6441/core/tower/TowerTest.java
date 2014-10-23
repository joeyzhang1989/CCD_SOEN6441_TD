package test.soen6441.core.tower;

import static org.junit.Assert.*;

import org.junit.Test;

import com.soen6441.core.tower.Tower;
import com.soen6441.core.tower.TowerManager;
import com.soen6441.core.tower.TowerManagerFactory;

public class TowerTest {
	
	private static TowerManagerFactory newTMF = TowerManagerFactory.currentManagerFactory();
	private static TowerManager newTM = newTMF.getManager("BottleTower");
	Tower tower = newTM.createTower();
	
	@Test
	public void copyToTest() {
		
		tower.setLevel(1);
		tower.setSellPrice(100);
		
		Tower newTower = new Tower();
		tower.copyTo(newTower);
		
		assertEquals(newTower.getLevel(),tower.getLevel());
		
	}

}
