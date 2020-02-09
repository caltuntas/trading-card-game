package com.caltuntas.cardgame;

public class GameFactory {

	public Game create() {
		Game game = new Game();
		Deck starterDeck = Deck.createWithManaCosts(0,0,1,1,2,2,2,3,3,3,3,4,4,4,5,5,6,6,7,8);
		game.setStarterDeck(starterDeck);
		Player player1 = createPlayer(game);
		player1.setName("Mehmet");
		Player player2 = createPlayer(game);
		player2.setName("Cihat");
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		return game;
	}

	private Player createPlayer(Game game) {
		Player player1 = new Player(30, 0, Deck.copy(game.getStarterDeck()));
		player1.drawCards(3);
		return player1;
	}

}
