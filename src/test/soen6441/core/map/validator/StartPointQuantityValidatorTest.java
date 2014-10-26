package test.soen6441.core.map.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
 */

public class StartPointQuantityValidatorTest {

	static PathValidator validator;
	static GridMap map;
	static MapPoint p1 ;
	static MapPoint p2 ;
	static Road r1 ;
	static Road r2 ;
	
	/*
	 * Method setUp() will run Once before this Test Class starts and will create the Objects required for
	 * all the Tests in this class.
	 */
	 
	@BeforeClass
	public static void setUp() {
		validator = new StartPointQuantityValidator();
		map = new GridMap();
		
		map.setWidth(6);
		map.setHeight(4);
	}
	
	/*
	 * Method TearDown() will run Once after this Test Class finsih running and will delete the references to 
	 * the objects, so taht garbage collector can collect them. 
	 */
	
	@AfterClass
	public static void TearDown() {
		validator = null;
		map = null;
		p1 = null;
		p2 = null;
		r1 = null;
		r2 = null;
	}
	
	
	/*
     * testMultipleStartPoints() will test StartPointQuantityValidator when there are more than one startPoint on the map.
     * 
     */
	
	@Test
	public void testMultipleStartPoints() {
		p1 = new MapPoint(1, 1);
		p2 = new MapPoint(2, 2);
		r1 = new Road(Road.Type.START);
		r2 = new Road(Road.Type.START);
		map.setItem(r1, p1);
		map.setItem(r2, p2);
		validator.setMap(map);
		
		assertFalse(validator.validate());	// StartPointQuantityValidator must return False.
		assertEquals(validator.getErrorMessage(),StartPointQuantityValidator.MULTIPLE_STARTPOINT_ERROR);
		validator.setErrorMassage(null);	//Set the error Message to null for followong tests
	}
	
	
	/*
     * testOnlyOneStartPoint() will test StartPointQuantityValidator when there is only one startPoint on the map.
     * 
     */
	
	@Test
	public void testOnlyOneStartPoint() {
		map.removeItem(map.getItem(p2));
		
		assertTrue(validator.validate());	// StartPointQuantityValidator must return True.
		assertEquals(validator.getErrorMessage(),null);		// error msg must be null.
	}

	 
	/*
     * testStartPointMissing() will test StartPointQuantityValidator when there is no startPoint on the map.
     * 
     */
	
	@Test
	public void testStartPointMissing() {
		map.removeItem(map.getItem(p1));
		
		assertFalse(validator.validate());	// StartPointQuantityValidator must return False.
		assertEquals(validator.getErrorMessage(),StartPointQuantityValidator.MISSING_STARTPOINT_ERROR);
	}
}
