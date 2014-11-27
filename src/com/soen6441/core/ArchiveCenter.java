package com.soen6441.core;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

import com.soen6441.core.map.Road;
import com.soen6441.core.tower.BottleTower;
import com.soen6441.core.tower.EnergyTower;
import com.soen6441.core.tower.IceTower;
import com.soen6441.core.tower.MoonTower;
import com.soen6441.core.tower.FireTower;
import com.soen6441.core.tower.SplashTower;
import com.soen6441.core.tower.SunTower;

public class ArchiveCenter {
	
	private static Map<String, Class> registedClasses = new HashMap<String, Class>();
	
	static {
		Road.registeToArchiveCenter();
		BottleTower.registeToArchiveCenter();
		EnergyTower.registeToArchiveCenter();
		IceTower.registeToArchiveCenter();
		MoonTower.registeToArchiveCenter();
		FireTower.registeToArchiveCenter();
		SunTower.registeToArchiveCenter();
		SplashTower.registeToArchiveCenter();
	}
	
	public static void registeClass(Class classObject, String elementName) {
		registedClasses.put(elementName, classObject);
	}
	
	public static Object decodeElement(Element objectElement) {
		Class classObject = registedClasses.get(objectElement.getName());
		IArchive archivable = null;
		try {
			archivable = (IArchive) classObject.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (archivable != null) {
			archivable.decode(objectElement);
		} else {
			System.out.println(objectElement.getName() + " not registed");
		}
		return archivable;
	}
}
