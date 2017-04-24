package com.github.camilochs;

public class TableColumnInfo {
	
	private String columnName;
	private String dataType;
	private long distinctValue;
	private double maxValue;
	private double minValue;
	private long totalNull;
	private double averageValue;
	
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public long getDistinctValue() {
		return distinctValue;
	}
	public void setDistinctValue(long distinctValue) {
		this.distinctValue = distinctValue;
	}
	public double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}
	public double getMinValue() {
		return minValue;
	}
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	public long getTotalNull() {
		return totalNull;
	}
	public void setTotalNull(long totalNull) {
		this.totalNull = totalNull;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public double getAverageValue() {
		return averageValue;
	}
	public void setAverageValue(double averageValue) {
		this.averageValue = averageValue;
	}
	
	
}
