package com.soen6441.core.play;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapPath;
import com.soen6441.core.map.MapPoint;


/**
 * The class PalyManager will handle the task of saving the gamePlay in XML file and reading the XML file to generate gamePlay;
 * 
 * @author Mohammad Ali
 */
public class PlayManager {

	String mapFilePath="./maps/"; 
	
	
	/**
	 * The method save will save the gamePlay in the specified file
	 * 
	 * @param file The name of the File where to save .
	 * @param play A play object
	 */

	public void save (File file ,Play play){
	
	
		
		String fileName=file.toString();
		/*
		 * getting the required information from PLay object to save in the file.
		 * */
		int totalCoins=play.getCoins();
		GridMap newGridMap=play.getMap();
		int mapWidth=newGridMap.getWidth();
		int mapHeight=newGridMap.getHeight();
		List<MapPoint> startPoints =newGridMap.getStartPoints();
		List<MapPoint> endPoints = newGridMap.getEndPoints();
		List<MapPath> path = newGridMap.getPaths();
        
		
		List<MapPoint> pathToPoint = new ArrayList<MapPoint>();
		
		/*
		 * Below I'm converting a list of MapPath into list of MapPoints for storage.
		 * we have a list Path of type MapPath.for each of MapPath in that list we will get a list of MapPoint by
		 * calling getLocations() of class MapPath. then after we will add all MapPoints into a new List pathToPoint. 
		 */
		
		for(int i=0;i<path.size();i++){
			for(int j=0;j<path.get(i).getLocations().size();j++)
				pathToPoint.add(path.get(i).getLocations().get(j));
			
		}
		
		
	   /*
	    * creating the General structure of the xml file
	    * */
		
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement( "xml" );
		Element Play = root.addElement( "Play" );
		Element Coins = Play.addElement( "coins" );
		Element map = Play.addElement( "map" );
		Element map1 = map.addElement( "Map" );
		Element width = map1.addElement( "width" );
		Element height = map1.addElement( "height" );
		Element startPoints1 = map1.addElement( "startPoints" );
		Element endPoints1 = map1.addElement( "endPoints" );
		Element paths = map1.addElement( "paths" );
		Element road = paths.addElement( "road" );
	      
		
		/*
		 * Saving the data read from Play object into XML Map file.
		 */
	        
	      Coins.addText(String.valueOf(totalCoins));
	      width.addText(String.valueOf(mapWidth));
	      height.addText(String.valueOf(mapHeight));
	      
	      //adding startPoints in document
	      for(int i=0;i<startPoints.size();i++){
	      Element startPnt=startPoints1.addElement("startPoint");
	      			startPnt.addElement("xValue").setText(String.valueOf(startPoints.get(i).getGridedX()));;
	      			startPnt.addElement("yValue").setText(String.valueOf(startPoints.get(i).getGridedY()));;
	     
	     }
	      
	      
	      
	    //adding endPoints in documents
	      for(int i=0;i<endPoints.size();i++){
	      Element endPnt=endPoints1.addElement("endPoint");
	      			endPnt.addElement("xValue").setText(String.valueOf(endPoints.get(i).getGridedX()));;
	      			endPnt.addElement("yValue").setText(String.valueOf(endPoints.get(i).getGridedY()));;
	     
	     }
	      
	      
	     //adding path in document
	      
	      for(int i=0;i<pathToPoint.size();i++){
		      Element loc=road.addElement("locations");
		      			loc.addElement("xValue").setText(String.valueOf(pathToPoint.get(i).getGridedX()));;
		      			loc.addElement("yValue").setText(String.valueOf(pathToPoint.get(i).getGridedY()));;
		     
		     }
		      
	      
	  // lets write to a file
	        XMLWriter writer = null;
			try {
				writer = new XMLWriter(new FileWriter( mapFilePath+fileName ) );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				writer.write( document );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		
		
		
		
	}
	
	
	/**
	 * The method read will read the specified file and generate a Play object.
	 * This method will use Xpath to navigate through MapXml file.
	 * Requires jaxen 1.1-beta-6.jar located in Dom4j lib folder.
	 * 
	 * @param file The name of the File form which to read 
	 * @return Play
	 */
	public Play read(File file){
		
		
		int totalCoins;
		int mapWidth;
		int mapHeight;
		ArrayList<MapPoint> startPoints = new ArrayList<MapPoint>();
		ArrayList<MapPoint> endPoints = new ArrayList<MapPoint>();
		ArrayList<MapPoint> path = new ArrayList<MapPoint>();
		ArrayList<MapPath> multiplePaths = new ArrayList<MapPath>();
		
		
		File mapFile=new File(mapFilePath+file);
		
		
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(mapFile);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		 
		// reading the number of coins stored in map F
		Node  numberOfCoins = document.selectSingleNode("//xml/Play/coins");
		totalCoins=Integer.parseInt(numberOfCoins.getText());  
				 
		// reading width
		Node  mWidth = document.selectSingleNode("//xml/Play/map/Map/width");
		mapWidth=Integer.parseInt(mWidth.getText());  
		  
		//reading height
		Node  mHeight = document.selectSingleNode("//xml/Play/map/Map/height");
		mapHeight=Integer.parseInt(mHeight.getText());
		  
  
		//reading startPoints
		@SuppressWarnings("unchecked")
		List<Node> list = document.selectNodes( "//xml/Play/map/Map/startPoints/startPoint");
			for (Iterator<Node> iter = list.iterator(); iter.hasNext(); ) {
				  Node n=(Node)iter.next();
				  MapPoint sPoint=new MapPoint(Double.parseDouble(n.valueOf("xValue")), Double.parseDouble(n.valueOf("yValue")));
				  startPoints.add(sPoint);
			 }
		  
		
		  
		//reading endPoint
		@SuppressWarnings("unchecked")
		List<Node> list1 = document.selectNodes("//xml/Play/map/Map/endPoints/endPoint" );
		   for (Iterator<Node> iter = list1.iterator(); iter.hasNext(); ) {
				  Node n=(Node)iter.next();
				  MapPoint ePoint=new MapPoint(Double.parseDouble(n.valueOf("xValue")), Double.parseDouble(n.valueOf("yValue")));
				  endPoints.add(ePoint);
			 }
		  
		
		//reading paths
		@SuppressWarnings("unchecked")
		List<Node> list2 = document.selectNodes( "//xml/Play/map/Map/paths/road/locations" );
			for (Iterator<Node> iter = list2.iterator(); iter.hasNext(); ) {
				  Node n=(Node)iter.next();
				  MapPoint rPoint=new MapPoint(Double.parseDouble(n.valueOf("xValue")), Double.parseDouble(n.valueOf("yValue")));
				  path.add(rPoint);
			}
		  

		/*
		 * Creating a GridMap Object in order to create an Play object after.
		 * In order to create a GridMap object we need a MapPath object which
		 * in turn Need an MapPoint Object
		 * */
		MapPath locations=new MapPath();
		locations.setLocations(path);
		multiplePaths.add(locations);
		
		
		GridMap newGridMap=new GridMap();
		newGridMap.setHeight(mapHeight);
		newGridMap.setWidth(mapWidth);
		newGridMap.setStartPoints(startPoints);
		newGridMap.setEndPoints(endPoints);
		newGridMap.setPaths(multiplePaths);
		
		//Finally creating a Play Object to return
		Play play = Play.currentPlay();
		play.setCoins(totalCoins);
		play.setMap(newGridMap);
		
		return play;
	}

}
