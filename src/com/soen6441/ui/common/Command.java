package com.soen6441.ui.common;

/**
 * Its a value object, which will be used to set on a button
 * 
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class Command {
	
	/*
	 * Mark - Constructors
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
	 * Mark - Basic - Properties
	 */
	
	private String title;
	private String subtitle;
	private String imageName;
	
	/*
	 * Mark - Method - Getters & Setters
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

	/**
	 * Method getImageName.
	 * @return String
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * Method setImageName.
	 * @param imageName String
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
