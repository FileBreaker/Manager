package com.filebreaker.manager.gui.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class Literals {

	private static ResourceBundle rb = ResourceBundle.getBundle("filebreaker", new Locale("es", "ES"));
	
	private static final Literals INSTANCE = new Literals();
	
	private Literals(){	/* private constructor */ }
	
	public static Literals getInstance(){
		return INSTANCE;
	}
	
	public String getString(String key){
		return rb.getString(key);
	}
	
}