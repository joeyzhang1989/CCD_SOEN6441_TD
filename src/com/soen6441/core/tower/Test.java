package com.soen6441.core.tower;

public class Test {
	
	public static void main(String[] args) {
		TowerManager tm = TowerManagerFactory.currentManagerFactory().getManager("MudTower");
		Tower tower = (MudTower)tm.createTower();
		
		System.out.println(tower.getdetailInformation());
		System.out.println(tower.getDescription());
	}
}
