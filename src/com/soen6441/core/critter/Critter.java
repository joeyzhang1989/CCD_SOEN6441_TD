package com.soen6441.core.critter;

import org.dom4j.Element;

import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.play.Play;

public class Critter extends MapItem implements TimerListener{

	private int totalHp;
	private int hp;
	private AffectableValue speed;
	
	private int reward;
	private int stealAmount;
	
	public Critter()
	{
	}
	
	/*
	 * Mark - Basic - Observerable
	 */
	
	public static String OBSERVABLE_EVENT_PROPERTY_HP_DID_CHANGE = "ObservableEvent_PropertyHpDidChange";
	public static String OBSERVABLE_EVENT_PROPERTY_LOCATION_DID_CHANGE = "ObservableEvent_PropertyLocationDidChange";

	public int getTotalHp() {
		return totalHp;
	}

	public void setTotalHp(int totalHp) {
		this.totalHp = totalHp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (hp < 0) {
			hp = 0;
			map.removeCritter(this);
		}
		this.hp = hp;

		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_HP_DID_CHANGE);
	}
	
	public void damaged(int damage) {
		this.setHp(this.getHp() - damage);
	}

	public AffectableValue getSpeed() {
		return speed;
	}

	public void setSpeed(AffectableValue speed) {
		this.speed = speed;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public int getStealAmount() {
		return stealAmount;
	}

	public void setStealAmount(int stealAmount) {
		this.stealAmount = stealAmount;
	}
	
	
	@Override
	public void timerTick(Timer timer) {
		
		double out = map.moveOnPath(this, this.getSpeed().getEffectedValue() / Play.RUNNER_FPS);
		this.damaged(1);
		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_HP_DID_CHANGE);
		
		if (out > 0) {
			map.removeCritter(this);
		} else {
			this.setChanged();
			this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_LOCATION_DID_CHANGE);
		}
		
	}
	
	public class NameForArchiving{
		public static final String Class = "Critter";
		private static final String Speed="speed";
		private static final String Reward="reward";
		private static final String Hp="hp";
		private static final String TotalHp="totalHp";
		private static final String StealAmount="stealAmount";
	}
	
	@Override
	public void decode(Element element) {

		AffectableValue speed=new AffectableValue(Double.parseDouble(element.element(NameForArchiving.Speed).getText()));
		this.setSpeed(speed);
		
		this.setReward(Integer.parseInt(element.element(NameForArchiving.Reward).getText()));
		
		this.setHp(Integer.parseInt(element.element(NameForArchiving.Hp).getText()));
		
		this.setTotalHp(Integer.parseInt(element.element(NameForArchiving.TotalHp).getText()));
		
		this.setStealAmount(Integer.parseInt(element.element(NameForArchiving.StealAmount).getText()));
				
		super.decode(element);
	}
	
	@Override
	public Element encode() {
		// TODO Auto-generated method stub
		return super.encode();
	}
	

	
}
