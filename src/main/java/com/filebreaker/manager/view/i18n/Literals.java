package com.filebreaker.manager.view.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Literals {

	private static ResourceBundle rb = ResourceBundle.getBundle("filebreaker", new Locale("en", "EN"));
	
	private static final Literals INSTANCE = new Literals();
	
	private Literals(){	/* private constructor */ }
	
	public static Literals getInstance(){
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