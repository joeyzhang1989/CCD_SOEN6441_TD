package com.soen6441.core.tower;

import org.dom4j.Element;

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
	 * @param tower
	 * @see Tower#copyTo(Tower)  
     */
	@Override
	public void copyTo(Tower tower){
		BottleTower bottleTower = (BottleTower)tower;
		super.copyTo(bottleTower);
	}
	
	/**
	 * Return detail information of a BottleTower object.
	 * @return String
	 */
	@Override
	public String getDetailInformation() {
		String result = super.getDetailInformation();
		return result;
	}
	
	/**
	 * Inner class cantains name strings for archiving.
	 * @author Haiyang Sun
	 *
	 */
	public class NameForArchiving{
		
		public static final String Class = "BottleTower";
	
	}
	
	/**
	 * Set value of properties of an bottle tower object.
	 * @see Tower#decode(Element)
	 * @see IArchive
	 */
	@Override
	public void decode(Element element) {
		super.decode(element);
	}
	
	/**
	 * Encode value of properties of a bottle tower object to an XML element.
	 * @see Tower#encode(Element)
	 * @see IArchive
	 */
	@Override
	public Element encode() {
		Element element = super.encode();
		element.setName(NameForArchiving.Class);
		return element;
	}

}
