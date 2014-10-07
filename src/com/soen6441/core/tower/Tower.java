package com.soen6441.core.tower;

import com.soen6441.core.Play;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapItem;


/**
 * This class is the parent class of all types of tower classes.
 * 
 * @author Haiyang Sun
 *
 */

public class Tower extends MapItem {
	
	/**
	 * Properties of Tower
	 */
	public int level;
	public int initialPrice;
	public int upgradePrice;
	public int sellPrice;
	
	/**
	 * Defines a TowerManager
	 * 
	 * @see TowerManager
	 */
	protected TowerManager manager;
	
	/**
	 * Affectable properties of Tower
	 * 
	 * @see AffectableValue
	 */
	public AffectableValue range;
	public AffectableValue damage;
	public AffectableValue cdTime;
	
	/**
	 * This method is used to upgrade the level of a specific tower.
	 * Check whether the currency of a user is enough to upgrade that tower. If it is enough, upgrade the tower and reduce user's currency.
	 * 
	 * @see TowerManager#upgrade(Tower)
	 */

	public void upgrade() {
		
		if (Play.currentPlay().getCoins() >= this.upgradePrice) {
			
			manager.upgrade(this);
			int afterUpgradeCoins;
			afterUpgradeCoins = Play.currentPlay().getCoins() - this.upgradePrice;
			Play.currentPlay().setCoins(afterUpgradeCoins);
		}
	}
	
	/**
	 * The method is used to sell an existing tower.
	 * Add the sell price of the tower to a user's currency, then remove the tower from the map.
	 * 
	 */
	public void sell() {
		int afterSellCoins;
		afterSellCoins = Play.currentPlay().getCoins() + this.sellPrice;
		Play.currentPlay().setCoins(afterSellCoins);
		
	}
	
	/**
	 * The attack method of Tower
	 */
	
	public void attack() {
		
	}
	
}