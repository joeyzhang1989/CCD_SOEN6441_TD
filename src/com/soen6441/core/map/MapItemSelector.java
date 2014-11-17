package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



import com.soen6441.core.critter.Critter;
import com.soen6441.core.strategy.Strategy;

/**
 * @author Chenglong Zhang 
 * @author Mengyao Wang 
 * @version $Revision: 1.0 $
 */
public class MapItemSelector {
	
	/*
	 * Mark - Context - Properties
	 */

	private GridMap map;
	private List<MapItem> items;
	
	/*
	 * Mark - Context - Getters & Setters
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
	}

	/**
	 * Method getItems.
	 * @return List<MapItem>
	 */
	public List<MapItem> getItems() {
		return items;
	}

	/**
	 * Method setItems.
	 * @param items List<MapItem>
	 */
	public void setItems(List<MapItem> items) {
		this.items = items;
	}
	
	/*
	 * Mark - Filters - Methods
	 */
	
	/**
	 * Method fliterByCircularArea.
    
     * @param mapPoint MapPoint
	 * @param radius double
	 * @return MapItemSelector mapItemSelector */
	public MapItemSelector filterByCircularArea(MapPoint mapPoint, double radius) {
		MapItemSelector mapItemSelector = new MapItemSelector ();
		mapItemSelector.setMap(map);
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		for (MapItem item:items){
			if (item.getLocation().distanceTo(mapPoint) <= radius) {
				filteredItems.add(item);
			}
		}
		mapItemSelector.setItems(filteredItems);
		return mapItemSelector;
	}
	
	/**
	 * Method filterByType.
     * @param type Class<?>
	 * @return MapItemSelector mapItemSelector
     */
	// need to define the Types
	public MapItemSelector filterByType(Class<?> type) {
		MapItemSelector mapItemSelector = new MapItemSelector ();
		mapItemSelector.setMap(map);
		
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		for (MapItem item:items){
			if (type.isInstance(item)) {
				filteredItems.add(item);
			}
		}
		
		mapItemSelector.setItems(filteredItems);
		
		return mapItemSelector;
		
	}
	
	/**
	 * Method filterByAmount.
     * @param amount int
	 * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector filterByAmount(int amount) {
    	MapItemSelector mapItemSelector = new MapItemSelector ();
    	mapItemSelector.setMap(map);
    	
    	List<MapItem> filteredItems = new ArrayList<MapItem>();
    	if (amount < items.size()){
    		filteredItems.addAll(items.subList(0, amount));
    	} else {
    		filteredItems.addAll(items);
    	}
    	
    	mapItemSelector.setItems(filteredItems);
    	
		return mapItemSelector;
	}
    
    /**
     * Method filterByExcluding.
     * @param item MapItem
     * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector filterByExcluding(MapItem item) {
		MapItemSelector mapItemSelector = new MapItemSelector();
		mapItemSelector.setMap(map);
		
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		filteredItems.addAll(items);
		filteredItems.remove(item);
		mapItemSelector.setItems(filteredItems);
	
		return mapItemSelector;
	}
    
    /*
	 * Mark - Sorts - Methods
	 */
	 
