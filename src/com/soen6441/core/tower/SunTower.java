package com.soen6441.core.tower;

/**
 * This class is a specific type of tower, SunTower.
 * A SunTower is a AOE tower attacks all Targets which in its range.
 * 
 * @see Tower
 * 
 * @author Haiyang Sun
 * @version $Revision: 1.0 $
 */
public class SunTower extends Tower {
	
	/**
	 * Copy properties from one SunTower object to another.
	 * 
	 * @param tower
	 * @see Tower#copyTo(Tower) 
	 */
	@Override
	public void copyTo(Tower tower){
		SunTower sunTower = (SunTower)tower;
		super.copyTo(sunTower);
	}
	
	/**
	 * Return detail information of a SunTower object.
	 */
	@Override
	public String getDetailInformation() {
		String result = super.getDetailInformation();
		return result;
	}

}
