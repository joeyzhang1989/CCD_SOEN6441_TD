package com.soen6441.core.critter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class CritterManager {

	private String critterName;

	private String filePath;

	private Critter critter;
	
	public CritterManager(String critterName, String filePath) {
		this.critterName = critterName;
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
		
		
	}
	
	public Critter generateCritter(CritterMultiplier critterMultiplier) {
		return null;
	}

	public String getCritterName() {
		return critterName;
	}

	public void setCritterName(String critterName) {
		this.critterName = critterName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
