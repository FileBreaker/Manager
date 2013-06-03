package com.filebreaker.manager.gui;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import com.filebreaker.manager.controllers.MainController;
import com.filebreaker.manager.gui.frames.ExperimentsFrameView;

public class Gui extends SingleFrameApplication {
    /**
     * At startup create and show the main frame of the application.
     */
	private MainController mainController;
	
	public void startup(MainController mainController){
    	this.mainController = mainController;
    	this.startup();
    }
	
    @Override 
    protected void startup() {
        show(new ExperimentsFrameView(this, this.mainController));
    }
    
    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    	
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DesktopApplication1
     */
    public static Gui getApplication() {
        return Application.getInstance(Gui.class);
    }
}
