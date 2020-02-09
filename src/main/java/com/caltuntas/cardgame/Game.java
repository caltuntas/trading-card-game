package com.caltuntas.cardgame;

import static com.caltuntas.cardgame.Commands.*;

public class Game {
	private static final String SWAPPING_PLAYERS_HEADERS = "Swapping players";
	private static final String OPPONENT_PLAYER_HEADER = "-----Opponent player-----";
	private static final String ACTIVE_PLAYER_HEADER = "-----Active player-----";
	private Player player1;
	private Player player2;
	private Deck starterDeck;
	private Player activePlayer;
	private Player opponent;
	private GameView inputDevice;

	public Game() {
		super();
	}

	public Game(GameView inputDevice) {
		setInputDevice(inputDevice);
	}

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
		this.activePlayer.activate();
	}

	public GameView getInputDevice() {
		return inputDevice;
	}

	public void setInputDevice(GameView inputDevice) {
		this.inputDevice = inputDevice;
	}

	public Player getOpponent() {
		return opponent;
	}

	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public void start() {
		setActivePlayer(player1);
		setOpponent(player2);
	}

	public void nextRound() {
		while (activePlayer.canPlay()) {
			display(ACTIVE_PLAYER_HEADER);
			display(getActivePlayer().toString());
			display(OPPONENT_PLAYER_HEADER);
			display(getOpponent().toString());
			String command = getInputDevice().getCommand();
			if (command.equals(Commands.SKIP_COMMAND)) {
				break;
			} else if (command.startsWith(PLAY_COMMAND)) {
				String cardIndexString = command.substring(PLAY_COMMAND.length());
				int cardIndex = Integer.parseInt(cardIndexString);
				if(activePlayer.canPlayWith(cardIndex)) {
					DamageCard card = activePlayer.playWith(cardIndex);
					opponent.hitBy(card);
				}
			}
		} 
		swapPlayers();
	}

	private void swapPlayers() {
		display(SWAPPING_PLAYERS_HEADERS);
		Player currentActivePlayer = getActivePlayer();
		Player currentOpponent = getOpponent();
		setActivePlayer(currentOpponent);
		setOpponent(currentActivePlayer);
	}

	public boolean isOver() {
		if (getActivePlayer().getHealth() <= 0 || getOpponent().getHealth() <= 0)
			return true;
		return false;
	}

	public Player getWinner() {
		if(player1.getHealth()>player2.getHealth())
			return player1;
		else
			return player2;
	}
	
	private void display(String text) {
		getInputDevice().show(text);
	}
}
