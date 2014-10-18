package com.soen6441.ui.common;

import java.util.List;

import com.soen6441.ui.parallel.View;

/**
 * This class defines the GridView, the main grid, map of the game.
 * 
 * @author JeanRaymondDaher
 * @see GridView
 */

public class GridView extends View {

	// this class should draw a GridView
	// it should also know when some cell is selected
	// so that we can do some stuff on inspector view

	private GridViewCell selectedCell;

	private int unitWidth;
	private int unitHeight;

	private GridViewCell[][] cells;

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
	 * Method addCell.
	 * @param cell GridViewCell
	 * @param row int
	 * @param column int
	 */
	public void addCell(GridViewCell cell, int row, int column) {
		if (this.cells[row][column] == null) {
			this.cells[row][column] = cell;
		} else {
			System.out.println("cannot add - cell already present");
		}
	}

	/**
	 * Method removeCell.
	 * @param cell GridViewCell
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
	 * Method replaceCell.
	 * @param cell GridViewCell
	 * @param newCell GridViewCell
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
