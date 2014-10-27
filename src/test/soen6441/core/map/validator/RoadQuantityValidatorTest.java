package test.soen6441.core.map.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
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

	 PathValidator validator;
	 GridMap map;
	
	 /*
	 * Method setUp() will run Once before this Test Class starts and will create the Objects required for
	 * all the Tests in this class.
	 */
	 
	@Before
	public void setUp() {
		validator = new RoadQuantityValidator();
		map = new GridMap();
		
		map.setWidth(6);
		map.setHeight(4);
		
		MapPoint p1 = new MapPoint(1, 1);
		MapPoint p2 = new MapPoint(2, 1);
		MapPoint p3 = new MapPoint(3, 1);
		
		
		Road r1 = new Road(Road.Type.START);
		Road r2 = new Road(Road.Type.NORMAL);
		Road r3 = new Road(Road.Type.END);
		
		map.setItem(r1, p1);
		map.setItem(r2, p2);
		map.setItem(r3, p3);
		validator.setMap(map);
	
	}
	/*
	 * Method TearDown() will run Once after this Test Class finsih running and will delete the references to 
	 * the objects, so that the garbage collector can collect them. 
	 */
	
	@After
	public void TearDown() {
		validator = null;
		map = null;
		
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
		GridMap map = this.validator.getMap();
		map.removeItem(map.getItem(new MapPoint(2,1)));	// delete the path
		map.removeItem(map.getItem(new MapPoint(3,1)));	// delete the endPoint
		MapPoint p1 = new MapPoint(2, 1); 
		Road r1 = new Road(Road.Type.END);	// Create an EndPoint
		map.setItem(r1, p1);	// Connect it directly with the startPoint
		
		assertFalse(validator.validate());	// RoadQuantityValidator must return False.
		assertEquals(validator.getErrorMessage(),RoadQuantityValidator.MISSING_ROAD_ERROR);	
	}
}
