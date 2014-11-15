package test.soen6441.core.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.soen6441.core.map.PathManager;
import com.soen6441.core.map.Road;

<<<<<<< HEAD
/**
 * MapItemSelector test case to test the sorting and flitering methods to
 * re-arrange the MapItem list
 * 
 * @author Chenglong Zhang
 * @author Zhe Zhao
 * @author Mohammad Ali
 * @author MengYao Wang
 * @version $Revision: 1.0 $
 */

=======
>>>>>>> parent of d5d4e53... add javadoc to testcase
public class MapItemSelectorTest {
	private MapPath path;
	private GridMap map;

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

		p1 = new MapPoint(0, 0);
		p2 = new MapPoint(0, 2);
		p3 = new MapPoint(2, 2);
		p4 = new MapPoint(0, 1);
		p5 = new MapPoint(1, 2);

		List<MapPoint> starts = new ArrayList<MapPoint>();
		starts.add(p1);
		map.setStartPoints(starts);

		List<MapPoint> ends = new ArrayList<MapPoint>();
		ends.add(p3);
		map.setEndPoints(ends);

		path = new MapPath();
		path.addLocation(p1);
		path.addLocation(p2);
		path.addLocation(p3);

		List<MapPath> paths = new ArrayList<MapPath>();
		paths.add(path);
		map.setPaths(paths);
		map.getPathManager().generateRoadItemsFromPaths();

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
		MapPoint p6 = new MapPoint(1, 1);

		items = map.getItemSelector()
				.filterByCircularArea(p6, 2)
				.getItems();
		assertTrue(items.size() == 7); // 5 road Item plus 2 Critters
	}

	@Test
	public void testFilterByTypes() {
		List<MapItem> items;
		items = map.getItemSelector().filterByType(Road.class).getItems();

		assertTrue(items.size() == 5);

		items = map.getItemSelector().filterByType(Critter.class).getItems();
		assertTrue(items.size() == 2);

	}

	@Test
	public void testFilterByAmount() {
		List<MapItem> items;
		items = map.getItemSelector()
				.filterByAmount(2)
				.getItems();

		assertTrue(items.size() == 2);

		items = map.getItemSelector()
				.filterByAmount(2).filterByAmount(3)
				.getItems();
		assertTrue(items.size() == 2);

	}

	@Test
	public void testSortByDirectlyClosestToPoint() {
		List<MapItem> items;
		MapPoint p6 = new MapPoint(3, 2);
		c3 = new Critter();
		c3.setHp(10);
		map.addCritter(c3, p3);

		items = map.getItemSelector()
				.filterByType(Critter.class)
				.sortByDirectlyClosestToPoint(p1)
				.getItems();

		assertTrue(items.size() == 3);

		assertTrue(items.indexOf(c1) == 0);
		assertTrue(items.indexOf(c2) == 1);
		assertTrue(items.indexOf(c3) == 2);

		items = null;
		items = map.getItemSelector()
				.filterByType(Critter.class)
				.sortByDirectlyClosestToPoint(p6)
				.getItems();

		assertTrue(items.indexOf(c1) == 2);
		assertTrue(items.indexOf(c2) == 1);
		assertTrue(items.indexOf(c3) == 0);

	}

	@Test
	public void testGetLastLocation() {
		MapPoint p = path.getLastLocation();
		assertEquals(p, p3);
	}

	@Test
	public void testsortByOnPathClosestToEndPoint() {
		List<MapItem> items;
		items = map.getItemSelector()
				.filterByType(Critter.class)
				.sortByOnPathClosestToEndPoint()
				.getItems();

		assertTrue(items.size() == 2);
		assertTrue(items.indexOf(c1) == 1);
		assertTrue(items.indexOf(c2) == 0);

	}

	@Test
	public void testItemAddition() {
		List<MapItem> items;
		items = map.getItemSelector()
				.getItems();

		assertTrue(items.size() == 7);
	}

	@Test
	public void testsortByWeakest() {
		List<MapItem> items;
		items = map.getItemSelector()
				.filterByType(Critter.class)
				.sortByWeakest()
				.getItems();

		assertTrue(items.indexOf(c1) == 0);
		assertTrue(items.indexOf(c2) == 1);

		// Placing a new Critter on the Map.
		Critter c3 = new Critter();
		c3.setHp(7);
		map.addCritter(c3, p3);

		items = null;
		items = map.getItemSelector()
				.filterByType(Critter.class)
				.sortByWeakest()
				.getItems();

		assertTrue(items.indexOf(c3) == 0);
		assertTrue(items.indexOf(c1) == 1);
		assertTrue(items.indexOf(c2) == 2);

	}

	@Test
	public void testsortByStrongest() {
		List<MapItem> items;
		items = map.getItemSelector()
				.filterByType(Critter.class)
				.sortByStrongest()
				.getItems();

		assertTrue(items.indexOf(c2) == 0);
		assertTrue(items.indexOf(c1) == 1);

		// Placing a new Critter on the Map.
		Critter c3 = new Critter();
		c3.setHp(15);
		map.addCritter(c3, p3);

		items = null;
		items = map.getItemSelector()
				.filterByType(Critter.class)
				.sortByStrongest()
				.getItems();

		assertTrue(items.indexOf(c3) == 0);
		assertTrue(items.indexOf(c2) == 1);
		assertTrue(items.indexOf(c1) == 2);

	}

	@Test
	public void testfilterByExcluding() {
		List<MapItem> items;
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(map.getItemSelector().getItems());
		items = map.getItemSelector().
				filterByExcluding(c1).
				getItems();

		assertTrue(items.size() == filteredItems.size() - 1);

	}

	@Test
	public void testSortByRandom() {
		List<MapItem> items;
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(map.getItemSelector().getItems());
		items = map.getItemSelector()
				.sortByRandom()
				.getItems();

		assertFalse(filteredItems == items);
	}

}
