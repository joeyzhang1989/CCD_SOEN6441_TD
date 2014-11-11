package test.soen6441.core.map;

import static org.junit.Assert.*;

import org.junit.Test;

import com.soen6441.core.map.MapPath;
import com.soen6441.core.map.MapPoint;

public class MapPathTestFunctional {

	@Test
	public void testGoAlong() {
		MapPath mapPath = new MapPath();
		mapPath.addLocation(new MapPoint(1, 1));
		mapPath.addLocation(new MapPoint(1, 3));
		mapPath.addLocation(new MapPoint(3, 3));
		mapPath.addLocation(new MapPoint(3, 4));
		
		double amount;
		
		MapPoint point;
		
		point = new MapPoint(1, 2);
		amount = mapPath.goAlong(point, 0.5);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(1, 2.5)));
		
		point = new MapPoint(1, 2);
		amount = mapPath.goAlong(point, 1);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(1, 3)));
		
		point = new MapPoint(1, 2);
		amount = mapPath.goAlong(point, 1.5);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(1.5, 3)));
		
		point = new MapPoint(1, 2);
		amount = mapPath.goAlong(point, 3);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(3, 3)));
		
		point = new MapPoint(1, 2);
		amount = mapPath.goAlong(point, 3.5);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(3, 3.5)));

		point = new MapPoint(1, 2);
		amount = mapPath.goAlong(point, 4);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(3, 4)));
		
		point = new MapPoint(1, 2);
		amount = mapPath.goAlong(point, 4.5);
		assertTrue(amount == 0.5);
		assertTrue(point.equals(new MapPoint(1, 2)));
		
		point = new MapPoint(3, 4);
		amount = mapPath.goAlong(point, 1);
		assertTrue(amount == 1);
		assertTrue(point.equals(new MapPoint(3, 4)));
		
	}

}
