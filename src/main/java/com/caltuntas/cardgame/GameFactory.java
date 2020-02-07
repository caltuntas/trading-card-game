package com.caltuntas.cardgame;

public class GameFactory {

	public Game create() {
		Game game = new Game();
		Deck deck1 = new Deck();
		Player player1 = new Player(30, 0, deck1);
		
		Deck deck2 = new Deck();
		Player player2 = new Player(30, 0, deck2);
		
		game.setPlayer1(player1);
		game.setPlayer1(player2);
		return game;
	}

}
