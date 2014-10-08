package com.soen6441.ui.common;

/**
 * Its a value object, which will be use to set on a button
 * 
 * @author Zhe Zhao
 */
public class Command {
	private String title;
	private String subtitle;
	
	/*
	 * Constructors
	 */
	
	public Command() {
		super();
	}
	
	public Command(String title, String subtitle) {
		super();
		this.title = title;
		this.subtitle = subtitle;
	}
	
	/*
	 * Getters and Setters
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
}
