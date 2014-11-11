package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Chenglong Zhang 
 * @author Mengyao Wang 
 */
public class MapItemSelector {

	private GridMap map;

	public GridMap getMap() {
		return map;
	}

	public void setMap(GridMap map) {
		this.map = map;
	}

	private List<MapItem> items;
	
	public List<MapItem> getItems() {
		return items;
	}

	public void setItems(List<MapItem> items) {
		this.items = items;
	}
	
	public enum MapItemSelectorTypeOption {
		Road, Tower, Critter
	}
	
	
	/**
	 * Method fliterByCircularArea.
     * @return MapItemSelector mapItemSelector
     */
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
     * @return MapItemSelector mapItemSelector
     */
	// need to define the Types
	public MapItemSelector filterByTypes(MapItemSelectorTypeOption[] types) {
		MapItemSelector mapItemSelector = new MapItemSelector ();
		return mapItemSelector;
		
	}
	
	/**
	 * Method filterByAmount.
     * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector filterByAmount(int amount) {
    	MapItemSelector mapItemSelector = new MapItemSelector ();
    	mapItemSelector.setMap(map);
    	
    	List<MapItem> filteredItems = new ArrayList<MapItem>();
    	if (amount > items.size()){
    		
    	} else {
    		filteredItems.addAll(items);
    	}
    	
    	mapItemSelector.setItems(filteredItems);
    	
    	
//    	mapItemSelector.setItems(items);
    	
		return mapItemSelector;
	}
    
    /**
	 * Method sortByDirectlyClosestToPoint.
     * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector sortByDirectlyClosestToPoint (MapPoint point) {
    	MapItemSelector mapItemSelector = new MapItemSelector ();
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
}

