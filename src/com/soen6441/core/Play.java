package com.soen6441.core;

import com.soen6441.core.map.GridMap;
import com.soen6441.ui.scene.PlayingScene;

/**
 * 
 * 
 * <h1>Introduction</h1>
 * <p>Play is the like the center data and logic of the game.</p>
 * <p>Play contains {@link GridMap} and some shared information like {@link #coins} and {@link #life}</p>
 * 
 * <br></br>
 * <h1>Tasks</h1>
 * 
 * <h3>Tasks - Using the object</h3>
 * 
 * <h5>General Idea</h5>
 * <p>Play is using the singleton design pattern. </p>
 * <p>The {@link Play} is kind of semi single.
 *  which means once the instance has been created, it can be destroyed and created a new one. 
 *  But there will be only zero / one object in the runtime </p>
 * 
 * <h5>To get the play object</h5>
 * <p>Use {@link Play#currentPlay()} method to get the instance.</p>
 * 
 * <h5>To destroy the play object</h5>
 * <p>When finished a play, like you quit from {@link PlayingScene} you should destroy the {@link Play} object.</p>
 * <p>Use {@link Play#destroy()} method to destroy the shared instance, so when someone call {@link Play#currentPlay()} later, they will get a new one.</p>
 * 
 * <br></br>
 * <h3>Tasks - Managing the coins</h3>
 * 
 * <h5>Getting and Setting the coins</h5>
 * <p>Use {@link #getCoins()} and {@link #setCoins(int)} to get and set the coins directly.</p>
 * <p>If you want to increase or decrease the {@link #coins} base on the logic, please use the following methods.</p>
 * 
 * <h5>To change the coins</h5>
 * <p>Use {@link #earnCoins(int)} to increase the coins.</p>
 * <p>Use {@link #spendCoins(int)} to decrease the coins.</p>
 * <p><strong>Attention:</strong> Call {@link #getCoins()} before call {@link #spendCoins(int)} to check whether the coins is enough to spend</p>
 * 
 * 
 * 
 * 
 * @author Zhe Zhao
 *
 */

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
	 */
	public static Play currentPlay(){
		if (currentPlay == null){
			currentPlay = new Play();
		}
		return currentPlay;
	}
	
	/**
	 * Use this method to set the shared instance to null
	 */
	public static void destroy(){
		currentPlay = null;
	}
	
	/**
	 * Close the access to the general constructor
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
	 * Methods
	 */
	
	/**
	 * Any logic wants to increase the coins, should call this method 
	 * 
	 * 
	 * @param coins Amount of increasing
	 *
	 */
	public void earnCoins(int coins){
		this.coins += coins;
	}

	
	/**
	 * Any logic wants to decrease the coins, should call this method.
	 * Please call {@code #getCoins()} first to check whether there is enough money to spend
	 * 
	 * @param coins
	 * 
	 * @see #getCoins()
	 * 
	 */
	public void spendCoins(int coins){
		this.coins -= coins;
	}

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
