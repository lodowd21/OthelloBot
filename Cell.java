package edu.unca.csci312;
/**
 * creates instance variables and intializes them in constructor and creates getters and setters
 * @author liamo
 *
 */
public class Cell {

	private String cellState;
	private int row;
	private int column;

	// Constructor - defines what a Cell is
	public Cell(String cellState, int row, int column) {
		this.cellState = cellState;
		//this.mineStatus = mineStatus;
		this.row = row;
		this.column = column;
	}

	public String getCellState() {
		return cellState;
	}

	public void setCellState(String cellState) {
		this.cellState = cellState;
	}

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

	// This is important to print the value of the cell as a readable String, rather
	public String toString() {
		return this.cellState;
	}

}
