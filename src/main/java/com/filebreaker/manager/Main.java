package com.filebreaker.manager;

import com.filebreaker.manager.controllers.MainController;
import com.filebreaker.manager.gui.Gui;

public class Main {

	public static void main(String [] args){
		MainController mainController = new MainController();
		
		Gui gui = Gui.getApplication();
		gui.startup(mainController);
	}
}
