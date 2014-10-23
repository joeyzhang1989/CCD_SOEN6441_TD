package com.soen6441.ui.parallel;

import java.awt.Graphics;

import javax.swing.JButton;

/**
 * A Parallel Class, which represents a button.
 *
 * @author Zhe Zhao
 *
 */
public class Button extends JButton {
	private static final long serialVersionUID = -3246005973006189406L;
	
	private String title;
	private String subtitle;
	
	/*
	 * Methods
	 */
	
	private void updateText(){
		super.setText(getText()); 
	}
	
	/*
	 * Getters & Setters
	 */
	
	/**
	 * This method should be no longer called. Use <code>setTitle</code> instead.
	 *  
	
	
	 * 
	 * @param text String
	 * @see #setTitle(String) */
	@Override
	public void setText(String text) {
		this.setTitle(text);
	}
	
	/**
	 * This method should be no longer called. Use <code>getTitle</code> instead.
	 *  
	
	
	 * 
	 * @deprecated since <unknown>
	 * @return String
	 * @see #getTitle() */
	@Override
	public String getText() {
		if (subtitle == null){
			return title;
		} else {
			return title + " " + subtitle;
		}
	}
	
	/**
	 * Getter for property <code>title</code>
	 * @return String
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Setter for property <code>title</code>
	 * @param title String
	 */
	public void setTitle(String title) {
		this.title = title;
		updateText();
	}
	
	/**
	 * Getter for property <code>subtitle</code>
	 * @return String
	 */
	public String getSubtitle() {
		return subtitle;
	}
	
	/**
	 * Setter for property <code>subtitle</code>
	 * 
	 * @param subtitle String
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
		updateText();
	}

	
}
