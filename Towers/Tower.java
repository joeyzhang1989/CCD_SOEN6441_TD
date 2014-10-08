package com.soen6441.Towers;

public class Tower extends MapItem {
	
	public int level;
	public int initialPrice;
	public int upgradePrice;
	public int sellPrice;
	
	protected TowerManager manager;

	public void upgrade() {
		
		if (Play.points >= this.upgradePrice) {
			
			manager.upgrade(this);
			
		}
	}
	
	public void sell() {
		
		Play.points += sellPrice;
		GridMap.removeItem(this);
		
	}
	
	
	public AffectableValue range;
	public AffectableValue damage;
	public AffectableValue cdTime;
	
	
	public void attack() {
		
	}
	
	

}

//delete
class MapItem {
	
}

class AffectableValue {
	
}

