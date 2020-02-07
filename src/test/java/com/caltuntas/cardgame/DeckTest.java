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
		Deck deck = new Deck();
		DamageCard card1 = new DamageCard(1);
		DamageCard card2 = new DamageCard(2);
		DamageCard card3 = new DamageCard(3);
		deck.add(card1);
		deck.add(card2);
		deck.add(card3);
		//test it 100 times and ensure random selected 2 cards from 3 card deck are not the same 
		for (int i = 0; i < 100; i++) {
			List<DamageCard> cards = deck.receiveRandom(2);
			assertEquals(2,cards.size()) ;
			assertNotEquals(cards.get(0).getManaCost(), cards.get(1).getManaCost());
			//object reference compasion or hashCode is enough to test if they point to same object
			assertNotEquals(cards.get(0), cards.get(1));
		}
	}

}
