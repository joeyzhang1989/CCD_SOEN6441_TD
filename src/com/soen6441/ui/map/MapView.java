package com.soen6441.ui.map;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapPoint;
import com.soen6441.ui.common.GridPoint;
import com.soen6441.ui.common.GridView;
import com.soen6441.ui.common.GridViewCell;

/**
 * This class defines the view of the map.
 * 
 * @author Zhe Zhao
 * @author JeanRaymondDaher
 * @author Chenglong Zhang
 * 
 */
public class MapView extends GridView {

	/*
	 * Mark - Basic - Properties
	 */
	
	private GridMap map;
	private static final int _UNIT_LENGTH = 40;
	
	
	/*
	 * Getters & Setters
	 */
	
	/**
	 * Method getMap.
	 * @return GridMap
	 */
	public GridMap getMap() {
		return map;
	}

	/**
	 * Method setMap.
	 * @param map GridMap
	 */
	public void setMap(GridMap map) {
		this.map = map;
		initialSetup();
	}
	
	private void initialSetup(){
		this.setUnitWidth(_UNIT_LENGTH);
		this.setUnitHeight(_UNIT_LENGTH);
		this.setNumberOfRows(map.getHeight());
		this.setNumberOfColumns(map.getWidth());
		
		for (int i = 0; i < map.getWidth(); i++){
			for (int j = 0; j < map.getHeight(); j++){
				MapItem item = map.getItem(new MapPoint(i, j));
				MapItemCell cell = MapItemCellFactory.cellFromItem(item);
				cell.setSize(_UNIT_LENGTH, _UNIT_LENGTH);
				this.addCell(cell, new GridPoint(j, i));
			}
		}
	}
	
	@Override
	public void setSelectedCell(GridViewCell cell) {
		GridPoint gridPoint = cell.getPoint();
		this.map.setSelectedPoint(new MapPoint(gridPoint.getColumn(), gridPoint.getRow()));
		super.setSelectedCell(cell);
	}
}
