package com.soen6441.ui.parallel;

import javax.swing.JButton;

/**
 * A Parallel Class, which represent a button.
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
	 * @deprecated
	 * @see #setTitle(String)
	 * 
	 * @author Zhe Zhao
	 * 
	 */
	@Override
	public void setText(String text) {
		this.setTitle(text);
	}
	
	/**
	 * This method should be no longer called. Use <code>getTitle</code> instead.
	 *  
	 * @deprecated
	 * @see #getTitle()
	 * 
	 * @author Zhe Zhao
	 * 
	 */
	@Override
	public String getText() {
		return title + " " + subtitle;
	}
	
	/**
	 * Getter for property <code>title</code>
	 *  
	 * @author Zhe Zhao
	 * 
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Setter for property <code>title</code>
	 *  
	 * @author Zhe Zhao
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
		updateText();
	}
	
	/**
	 * Getter for property <code>subtitle</code>
	 *  
	 * @author Zhe Zhao
	 * 
	 */
	public String getSubtitle() {
		return subtitle;
	}
	
	/**
	 * Setter for property <code>subtitle</code>
	 *  
	 * @author Zhe Zhao
	 * 
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
		updateText();
	}

	
}
