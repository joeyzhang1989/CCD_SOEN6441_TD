package com.soen6441.core.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Strategy {
	
	/*
	 * Mark - Basic - Properties
	 */
	
	private String name;
	private String imageName;
	private String description;
	
	/*
	 * Mark - Basic - Getters & Setters
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	 * Mark - Supported Strategies - Properties
	 */
	 
	public static final String STRATEGY_NAME_DIRECTLY_CLOSEST_TO_POINT = "StrategyNameDirectlyClosestToPoint";
	public static final String STRATEGY_NAME_ON_PATH_CLOSEST_TO_END_POINT = "StrategyNameOnPathClosestToEndPoint";
	public static final String STRATEGY_NAME_WEAKEST = "StrategyNameWeakest";
	public static final String STRATEGY_NAME_STRONGEST = "StrategyNameStrongest";
	
	private static Map<String, Strategy> strategies;
	private static List<String> strategyNames;
	
	/*
	 * Mark - Supported Strategies - Methods
	 */
	 
	static {
		strategies = new HashMap<String, Strategy>();
		strategyNames = new ArrayList<String>();
		
		Strategy strategy;
		
		strategy = new Strategy();
		strategy.setName(STRATEGY_NAME_DIRECTLY_CLOSEST_TO_POINT);
		strategy.setImageName("strategy_1.png");
		strategy.setDescription("XXXXX");
		strategies.put(strategy.getName(), strategy);
		strategyNames.add(strategy.getName());
		
		strategy = new Strategy();
		strategy.setName(STRATEGY_NAME_ON_PATH_CLOSEST_TO_END_POINT);
		strategy.setImageName("strategy_2.png");
		strategy.setDescription("XXXXX");
		strategies.put(strategy.getName(), strategy);
		strategyNames.add(strategy.getName());

		strategy = new Strategy();
		strategy.setName(STRATEGY_NAME_WEAKEST);
		strategy.setImageName("strategy_3.png");
		strategy.setDescription("XXXXX");
		strategies.put(strategy.getName(), strategy);
		strategyNames.add(strategy.getName());

		strategy = new Strategy();
		strategy.setName(STRATEGY_NAME_STRONGEST);
		strategy.setImageName("strategy_4.png");
		strategy.setDescription("XXXXX");
		strategies.put(strategy.getName(), strategy);
		strategyNames.add(strategy.getName());
	}
	

	public static List<Strategy> getAllStrategies() {
		List<Strategy> sortedStrategies = new ArrayList<Strategy>();
		
		for (String strategyName : strategyNames) {
			sortedStrategies.add(strategies.get(strategyName));
		}
		
		return sortedStrategies;
	}
	
	public static Strategy getStrategy(String strategyName) {
		return strategies.get(strategyName);
	}

	public static List<String> getStrategyNames() {
		return strategyNames;
	}
	
}
