package com.soen6441.core.critter;

import java.util.List;

public class CritterManagerFactory {

	private List<String> typeNames;
	
	public static CritterManagerFactory defaultFactory(){
		return null;
	}

	public List<String> getTypeNames() {
		return typeNames;
	}

	public void getManager(String typeName){
		
	}
}
