package com.soen6441.core.map;


import java.util.List;

public class StartPointValidator extends PathValidator {
	
	@Override
	public boolean validate() {
	
	    boolean result=true;
		
		int numberOfStartPoint = 0;
		List<MapItem> mapItems=this.getMap().getAllItems();
		
		for (int i=0;i< mapItems.size();i++){
			   MapItem  mapItem = mapItems.get(i);
			if ( mapItem instanceof Road){
				Road road =(Road)mapItem;
				if(road.getType() == Road.Type.START){
					numberOfStartPoint += 1;
				}
			}
		}
		
		
		if (numberOfStartPoint<1){
			result=false;
			this.setErrorMassage("There is No start Point In the Map");
		}
	
		return result;
	
	}
	
	
}
