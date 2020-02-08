package com.caltuntas.cardgame;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
		assertEquals(0, player.getCardsOnHand().size());
		assertEquals(0, player.getMana());
		player.activate();
		assertEquals(1, player.getMana());
		assertEquals(1, player.getCardsOnHand().size());
		assertEquals(2, player.getDeck().getDamageCardCount());
	}
	
	@Test
	public void hitByDamageCard() {
		Player player = new Player(30,0, createTestDeck());
		DamageCard card = new DamageCard(2);
		player.damage(card);
		assertEquals(28, player.getHealth());
		
	}

	@Test
	public void playWithACard() {
		Player player = new Player(30,10, createTestDeck());
		List<DamageCard> cardsOnHand = createTestDeck().getCards();
		player.setCardsOnHand(cardsOnHand);
		player.playWith(2);
		assertEquals(2, player.getCardsOnHand().size());
		assertEquals(7, player.getMana());
	}

	@Test
	public void playWithMultipleCards() {
		Player player = new Player(30,10, createTestDeck());
		List<DamageCard> cardsOnHand = createTestDeck().getCards();
		player.setCardsOnHand(cardsOnHand);
		player.playWith(0);
		player.playWith(0);
		player.playWith(0);
		assertEquals(0, player.getCardsOnHand().size());
		assertEquals(4, player.getMana());
	}

	@Test
	public void playerCantPlayWhenDoesNotHaveEnoughMana() {
		Player player = new Player(30,0, createTestDeck());
		List<DamageCard> cardsOnHand = createTestDeck().getCards();
		player.setCardsOnHand(cardsOnHand);
		assertFalse(player.canPlay());
	}

	@Test
	public void playerCantPlayWhenDoesNotHaveEnoughCardsOnHand() {
		Player player = new Player(30,10, createTestDeck());
		List<DamageCard> cardsOnHand = new ArrayList<DamageCard>();
		player.setCardsOnHand(cardsOnHand);
		assertFalse(player.canPlay());
	}
	@Test
	public void bleedingOut() {
		Deck emptyDeck = new Deck();
		Player player = new Player(30,10, emptyDeck);
		List<DamageCard> cardsOnHand = new ArrayList<DamageCard>();
		player.setCardsOnHand(cardsOnHand);
		player.activate();
		assertEquals(29, player.getHealth());
	}

	@Test
	public void overload() {
		Player player = new Player(30,10, createTestDeck());
		List<DamageCard> cardsOnHand = Deck.createWithManaCosts(1,1,1,1,1,1).getCards();
		player.setCardsOnHand(cardsOnHand);
		player.drawCards(1);
		assertEquals(10, player.getMana());
	}

}
