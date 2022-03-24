package view;

import controller.AeroportoController;

public class Main {

	public static void main(String[] args) {
		
		
		for(int i = 0; i < 12; i++) {
			new AeroportoController(i).start();
		}
	}

}
