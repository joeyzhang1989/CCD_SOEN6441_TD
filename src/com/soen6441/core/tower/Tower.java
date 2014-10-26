package com.soen6441.core.tower;

import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.map.MapItem;



/**
 * This class is the parent class of all types of tower classes.
 * The class defines some common properties and methods of child tower classes.
 * To create or upgrade a tower object should use TowerManager, which is defined by TowerManagerFactory.
 * 
 * @see TowerManager
 * @see TowerManagerFactory
 * 
 * @author Haiyang Sun
 *
 * @version $Revision: 1.0 $
 */

public class Tower extends MapItem {
	
	/*
	 * Mark - Context - Properties
	 */

	/**
	 * Defines a TowerManager
	 * 
	 * @see TowerManager
	 */
	protected TowerManager manager;
	
	/*
	 * Mark - Context - Getters & Setters
	 */
	
	/**
	 * Method getManager.
	 * @return TowerManager 
	 */
	public TowerManager getManager() {
		return manager;
	}

	/**
	 * Method setManager.
	 * @param manager TowerManager
	 */
	public void setManager(TowerManager manager) {
		this.manager = manager;
	}
	
	/*
	 * Mark - Basic - Properties
	 */
	
	protected int level;
	protected int initialPrice;
	protected int upgradePrice;
	protected int sellPrice;
	
	/*
	 * Mark - Basic - Methods
	 */
	
	/**
	 * This method is used to check whether the tower is in its highest level.
	 * @see TowerManager#canUpgrade(Tower)
	 */
	public boolean canUpgrade() {
		
		return this.manager.canUpgrade(this);
		
	}
	
	/**
	 * This method is used to upgrade the level of a specific tower.
	 * Call TowerManager to upgrade this tower.
	 * 
	
	 * @see TowerManager#upgrade(Tower) */

	public void upgrade() {
		
			manager.upgrade(this);
	
	}
	
	/**
	 * This method is used to transfer properties of a particular tower object
	 * to another, in order to avoid pointer exception of affactableValue.
	 * @param tower
	 * @see AffectableValue */
	
	public void copyTo(Tower tower){
		
		tower.manager = this.manager;
		tower.level = this.level;
		tower.upgradePrice = this.upgradePrice;
		tower.sellPrice = this.sellPrice;
		tower.range = new AffectableValue(this.range.getOriginalValue ());
		tower.damage = new AffectableValue(this.damage.getOriginalValue ());
		tower.cdTime = new AffectableValue(this.cdTime.getOriginalValue ());
		tower.setCellImageName(this.getCellImageName());
		tower.setName(this.getName());
		tower.setDescription(this.getDescription());
		
	}
	
	/*
	 * Mark - Basic - Observerable
	 */
	
	public static String OBSERVABLE_EVENT_PROPERTY_LEVEL_DID_CHANGE = "ObservableEvent_PropertyLevelDidChange";
	
	/*
	 * Mark - Basic - Getters & Setters
	 */
	

	/**
	 * Method getLevel.
	 * @return int 
     */
	public int getLevel() {
		return level;
	}

	/**
	 * Method setLevel.
	 * @param level int
	 */
	public void setLevel(int level) {
		this.level = level;
		
		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_LEVEL_DID_CHANGE);
	}

	/**
	 * Method getInitialPrice.
	 * @return int 
     */
	public int getInitialPrice() {
		return initialPrice;
	}

	/**
	 * Method setInitialPrice.
	 * @param initialPrice int
	 */
	public void setInitialPrice(int initialPrice) {
		this.initialPrice = initialPrice;
	}

	/**
	 * Method getUpgradePrice.
	 * @return int 
     */
	public int getUpgradePrice() {
		return upgradePrice;
	}

	/**
	 * Method setUpgradePrice.
	 * @param upgradePrice int
	 */
	public void setUpgradePrice(int upgradePrice) {
		this.upgradePrice = upgradePrice;
	}

	/**
	 * Method getSellPrice.
	 * @return int 
	 */
	public int getSellPrice() {
		return sellPrice;
	}

	/**
	 * Method setSellPrice.
	 * @param sellPrice int
	 */
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 * Method getRange.
	 * @return AffectableValue 
	 */
	public AffectableValue getRange() {
		return range;
	}

	/**
	 * Method setRange.
	 * @param range AffectableValue
	 */
	public void setRange(AffectableValue range) {
		this.range = range;
	}
	
	/*
	 * Mark - Attack - Properties 
	 */
	
	/**
	 * Affectable properties of Tower
	 * 
	 * @see AffectableValue
	 */
	protected AffectableValue range;
	protected AffectableValue damage;
	protected AffectableValue cdTime;
	
	/*
	 * Mark - Attack - Methods
	 */
	
	/**
	 * The attack method of Tower
	 */
	
	public void attack() {
		
	}
	
	/*
	 * Mark - Attack - Getters & Setters
	 */

	/**
	 * Method getDamage.
	 * @return AffectableValue
	 */
	public AffectableValue getDamage() {
		return damage;
	}

	/**
	 * Method setDamage.
	 * @param damage AffectableValue
	 */
	public void setDamage(AffectableValue damage) {
		this.damage = damage;
	}

	/**
	 * Method getCdTime.
	 * @return AffectableValue 
	 */
	public AffectableValue getCdTime() {
		return cdTime;
	}

	/**
	 * Method setCdTime.
	 * @param cdTime AffectableValue
	 */
	public void setCdTime(AffectableValue cdTime) {
		this.cdTime = cdTime;
	}
	
	
	/*
	 * Mark - Display - Properties
	 */

	protected String description;
	
	/*
	 * Mark - Display - Methods
	 */
	
	public String getDetailInformation() {
		
		String result;
		result = "Range  : " + this.range.getOriginalValue () + System.getProperty("line.separator")
				+"Damage : " + this.damage.getOriginalValue () + System.getProperty("line.separator")
				+"CDTime : " + this.cdTime.getOriginalValue ()/1000 + "s" +  System.getProperty("line.separator");
		return result;
	}

	/*
	 * Mark - Display - Getters & Setters
	 */
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}