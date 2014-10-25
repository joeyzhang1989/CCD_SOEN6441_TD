package com.soen6441.ui.common;

/**
 */
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
	/**
	 * Constructor for GridPoint.
	 * @param row int
	 * @param column int
	 */
	public GridPoint(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	/*
	 * Mark - Getters & Setters
	 */
	
	/**
	 * Method getRow.
	 * @return int
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Method setRow.
	 * @param row int
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * Method getColumn.
	 * @return int
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * Method setColumn.
	 * @param column int
	 */
	public void setColumn(int column) {
		this.column = column;
	}
}
