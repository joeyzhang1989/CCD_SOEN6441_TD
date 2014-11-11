package com.soen6441.core.critter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
		
		//this.decode(root);
		
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
