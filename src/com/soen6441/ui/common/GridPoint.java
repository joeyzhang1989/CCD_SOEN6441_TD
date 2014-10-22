package com.soen6441.ui.common;

public class GridPoint {
	
	/*
	 * Mark - Properties
	 */
	
	private int row;
	private int column;

	/*
	 * Mark - Constructors
	 */
	
	public GridPoint() {
		super();
	}
	public GridPoint(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	/*
	 * Mark - Getters & Setters
	 */
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
}
