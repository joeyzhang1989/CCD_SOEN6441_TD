package test.soen6441.core.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.core.map.MapPoint;

/**
 * @author Zhe Zhao
 */
public class MapPointTestFunctional {
	
	MapPoint p1;
	MapPoint p2;
	MapPoint p3;
	MapPoint p4;
	
	@Before
	public void setup() {
		p1 = new MapPoint(10, 2);
		p2 = new MapPoint(2, 2);
		p3 = new MapPoint(0, 0);
		p4 = new MapPoint(-2, -2);
		
	}
	@Test
	public void testDistanceTo() {
		double distance = p1.distanceTo(p2);
		assertSame(distance, 8.0);
	}

	@Test
	public void testAdd() {
		MapPoint result = p1.add(p2);
		MapPoint expected = new MapPoint(12, 4);
		assertEquals(result, expected);
	}

	@Test
	public void testSubstract() {
		MapPoint result = p1.substract(p2);
		MapPoint expected = new MapPoint(8, 0);
		assertEquals(result, expected);
	}

	@Test
	public void testNormalize() {
		MapPoint result;
		MapPoint expected;
		
		result = p2.normalize();
		expected = new MapPoint(1, 1);
		assertEquals(result, expected);
		
		result = p3.normalize();
		expected = new MapPoint(0, 0);
		assertEquals(result, expected);
		
		result = p4.normalize();
		expected = new MapPoint(-1, -1);
		assertEquals(result, expected);
	}

}
