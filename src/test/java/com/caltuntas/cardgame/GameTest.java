package com.caltuntas.cardgame;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class GameTest {
	private Deck createTestDeck() {
		Deck deck = new Deck();
		DamageCard card1 = new DamageCard(1);
		DamageCard card2 = new DamageCard(1);
		DamageCard card3 = new DamageCard(1);
		deck.add(card1, card2, card3);
		return deck;
	}

	@Test
	public void start() {
		Game game = new Game();
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		
		game.start();
		assertEquals(player1, game.getActivePlayer());
		assertEquals(1, player1.getMana());
	}

	@Test
	public void playerSkips() {
		InputDevice inputDevice = mock(InputDevice.class);
		
		when(inputDevice.getCommand()).thenReturn("skip");
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		
		game.progress();
		assertEquals(player2, game.getActivePlayer());
	}

	@Test
	public void playerUsesDamageCard() {
		InputDevice inputDevice = mock(InputDevice.class);
		
		when(inputDevice.getCommand()).thenReturn("playWithCard0");
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		
		game.progress();
		assertTrue(game.getActivePlayer().getHealth()<30);
	}

	@Test
	public void opponentBecomesActiveWhenActivePlayerHasNoCards() {
		InputDevice inputDevice = mock(InputDevice.class);
		
		when(inputDevice.getCommand()).thenReturn("playWithCard0");
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		
		game.progress();
		assertEquals(player2, game.getActivePlayer());
	}

	@Test
	public void playerUsesMultipleDamageCards() {
		InputDevice inputDevice = mock(InputDevice.class);
		
		when(inputDevice.getCommand()).thenReturn("playWithCard0");
		when(inputDevice.getCommand()).thenReturn("playWithCard0");
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 10, createTestDeck());
		player1.setCardsOnHand(createTestDeck().getCards());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		
		game.progress();
		game.progress();
		assertEquals(28,game.getOpponent().getHealth());
		assertEquals(player1, game.getActivePlayer());
	}

}