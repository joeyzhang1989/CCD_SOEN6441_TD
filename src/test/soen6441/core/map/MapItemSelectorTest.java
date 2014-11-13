package test.soen6441.core.map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.core.critter.Critter;
import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapItemSelector;
import com.soen6441.core.map.MapPath;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.Road;

public class MapItemSelectorTest {
	MapPath path;
	private GridMap map;
	private MapItem item1;
	private MapItem item2;
	private MapItem item3;
	private MapPoint p1;
	private MapPoint p2; 
	private MapPoint p3; 
	private MapPoint p4; 
	private MapPoint p5; 
	
	@Before
	public void setUp() throws Exception {
		map = new GridMap();
		map.setWidth(8);
		map.setHeight(8);
		
		path = new MapPath();
		
		p1 = new MapPoint(0, 0);
		p2 = new MapPoint(1, 1);
		p3 = new MapPoint(2, 2);
		p4 = new MapPoint(1, 0);
		p5 = new MapPoint(2, 0);
		
		
		path.addLocation(p1);
		path.addLocation(p2);
		path.addLocation(p3);
		item1 = new Road();
		map.setItem(item1, p1);

		item2 = new Road();
		map.setItem(item2, p2);
		
		item3 = new Road();
		p3 = new MapPoint(3, 3);
		map.setItem(item3, p3);
		
		Critter c1 = new Critter();
		c1.setHp(10);
		map.addCritter(c1, p4);

		Critter c2 = new Critter();
		c1.setHp(11);
		map.addCritter(c2, p5);
		
		
	}

	@Test
	public void testFilterByCircularArea() {
//		mapItemSelector.filterByCircularArea(p1, 1);
//		List<MapItem> filteredItems = new ArrayList<MapItem>();
//		filteredItems.addAll(items);
//		assertTrue(filteredItems == items);
	}

	@Test
	public void testFilterByTypes() {
		fail("Not yet implemented");
	}

	@Test
	public void testFilterByAmount() {
		List<MapItem> items;
		
		items = map.getItemSelector()
				.filterByAmount(2)
				.getItems();
		assertTrue(items.size() == 2);
		
		
		items = map.getItemSelector()
				.filterByAmount(2)
				.filterByAmount(3)
				.getItems();
		assertTrue(items.size() == 2);
		
	}

	@Test
	public void testSortByDirectlyClosestToPoint() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortByOnPathClosestToEndPoint() {
		List<MapItem> items;
		
		items = map.getItemSelector()
				.sortByOnPathClosestToEndPoint()
				.getItems();
		assertTrue();
		
//		mapItemSelector.sortByOnPathClosestToEndPoint();
//		List<MapItem> filteredItems = new ArrayList<MapItem>();
//		filteredItems.addAll(items);
//		assertFalse(filteredItems == items);
	}
	public void testsortByWeakest() {
//		mapItemSelector.sortByWeakest();
//		List<MapItem> filteredItems = new ArrayList<MapItem>();
//		filteredItems.addAll(items1);
//		assertFalse(filteredItems == items);
	}
	public void testsortByStrongest() {
//		mapItemSelector.sortByStrongest();
//		List<MapItem> filteredItems = new ArrayList<MapItem>();
//		filteredItems.addAll(items1);
//		assertFalse(filteredItems == items);
	}
	
	@Test
	public void testSortByRandom() {
//		mapItemSelector.sortByRandom();
//		List<MapItem> filteredItems = new ArrayList<MapItem>();
//		filteredItems.addAll(items);
//		assertFalse(filteredItems == items);
		
	}

}
