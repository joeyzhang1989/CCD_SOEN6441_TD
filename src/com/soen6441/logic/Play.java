package com.soen6441.logic;

import com.soen6441.logic.map.GridMap;

public class Play {
	
	/*
	 * Singleton
	 */
	
	/**
	 * The private shared instance to make singleton
	 * 
	 * @author Zhe Zhao
	 * 
	 */
	private static Play currentPlay;
	
	/**
	 * Use this method to get the shared instance
	 * 
	 * @author Zhe Zhao
	 * 
	 */
	public static Play currentPlay(){
		if (currentPlay == null){
			currentPlay = new Play();
		}
		return currentPlay;
	}
	
	/**
	 * Use this method to set the shared instance to null
	 * 
	 * @author Zhe Zhao
	 * 
	 */
	public static void destroy(){
		currentPlay = null;
	}
	
	/**
	 * Close the access to the general constructor
	 * 
	 * @author Zhe Zhao
	 * 
	 */
	private Play()
	{
		
	}
	
	/*
	 * Properties
	 */
	
	private GridMap map;
	
	private int life;
	private int coins;


	/*
	 * Getters and Setters
	 */
	
	public GridMap getMap() {
		return map;
	}

	public void setMap(GridMap map) {
		this.map = map;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
}
