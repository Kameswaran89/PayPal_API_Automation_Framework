package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.constants.FrameworkConstants;

public class ConfigReader {
	
	// Class To Read Data From Properties File
	
	private static Properties prop;
	
	static {
		try {
			FileInputStream fis=new FileInputStream(FrameworkConstants.CONFIG_FILEPATH);
			prop=new Properties();
			prop.load(fis);
		}
		catch(IOException e){
			throw new RuntimeException("Failed to load properties file",e);
		}
	}
	
	public static String getUsername() {
		String uname=prop.getProperty("username");
		return uname;
	}
	
	public static String getPassword() {
		String pword=prop.getProperty("password");
		return pword;
	}
	
	public static String getBaseURI() {
		String baseURI=prop.getProperty("baseURI");
		return baseURI;
	}

}
