package com.caltuntas.cardgame;

public class Game {
	private Player player1;
	private Player player2;
	private Deck starterDeck;

	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	public Deck getStarterDeck() {
		return starterDeck;
	}
	public void setStarterDeck(Deck starterDeck) {
		this.starterDeck = starterDeck;
	}
}