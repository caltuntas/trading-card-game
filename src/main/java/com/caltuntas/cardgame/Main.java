package com.caltuntas.cardgame;

public class Main {

	public static void main(String[] args) {
		System.out.println("Game is starting soon...");
		GameFactory factory = new GameFactory();
		Game game = factory.create();
		game.setInputDevice(new TerminalView());
		game.start();
		while(!game.isOver()) {
			game.nextRound();
		}

	}

}
