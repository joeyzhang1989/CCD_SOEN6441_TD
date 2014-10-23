package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;




/**
 * This class will check whether the map is valid or not.In addition, this class
 * will provide methods to generate paths on the map  
 * 
 * @author Mohammad Ali
 */

public  class PathValidator {

	private GridMap map;
	private String errorMessage; 
	
	
	
	private int width;
	private int height;
	
	List<MapPoint> startPoints=new ArrayList<MapPoint>();
	List<MapPoint> endPoints=new ArrayList<MapPoint>();
	List<MapPath>  paths= new ArrayList<MapPath>();
	
	
	
	

	/**
	 * Method validate will check whether a map is valid.
	 * @param GridMap 
	 * @return boolean
	 */
	
	public  boolean validate(){
		
		/*
		 * Getting the required information from the gridMap to analyze its correctness.
		 * 
		 * */
		boolean isValid=true;
		
		
		
		width=this.map.getWidth();
		height=this.map.getHeight();
		startPoints=this.map.getStartPoints();
		endPoints=this.map.getEndPoints();
		paths=this.map.getPaths();

		
		
		
		
		
		
		
		
		return isValid;
		
		
	}
	
	
	/*
	 * Method checkDimensionsvalidates the Maps Dimensions. The map dimension must be greater than 3x3.
	 * IF not there is no way to have a valid map with startPoin, endPoint, and path.
	 * 
	 * @param width The width of the map
	 * @param height The Height of the map
	 * @return boolean A boolean values showing whether Map Dimensions are valid or no.
	 * */
	public boolean checkDimensions(int width,int height){
		boolean result=true;
		if(width<3||height<3){
			result=false;
			this.setErrorMassage("The dimension is less than Minimum dimension allowed");
		}
		return result;
		
	}
	
	

	/*
	 * Method checkStartPoints validates the startPoints of the Map.The Map must have at least one startPoint.
	 * The startPoint must be inside the map dimensions.
	 * 
	 * @param startPoints A list of startPoints. 
	 * @return boolean A boolean values showing whether the startPoints exist and is/are Valid.
	 * */
	
	public boolean checkStartPoints(List<MapPoint> startPoints){
		boolean result=true;
		
		int noOfStartPoints=startPoints.size();
		if (noOfStartPoints<1){
			result=false;
			this.setErrorMassage("There is No start Point In the Map");
		}
		
		for(int i=0;i<noOfStartPoints;i++){
			if (startPoints.get(i).getGridedX()<0||startPoints.get(i).getGridedY()<0){
				result=false;
				this.setErrorMassage("Alrert: the startPoint is out of Map:StartPoint cant be less than (0,0) ");
			}
			
			else if (startPoints.get(i).getGridedX()>width||startPoints.get(i).getGridedY()>height){
				result=false;
				this.setErrorMassage("Alrert: the startPoint is out of Map:StartPoint can't be greater than Map Dimension");
			}
		}
		return result;
	}
	
	
	
	/*
	 * Method checkEndPoints validates the EndPoints of the Map.The Map must have at least one EndPoint.
	 * The EndPoint must be inside the map dimensions.
	 * 
	 * @param endPoints A list of endPoints. 
	 * @return boolean A boolean values showing whether the endPoints exist and is/are Valid.
	 * */
	
	
	public boolean checkEndPoints(List<MapPoint> endPoints){
		boolean result=true;
		
		int noOfEndPoints=endPoints.size();
		if (noOfEndPoints<1){
			result=false;
			this.setErrorMassage("There is No End Point In the Map");
		}
		
		for(int i=0;i<noOfEndPoints;i++){
			if (endPoints.get(i).getGridedX()<0||endPoints.get(i).getGridedY()<0){
				result=false;
				this.setErrorMassage("Alrert: the End is out of Map: EbdPoint cant be less than (0,0) ");
			}
			
			else if (endPoints.get(i).getGridedX()>width||endPoints.get(i).getGridedY()>height){
				result=false;
				this.setErrorMassage("Alrert: the EndPoint is out of Map:EndPoint can't be greater than Map Dimension");
			}
		}
		return result;
	}
	
	
	
	/*
	 * Method checkPath validates the Paths of the Map.The path from startPoints to EndPoint must be connected
	 * 
	 * @param paths A list of MapPaths 
	 * @return boolean A boolean values showing whether the paths are Valid.
	 * */
	
	
	public boolean checkPaths(List<MapPath> paths){
		
		
		
		return true;
	}
	
	
	
	
	
	
	
	
	  
	public GridMap getMap() {
		return map;
	}

	public void setMap(GridMap map) {
		this.map = map;
	}

	public String getErrorMessage(){
		return errorMessage;
	}
	
	public void setErrorMassage(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	
}
