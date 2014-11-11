package com.soen6441.core.critter;

import java.util.List;

import org.dom4j.Element;

import com.soen6441.core.IArchive;

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
		return 0;
	}

	public Critter nextCritter() {
		return null;
	}

	@Override
	public void decode(Element element) {
		// TODO Auto-generated method stub

	}

	@Override
	public Element encode() {
		// TODO Auto-generated method stub
		return null;
	}

}
