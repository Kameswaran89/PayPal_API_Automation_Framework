package com.dataproviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.constants.FrameworkConstants;
import com.utilities.ExcelRead;

public class ExcelDataProvider {
	
	// Data Provider Class to Retrieve Test Data From Excel Sheet and Pass it to Test Class
	
	// Method to Read Data from Excel File
	public Object[][] getData(String filePath, String sheetName) throws IOException{
		
		ExcelRead readExcelData=new ExcelRead(filePath);
		
		int totalRows=readExcelData.getRowCount(sheetName); // To Get Total Number of Rows
		int totalColumns=readExcelData.getCellCount(sheetName, 1); // To Get Total Number of Columns in a Row
		
		Object[][] data=new Object[totalRows-1][totalColumns];
		
		for(int i=1;i<totalRows;i++) {
			for(int j=0;j<totalColumns;j++) {
				data[i-1][j]=readExcelData.getCellValue(sheetName, i, j);
			}
		}
		return data;
	}
	
	
	// Method to Get Order Data 
	@DataProvider(name="orderTestData")
	public Object[][] getOrderData() throws IOException{
		String filePath=FrameworkConstants.ORDER_DATA;
		String sheetName=FrameworkConstants.ORDER_SHEET_NAME;
		return getData(filePath,sheetName);
	}
	
	// Method to Get Product Data
	@DataProvider(name="productTestData")
	public Object[][] getProductData() throws IOException{
		String filePath=FrameworkConstants.PRODUCT_DATA;
		String sheetName=FrameworkConstants.PRODUCT_SHEET_NAME;
		return getData(filePath,sheetName);
	}

}
