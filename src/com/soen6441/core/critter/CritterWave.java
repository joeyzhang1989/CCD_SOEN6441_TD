package com.soen6441.core.critter;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.Node;

import com.soen6441.core.IArchive;
import com.soen6441.core.critter.CritterMultiplier.NameForArchiving;
import com.soen6441.core.map.MapPoint;

public class CritterWave implements IArchive {

	private double timeGap;
	private List<CritterMultiplier> critters;

	private int currentIndex;

	public double getTimeGap() {
		return timeGap;
	}

	public void setTimeGap(double timeGap) {
		this.timeGap = timeGap;
	}

	public List<CritterMultiplier> getCritters() {
		return critters;
	}

	public void setCritters(List<CritterMultiplier> critters) {
		this.critters = critters;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public int amount() {
		return critters.size();
	}

	public Critter nextCritter() {
		CritterMultiplier critterMultiplier = critters.get(currentIndex);
		currentIndex ++;
		String name=critterMultiplier.getCritterName();
		CritterManager manager= CritterManagerFactory.defaultFactory().getManager(name);
		return manager.generateCritter(critterMultiplier);
	}

	public class NameForArchiving {
		public static final String Class = "CritterWave";
		private static final String TimeGap = "timeGap";
		private static final String Critters = "critters";
	}

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

	@Override
	public Element encode() {
		// TODO Auto-generated method stub
		return null;
	}

}
