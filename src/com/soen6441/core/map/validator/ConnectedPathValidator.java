package com.soen6441.core.map.validator;

import java.util.ArrayList;
import java.util.List;

import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.PathValidator;
import com.soen6441.core.map.Road;

/**
 * The class ConnectedPathValidator is a subclass of PathValidator.
 * It overrides the method Validate() of its parent class and checks whether there is a connected
 * path between startPoint and EndPoint.
 * 
 * @author Mohammad Ali
 */

public class ConnectedPathValidator extends PathValidator {

	/**
	 * This method Validate() overrides the method Validate() of parent class PathValidator.
	 * It checks whether a path exist between startPoint and EndPoint.
	 * 
	 * @return boolean A boolean values that represent whether a Path exist between startPoint and EndPoint.
	 */
	
	@Override
	public boolean validate() {
		
		boolean result = true;
		
		List<MapItem> mapItems = this.getMap().getAllItems();
		List<Road> roadItems = new ArrayList<Road>();
		
		/*
		 * Below we are seperating the road items from the list of all items.
		 * All orad items will be saved int roadItems List for further Validation.
		 */
		
		
		for (int i=0; i < mapItems.size();i++) {
		    MapItem  aItem = mapItems.get(i);
			if ( aItem instanceof Road) {
				Road road =(Road)aItem;
				roadItems.add(road);
			}
		}
		
		/*
		 * Below we will check all MapItem of Type Road and eill calculate their neighbours by calling a
		 * helper method NumberOfNeighbours().
		 */
		
		for (int i=0; i < roadItems.size();i++) {
			    int totalNeighbours = numberOfNeighbours(roadItems.get(i));
			    
			    if(roadItems.get(i).getType() == Road.Type.NORMAL && totalNeighbours != 2 ) {
			    	
			    	result = false;
			    	this.setErrorMassage("The Map is either Disconnected or Has more than one Path");
			   
			    } else if(roadItems.get(i).getType() == Road.Type.START && totalNeighbours != 1 ) {
			    	
			    	result = false;
			    	this.setErrorMassage("The Map has multiple Paths which is not Allowed");
			    	
			    } else if(roadItems.get(i).getType() == Road.Type.END && totalNeighbours != 1 ) {
			    	
			    	result = false;
			    	this.setErrorMassage("The Map has multiple Paths which is not Allowed");
			    	
			    }
				
		}
		
		return result;
	}
	
	
	/**
	 * NumberOfNeighbours is a helper method that gets a road object and count its nwighbours.
	 * For a Map with one entry and exit point and only one path bwtween entry and exit. Every road object must has 
	 * exactly two neighbours unless it is Entry or Exit Point in which case It must have only ont connected neighbour,
	 * 
	 * @param aRoad  A Road Object
	 * @return numberOfNeighbours 
	 */
	
	public int numberOfNeighbours(Road aRoad) {
		
		int numOFNeighbours=0;
		
		int sPointX = aRoad.getLocation().getGridedX();
		int sPointY = aRoad.getLocation().getGridedY();
		
		//neighbour MapPoints
		MapPoint lookLeft = new MapPoint(sPointX-1,sPointY);
		MapPoint lookRight = new MapPoint(sPointX+1,sPointY);
		MapPoint lookUp = new MapPoint(sPointX,sPointY+1);
		MapPoint lookDown = new MapPoint(sPointX,sPointY-1);
		
		MapPoint[] neighbours = new MapPoint[4];
		
		neighbours[0] = lookLeft;
		neighbours[1] = lookRight;
		neighbours[2] = lookUp;
		neighbours[3] = lookDown;
		
		/*
		 * Check the neighbours of the given mapPoints and return the one that is part of path.
		 * Limitation: Only work on a single Path.If multiple paths have to be supported we then the method 
		 * must return a list of MapPoints rather than a single MapPoint.
		 */
		
		for(int i=0; i < neighbours.length; i++) {
			MapItem currentItem = this.getMap().getItem(neighbours[i]);
			if(currentItem != null && currentItem instanceof Road) {
				numOFNeighbours +=1;
			}
		}

		return numOFNeighbours;
	}
	
}
