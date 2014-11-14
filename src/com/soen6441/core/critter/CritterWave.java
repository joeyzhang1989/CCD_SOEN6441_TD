package com.soen6441.core.critter;
/**
 * This class defines the CritterWave , it reads an xml file that defines for each wave which critters are going to show up.
 * 
 * @author JeanRaymondDaher
 *
 * @version $Revision: 1.1 $
 */
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

	/**
	 * Access the next critter entering the scene.
	 * @return Critter returns the next critter in the wave
	 */
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

	/**
	 * Decode method reads XML wave file and stores waves in a List
	 * @see com.soen6441.core.IArchive#decode(org.dom4j.Element)
	 */
	@Override
	public void decode(Element element) {
		this.setTimeGap(Double.parseDouble(element.element(NameForArchiving.TimeGap).getText()));
		Node critters = element.selectSingleNode(NameForArchiving.Critters);

		@SuppressWarnings("unchecked")
		List<Node> critterNodes = critters.selectNodes(Critter.NameForArchiving.Class);

		for (Node critterNode : critterNodes) {
			Element critterElement= (Element) critterNode;
			Critter critter= new Critter();
			critter.decode(critterElement);
		}		
	}

	@Override
	public Element encode() {
		// TODO Auto-generated method stub
		return null;
	}

}
