package com.filebreaker.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.filebreaker.manager.view.Gui;

public class FilebreakerMain {

	private static final String APPLICATION_CONTEXT_FILENAME = "application-context.xml";
	
	@Autowired
	private Gui gui;
	
	public FilebreakerMain(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_FILENAME);
		
		try {
			AutowireCapableBeanFactory acbFactory = context.getAutowireCapableBeanFactory();
			acbFactory.autowireBean(this);
		} finally {
			context.close();
		}
	}
	
	public static void main(String [] args){
		FilebreakerMain main = new FilebreakerMain();
		main.gui.startup();
	}
}