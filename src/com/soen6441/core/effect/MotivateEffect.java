package com.soen6441.core.effect;

import com.soen6441.core.Timer;
import com.soen6441.core.tower.Tower;

public class MotivateEffect extends Effect {
	
	double enhanceRate;
	double enhanceDuration;

	public MotivateEffect(String type) {
		
		this.setType(type);
		
	}
	
	public MotivateEffect() {
		
		
	}
	@Override
	public boolean strongerThan (Effect effect) {
		
		if (effect.type.equalsIgnoreCase(type)) {
			MotivateEffect exist = (MotivateEffect)effect;			
			if (this.getEnhanceRate() > exist.getEnhanceRate()) {
				return true;
			}
		}
		return false;
		
	}
	
	@Override
	public void start() {
		this.getTimer().setTimeIntervalSecond(10);
		this.getTimer().start();
	}
	
	@Override
	public void affect() {
		
		Tower tower = (Tower)this.getOn();
		tower.reinforce(enhanceRate);
		System.out.println("Motivate Effect added : MotivateEffect");
		
	}
	
	public void timerTick(Timer timer) {
		
		this.getOn().removeEffect(this);
		this.stop();
		System.out.println("Motivate Effect removed : MotivateEffect");
		
	}
	
	@Override
	public void stop() {
		
		this.getTimer().stop();
		System.out.println("Timer stop : motivateEffect");
		
	}

	public double getEnhanceRate() {
		return enhanceRate;
	}

	public void setEnhanceRate(double enhanceRate) {
		this.enhanceRate = enhanceRate;
	}

	public double getEnhanceDuration() {
		return enhanceDuration;
	}

	public void setEnhanceDuration(double enhanceDuration) {
		this.enhanceDuration = enhanceDuration;
	}

	
	
}
