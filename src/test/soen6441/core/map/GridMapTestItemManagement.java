package test.soen6441.core.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.Road;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class GridMapTestItemManagement {
	
	private static GridMap sharedGridMap;
	private static Road aRoadOnSharedGridMap;
	
	@BeforeClass
	public static void beforeClass(){
		sharedGridMap = new GridMap();
		sharedGridMap.setWidth(10);
		sharedGridMap.setHeight(10);
		
		
		
	}
	@Before
	public void setUp()
	{
		aRoadOnSharedGridMap = new Road();
		sharedGridMap.setItem(aRoadOnSharedGridMap, new MapPoint(5, 5));
	}

	@Test
	public void testSetItemMapItem() {
//		fail("Not yet implemented");
	}

	@Test
	public void testSetItemMapItemMapPoint() {
		
		// location setup test
		Road road = new Road();
		MapPoint location = new MapPoint(4, 4);
		sharedGridMap.setItem(road, location);
		assertSame(road.getLocation(), location);
	}

	@Test
	public void testGetItem() {
		MapItem item;
		// get a current item
		item = sharedGridMap.getItem(new MapPoint(5, 5));
		assertSame(item, aRoadOnSharedGridMap);
		
		// get a non existed item
		item = sharedGridMap.getItem(new MapPoint(8, 8));
		assertNull(item);
		
		// boundary test
		item = sharedGridMap.getItem(new MapPoint(10, 10));
		assertNull(item);
		
		item = sharedGridMap.getItem(new MapPoint(-1, -1));
		assertNull(item);
	}

	@Test
	public void testRemoveItem() {
		sharedGridMap.removeItem(aRoadOnSharedGridMap);

		MapItem item;
		// get a removed item
		item = sharedGridMap.getItem(new MapPoint(3, 3));
		assertNull(item);
	}

	@Test
	public void testGetAllItems() {
//		fail("Not yet implemented");
	}

}
