package com.soen6441.core.map;

import java.util.List;

/**
 * @author Chenglong Zhang 
 * @author Mengyao Wang 
 */
public class MapItemSelector {
	private GridMap map;
	private List<MapItem> items; 
	
	public enum MapItemSelectorTypeOption {
		Road, Tower, Critter
	}
	
	
	/**
	 * Method fliterByCircularArea.
     * @return MapItemSelector mapItemSelector
     */
	public MapItemSelector fliterByCircularArea(MapPoint mapoint, double radius) {
		MapItemSelector mapItemSelector = new MapItemSelector ();
		return mapItemSelector;
		
	}
	
	/**
	 * Method fliterByType.
     * @return MapItemSelector mapItemSelector
     */
	// need to define the Types
	public MapItemSelector fliterByTypes(MapItemSelectorTypeOption[] types) {
		MapItemSelector mapItemSelector = new MapItemSelector ();
		return mapItemSelector;
		
	}
	
	/**
	 * Method fliterByAmount.
     * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector fliterByAmount(int amount) {
    	MapItemSelector mapItemSelector = new MapItemSelector ();
		return mapItemSelector;
	}
    
    /**
	 * Method sortByclosestToPoint.
     * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector sortByDirectlyClosestToPoint (MapPoint point) {
    	MapItemSelector mapItemSelector = new MapItemSelector ();
		return mapItemSelector;
  	}
    
    /**
	 * Method sortByclosestToEndPoint.
     * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector sortByOnPathClosestToEndPoint () {
    	MapItemSelector mapItemSelector = new MapItemSelector ();
		return mapItemSelector;
  	}
    
    /**
	 * Method sortByRandom.
     * @return MapItemSelector mapItemSelector
     */
    public MapItemSelector sortByRandom () {
    	MapItemSelector mapItemSelector = new MapItemSelector ();
		return mapItemSelector;
  	}
}

