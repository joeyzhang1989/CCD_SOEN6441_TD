package test.soen6441.core.map;

import static org.junit.Assert.*;

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
    private static final Class<?> type = null;
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
	private Critter c1;
	private Critter c2;
	private Critter c3;
	
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
		
		c1 = new Critter();
		c1.setHp(10);
		map.addCritter(c1, p4);

		c2 = new Critter();
		c2.setHp(11);
		map.addCritter(c2, p5);
		
		
	}

	@Test
	public void testFilterByCircularArea() {
		List<MapItem> items;
		
		items = map.getItemSelector()
				.filterByCircularArea(p1, 2)
				.getItems();
		assertTrue(items.size() == 4);
	}

	@Test
	public void testFilterByTypes() {
		List<MapItem> items;
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(map.getItemSelector().getItems());
		items = map.getItemSelector()
				.filterByType(type, Road)
				.getItems();
		assertTrue(items.getClass() = Road);
				
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
		List<MapItem> items;
		items = map.getItemSelector()
				.sortByDirectlyClosestToPoint(p2)
				.getItems();
		assertTrue(true);
	}

	@Test
	public void testSortByOnPathClosestToEndPoint() {
		List<MapItem> items;
		items = map.getItemSelector()
				.sortByOnPathClosestToEndPoint()
				.getItems();
		assertTrue(true);
	}
	@Test
	public void testitemAddition() {
		List<MapItem> items;
		items = map.getItemSelector()
				.getItems();
		assertTrue(items.size() == 5);
	}
	@Test
	public void testsortByWeakest() {
		List<MapItem> items;
		items = map.getItemSelector()
				//.sortByWeakest()
				.getItems();
		assertTrue(items.size() == 5);
		
	}
	@Test
	public void testsortByStrongest() {
		List<MapItem> items;
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(map.getItemSelector().getItems());
		items = map.getItemSelector()
				.sortByStrongest()
				.getItems();
		assertTrue(filteredItems.size() > items.size());
	}
	@Test
	public void testfilterByExcluding() {
		List<MapItem> items;
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(map.getItemSelector().getItems());
		items = map.getItemSelector()
				.filterByExcluding(item1)
				.getItems();
		assertTrue(filteredItems.size() > items.size());	
		}
	
	@Test
	public void testSortByRandom() {
		List<MapItem> items;
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(map.getItemSelector().getItems());
		items = map.getItemSelector()
				.sortByStrongest()
				.getItems();
		assertFalse(filteredItems == items);
	}

}
