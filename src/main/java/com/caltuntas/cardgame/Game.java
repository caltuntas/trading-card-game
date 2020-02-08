package com.caltuntas.cardgame;

public class Game {
	private Player player1;
	private Player player2;
	private Deck starterDeck;
	private Player activePlayer;

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
	public Player getActivePlayer() {
		return this.activePlayer;
	}
	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}
	public void start() {
		setActivePlayer(player1);		
		player1.activate();
	}
}
