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
import com.soen6441.core.map.validator.EndPointQuantityValidator;


/**
 * EndPointQuantityValidatorTest test the validity of EndPointQuantityValidator which is the subclass 
 * of PathValidator.
 * 
 * @author Mohammad Ali
 */


@FixMethodOrder(MethodSorters.JVM)
public class EndPointQuantityValidatorTest {

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
		validator = new EndPointQuantityValidator();
		map = new GridMap();
		
		map.setWidth(6);
		map.setHeight(4);
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
		p2 = null;
		r1 = null;
		r2 = null;
	}
	
	
	/*
     * testMultipleEndPoints() will test EndPointQuantityValidator when there are more than one EndPoint on the map.
     * 
     */
	
	@Test
	public void testMultipleEndPoints() {
		p1 = new MapPoint(1, 1);
		p2 = new MapPoint(3, 3);
		r1 = new Road(Road.Type.END);
		r2 = new Road(Road.Type.END);
		map.setItem(r1, p1);
		map.setItem(r2, p2);
		validator.setMap(map);
		
		assertFalse(validator.validate());	// EndPointQuantityValidator must return False.
		assertEquals(validator.getErrorMessage(),EndPointQuantityValidator.MULTIPLE_ENDPOINT_ERROR);
		validator.setErrorMassage(null);	//Set the error Message to null for followong tests
		
	}
	
	
	/*
     * testOnlyOneEndPoint() will test EndPointQuantityValidator when there is only one endPoint on the map.
     * 
     */
	
	@Test
	public void testOnlyOneEndPoint() {
		map.removeItem(map.getItem(p2));	// remove one end Point from the map
		
		assertTrue(validator.validate());	// EndPointQuantityValidator must return True.
		assertEquals(validator.getErrorMessage(),null);		// error msg must be null.
	}
   
	
	/*
     * testEndPointMissing() will test EndPointQuantityValidator when there is no EndPoint on the map.
     * 
     */
	
	@Test
	public void testEndPointMissing() {
		map.removeItem(map.getItem(p1));
		
		assertFalse(validator.validate());	// EndPointQuantityValidator must return False as no EndPoint on map now.
		assertEquals(validator.getErrorMessage(),EndPointQuantityValidator.MISSING_ENDPOINT_ERROR);
	}
}
