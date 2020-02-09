package com.caltuntas.cardgame;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.caltuntas.cardgame.Commands.*;

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
	public void preparePlayers() {
		Game game = new Game();
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		
		game.preparePlayers();
		assertEquals(player1, game.getActivePlayer());
		assertEquals(1, player1.getMana());
	}

	@Test
	public void playerSkips() {
		GameView inputDevice = mock(GameView.class);
		
		when(inputDevice.getCommand()).thenReturn(SKIP_COMMAND);
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.preparePlayers();
		
		game.nextRound();
		assertEquals(player2, game.getActivePlayer());
	}

	@Test
	public void playerUsesDamageCard() {
		GameView inputDevice = mock(GameView.class);
		
		when(inputDevice.getCommand()).thenReturn(PLAY_COMMAND+"0");
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.preparePlayers();
		
		game.nextRound();
		assertTrue(game.getActivePlayer().getHealth()<30);
	}

	@Test
	public void opponentBecomesActiveWhenActivePlayerHasNoCards() {
		GameView inputDevice = mock(GameView.class);
		
		when(inputDevice.getCommand()).thenReturn(PLAY_COMMAND+"0");
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.preparePlayers();
		
		game.nextRound();
		assertEquals(player2, game.getActivePlayer());
	}

	@Test
	public void playerUsesMultipleDamageCards() {
		GameView inputDevice = mock(GameView.class);
		
		when(inputDevice.getCommand()).thenReturn(PLAY_COMMAND+"0",PLAY_COMMAND+"0",SKIP_COMMAND);
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 10, createTestDeck());
		player1.setCardsOnHand(createTestDeck().getCards());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.preparePlayers();
		
		game.nextRound();
		assertEquals(28,player2.getHealth());
		assertEquals(player2, game.getActivePlayer());
	}
	
	@Test
	public void playerChoosesCardRequiresMoreMana() {
		Deck deck = new Deck();
		DamageCard card1 = new DamageCard(1);
		DamageCard card2 = new DamageCard(5);
		DamageCard card3 = new DamageCard(4);
		deck.add(card1, card2, card3);
		
		GameView inputDevice = mock(GameView.class);
		
		when(inputDevice.getCommand()).thenReturn(PLAY_COMMAND+"1",SKIP_COMMAND);
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 1, deck);
		player1.setCardsOnHand(deck.getCards());
		Player player2 = new Player(30, 10, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.preparePlayers();
		
		game.nextRound();
		assertEquals(30,player2.getHealth());
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
		game.preparePlayers();
		
		assertTrue(game.isOver());
		player1.setHealth(30);
		player2.setHealth(-1);
		assertTrue(game.isOver());
	}

	@Test
	public void isNotOver() {
		GameView inputDevice = mock(GameView.class);
		
		Game game = new Game(inputDevice);
		Player player1 = new Player(10, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.preparePlayers();
		
		assertFalse(game.isOver());
	}

	@Test
	public void getWinner() {
		GameView inputDevice = mock(GameView.class);
		
		Game game = new Game(inputDevice);
		Player player1 = new Player(-1, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.preparePlayers();
		
		assertTrue(game.isOver());
		assertEquals(player2, game.getWinner());
		player2.setHealth(-1);
		player1.setHealth(30);
		assertEquals(player1, game.getWinner());
	}
	
	@Test
	public void start() {
		GameView inputDevice = mock(GameView.class);
		Game game = new Game();
		game.setInputDevice(inputDevice);
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		
		Game spyGame = spy(game);

	    doReturn(false,true).when(spyGame).isOver();
	    doNothing().when(spyGame).nextRound();

	  
		
		spyGame.start();
		verify(inputDevice).show("Game is over. Winner is....");
	}
	
	@Test
	public void playerGivesInvalidCommand() {
		GameView inputDevice = mock(GameView.class);
		
		when(inputDevice.getCommand()).thenReturn("InvalidCommand", SKIP_COMMAND);
		Game game = new Game(inputDevice);
		Player player1 = new Player(30, 0, createTestDeck());
		Player player2 = new Player(30, 0, createTestDeck());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.preparePlayers();
		
		game.nextRound();
		
		verify(inputDevice).show("Invalid command! Valid commands : skip, playWithCard[cardNumber]");		
		assertEquals(player2, game.getActivePlayer());
	}
	
	

}
