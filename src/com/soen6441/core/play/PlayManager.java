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
 * @author Chenglong Zhang
 * @version $Revision: 1.0 $
 */
public class PlayManager {

	/**
	 * The method save will save the gamePlay in the specified file
	 * 
	 * @param file The name of the File where to save .
	 * @param play A play object
	 */

	 public void save (File file ,Play play) {
		
		/*
		 * Getting the required information from PLay object to save in the file.
		 * 
		 */
		
		int coins = play.getCoins();
		GridMap map = play.getMap();
		int width = map.getWidth();
		int height = map.getHeight();
		List<MapPoint> startPoints = map.getStartPoints();
		List<MapPoint> endPoints = map.getEndPoints();
		List<MapPath> path = map.getPaths();
        
		
	   /*
	    * Creating the General structure of the xml file
	    * 
	    */
		
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement( "xml" );
		Element playElement = rootElement.addElement( "Play" );
		Element coinsElement = playElement.addElement( "coins" );
		Element mapElement = playElement.addElement( "map" );
		Element mapObjectElement = mapElement.addElement( "Map" );
		Element widthElement = mapObjectElement.addElement( "width" );
		Element heightElement = mapObjectElement.addElement( "height" );
		Element startPointsElement = mapObjectElement.addElement( "startPoints" );
		Element endPointsElement = mapObjectElement.addElement( "endPoints" );
		Element pathsElement = mapObjectElement.addElement( "paths" );
		
		/*
		 * Saving the data read from Play object into XML Map file.
		 */
	        
		coinsElement.addText(String.valueOf(coins));
	    widthElement.addText(String.valueOf(width));
	    heightElement.addText(String.valueOf(height));
	      
	    //adding startPoints in document
	    for(int i= 0; i < startPoints.size();i++) {
	    	Element pointElement = startPointsElement.addElement("MapPoint");
	    	String xValue = String.valueOf(startPoints.get(i).getGridedX());
	    	String yValue = String.valueOf(startPoints.get(i).getGridedY());
	      
	    	pointElement.addAttribute("x", xValue);
	    	pointElement.addAttribute("y", yValue);
	    }
	      
	    //adding endPoints in documents
	    for(int i= 0; i < endPoints.size();i++) {
	    	Element pointElement = endPointsElement.addElement("MapPoint");
	    	String xValue = String.valueOf(endPoints.get(i).getGridedX());
	    	String yValue = String.valueOf(endPoints.get(i).getGridedY());
	      	
	    	pointElement.addAttribute("x", xValue);
	    	pointElement.addAttribute("y", yValue);
	     
	    }	
	      
	    //adding path in document
	    for(int i = 0; i < path.size();i++ ) {
	    	 
	    	List<MapPoint> locations = path.get(i).getLocations();
	    	Element pathObjectElement = pathsElement.addElement("Path");
	    	Element locationsElement = pathObjectElement.addElement("locations");
	    	for ( int j= 0; j < locations.size();j++) {
	    		Element pointElement = locationsElement.addElement("MapPoint");
	    		String xValue = String.valueOf(locations.get(j).getGridedX()); 
	    		String yValue = String.valueOf(locations.get(j).getGridedY()); 
	      			
	    		pointElement.addAttribute("x", xValue);
	    		pointElement.addAttribute("y", yValue);
	    		
	    	}
	    }
	      
	    // lets write to a file
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileWriter( file ) );
			writer.write( document );
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) { try { writer.close(); } catch (Exception e) {}}
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
			
			
		int coins;
		int width;
		int height;
		ArrayList<MapPoint> startPoints = new ArrayList<MapPoint>();
		ArrayList<MapPoint> endPoints = new ArrayList<MapPoint>();
		ArrayList<MapPath> paths = new ArrayList<MapPath>();
				
			
			
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
			 
		
		// reading the number of coins stored in map F
		Node  coinsNode = document.selectSingleNode("//xml/Play/coins");
		coins=Integer.parseInt(coinsNode.getText());  
				 
		
		// reading width
		Node  widthNode = document.selectSingleNode("//xml/Play/map/Map/width");
		width=Integer.parseInt(widthNode.getText());  
		  
		
		//reading height
		Node  heightNode = document.selectSingleNode("//xml/Play/map/Map/height");
		height=Integer.parseInt(heightNode.getText());
			  
			  
		//reading startPoints
		@SuppressWarnings("unchecked")
		List<Node> startPointNodes = document.selectNodes( "//xml/Play/map/Map/startPoints/MapPoint");
		for (Iterator<Node> iter = startPointNodes.iterator(); iter.hasNext(); ) {
			Node pointNode = (Node) iter.next();
			Element pointElement = (Element) pointNode;
			
			String xValue = pointElement.attributeValue("x");
			String yValue = pointElement.attributeValue("y");
			  
			MapPoint point = new MapPoint(Double.parseDouble(xValue), Double.parseDouble(yValue));				  
		
			startPoints.add(point);
		 }
					  
					
					  
		//reading endPoint
		@SuppressWarnings("unchecked")
		List<Node> endPointNodes = document.selectNodes( "//xml/Play/map/Map/endPoints/MapPoint");
		for (Iterator<Node> iter = endPointNodes.iterator(); iter.hasNext(); ) {
			Node pointNode = (Node) iter.next();
			Element pointElement = (Element) pointNode;
			
			String xValue = pointElement.attributeValue("x");
			String yValue = pointElement.attributeValue("y");
					  
			MapPoint point = new MapPoint(Double.parseDouble(xValue), Double.parseDouble(yValue));				  
			
			endPoints.add(point);
		}
				  
					
					
		//reading paths
		@SuppressWarnings("unchecked")
		List<Node> locationsNodes = document.selectNodes( "//xml/Play/map/Map/paths/Path/locations" );
		for (Iterator<Node> iter = locationsNodes.iterator(); iter.hasNext(); ) {
			Node locationsNode = (Node) iter.next();
			@SuppressWarnings("unchecked")
			List<Node> pointNodes = locationsNode.selectNodes("MapPoint") ;
			
			MapPath path=new MapPath();
			MapPoint point=null;
			for (Iterator<Node> iter2 = pointNodes.iterator(); iter2.hasNext(); ) {
				Node pointNode = (Node) iter2.next();
				Element pointElement = (Element) pointNode;
				
				String xValue = pointElement.attributeValue("x");
				String yValue = pointElement.attributeValue("y");
				
				point = new MapPoint(Double.parseDouble(xValue), Double.parseDouble(yValue));	
				path.addLocation(point);
			}
			paths.add(path);
		}
					
					
					
		/*
		 * Creating a GridMap Object in order to create an Play object after.
		 * In order to create a GridMap object we need a MapPath object
		 */
		
		
		
		GridMap map=new GridMap();
		map.setHeight(height);
		map.setWidth(width);
		map.setStartPoints(startPoints);
		map.setEndPoints(endPoints);
		map.setPaths(paths);
		
		//Finally creating a Play Object to return
		Play play = Play.currentPlay();
		play.setCoins(coins);
		play.setMap(map);
		
		return play;
    }   
	 
	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		Play play = Play.currentPlay();
		play.buildDemo();
		File file = new File("maps/a.xml");
		new PlayManager().save(file, play);
	}
}
