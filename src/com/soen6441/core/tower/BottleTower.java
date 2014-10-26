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
	
	 * @param bottleTower
	 * @see Tower#copyTo(Tower) */
	
	public void copyTo(BottleTower bottleTower){
		super.copyTo(bottleTower);
	}
	
	@Override
	public String getdetailInformation() {
		String result = super.getdetailInformation();
		return result;
	}

}
