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
	 * Set value of properties of an bottle tower object.
	 * @see Tower#decode(Element)
	 * @see IArchive
	 */
	@Override
	public void decode(Element element) {
		super.decode(element);
	}
	
	@Override
	public Element encode() {
		Element element = super.encode();
		element.setName("BottleTower");
		return element;
	}
	/*
	public static void main(String[] args) {
		Tower tower = TowerManagerFactory.defaultFactory().getManager("BottleTower").createTower();
		Element element = tower.encode();
		Document document = DocumentHelper.createDocument();
        document.add(element);
        System.out.println("\n Objects -> XML");
        System.out.println(document.asXML());
		
	}
	*/
	
}
