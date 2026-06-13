package com.constants;

public class FrameworkConstants {
	
	// Class to Store the All Constants To Be Used In Framework
	
	// System Directory Root Path
	public static final String USER_DIR=System.getProperty("user.dir");
	
	// Properties File Path
	public static final String CONFIG_FILEPATH=USER_DIR+"/src/test/resources/config.properties";
	
	// Test Data Excel Sheet File Paths
	public static final String ORDER_DATA=USER_DIR+"/testdata/Orders.xlsx";
	public static final String ORDER_SHEET_NAME="Orders";
	public static final String PRODUCT_DATA=USER_DIR+"/testdata/Products.xlsx";
	public static final String PRODUCT_SHEET_NAME="Products";
	
	// Extent Reports Folder Path
	public static final String REPORT_FILEPATH=USER_DIR+"/reports/";
	

}
