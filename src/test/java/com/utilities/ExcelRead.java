package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	
	// Utils Class to Read Test Data From Excel Sheet 
	
	private final Workbook workbook;
	private final DataFormatter formatter;
	
	// Constructor to Load File Path and Data Formatter
	public ExcelRead(String path) throws IOException {
		FileInputStream fis=new FileInputStream(Path.of(path).toFile());
		this.workbook=new XSSFWorkbook(fis);
		this.formatter=new DataFormatter();
	}
	
	// Method to Get Sheet From Excel File
	private Sheet getSheet(String sheetName) {
		Sheet sheet=workbook.getSheet(sheetName);
		if(sheet==null) {
			throw new IllegalArgumentException("Sheet not found:"+sheetName);
		}
		return sheet;
	}
	
	// Method to Get Total Number of Rows
	public int getRowCount(String sheetName) {
		return getSheet(sheetName).getPhysicalNumberOfRows();
		
		/*Sheet sheet=getSheet(sheetName);
		int totalRows=sheet.getPhysicalNumberOfRows();
		return totalRows;
		*/
	}
	
	// Method to Get TotalNumber of Columns in a Row
	public int getCellCount(String sheetName,int rowNum) {
		Row row=getSheet(sheetName).getRow(rowNum);
		if(row==null) {
			return 0;
		}
		int totalColumns=row.getLastCellNum();
		return totalColumns;
	}
	
	// Method To Get Cell Value
	
	public String getCellValue(String sheetName,int rowNum,int colNum) {
		Row row=getSheet(sheetName).getRow(rowNum);
		if(row==null) {
			return "";
		}
		
		Cell cell=row.getCell(colNum);
		String cellValue=formatter.formatCellValue(cell);
		return cellValue;
	}
	
	// Release File Memory
	public void close() throws IOException {
		workbook.close();
	}

}
