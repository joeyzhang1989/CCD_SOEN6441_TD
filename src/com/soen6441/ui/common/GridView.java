package com.soen6441.ui.common;

import com.soen6441.ui.parallel.View;

/**
 * This class defines the GridView, the main grid, map of the game.
 * 
 * @author JeanRaymondDaher
 * @since 0.1
 * @see GridViewCell
 */

public class GridView extends View {

	private GridViewCell selectedCell;

	private int unitWidth;
	private int unitHeight;

	private int numberOfRows = 2;
	private int numberOfColumns = 2;	

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	private GridViewCell[][] cells = new GridViewCell[10][10];

	/*
	 * Getters & Setters
	 */

	/**
	 * Method getUnitWidth.
	 * @return int
	 */
	public int getUnitWidth() {
		return unitWidth;
	}

	/**
	 * Method setUnitWidth.
	 * @param unitWidth int
	 */
	public void setUnitWidth(int unitWidth) {
		this.unitWidth = unitWidth;
	}

	/**
	 * Method getUnitHeight.
	 * @return int
	 */
	public int getUnitHeight() {
		return unitHeight;
	}

	/**
	 * Method setUnitHeight.
	 * @param unitHeight int
	 */
	public void setUnitHeight(int unitHeight) {
		this.unitHeight = unitHeight;
	}

	/**
	 * Add a cell to the cells[row][column].
	 * @param cell Defines the cell that needs to be added
	 * @param point Defines the location where the cell should reside
	 */
	public void addCell(GridViewCell cell, GridPoint point) {
		int row = point.getRow();
		int column = point.getColumn();
		if (this.cells[row][column] == null) {
			this.cells[row][column] = cell;
			cell.setPoint(point);
			cell.setGridView(this);
			cell.setLocation(column * unitWidth, row * unitHeight);
			this.add(cell);
		} else {
			System.out.println("cannot add - cell already present");
		}
	}

	/**
	 * Method removeCell.
	 * @param cell GridViewCell
	 * Loop through all the cells and remove the appropriate cell.
	 * @param cell The cell that needs to be removed
	 */
	public void removeCell(GridViewCell cell) {
		for (int i = 0; i < this.cells.length; i++) {
			for (int j = 0; j < this.cells.length; j++) {
				if (cells[i][j] != null && cell == cells[i][j]) {
					cells[i][j] = null;
				}
			}
		}
	}

	/**
	 * Loop through all the cells and replace the appropriate cell.
	 * @param cell The cell that needs to be replaced
	 * @param newCell The new cell that needs to be added
	 */
	public void replaceCell(GridViewCell cell, GridViewCell newCell) {
		for (int i = 0; i < this.cells.length; i++) {
			for (int j = 0; j < this.cells.length; j++) {
				if (cells[i][j] != null && cell == cells[i][j]) {
					cells[i][j] = newCell;
				}
			}
		}
	}

	/**
	 * Method selectCell.
	 * @param cell cell
	 */
	public void selectCell(GridViewCell cell) {
		if (this.selectedCell != cell) {
			if (this.selectedCell != null) this.selectedCell.setSelected(false);
			this.selectedCell = cell;
			if (this.selectedCell != null) this.selectedCell.setSelected(true);
		}
	}
}
