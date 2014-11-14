package com.soen6441.core.critter;
/**
 * This class defines the CritterManager which will get critter data from the XMl critter file.
 * 
 * @author JeanRaymondDaher
 *
 * @see Critter
 * @version $Revision: 1.1 $
 */
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.tower.Tower.NameForArchiving;

public class CritterManager {

	private String critterName;

	private String filePath;

	private Critter critter;
	
	/**
	 * Constructor for CritterManager
	 * @param critterName Name of critter
	 * @param filePath Path of XML file
	 */
	public CritterManager(String critterName, String filePath) {
		this.critterName = critterName;
		this.filePath = filePath;
		this.analyse();
	}

	/**
	 * Analyse method : Opens xml file and decodes it using critter object
	 */
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
		Element critterElement = root.element(Critter.NameForArchiving.Class);
		critter = new Critter();
		critter.decode(critterElement);
	}
	
	/**
	 * Generate Upgraded critter using critterMultiplier.
	 * @param critterMultiplier takes a critter multiplier
	 * @return Critter Returns the generated critter
	 */
	public Critter generateCritter(CritterMultiplier critterMultiplier) {
		Critter temp=new Critter();
		temp.setName(critter.getName());
		temp.setSpeed(new AffectableValue(critter.getSpeed().getOriginalValue () * critterMultiplier.getSpeedMultiplier()));		
		temp.setTotalHp((int) (critterMultiplier.getHpMultiplier()* critter.getTotalHp()));
		temp.setReward((int) (critter.getReward()* critterMultiplier.getRewardMultiplier()));
		temp.setStealAmount(critter.getStealAmount());
		return temp;
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
