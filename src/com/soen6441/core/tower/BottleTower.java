package com.soen6441.core.tower;

/**
 * This class is a specific type of tower, BottleTower.
 * A BottleTower attacks a single target.
 * 
 * @see Tower
 * 
 * @author Haiyang Sun
 * @version $Revision: 1.0 $
 */
public class BottleTower extends Tower {
	
	/**
	 * Copy properties from one BottleTower object to another.
	 * 
	 * @param bottleTower
	 * @see Tower#copyTo(Tower) 
	 */
	@Override
	public void copyTo(Tower tower){
		BottleTower bottleTower = (BottleTower)tower;
		super.copyTo(bottleTower);
	}
	
	/**
	 * Return detail information of a BottleTower object.
	 */
	@Override
	public String getDetailInformation() {
		String result = super.getDetailInformation();
		return result;
	}

}
