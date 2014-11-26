package com.soen6441.ui.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;
import com.soen6441.core.critter.Critter;
import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.GridMapItemsListener;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.Road;
import com.soen6441.core.tower.Tower;
import com.soen6441.ui.common.GridPoint;
import com.soen6441.ui.common.GridView;
import com.soen6441.ui.common.GridViewCell;
import com.soen6441.ui.common.GridViewSelectionListener;
import com.soen6441.ui.map.itemView.CritterView;
import com.soen6441.ui.map.itemView.RoadView;
import com.soen6441.ui.map.itemView.SceneryView;
import com.soen6441.ui.map.itemView.TowerView;
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
public class MapView extends View implements GridMapItemsListener, GridViewSelectionListener, TimerListener{
	
	/*
	 * Mark - Views - Properties
	 */

	private GridView gridView;
	private View critterLayerView;
	private Map<Critter, CritterView> critterViews;
	
	/*
	 * Mark - Views - Life Cycle
	 */
	 
	@Override
	protected void initSubviews() {
		super.initSubviews();

		gridView = new GridView();
		this.add(gridView);
		
		critterLayerView = new View();
		critterLayerView.setBackground(new Color(0, 0, 0, 0));
		this.add(critterLayerView);
		
		critterViews = new HashMap<Critter, CritterView>();
	}
	
	@Override
	protected void initEvents() {
		super.initEvents();
		gridView.setSelectionListener(this);
	}
	
	
	
	/**
	 * Method suggestedSize.
	 * @return Dimension
	 */
	public Dimension suggestedSize() {
		return gridView.suggestedSize();
	}

	/**
	 * Method paint.
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintAttack((Graphics2D)g);
		if (map.getSelectedPoint() != null) {
			MapItem item = map.getSelectedItem();
			
			if (item != null && item instanceof Tower){
				paintRange((Graphics2D)g);
			}
		}
	}
	
	/**
	 * Method paintRange.
	 * @param g Graphics2D
	 */
	private void paintRange(Graphics2D g) {
		Tower tower = (Tower) map.getSelectedItem();
		Point center = mapPointToSwingPoint(tower.getLocation());
		center.x += _UNIT_LENGTH * 0.5;
		center.y += _UNIT_LENGTH * 0.5;
		int range = (int)(tower.getRange().getAffectedValue() * _UNIT_LENGTH);
		
		for (int i = 0; i < 4; i ++) {
			g.setColor(new Color(0x00, 0xCC, 0xCC, 256 * (6-i) / 8 - 1));
			g.drawOval(center.x - range, center.y - range, range * 2, range * 2);
			range --;
		}
	}
	
	private void paintAttack(Graphics2D g) {
		List<MapItem> items = map.getItemSelector()
								 .filterByType(Tower.class)
								 .getItems();
		g.setColor(new Color(0x00CCCC));
		
		for (MapItem item : items) {
			
			Tower tower = (Tower)item;
			if (tower.isShowLinkingEffect()) {

				Point sourcePoint = mapPointToSwingPoint(item.getLocation());
				sourcePoint.x += _UNIT_LENGTH / 2;
				sourcePoint.y += _UNIT_LENGTH / 2;
				
				List<MapItem> targets = tower.getTargets();
				
				for (MapItem target : targets) {
					Point targetPoint = mapPointToSwingPoint(target.getLocation());
					targetPoint.x += _UNIT_LENGTH / 2;
					targetPoint.y += _UNIT_LENGTH / 2;
					
					g.drawLine(sourcePoint.x, sourcePoint.y, targetPoint.x, targetPoint.y);
				}
			}
		}
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
	}
	
	
	/*
	 * Mark - Grid View Selection Delegation - Properties
	 */
	
	private GridViewSelectionListener selectionListener;

	/*
	 * Mark - Grid View Selection Delegation - Methods
	 */
	
