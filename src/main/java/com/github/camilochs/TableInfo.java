package com.github.camilochs;
/**
 * Manage general table information.
 */
public class TableInfo {
	
	private String name;
	private int rowNumber;
	private int columnNumber;
	
	public TableInfo(String name, int rowNumber, int columnNumber){
		this.setName(name);
		this.setRowNumber(rowNumber);
		this.setColumnNumber(columnNumber);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}
}