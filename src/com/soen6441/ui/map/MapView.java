package com.soen6441.ui.map;

import java.awt.Dimension;
import java.awt.Point;

import com.soen6441.core.critter.Critter;
import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.GridMapItemsListener;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.Road;
import com.soen6441.core.play.Play;
import com.soen6441.core.tower.Tower;
import com.soen6441.ui.common.GridPoint;
import com.soen6441.ui.common.GridView;
import com.soen6441.ui.common.GridViewCell;
import com.soen6441.ui.common.GridViewSelectionListener;
import com.soen6441.ui.map.cell.CritterView;
import com.soen6441.ui.map.cell.RoadView;
import com.soen6441.ui.map.cell.SceneryView;
import com.soen6441.ui.map.cell.TowerView;
import com.soen6441.ui.parallel.View;

/**
 * This class defines the view of the map.
 * 
 * @author Zhe Zhao
 * @author JeanRaymondDaher
 * @author Chenglong Zhang
 * 
 * @version $Revision: 1.0 $
 */
public class MapView extends View implements GridMapItemsListener, GridViewSelectionListener{
	
	/*
	 * Mark - Views - Properties
	 */

	private GridView gridView;
	private View critterLayerView;
	
	/*
	 * Mark - Views - Life Cycle
	 */
	 
	@Override
	protected void initSubviews() {
		super.initSubviews();
		gridView = new GridView();
		this.add(gridView);
		
		critterLayerView = new View();
		this.add(critterLayerView);
	}
	
	@Override
	protected void initEvents() {
		super.initEvents();
		gridView.setSelectionListener(this);
	}
	
	
	
	public Dimension suggestedSize() {
		return gridView.suggestedSize();
	}

	/*
	 * Mark - Basic - Properties
	 */
	
	private GridMap map;
	private static final int _UNIT_LENGTH = 40;
	
	
	/*
	 * Mark - Basic - Getters & Setters
	 */
	
	/**
	 * Method getMap.
	
	 * @return GridMap */
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
	
	/*
	 * Mark - Setup - Methods
	 */
	
	private void initialSetup(){
		gridView.setUnitWidth(_UNIT_LENGTH);
		gridView.setUnitHeight(_UNIT_LENGTH);
		gridView.setNumberOfRows(map.getHeight());
		gridView.setNumberOfColumns(map.getWidth());
		gridView.setSize(this.suggestedSize());
		critterLayerView.setSize(this.suggestedSize());
		
		for (int i = 0; i < map.getWidth(); i++){
			for (int j = 0; j < map.getHeight(); j++){
				MapItem item = map.getItem(new MapPoint(i, j));
				MapItemView itemView = itemViewFromItem(item);
				MapItemCell cell = cellFromItemView(itemView);
				gridView.addCell(cell, new GridPoint(j, i));
			}
		}
		map.setItemsListener(this);
		demoCritter();
	}
	
	
	private void demoCritter(){
		Play play = Play.currentPlay();
		Critter critter = play.nextWave().nextCritter();
		critter.setLocation(map.getStartPoints().get(0));
		map.setCritter(critter);
		
	}
	
	/*
	 * Mark - Grid View Selection Delegation - Properties
	 */
	
	private GridViewSelectionListener selectionListener;

	/*
	 * Mark - Grid View Selection Delegation - Methods
	 */
	
	@Override
	public void gridViewDidSelect() {
		GridPoint gridPoint = gridView.getSelectedCell().getPoint();
		this.map.setSelectedPoint(new MapPoint(gridPoint.getColumn(), gridPoint.getRow()));
		selectionListener.gridViewDidSelect();
	}
	
	/*
	 * Mark - Grid View Selection Delegation - Getters & Setters
	 */

	public GridViewSelectionListener getSelectionListener() {
		return selectionListener;
	}

	public void setSelectionListener(GridViewSelectionListener selectionListener) {
		this.selectionListener = selectionListener;
	}

	
	
	/*
	 * Mark - Item Events Handle - Methods
	 */
	 
	@Override
	public void gridMapDidAddItem(MapItem item) {
		if(item != null) {
			GridPoint point = mapPointToGridPoint(item.getLocation());
			GridViewCell scenaryCell = gridView.getCell(point);
			MapItemView itemView = itemViewFromItem(item);
			GridViewCell itemCell = cellFromItemView(itemView);
			gridView.replaceCell(scenaryCell, itemCell);
		}
	}

	@Override
	public void gridMapDidRemoveItem(MapItem item) {
		GridPoint point = mapPointToGridPoint(item.getLocation());
		GridViewCell itemCell = gridView.getCell(point);
		MapItemView itemView = itemViewFromItem(null);
		GridViewCell scenaryCell = cellFromItemView(itemView);
		gridView.replaceCell(itemCell, scenaryCell);
	}

	@Override
	public void gridMapDidAddCritter(Critter critter) {
		Point point = mapPointToSwingPoint(critter.getLocation());
		MapItemView critterView = itemViewFromItem(critter);
		critterView.setLocation(point);
		critterLayerView.add(critterView);
	}

	@Override
	public void gridMapDidRemoveCritter(Critter critter) {
		
	}
	
	private MapItemCell cellFromItemView(MapItemView itemView) {
		MapItemCell cell = new MapItemCell();
		cell.setSize(_UNIT_LENGTH, _UNIT_LENGTH);
		cell.add(itemView);
		return cell;
	}
	
	private MapItemView itemViewFromItem(MapItem item){
		MapItemView itemView = null;
		if(item == null) {
			itemView = new SceneryView();
		} else if (item instanceof Road) {
			itemView = new RoadView();
		} else if (item instanceof Tower) {
			itemView = new TowerView();
		} else if (item instanceof Critter) {
			itemView = new CritterView();
		}
		
		itemView.setItem(item);
		itemView.setSize(_UNIT_LENGTH, _UNIT_LENGTH);
		return itemView;
	}
	
	/*
	 * Mark - Grid Point & Map Point & Point , Conversion - Methods
	 */
	
	public static GridPoint mapPointToGridPoint(MapPoint mapPoint) {
		return new GridPoint(mapPoint.getGridedY(), mapPoint.getGridedX());
	}
	
	public static MapPoint gridPointToMapPoint(GridPoint gridPoint) {
		return new MapPoint(gridPoint.getColumn(), gridPoint.getRow());
	}
	
	public static Point mapPointToSwingPoint(MapPoint mapPoint) {
		int x = (int)(mapPoint.getX() * _UNIT_LENGTH + _UNIT_LENGTH * 0.5);
		int y = (int)(mapPoint.getY() * _UNIT_LENGTH + _UNIT_LENGTH * 0.5);
		return new Point(x, y);
	}
}
