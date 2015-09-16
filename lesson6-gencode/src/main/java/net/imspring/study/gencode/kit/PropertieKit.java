package net.imspring.study.gencode.kit;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertieKit {

	// 根据key读取value
	public static String readValue(String filePath, String key) {
		String basepath  = PropertieKit.class.getClass().getResource("/").getPath();
		filePath = basepath + filePath; 
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
