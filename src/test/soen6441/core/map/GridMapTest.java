package test.soen6441.core.map;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.Road;

/**
 */
public class GridMapTest {
	
	private static GridMap sharedGridMap;
	private static Road aRoadOnSharedGridMap;
	
	@BeforeClass
	public static void beforeClass(){
		sharedGridMap = new GridMap();
		sharedGridMap.setWidth(10);
		sharedGridMap.setHeight(10);
		
		aRoadOnSharedGridMap = new Road();
		sharedGridMap.setItem(aRoadOnSharedGridMap, new MapPoint(3, 3));
		
	}

	@Test
	public void setItemTest() {
		
		// location setup test
		Road road = new Road();
		MapPoint location = new MapPoint(4, 4);
		sharedGridMap.setItem(road, location);
		assertSame(road.getLocation(), location);
	}
	
	@Test
	public void getItemTest(){
		MapItem item;
		// get a current item
		item = sharedGridMap.getItem(new MapPoint(3, 3));
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
	public void removeItem(){
		sharedGridMap.removeItem(aRoadOnSharedGridMap);

		MapItem item;
		// get a removed item
		item = sharedGridMap.getItem(new MapPoint(3, 3));
		assertNull(item);
		
	}

}
