package test.soen6441.core.map.validator;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.PathValidator;
import com.soen6441.core.map.Road;
import com.soen6441.core.map.validator.EndPointQuantityValidator;
import com.soen6441.core.map.validator.StartPointQuantityValidator;

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
	
}
