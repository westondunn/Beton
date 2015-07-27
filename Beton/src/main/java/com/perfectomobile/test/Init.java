package com.perfectomobile.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Init {

	protected static Properties prop;	
	
	private Init(){}
	
	public static Properties Prop() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("src/main/resources/beton.properties"));
			return prop;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}
	

	public static Properties getProp() {
		return prop;
	}
	public static void setProp(Properties prop) {
		Init.prop = prop;
	}

}
