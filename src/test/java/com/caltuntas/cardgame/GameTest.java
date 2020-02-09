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
		GameView inputDevice = mock(GameView.class);
		
		when(inputDevice.getCommand()).thenReturn("skip");
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		
		game.nextRound();
		assertEquals(player2, game.getActivePlayer());
	}

	@Test
	public void playerUsesDamageCard() {
		GameView inputDevice = mock(GameView.class);
		
		when(inputDevice.getCommand()).thenReturn("playWithCard0");
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		
		game.nextRound();
		assertTrue(game.getActivePlayer().getHealth()<30);
	}

	@Test
	public void opponentBecomesActiveWhenActivePlayerHasNoCards() {
		GameView inputDevice = mock(GameView.class);
		
		when(inputDevice.getCommand()).thenReturn("playWithCard0");
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		
		game.nextRound();
		assertEquals(player2, game.getActivePlayer());
	}

	@Test
	public void playerUsesMultipleDamageCards() {
		GameView inputDevice = mock(GameView.class);
		
		when(inputDevice.getCommand()).thenReturn("playWithCard0","playWithCard0","skip");
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 10, createTestDeck());
		player1.setCardsOnHand(createTestDeck().getCards());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		
		game.nextRound();
		assertEquals(28,player2.getHealth());
		assertEquals(player2, game.getActivePlayer());
	}

	@Test
	public void isOver() {
		GameView inputDevice = mock(GameView.class);
		
		Game game = new Game(inputDevice);
		Player player1 = new Player(-1, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		
		assertTrue(game.isOver());
	}

}
