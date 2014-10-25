package com.soen6441.ui.map;

import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.Road;
import com.soen6441.core.tower.Tower;
import com.soen6441.ui.map.cell.RoadCell;
import com.soen6441.ui.map.cell.SceneryCell;
import com.soen6441.ui.map.cell.TowerCell;

/**
 * 
 * @author Zhe Zhao
 * @author JeanRaymondDaher 
 * @author Chenglong Zhang
 * @version $Revision: 1.0 $
 */

public class MapItemCellFactory {
	/**
	 * Method createScenaryCell.
	 * @return MapItemCell
	 */
	public static MapItemCell createScenaryCell(){
		return cellFromItem(null);
	}
	
	/**
	 * Method cellFromItem.
	 * @param item MapItem
	 * @return MapItemCell
	 */
	public static MapItemCell cellFromItem(MapItem item){
		MapItemCell cell = null;
		if(item == null) {
			cell = new SceneryCell();
		} else if (item instanceof Road) {
			cell = new RoadCell();
		} else if (item instanceof Tower) {
			cell = new TowerCell();
		}
		cell.setItem(item);
		return cell;
	}
}
