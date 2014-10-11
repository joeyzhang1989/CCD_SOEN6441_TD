package com.soen6441.ui.parallel;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * <h1>Introduction</h1>
 * <p>A Parallel Class, which represent a view.</p>
 * <br></br>
 * 
 * <h1>Life Cycle</h1>
 * 
 * <h3>Life Cycle - Initialization</h3>
 * 
 * <h5>General Idea</h5>
 * <p>When a view is contructing using the constructor, it will call some hook methods.</p>
 * <p>Every subclass of the {@link View} should know them, and put the correct code into the right method.</p>
 * 
 * <h5>1. {@link #init()}</h5>
 * <p>The constructor first call this method, so every subclass could use this method to initialize some insential properties.</p>
 * <p>Getting some thing from model to the property, setting the size of the view should be put here.</p>
 * <p><strong>Attention:</strong> {@code Scene} is able to not set the size in this class.
 * Because in defualt, it will be set to 800,600 in this method.
 * So, if there is not any else things that a {@code Scene} wants to do in the {@link #init()}, it can choose not to override this method.</p>
 * 
 * <h5>2. {@link #initSubviews()}</h5>
 * <p>The constructor then call this method. Every subclass should use this method to create subviews.</p>
 * <p>Creating, Modifying, Layouting the subviews should be put here.</p>
 * 
 * <h5>3. {@link #initEvents()}</h5>
 * <p>The constructor finally call this method. It's time to link the events.</p>
 * 
 * 
 * @author Zhe Zhao
 */

public class View extends JPanel {
	
	
	
	/* 	
	 * Constructors 
	 */
	
	/**
	 * The Standard Constructor
	 */
	public View(){
		super();
		initialize();
	}
	
	/**
	 * close the access to the Constructor inherated from the JPanel
	 */
	private View(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	
	/**
	 * close the access to the Constructor inherated from the JPanel
	 */
	private View(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	
	/**
	 * close the access to the Constructor inherated from the JPanel
	 */
	private View(LayoutManager layout) {
		super(layout);
	}

	
	/**
	 * Initialization
	 */
	private void initialize(){
		this.setLayout(null);
		init();
		initSubviews();
		initEvents();
	}
	
	/*
	 * Methods
	 */
	
	protected void init(){
		this.setSize(800, 600);
		this.setBackground(new Color(255, 255, 255));
	}
	
	/**
	 * An method will be called when contracting the view object. 
	 * Any code related to creating or modifying a view should be put in here.
	 */
	protected void initSubviews(){
		
	}
	
	/**
	 * An method will be called when contracting the view object. 
	 * When you want to add event listener to a subview, you should put the code here.
	 * This method will be called right after <code> initSubviews </code>
	 */
	protected void initEvents(){
		
	}
	
	@Override
	public Component add(Component comp) {
		Component result = super.add(comp, 0);
		this.repaint();
		return result;
	}

	@Override
	public void remove(Component comp){
		super.remove(comp);
		this.repaint();
	}
	
//	@Override
//	public boolean isOptimizedDrawingEnabled() {
//		return false;
//	}
	
	/*
	 * Property - View Flow
	 */
	
	protected ViewFlow viewFlow;

	
	/*
	 * Getters & Setters - View Flow
	 */
	
	public ViewFlow getViewFlow() {
		return viewFlow;
	}

	public void setViewFlow(ViewFlow viewFlow) {
		this.viewFlow = viewFlow;
	}
	
}

