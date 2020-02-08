package com.caltuntas.cardgame;

public class GameFactory {

	public Game create() {
		Game game = new Game();
		Player player1 = createPlayer();
		Player player2 = createPlayer();
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		return game;
	}

	private Player createPlayer() {
		Deck deck1 = Deck.createWithManaCosts(0,0,1,1,2,2,2,3,3,3,3,4,4,4,5,5,6,6,7,8);
		Player player1 = new Player(30, 0, deck1);
		return player1;
	}

}
