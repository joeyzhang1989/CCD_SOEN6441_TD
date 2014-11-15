package com.soen6441.core.effect;

import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;
import com.soen6441.core.tower.Tower;

public class MotivateEffect extends Effect implements TimerListener{
	
	double enhanceRate;
	double enhanceDuration;
	@Override
	public boolean strongerThan (Effect effect) {
		
		if (effect.type.equalsIgnoreCase(type)) {
			MotivateEffect exist = (MotivateEffect)effect;			
			if (this.enhanceRate > exist.enhanceRate) {
				return true;
			}
		}
		return false;	
	}
	
	@Override
	public void start() {
		this.getTimer().setTimeIntervalSecond(this.getEnhanceDuration());
		this.getTimer().start();
	}
	
	@Override
	public void affect() {
		
		Tower tower = (Tower)this.getOn();
		tower.reinforce(enhanceRate);
		
		
	}
	@Override
	public void timerTick(Timer timer) {
		
		this.getOn().removeEffect(this);
		
	}
	
	@Override
	public void stop() {
		
		this.getTimer().stop();
		
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
