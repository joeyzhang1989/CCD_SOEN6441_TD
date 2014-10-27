package test.soen6441.ui.map;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.Road;
import com.soen6441.core.tower.Tower;
import com.soen6441.ui.map.MapItemCell;
import com.soen6441.ui.map.MapItemCellFactory;
import com.soen6441.ui.map.cell.RoadCell;
import com.soen6441.ui.map.cell.SceneryCell;
import com.soen6441.ui.map.cell.TowerCell;

/**
 */
public class MapItemCellFactoryTest {

	@Test
	public void testCreateScenaryCell() {
		MapItemCell cell = MapItemCellFactory.createScenaryCell();
		assertTrue(cell instanceof SceneryCell);
	}

	@Test
	public void testCellFromItem() {
		MapItem item;
		MapItemCell cell;
		
		item = new Road();
		cell = MapItemCellFactory.cellFromItem(item);
		assertTrue(cell instanceof RoadCell);
		assertTrue(cell.getItem() == item);

		item = new Tower();
		cell = MapItemCellFactory.cellFromItem(item);
		assertTrue(cell instanceof TowerCell);
		assertTrue(cell.getItem() == item);

		item = null;
		cell = MapItemCellFactory.cellFromItem(item);
		assertTrue(cell instanceof SceneryCell);
		assertTrue(cell.getItem() == item);
		
		
	}

}
