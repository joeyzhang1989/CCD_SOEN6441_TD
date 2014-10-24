package com.soen6441.ui.common;

import java.awt.Dimension;

import com.soen6441.ui.parallel.View;

/**
 * This class defines the GridView, the main grid, map of the game.
 *
 * @author JeanRaymondDaher
 * @since 0.1
 * @see GridViewCell
 */

public class GridView extends View {


	/*
	 * Constructor
	 */
	
	public GridView(){
		this(10, 10);
	}
	
	public GridView(int row, int column) {
		this.numberOfColumns = column;
		this.numberOfRows = row;
		this.cells = new GridViewCell[row][column];
	}
	
	/*
	 * Mark - Basic - Properties
	 */
	
	private int numberOfRows;
	private int numberOfColumns;

	private int unitWidth;
	private int unitHeight;

	private GridViewCell[][] cells;

	/*
	 * Mark - Basic - Methods
	 */
	
	/**
	 * This method will return the suggested width by taking the number of columns and multiplying it by the cell's width AND
	 * the suggested height by taking the number of rows and multiplying it by the cell's height
	 * @return Dimension 
	 * 			  		Object containing (width and height)
	 */
	
	public Dimension suggestedSize() {
		int width=this.numberOfColumns*this.unitWidth;
		int height=this.numberOfRows*this.unitHeight;
		return new Dimension(width, height);
	}

	/*
	 * Getters & Setters for numberOfRows and numberOfColumns
	 */
	
	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
		this.cells=new GridViewCell[numberOfRows][this.numberOfColumns];
	}

	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
		this.cells=new GridViewCell[this.numberOfRows][numberOfColumns];
	}

	/*
	 * Getters & Setters for unitWidth and unitHeight
	 */

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

	/*
	 * Methods for managing GridViewCell: adding, removing and replacing a cell.
	 */

	/**
	 * Add a cell to the cells[row][column].
	 * 
	 * @param cell
	 *            Defines the cell that needs to be added
	 * @param point
	 *            Defines the location where the cell should reside
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
	 * 
	 * @param cell
	 *            GridViewCell Loop through all the cells and remove the
	 *            appropriate cell.
	 * @param cell
	 *            The cell that needs to be removed
	 */
	public void removeCell(GridViewCell cell) {
		int row = cell.getPoint().getRow();
		int column = cell.getPoint().getColumn();
		this.remove(cells[row][column]);
	}

	/**
	 * Loop through all the cells and replace the appropriate cell.
	 * 
	 * @param cell
	 *            The cell that needs to be replaced
	 * @param newCell
	 *            The new cell that needs to be added
	 */
	public void replaceCell(GridViewCell cell, GridViewCell newCell) {
	
		newCell.setSize(cell.getSize());
		newCell.setLocation(cell.getLocation());
		newCell.setPoint(cell.getPoint());
		newCell.setGridView(this);
		
		int row=cell.getPoint().getRow();
		int column=cell.getPoint().getColumn();
		this.remove(cells[row][column]);
		
		this.add(newCell);
		
		if (cell == this.selectedCell) {
			setSelectedCell(newCell);
		}
		
//		newCell.repaint();
//		this.repaint(newCell.getVisibleRect());
		this.validate();
		this.repaint();
	}

	/*
	 * Mark - Selection - Properties
	 */
	
	private GridViewCell selectedCell;
	private GridViewSelectionListener selectionListener;
	
	/*
	 * Mark - Selection - Getters & Setters
	 */

	public GridViewCell getSelectedCell() {
		return selectedCell;
	}

	public void setSelectedCell(GridViewCell selectedCell) {

		if (this.selectedCell != selectedCell) {
			if (this.selectedCell != null) {
				this.selectedCell.setSelected(false);
			}
			this.selectedCell = selectedCell;
			if (this.selectionListener != null) {
				this.selectionListener.gridViewDidSelect();
			}
			if (this.selectedCell != null) {
				this.selectedCell.setSelected(true);
			}
		}
	}

	public GridViewSelectionListener getSelectionListener() {
		return selectionListener;
	}
	public void setSelectionListener(GridViewSelectionListener selectionListener) {
		this.selectionListener = selectionListener;
	}
	
}
