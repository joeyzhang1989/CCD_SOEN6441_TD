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

	public int getUnitWidth() {
		return unitWidth;
	}

	public void setUnitWidth(int unitWidth) {
		this.unitWidth = unitWidth;
	}

	public int getUnitHeight() {
		return unitHeight;
	}

	public void setUnitHeight(int unitHeight) {
		this.unitHeight = unitHeight;
	}

	public void addCell(GridViewCell cell, int row, int column) {
		if (this.cells[row][column] == null) {
			this.cells[row][column] = cell;
		} else {
			System.out.println("cannot add - cell already present");
		}
	}

	public void removeCell(GridViewCell cell) {
		for (int i = 0; i < this.cells.length; i++) {
			for (int j = 0; j < this.cells.length; j++) {
				if (cells[i][j] != null && cell == cells[i][j]) {
					cells[i][j] = null;
				}
			}
		}
	}

	public void replaceCell(GridViewCell cell, GridViewCell newCell) {
		for (int i = 0; i < this.cells.length; i++) {
			for (int j = 0; j < this.cells.length; j++) {
				if (cells[i][j] != null && cell == cells[i][j]) {
					cells[i][j] = newCell;
				}
			}
		}
	}

	public void selectCell(int row, int column) {
//		this.selectedCell = new GridViewCell();
		this.selectedCell = this.cells[row][column];
	}

}
