package com.caltuntas.cardgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	
	private Deck createTestDeck() {
		Deck deck = new Deck();
		DamageCard card1 = new DamageCard(1);
		DamageCard card2 = new DamageCard(2);
		DamageCard card3 = new DamageCard(3);
		deck.add(card1, card2, card3);
		return deck;
	}

	@Test
	public void createPlayer() {
		Player player = new Player(30,0,createTestDeck());
		assertEquals(30, player.getHealth());
		assertEquals(0, player.getMana());
	}
	
	@Test
	public void afterCreatePlayerCurrentCardShouldBeNull() {
		Player player = new Player(30,0,createTestDeck());
		assertEquals(0, player.getCardsOnHand().size());
	}
	
	@Test
	public void activatePlayer() {
		Player player = new Player(30,0, createTestDeck());
		assertEquals(0, player.getMana());
		player.activate();
		assertEquals(1, player.getMana());
	}

}
