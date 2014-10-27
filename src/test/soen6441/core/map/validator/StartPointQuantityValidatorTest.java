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
import com.soen6441.core.map.validator.StartPointQuantityValidator;

/**
 * StartPointQuantityValidatorTest test the validity of StartPointQuantityValidator which is the subclass 
 * of PathValidator.
 * 
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */


@FixMethodOrder(MethodSorters.JVM)
public class StartPointQuantityValidatorTest {

	private PathValidator validator;
	private GridMap map;
	
	/**
	 * Method setUp() will run Once before this Test Class starts and will create the Objects required for
	 * all the Tests in this class.
	 */
	@Before
	public void setUp() {
		validator = new StartPointQuantityValidator();
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
     * testMultipleStartPoints() will test StartPointQuantityValidator when there are more than one startPoint on the map.
     * 
     */
	@Test
	public void testMultipleStartPoints() {
		MapPoint p1 = new MapPoint(1, 1);
		MapPoint p2 = new MapPoint(3, 2);
		Road r1 = new Road(Road.Type.START);
		Road r2 = new Road(Road.Type.START);
		map.setItem(r1, p1);
		map.setItem(r2, p2);
		validator.setMap(map);
		
		assertFalse(validator.validate());	// StartPointQuantityValidator must return False.
		assertEquals(validator.getErrorMessage(),StartPointQuantityValidator.MULTIPLE_STARTPOINT_ERROR);
		validator.setErrorMassage(null);	//Set the error Message to null for followong tests
	}
	
	
	/**
     * testOnlyOneStartPoint() will test StartPointQuantityValidator when there is only one startPoint on the map.
     * 
     */
	@Test
	public void testOnlyOneStartPoint() {
		MapPoint p1 = new MapPoint(1, 1);
		Road r1 = new Road(Road.Type.START);
		map.setItem(r1, p1);
		validator.setMap(map);
		
		assertTrue(validator.validate());	// StartPointQuantityValidator must return True.
		assertEquals(validator.getErrorMessage(),null);		// error msg must be null.
	}

	 
	/**
     * testStartPointMissing() will test StartPointQuantityValidator when there is no startPoint on the map.
     * 
     */
	@Test
	public void testStartPointMissing() {
		
		assertFalse(validator.validate());	// StartPointQuantityValidator must return False.
		assertEquals(validator.getErrorMessage(),StartPointQuantityValidator.MISSING_STARTPOINT_ERROR);
	}
}
