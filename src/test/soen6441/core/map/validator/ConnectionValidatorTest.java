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
import com.soen6441.core.map.validator.ConnectionValidator;

/**
 * ConnectionValidatorTest tests the validity of ConnectionValidator which is the subclass 
 * of PathValidator.
 * 
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */

@FixMethodOrder(MethodSorters.JVM)
public class ConnectionValidatorTest {

	private PathValidator validator ;
	
	/**
	 * Method setUp() will run Once before this Test Class starts and will create the Objects required for
	 * all the Tests in this class.
	 */
	@Before
	public void setUp() {
		validator = new ConnectionValidator();
		GridMap map = new GridMap();
		
		map.setWidth(6);
		map.setHeight(4);
		
		MapPoint p1 = new MapPoint(1, 1);
		MapPoint p2 = new MapPoint(2, 1);
		MapPoint p3 = new MapPoint(3, 1);
		MapPoint p4 = new MapPoint(4, 1);
		
		Road r1 = new Road(Road.Type.START);
		Road r2 = new Road(Road.Type.NORMAL);
		Road r3 = new Road(Road.Type.NORMAL);
		Road r4 = new Road(Road.Type.END);
		
		map.setItem(r1, p1);
		map.setItem(r2, p2);
		map.setItem(r3, p3);
		map.setItem(r4, p4);
		validator.setMap(map);
		
	}
	
	/**
	 * Method {@link #tearDown()} will run Once after this Test Class finsih running and will delete the references to 
	 * the objects, so that the garbage collector can collect them. 
	 */
	@After
	public void tearDown() {
		validator = null;
		
	}
	
	/**
     * {@link #testValidConnection()} will test ConnectionValidator when the startPoint and EndPont are connected.
     */
	@Test
	public void testValidConnection() {
		
		assertTrue(validator.validate());	// ConnectionValidator must return True.
		assertEquals(validator.getErrorMessage(),null);
	}
	
	/**
     * {@link #testBrokenConnection()} will test ConnectionValidator when the startPoint and EndPont are connected.
     */
	@Test
	public void testBrokenConnection() {
		GridMap map=this.validator.getMap();
		map.removeItem(map.getItem(new MapPoint(3,1)));	// breaking the path
		
		assertFalse(validator.validate());	// ConnectionValidator must return False as path is broken.
		assertEquals(validator.getErrorMessage(),ConnectionValidator.BROKEN_PATH_ERROR);
	}
	
	/**
     * {@link #testPathsFromStartPoint ()} will test ConnectionValidator when there is more than one branch at startPoint.
     */
	@Test
	public void testPathsFromStartPoint () {
		GridMap map=this.validator.getMap();
		
		MapPoint p1 = new MapPoint(1, 2);
		Road r1 = new Road(Road.Type.NORMAL);
		map.setItem(r1, p1);
		
		assertFalse(validator.validate());	// ConnectionValidator must return False as StartPoint has more than one neighbour.
		assertEquals(validator.getErrorMessage(),ConnectionValidator.STARTPOINT_BRANCH_ERROR);
	}
	
	/**
     * testPathsFromEndPoint () will test ConnectionValidator when there is more than one branch at endPoint.
     */
	@Test
	public void testPathsFromEndPoint() {
		GridMap map=this.validator.getMap();
		
		MapPoint p1 = new MapPoint(4, 2);
		Road r1 = new Road(Road.Type.NORMAL);
		map.setItem(r1, p1);
		
		assertFalse(validator.validate());	// ConnectionValidator must return False as EndPoint has more than one neighbour.
		assertEquals(validator.getErrorMessage(),ConnectionValidator.ENDPOINT_BRANCH_ERROR);
	}
	
	/**
     * testRoadBranches() will test ConnectionValidator when there is more than one path in the map.
     */
	@Test
	public void testRoadBranches() {
		GridMap map=this.validator.getMap();
		
		MapPoint p1 = new MapPoint(2, 2);
		Road r1 = new Road(Road.Type.NORMAL);
		map.setItem(r1, p1);
		
		assertFalse(validator.validate());	// ConnectionValidator must return False as EndPoint has more than one neighbour.
		assertEquals(validator.getErrorMessage(),ConnectionValidator.MULTIPLE_BRANCH_ERROR);
	}
	
}
