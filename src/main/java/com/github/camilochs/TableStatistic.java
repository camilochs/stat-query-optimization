package com.github.camilochs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Manage table statistics.
 */
public class TableStatistic {
	private final String fileNameTableStat = "table_stat.txt";
	
	private void createTableFile(){
		try {
			File file = new File(fileNameTableStat);
			if (file.exists()){
				return;
			}
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("TABLE_NAME|ROWS_NUMBER|COLUMN_NUMBER\n");
			writer.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	private void createColumnFile(String tableName){
		try {
			File file = new File(tableName + "_stat.txt");
			if (file.exists()){
				return;
			}
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("COLUMN_NAME|DISTINCT|NULL|MAX|MIN|AVERAGE|\n");
			writer.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public boolean create(TableInfo tableInfo, List<TableColumnInfo> tableColumnsInfo){
		if(tableInfo == null){
			return false;
		}
		try {
			createTableFile();
			FileWriter writer = new FileWriter(new File(fileNameTableStat), true);
			writer.append(String.format("%s|%s|%s\n", tableInfo.getName(), tableInfo.getRowNumber(), tableInfo.getColumnNumber()));
			writer.close();
			
			if(tableColumnsInfo == null){
				writer.close();
				return false;
			}
			//Create column statistic file.
			createColumnFile(tableInfo.getName());
			
			writer = new FileWriter(new File(tableInfo.getName() + "_stat.txt"), true);
			
			for(TableColumnInfo e: tableColumnsInfo){	
				writer.append(String.format("%s|%s|%s|%s|%s|%s\n", 
						e.getColumnName(), 
						e.getDistinctValue(), 
						e.getTotalNull(),
						e.getMaxValue(),
						e.getMinValue(),
						e.getAverageValue()));
			}
			
			writer.close();
			
			
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
