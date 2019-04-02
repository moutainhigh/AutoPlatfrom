package com.gs.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 邮件配置类，读取传递进来的配置文件
 * @author Administrator
 *
 */
public class MailConfig {

	/**
	 * 根据配置文件生成的配置对象
	 */
	private static Properties properties; 
	
	/**
	 * 根据发送的类型读取默认配置文件
	 * @param type 邮件类型，可选值为126，163，QQ，默认是QQ
	 * @return 返回配置文件对象
	 */
	public static Properties readProperties(String type, String userEmail, String password) {
		properties = new Properties();
		try {
			if ("163".equals(type)) {
				properties.load(MailConfig.class.getResourceAsStream("163Email.properties"));
			} else if ("126".equals(type)) {
				properties.load(MailConfig.class.getResourceAsStream("126Email.properties"));
			} else {
				properties.load(MailConfig.class.getResourceAsStream("QQEmail.properties"));
			}
			properties.setProperty("userEmail", userEmail);
			properties.setProperty("password", password);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * 读取传递进来的配置文件，生成Properties对象
	 * @param file Mail配置文件
	 * @return 根据传递进来的配置文件生成的配置对象
	 */
	public static Properties readProperties(File file) {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * 获取到配置文件中的值转化为String类型
	 * @param key 配置文件中的key
	 * @return 传递进来key所对应的Value
	 */
	public static String getString(String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * 获取到配置文件中的值转化成int类型
	 * @param key 配置文件中的key值
	 * @return key所对应的Value
	 */
	public static int getInt(String key) {
		return Integer.valueOf(properties.getProperty(key));
	}
}
