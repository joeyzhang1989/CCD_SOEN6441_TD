package com.soen6441.core.map;

import java.util.List;

/**
 * @author Chenglong Zhang 
 * @author Mengyao Wang 
 */
public class MapItemSelector {
	private GridMap map;
	private List<MapItem> items;
	

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	private MapPoint mapPoint;
	private double radius;
	private MapItemSelectorTypeOption[] types;
	private int amount;
	public enum MapItemSelectorTypeOption {
		Road, Tower, Critter
	}
	
	public List<MapItem> getItems() {
		return items;
	}

	public void setItems(List<MapItem> items) {
		this.items = items;
	}
	
	public MapPoint getMapPoint() {
		return mapPoint;
	}

	public void setMapPoint(MapPoint mapPoint) {
		this.mapPoint = mapPoint;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public MapItemSelectorTypeOption[] getTypes() {
		return types;
	}

	public void setTypes(MapItemSelectorTypeOption[] types) {
		this.types = types;
	}
	/**
	 * Method fliterByCircularArea.
     * @return MapItemSelector mapItemSelector
     */
	public MapItemSelector fliterByCircularArea(MapPoint mapPoint, double radius) {
		MapItemSelector mapItemSelector = new MapItemSelector ();
		this.mapPoint = mapPoint;
		this.radius = radius;
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
    	this.amount = amount;
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

