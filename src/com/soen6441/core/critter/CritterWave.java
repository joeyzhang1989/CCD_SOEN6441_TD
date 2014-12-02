package com.soen6441.core.critter;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import com.soen6441.core.IArchive;

/**
 * @author Zhe Zhao
 */
public class CritterWave implements IArchive {
	
	/*
	 * Mark - Basic - Methods
	 */
	 

	private double timeGap;
	private List<CritterMultiplier> critters;

	/*
	 * Mark - Basic - Getters & Setters
	 */
	 
	/**
	 * Method getTimeGap.
	 * @return double
	 */
	public double getTimeGap() {
		return timeGap;
	}

	/**
	 * Method setTimeGap.
	 * @param timeGap double
	 */
	public void setTimeGap(double timeGap) {
		this.timeGap = timeGap;
	}

	/**
	 * Method getCritters.
	 * @return List<CritterMultiplier>
	 */
	public List<CritterMultiplier> getCritters() {
		return critters;
	}

	/**
	 * Method setCritters.
	 * @param critters List<CritterMultiplier>
	 */
	public void setCritters(List<CritterMultiplier> critters) {
		this.critters = critters;
	}
	
	/*
	 * Mark - Index Control - Properties
	 */
	 
	private int currentIndex;

	/*
	 * Mark - Index Control - Methods
	 */
	 

	/**
	 * Method getCurrentIndex.
	 * @return int
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * Method amount.
	 * @return int
	 */
	public int amount() {
		return critters.size();
	}

	/**
	 * Method nextCritter.
	 * @return Critter
	 */
	public Critter nextCritter() {
		CritterMultiplier critterMultiplier = critters.get(currentIndex);
		currentIndex ++;
		String name=critterMultiplier.getCritterName();
		CritterManager manager= CritterManagerFactory.defaultFactory().getManager(name);
		return manager.generateCritter(critterMultiplier);
	}
	
	/*
	 * Mark - Archive - Methods
	 */

	/**
     * @author Zhe Zhao
	 */
	public class NameForArchiving {
		public static final String Class = "CritterWave";
		private static final String TimeGap = "timeGap";
		private static final String Critters = "critters";
	}

	/**
	 * Method decode.
	 * @param element Element
	 * @see com.soen6441.core.IArchive#decode(Element)
	 */
	@Override
	public void decode(Element element) {
		this.setTimeGap(Double.parseDouble(element.element(NameForArchiving.TimeGap).getText()));
		Element crittersElement = element.element(NameForArchiving.Critters);

		@SuppressWarnings("unchecked")
		List<Element> critterElements = crittersElement.elements(CritterMultiplier.NameForArchiving.Class);

		critters = new ArrayList<CritterMultiplier>();
		for (Element critterMultiplierElement : critterElements) {
			CritterMultiplier critterMultiplier= new CritterMultiplier();
			critterMultiplier.decode(critterMultiplierElement);
			
			critters.add(critterMultiplier);
		}		
	}

	/**
	 * Method encode.
	 * @return Element
	 * @see com.soen6441.core.IArchive#encode()
	 */
	@Override
	public Element encode() {
		
		return null;
	}

}