	/**
	 * Method gridViewDidSelect.
	 * @see com.soen6441.ui.common.GridViewSelectionListener#gridViewDidSelect()
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

	/**
	 * Method getSelectionListener.
	 * @return GridViewSelectionListener
	 */
	public GridViewSelectionListener getSelectionListener() {
		return selectionListener;
	}

	/**
	 * Method setSelectionListener.
	 * @param selectionListener GridViewSelectionListener
	 */
	public void setSelectionListener(GridViewSelectionListener selectionListener) {
		this.selectionListener = selectionListener;
	}

	
	
	/*
	 * Mark - Item Events Handle - Methods
	 */
	 
	/**
	 * Method gridMapDidAddItem.
	 * @param item MapItem
	 * @see com.soen6441.core.map.GridMapItemsListener#gridMapDidAddItem(MapItem)
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

	/**
	 * Method gridMapDidRemoveItem.
	 * @param item MapItem
	 * @see com.soen6441.core.map.GridMapItemsListener#gridMapDidRemoveItem(MapItem)
	 */
	@Override
	public void gridMapDidRemoveItem(MapItem item) {
		GridPoint point = mapPointToGridPoint(item.getLocation());
		GridViewCell itemCell = gridView.getCell(point);
		MapItemView itemView = itemViewFromItem(null);
		GridViewCell scenaryCell = cellFromItemView(itemView);
		gridView.replaceCell(itemCell, scenaryCell);
	}

	/**
	 * Method gridMapDidAddCritter.
	 * @param critter Critter
	 * @see com.soen6441.core.map.GridMapItemsListener#gridMapDidAddCritter(Critter)
	 */
	@Override
	public void gridMapDidAddCritter(Critter critter) {
		Point point = mapPointToSwingPoint(critter.getLocation());
		CritterView critterView = (CritterView) itemViewFromItem(critter);
		critterView.setLocation(point);
		
		critterLayerView.add(critterView);
		critterViews.put(critter, critterView);
		this.repaint();
	}

	/**
	 * Method gridMapDidRemoveCritter.
	 * @param critter Critter
	 * @see com.soen6441.core.map.GridMapItemsListener#gridMapDidRemoveCritter(Critter)
	 */
	@Override
	public void gridMapDidRemoveCritter(Critter critter) {
		CritterView critterView = critterViews.get(critter);
		critterLayerView.remove(critterView);
		critterViews.remove(critter);
	}
	
	/**
	 * Method cellFromItemView.
	 * @param itemView MapItemView
	 * @return MapItemCell
	 */
	private MapItemCell cellFromItemView(MapItemView itemView) {
		MapItemCell cell = new MapItemCell();
		cell.setSize(_UNIT_LENGTH, _UNIT_LENGTH);
		cell.add(itemView);
		return cell;
	}
	
	/**
	 * Method itemViewFromItem.
	 * @param item MapItem
	 * @return MapItemView
	 */
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
	
	/**
	 * Method mapPointToGridPoint.
	 * @param mapPoint MapPoint
	 * @return GridPoint
	 */
	public static GridPoint mapPointToGridPoint(MapPoint mapPoint) {
		return new GridPoint(mapPoint.getGridedY(), mapPoint.getGridedX());
	}
	
	/**
	 * Method gridPointToMapPoint.
	 * @param gridPoint GridPoint
	 * @return MapPoint
	 */
	public static MapPoint gridPointToMapPoint(GridPoint gridPoint) {
		return new MapPoint(gridPoint.getColumn(), gridPoint.getRow());
	}
	
	/**
	 * Method mapPointToSwingPoint.
	 * @param mapPoint MapPoint
	 * @return Point
	 */
	public static Point mapPointToSwingPoint(MapPoint mapPoint) {
		int x = (int)(mapPoint.getX() * _UNIT_LENGTH);
		int y = (int)(mapPoint.getY() * _UNIT_LENGTH);
		return new Point(x, y);
	}
	
	/**
	 * Method timerTick.
	 * @param timer Timer
	 * @see com.soen6441.core.TimerListener#timerTick(Timer)
	 */
	@Override
	public void timerTick(Timer timer) {
		this.repaint();
	}

}
