package test.soen6441.core.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.Road;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class GridMapTestSelection {
	
	private GridMap map;
	private MapItem item1;
	private MapItem item2;
	private MapPoint p1;
	private MapPoint p2;
	
	@Before
	public void setUp(){
		map = new GridMap();
		map.setWidth(10);
		map.setHeight(10);
		
		item1 = new Road();
		p1 = new MapPoint(2, 2);
		map.setItem(item1, p1);

		item2 = new Road();
		p2 = new MapPoint(3, 3);
		map.setItem(item2, p2);
		
		map.setSelectedPoint(p1);
		map.setSelectedPoint(p2);
	}
	

	@Test
	public void testGetSelectedItem() {
		MapItem item = map.getSelectedItem();
		assertTrue(item == item2);
	}

	@Test
	public void testGetSelectedPoint() {
		MapPoint p = map.getSelectedPoint();
		assertTrue(p.equals(p2));
	}

}
