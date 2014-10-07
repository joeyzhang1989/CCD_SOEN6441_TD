package com.soen6441.ui.parallel;

import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * A Parallel Class, which represent a view.
 * It provide some template methods, which give views a life-cycle.
 * 
 * @author Zhe Zhao
 */
public class View extends JPanel {
	
	/* 	
	 * Constructors 
	 */
	
	/**
	 * The Standard Constructor
	 * 
	 * @author Zhe Zhao
	 */
	public View(){
		super();
		initialize();
	}
	
	/**
	 * close the access to the Constructor inherated from the JPanel
	 * 
	 * @author Zhe Zhao
	 */
	private View(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	
	/**
	 * close the access to the Constructor inherated from the JPanel
	 * 
	 * @author Zhe Zhao
	 */
	private View(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	
	/**
	 * close the access to the Constructor inherated from the JPanel
	 * 
	 * @author Zhe Zhao
	 */
	private View(LayoutManager layout) {
		super(layout);
	}

	
	/**
	 * Initialization
	 * 
	 * @author Zhe Zhao
	 */
	private void initialize(){
		initSubviews();
		initEvents();
	}
	
	/*
	 * Methods
	 */
	
	/**
	 * An method will be called when contracting the view object. 
	 * Any code related to creating or modifying a view should be put in here.
	 * 
	 * @author Zhe Zhao
	 */
	protected void initSubviews(){
		
	}
	
	/**
	 * An method will be called when contracting the view object. 
	 * When you want to add event listener to a subview, you should put the code here.
	 * This method will be called right after <code> initSubviews </code>
	 * 
	 * @author Zhe Zhao
	 */
	protected void initEvents(){
		
	}
}
