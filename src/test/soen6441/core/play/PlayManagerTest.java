package test.soen6441.core.play;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapPath;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.play.Play;
import com.soen6441.core.play.PlayManager;

/**
 * PlayManagerTest class validates the correct implementation of class PlayManager.
 * 
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */


public class PlayManagerTest {
 
	private static File testFile;
	
	@BeforeClass
	public static void setup() {
		
		Play play = Play.currentPlay();
		play.buildDemo();
		testFile = new File("maps/PlayManagerTest.xml");
		new PlayManager().save(testFile, play);
	
	}
	
	@AfterClass
	public static void tearDown() {
		try {
			if (testFile != null && testFile.exists()) {
				testFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Test SaveTest () checks whether the file, which is created in setup() by calling PLayManager save() Method
	 *  is actually created.
	 */
	
	@Test
	public void saveTest() {
		
		assertTrue(testFile.exists());
		assertTrue(testFile.isFile());
		assertTrue(testFile.canRead());
		assertTrue(testFile.length() > 0);
		
	}

	
	/*
	 * Test readTest() will read the map file created in the setup () method and compare it with the values 
	 * defined in the buildDemo() Method of Play class 
	 */
	
	@Test
	public void readTest() {
		
		Play play = new PlayManager().read(testFile);
		GridMap map = play.getMap();
		
		List<MapPoint> startPoints = map.getStartPoints();
		List<MapPoint> endPoints = map.getEndPoints();
		List<MapPath> path = map.getPaths();

		int coinOnFile = play.getCoins();   
		int heightOnFile = map.getHeight(); 
		int widthOnFile = map.getWidth();  
		
		/*
		 * Comparing the actual values in the buildDemo() method of Class Play with the ones read from xml file.
		 */
		
		assertTrue(1000 == coinOnFile);	// Compare number of Coins
		assertTrue(4 == heightOnFile);	// Compare map Height
		assertTrue(6 == widthOnFile);	// Compare map Width
		
		
		// StartPoint Validation
		assertTrue(1 == startPoints.size());	//Compare number of startPoints
		assertTrue(1 == startPoints.get(0).getGridedX());	 //compare X value
		assertTrue(1 == startPoints.get(0).getGridedY()); 	//compare Y value
		
		
		// EndPoint Validation
		assertTrue(1 == endPoints.size());	//Compare number of endPoints
		assertTrue(2 == endPoints.get(0).getGridedX());	//compare X value
		assertTrue(3 == endPoints.get(0).getGridedY());	//compare Y value
				
		
		/*
		 * Path Validation
		 */
		
		List<MapPoint> locations = path.get(0).getLocations();
		
		assertTrue(1 == path.size());		//comapre number of paths
		assertTrue(4 == locations.size());	//comapre size of first path
		
		//compare mapPoint 1
		assertTrue(1 == locations.get(0).getGridedX());	//compare X value
		assertTrue(1 == locations.get(0).getGridedY());	//compare X value
		
		//compare mapPoint 2
		assertTrue(4 == locations.get(1).getGridedX());	//compare X value
		assertTrue(1 == locations.get(1).getGridedY());	//compare X value
		
		//compare mapPoint 3
		assertTrue(4 == locations.get(2).getGridedX());	//compare X value
	    assertTrue(3 == locations.get(2).getGridedY());	//compare X value
				
		//compare MapPoint 4
		assertTrue(2 == locations.get(3).getGridedX());	//compare X value
		assertTrue(3 == locations.get(3).getGridedY());	//compare X value
		
		
	}
	
}
