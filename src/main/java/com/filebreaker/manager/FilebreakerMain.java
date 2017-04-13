package com.filebreaker.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.filebreaker.manager.controllers.MainController;
import com.filebreaker.manager.experiments.ExperimentsController;
import com.filebreaker.manager.samples.SamplesController;
import com.filebreaker.manager.view.Gui;

public class FilebreakerMain {

	private static final String APPLICATION_CONTEXT_FILENAME = "application-context.xml";
	
	@Autowired
	MainController mainController;
	
	@Autowired
	SamplesController samplesController;
	
	@Autowired
	ExperimentsController experimentsController;
	
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
		FilebreakerMain fileBreakerMain = new FilebreakerMain();
		Gui.getApplication().startup(fileBreakerMain.mainController, fileBreakerMain.samplesController, fileBreakerMain.experimentsController);
	}
}