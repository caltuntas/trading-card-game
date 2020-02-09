package com.caltuntas.cardgame;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class DeckTest {

	@Test
	public void addDamageCard() {
		Deck deck = new Deck();
		DamageCard card1 = new DamageCard(1);
		deck.add(card1);
		assertEquals(1, deck.getDamageCardCount());
	}
	
	@Test
	public void receiveRandomCards() {
		//test it 100 times and ensure random selected 2 cards from 3 card deck are not the same 
		for (int i = 0; i < 100; i++) {
			Deck deck = new Deck();
			DamageCard card1 = new DamageCard(1);
			DamageCard card2 = new DamageCard(2);
			DamageCard card3 = new DamageCard(3);
			deck.add(card1);
			deck.add(card2);
			deck.add(card3);
			List<DamageCard> cards = deck.drawRandom(2);
			assertEquals(2,cards.size()) ;
			assertNotEquals(cards.get(0).getManaCost(), cards.get(1).getManaCost());
			//object reference compasion or hashCode is enough to test if they point to same object
			assertNotEquals(cards.get(0), cards.get(1));
		}
	}

	@Test
	public void afterDrawingCard() {
		Deck deck = new Deck();
		DamageCard card1 = new DamageCard(1);
		DamageCard card2 = new DamageCard(2);
		DamageCard card3 = new DamageCard(3);
		deck.add(card1);
		deck.add(card2);
		deck.add(card3);
		List<DamageCard> cards = deck.drawRandom(2);
		assertEquals(2,cards.size()) ;
		assertEquals(1,deck.getDamageCardCount()) ;
	}

	@Test
	public void drawRandomFromEmptyDeck() {
		Deck deck = new Deck();
		List<DamageCard> cards = deck.drawRandom(1);
		assertEquals(0,cards.size()) ;
		assertEquals(0,deck.getDamageCardCount()) ;
	}

	@Test
	public void drawMoreCardsThanDeckHas() {
		Deck deck = new Deck();
		DamageCard card1 = new DamageCard(1);
		deck.add(card1);
		List<DamageCard> cards = deck.drawRandom(3);
		assertEquals(1,cards.size()) ;
		assertEquals(0,deck.getDamageCardCount()) ;
	}

	@Test
	public void customToString() {
		Deck deck = new Deck();
		DamageCard card1 = new DamageCard(1);
		deck.add(card1);
		String str = deck.toString();
		assertTrue(str.contains("Deck [hashCode()="));
		assertTrue(str.contains(card1.toString()));
	}
	
	//TODO: test drawing more cards than a deck has

}
