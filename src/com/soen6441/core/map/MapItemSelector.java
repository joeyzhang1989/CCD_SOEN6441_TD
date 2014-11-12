package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Chenglong Zhang 
 * @author Mengyao Wang 
 */
public class MapItemSelector {

	private GridMap map;

	private List<MapItem> items;
	
	
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
	public MapItemSelector filterByTypes(Class[] types) {
		MapItemSelector mapItemSelector = new MapItemSelector ();
		mapItemSelector.setMap(map);
		
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		for (MapItem item:items){
			for(Class type:types){
				if (type.isInstance(item)) {
					filteredItems.add(item);
				}
			}
		}
		
		mapItemSelector.setItems(filteredItems);
		
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
    		items.subList(0, amount);
    	} else {
    		filteredItems.addAll(items);
    	}
    	
    	mapItemSelector.setItems(filteredItems);
    	
		return mapItemSelector;
	}
    
    /**
     * Method filterByExcluding.
     * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector filterByExcluding() {
		MapItemSelector mapItemSelector = new MapItemSelector();
		mapItemSelector.setMap(map);
		List<MapItem> filteredItems = new ArrayList<MapItem>();
		for (MapItem item:items){
			
			
		}
		mapItemSelector.setItems(filteredItems);
		return mapItemSelector;
	}
    
    
    /**
	 * Method sortByDirectlyClosestToPoint.
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
			public int compare(MapItem arg0, MapItem arg1) {
				
				if(arg0.getLocation().distanceTo(finalPoint) > arg1.getLocation().distanceTo(finalPoint)){
					return 1;
				} 
				if(arg0.getLocation().distanceTo(finalPoint) < arg1.getLocation().distanceTo(finalPoint)){
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
			public int compare(MapItem arg0, MapItem arg1) {
				
				if(map.pathDistanceToEndPoints (arg0.getLocation()) > map.pathDistanceToEndPoints (arg1.getLocation())){
					return 1;
				} 
				if(map.pathDistanceToEndPoints (arg0.getLocation()) < map.pathDistanceToEndPoints (arg1.getLocation())){
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
    
    public GridMap getMap() {
		return map;
	}

	public void setMap(GridMap map) {
		this.map = map;
	}

	public List<MapItem> getItems() {
		return items;
	}

	public void setItems(List<MapItem> items) {
		this.items = items;
	}
}

