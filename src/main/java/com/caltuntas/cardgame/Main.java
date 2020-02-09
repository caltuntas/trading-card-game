package com.caltuntas.cardgame;

public class Main {

	public static void main(String[] args) {		
		GameFactory factory = new GameFactory();
		Game game = factory.create();
		game.setInputDevice(new TerminalView());
		game.start();
	}

}
