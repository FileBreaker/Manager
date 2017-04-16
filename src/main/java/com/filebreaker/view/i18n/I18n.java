package com.filebreaker.view.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {

	private static ResourceBundle rb = ResourceBundle.getBundle("filebreaker", new Locale("en", "EN"));
	
	private static final I18n INSTANCE = new I18n();
	
	private I18n(){	/* private constructor */ }
	
	public static I18n getInstance(){
		return INSTANCE;
	}
	
	public String getString(String key){
		try {
			return new String(rb.getString(key).getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}