    /**
	 * Method sortByDirectlyClosestToPoint.
     * @param point MapPoint
     * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector sortByDirectlyClosestToPoint (MapPoint point) {
    	MapItemSelector mapItemSelector = new MapItemSelector ();
    	mapItemSelector.setMap(map);
    	
    	List<MapItem> filteredItems = new ArrayList<MapItem>();
    	filteredItems.addAll(items);
    	final MapPoint finalPoint = point;
    	Collections.sort(filteredItems, new Comparator<MapItem>() {

			@Override
			public int compare(MapItem item1, MapItem item2) {
				double distance1 = item1.getLocation().distanceTo(finalPoint);
				double distance2 = item2.getLocation().distanceTo(finalPoint);
				
				if (distance1 > distance2){
					return 1;
				}
				
				if (distance1 < distance2){
					return -1;
				}
				
				return 0;
			}
    		
		});
    	
    	mapItemSelector.setItems(filteredItems);
		return mapItemSelector;
		
  	}
    
    /**
	 * Method sortByOnPathClosestToEndPoint.
     * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector sortByOnPathClosestToEndPoint () {  
		MapItemSelector mapItemSelector = new MapItemSelector ();
		mapItemSelector.setMap(map);
    	List<MapItem> filteredItems = new ArrayList<MapItem>();
    	filteredItems.addAll(items);
    	
    	Collections.sort(filteredItems, new Comparator<MapItem>() {

			@Override
			public int compare(MapItem item1, MapItem item2) {
				double distance1 = map.pathDistanceToEndPoints(item1.getLocation());
				double distance2 = map.pathDistanceToEndPoints(item2.getLocation());
				if (distance1 > distance2){
					return 1;
				}
				
				if (distance1 < distance2){
					return -1;
				}
				
				return 0;
			}
    		
		});
    	
    	mapItemSelector.setItems(filteredItems);
		return mapItemSelector;
  	}
    
    /**
	 * Method sortByRandom.
     * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector sortByRandom () {
    	MapItemSelector mapItemSelector = new MapItemSelector ();
    	mapItemSelector.setMap(map);
    	List<MapItem> filteredItems = new ArrayList<MapItem>();
    	filteredItems.addAll(items);
    	
    	Collections.shuffle(filteredItems);
    	
    	mapItemSelector.setItems(filteredItems);
		return mapItemSelector;
  	}
    
    /**
  	 * Method sortByWeakest.
     * @return MapItemSelector mapItemSelector 
     */
    public MapItemSelector sortByWeakest() {
      	MapItemSelector mapItemSelector = new MapItemSelector ();
      	mapItemSelector.setMap(map);
      	List<MapItem> filteredItems = new ArrayList<MapItem>();
    	filteredItems.addAll(items);

    	Collections.sort(filteredItems, new Comparator<MapItem>() {
			@Override
			public int compare(MapItem item1, MapItem item2) {
				//cast the MapItem to the Critter use getHp() to compare 
				Critter c1 = (Critter)item1;
				Critter c2 = (Critter)item2;
				int hp1 = c1.getHp();
				int hp2 = c2.getHp();
				if (hp1 < hp2) {
					return -1;
				}
				if (hp1 > hp2) {
					return 1;
				}
				return 0;
			}

    	});
	  	mapItemSelector.setItems(filteredItems);
		return mapItemSelector;
	}
      
	/** 
	 * Method sortByStrongest.
     * @return MapItemSelector mapItemSelector
     */
	public MapItemSelector sortByStrongest() {
    	MapItemSelector mapItemSelector = new MapItemSelector ();
    	mapItemSelector.setMap(map);
    	List<MapItem> filteredItems = new ArrayList<MapItem>();
    	filteredItems.addAll(items);
    	
    	Collections.sort(filteredItems, new Comparator<MapItem>() {
			@Override
			public int compare(MapItem item1, MapItem item2) {
				//cast the MapItem to the Critter use getHp() to compare 
				Critter c1 = (Critter)item1;
				Critter c2 = (Critter)item2;
				int hp1 = c1.getHp();
				int hp2 = c2.getHp();
				if (hp1 > hp2) {
					return -1;
				}
				if (hp1 < hp2) {
					return 1;
				}
				return 0;
			}

    	});
    	mapItemSelector.setItems(filteredItems);
		return mapItemSelector;
  	}
	
	/**
	 * Method sortByStrategy.
	 * @param strategyName String
	 * @param source MapItem
	 * @return MapItemSelector
	 */
	public MapItemSelector sortByStrategy(String strategyName, MapItem source) {
		if (strategyName.equals(Strategy.STRATEGY_NAME_DIRECTLY_CLOSEST_TO_POINT)) {
			return sortByDirectlyClosestToPoint(source.getLocation());
		} else if (strategyName.equals(Strategy.STRATEGY_NAME_ON_PATH_CLOSEST_TO_END_POINT)) {
			return sortByOnPathClosestToEndPoint();
		} else if (strategyName.equals(Strategy.STRATEGY_NAME_WEAKEST)) {
			return sortByWeakest();
		} else if (strategyName.equals(Strategy.STRATEGY_NAME_STRONGEST)) {
			return sortByStrongest();
		}
		
		return this;
	}
}

