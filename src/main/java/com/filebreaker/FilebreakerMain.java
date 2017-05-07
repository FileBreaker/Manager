package com.filebreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.filebreaker.experiments.ExperimentsController;
import com.filebreaker.samples.SamplesController;
import com.filebreaker.view.Gui;

public class FilebreakerMain {

	private static final String APPLICATION_CONTEXT_FILENAME = "application-context.xml";
	
	@Autowired
	SamplesController samplesController;
	
	@Autowired
	ExperimentsController experimentsController;
	
	static ClassPathXmlApplicationContext applicationContext;
	
	public FilebreakerMain(){
		FilebreakerMain.applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_FILENAME);
		
		AutowireCapableBeanFactory acbFactory = applicationContext.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);
	}
	
	public static void main(String [] args){
		FilebreakerMain fileBreakerMain = new FilebreakerMain();
		Gui.getApplication().startup(fileBreakerMain.samplesController, fileBreakerMain.experimentsController);
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
		    @Override
		    public void run() {
		    	FilebreakerMain.applicationContext.close();
		    }
		});
	}
	
}