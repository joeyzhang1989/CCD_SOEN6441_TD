package test.soen6441.core.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.core.map.MapPoint;

/**
 */
public class MapPointTest {
	
	MapPoint p1;
	MapPoint p2;
	MapPoint p3;
	
	@Before
	public void setup() {
		p1 = new MapPoint(10.2, 10.4);
		p2 = new MapPoint(10.2, 10.4);
		p3 = new MapPoint(5.2, 5.4);
		
	}

	@Test
	public void testGetGridedX() {
		assertSame(p1.getGridedX(), 10);
	}

	@Test
	public void testGetGridedY() {
		assertSame(p1.getGridedY(), 10);
	}

	@Test
	public void testEqualsObject() {
		assertEquals(p1, p2);
	}
	
	@Test
	public void testDistanceTo() {
		assertTrue(p1.distanceTo(p2) == 0);
		assertTrue((int) p1.distanceTo(p3) == 7);
	}
	
}
