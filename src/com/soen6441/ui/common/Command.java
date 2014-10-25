package com.soen6441.ui.common;

/**
 * Its a value object, which will be used to set on a button
 * 
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
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
	
	/**
	 * Constructor for Command.
	 * @param title String
	 * @param subtitle String
	 */
	public Command(String title, String subtitle) {
		super();
		this.title = title;
		this.subtitle = subtitle;
	}
	
	/*
	 * Getters and Setters
	 */
	/**
	 * Method getTitle.
	 * @return String 
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Method setTitle.
	 * @param title String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Method getSubtitle.
	 * @return String 
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * Method setSubtitle.
	 * @param subtitle String
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
}
