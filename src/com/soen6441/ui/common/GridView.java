package com.soen6441.ui.common;

import java.util.List;

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

	private GridViewCell[][] cells;

<<<<<<< Updated upstream
	/**
	 * Method getUnitWidth.
	 * @return int
=======
	/*
	 * Getters & Setters
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	 * Method addCell.
	 * @param cell GridViewCell
	 * @param row int
	 * @param column int
=======
	 * Add a cell to the cells[row][column].
	 * @param cell Defines the cell that needs to be added
	 * @param row Defines the row where the cell should reside
	 * @param column Defines the column where the cell should be
>>>>>>> Stashed changes
	 */
	public void addCell(GridViewCell cell, int row, int column) {
		if (this.cells[row][column] == null) {
			this.cells[row][column] = cell;
		} else {
			System.out.println("cannot add - cell already present");
		}
	}

	/**
<<<<<<< Updated upstream
	 * Method removeCell.
	 * @param cell GridViewCell
=======
	 * Loop through all the cells and remove the appropriate cell.
	 * @param cell The cell that needs to be removed
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

	/**
	 * Method replaceCell.
	 * @param cell GridViewCell
	 * @param newCell GridViewCell
=======
	
	/**
	 * Loop through all the cells and replace the appropriate cell.
	 * @param cell The cell that needs to be replaced
	 * @param newCell The new cell that needs to be added
>>>>>>> Stashed changes
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
	 * @param row int
	 * @param column int
	 */
	public void selectCell(int row, int column) {
//		this.selectedCell = new GridViewCell();
		this.selectedCell = this.cells[row][column];
	}

}
