package test.soen6441.core.map.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.PathValidator;
import com.soen6441.core.map.Road;
import com.soen6441.core.map.Road.RoadType;
import com.soen6441.core.map.validator.EndPointQuantityValidator;


/**
 * EndPointQuantityValidatorTest test the validity of EndPointQuantityValidator which is the subclass 
 * of PathValidator.
 * 
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */

public class EndPointQuantityValidatorTest {

    private PathValidator validator;
	GridMap map;
	
	/**
	 * Method setUp() will run Once before this Test Class starts and will create the Objects required for
	 * all the Tests in this class.
	 */
	@Before
	public void setUp() {
		validator = new EndPointQuantityValidator();
		map = new GridMap();
		
		map.setWidth(6);
		map.setHeight(4);
		validator.setMap(map);
	}
	
	/**
	 * Method tearDown() will run Once after this Test Class finsih running and will delete the references to 
	 * the objects, so that the garbage collector can collect them. 
	 */
	@After
	public void tearDown() {
		validator = null;
		map = null;
		
	}
	
	
	/**
     * testMultipleEndPoints() will test EndPointQuantityValidator when there are more than one EndPoint on the map.
     */
	@Test
	public void testMultipleEndPoints() {
		MapPoint p1 = new MapPoint(1, 1);
		MapPoint p2 = new MapPoint(3, 3);
		Road r1 = new Road(RoadType.END);
		Road r2 = new Road(RoadType.END);
		map.setItem(r1, p1);
		map.setItem(r2, p2);
		validator.setMap(map);
		
		assertFalse(validator.validate());	// EndPointQuantityValidator must return False.
		assertEquals(validator.getErrorMessage(),EndPointQuantityValidator.MULTIPLE_ENDPOINT_ERROR);
		validator.setErrorMassage(null);	//Set the error Message to null for followong tests
		
	}
	
	
	/**
     * testOnlyOneEndPoint() will test EndPointQuantityValidator when there is only one endPoint on the map.
     */
	@Test
	public void testOnlyOneEndPoint() {
		MapPoint p1 = new MapPoint(1, 1);
		Road r1 = new Road(RoadType.END);
		map.setItem(r1, p1);
		validator.setMap(map);
		
		assertTrue(validator.validate());	// EndPointQuantityValidator must return True.
		assertEquals(validator.getErrorMessage(),null);		// error msg must be null.
	}
   
	
	/**
     * testEndPointMissing() will test EndPointQuantityValidator when there is no EndPoint on the map.
     * 
     */
	@Test
	public void testEndPointMissing() {
		
		assertFalse(validator.validate());	// EndPointQuantityValidator must return False as no EndPoint on map now.
		assertEquals(validator.getErrorMessage(),EndPointQuantityValidator.MISSING_ENDPOINT_ERROR);
	}
}
