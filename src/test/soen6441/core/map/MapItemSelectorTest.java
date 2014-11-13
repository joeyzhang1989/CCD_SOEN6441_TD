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
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.Road;

public class MapItemSelectorTest {
	private GridMap map;
	private MapItemSelector mapItemSelector;
	private MapItem item1;
	private MapItem item2;
	private MapItem item3;
	private MapPoint p1;
	private MapPoint p2; 
	private MapPoint p3; 
	private double radius;
	private List<MapItem> items;
	private List<MapItem> items1;
	
	@Before
	public void setUp() throws Exception {
		map = new GridMap();
		map.setWidth(8);
		map.setHeight(8);
		
		radius = 1;
		item1 = new Road();
		p1 = new MapPoint(1, 1);
		map.setItem(item1, p1);

		item2 = new Road();
		p2 = new MapPoint(2, 2);
		map.setItem(item2, p2);
		
		item3 = new Road();
		p3 = new MapPoint(3, 3);
		map.setItem(item3, p3);
		
		items.add(item1);
		items.add(item2);
		items.add(item3);
		map.setSelectedPoint(p1);
		map.setSelectedPoint(p2);
		map.setSelectedPoint(p3);
		
		Critter c1 = new Critter();
		c1.setHp(10);
		map.setItem(c1, p1);

		Critter c2 = new Critter();
		c1.setHp(11);
		map.setItem(c2, p2);
		
		items1.add(c1);
		items1.add(c2);
		
	}

	@Test
	public void testFilterByCircularArea() {
		mapItemSelector.filterByCircularArea(p1, radius);
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(items);
		assertTrue(filteredItems == items);
	}

	@Test
	public void testFilterByTypes() {
		fail("Not yet implemented");
	}

	@Test
	public void testFilterByAmount() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortByDirectlyClosestToPoint() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortByOnPathClosestToEndPoint() {
		mapItemSelector.sortByOnPathClosestToEndPoint();
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(items);
		assertFalse(filteredItems == items);
	}
	public void testsortByWeakest() {
		mapItemSelector.sortByWeakest();
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(items1);
		assertFalse(filteredItems == items);
	}
	public void testsortByStrongest() {
		mapItemSelector.sortByStrongest();
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(items1);
		assertFalse(filteredItems == items);
	}
	
	@Test
	public void testSortByRandom() {
		mapItemSelector.sortByRandom();
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(items);
		assertFalse(filteredItems == items);
		
	}

}
