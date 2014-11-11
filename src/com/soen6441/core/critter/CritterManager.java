package com.soen6441.core.critter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.soen6441.core.tower.Tower;
import com.soen6441.core.tower.TowerManager.NameForArchiving;

public class CritterManager {

	private String towerType;
	private String filePath;

	private Critter critter;
	
	public CritterManager(String towerType, String filePath) {
		this.towerType = towerType;
		this.filePath = filePath;
		this.analyse();
	}

	public void analyse() {
		
		//Read XML file which stores tower information.
		SAXReader reader = new SAXReader();
	    Document document = null;
	    
		try {
			document = reader.read(filePath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		Element root = document.getRootElement();
		
		this.decode(root);
		
	}
	
	public class NameForArchiving{
		
		public static final String Class = "CritterManager";
		private static final String Critter="Critter";
		
	}
	
	private Critter generateCritter(String typeName) {
		Critter critter = null;
		try {
			critter = (Critter) (Class.forName("com.soen6441.core.critter." + typeName).newInstance());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return critter;
	}
	
	public void decode(Element root) {
		
		Element critterManagerElement = root.element(NameForArchiving.Class);
       // this.initialPrice = (Integer.parseInt(towerManagerElement.element(NameForArchiving.InitialPrice).getText()));
        Element critterElement = critterManagerElement.element(NameForArchiving.Critter);
        
        //Generate tower depending on towerType property of this class.
        for ( @SuppressWarnings("rawtypes")Iterator i = critterElement.elementIterator(); i.hasNext(); ) {
        	
            Element element = (Element)i.next();
                 
            Critter c= this.generateCritter(towerType);
            
            //c.setManager(this);
    		
    		c.decode(element);
    		
    		this.critter=c;
    		
    		//Critter.add(c);
           	
        }
		
	}
	
	public Critter generateCritter(CritterMultiplier multiplier) {
		return null;
	}

	public String getTowerType() {
		return towerType;
	}

	public void setTowerType(String towerType) {
		this.towerType = towerType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
