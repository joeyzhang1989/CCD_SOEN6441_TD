package test.soen6441.core.map.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.PathValidator;
import com.soen6441.core.map.Road;
import com.soen6441.core.map.validator.RoadQuantityValidator;

/**
 * RoadQuantityValidatorTest test the validity of RoadQuantityValidator which is the subclass 
 * of PathValidator.
 * 
 * @author Mohammad Ali
 */

@FixMethodOrder(MethodSorters.JVM)
public class RoadQuantityValidatorTest {

	static PathValidator validator;
	static GridMap map;
	static MapPoint p1 ;
	static Road r1 ;

	/*
	 * Method setUp() will run Once before this Test Class starts and will create the Objects required for
	 * all the Tests in this class.
	 */
	 
	@BeforeClass
	public static void setUp() {
		validator = new RoadQuantityValidator();
		map = new GridMap();
		
		map.setWidth(6);
		map.setHeight(4);
		p1 = new MapPoint(1, 1);
		r1 = new Road(Road.Type.NORMAL);
		
		map.setItem(r1, p1);
		validator.setMap(map);
	}
	
	/*
	 * Method TearDown() will run Once after this Test Class finsih running and will delete the references to 
	 * the objects, so that the garbage collector can collect them. 
	 */
	
	@AfterClass
	public static void TearDown() {
		validator = null;
		map = null;
		p1 = null;
		r1 = null;
	}
	
	/*
     * testRoadExist() will test RoadQuantityValidator when there is atleast one road item on the map.
     * 
     */
	
	@Test
	public void testRoadExist() {
	
		assertTrue(validator.validate());	// RoadQuantityValidator must return True.
		assertEquals(validator.getErrorMessage(),null);		// error msg must be null.
	}
	
	
	/*
     * testRoadMissing() will test RoadQuantityValidator when there is no road item on the map.
     * 
     */
	
	@Test
	public void testRoadMissing() {
		map.removeItem(map.getItem(p1));	// remove one end Point from the map
		
		assertFalse(validator.validate());	// RoadQuantityValidator must return False.
		assertEquals(validator.getErrorMessage(),RoadQuantityValidator.MISSING_ROAD_ERROR);		// error msg must be null.
	}
   
}
