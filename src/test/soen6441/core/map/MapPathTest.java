package test.soen6441.core.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.core.map.MapPath;
import com.soen6441.core.map.MapPoint;

public class MapPathTest {
	
	MapPath path;
	MapPoint p1;
	MapPoint p2;
	MapPoint p3;
	
	@Before
	public void setup() {
		path = new MapPath();
		
		p1 = new MapPoint(0, 0);
		p2 = new MapPoint(1, 1);
		p3 = new MapPoint(2, 2);
		
		path.addLocation(p1);
		path.addLocation(p2);
		path.addLocation(p3);
	}

	@Test
	public void testGetFirstLocation() {
		MapPoint p = path.getFirstLocation();
		assertEquals(p, p1);
	}

	@Test
	public void testGetLastLocation() {
		MapPoint p = path.getLastLocation();
		assertEquals(p, p3);
	}

	@Test
	public void testAddLocation() {
		path.addLocation(new MapPoint());
		int length = path.getLocations().size();
		assertEquals(length, 4);
	}

}
