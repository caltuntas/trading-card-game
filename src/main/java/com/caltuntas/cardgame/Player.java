package com.caltuntas.cardgame;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private int health;
	private int mana;
	private Deck deck;
	private List<DamageCard> cardsOnHand = new ArrayList<DamageCard>();

	public Player(int health, int mana, Deck deck) {
		this.health = health;
		this.mana = mana;
		this.deck = deck;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void activate() {
		this.mana += 1;		
		drawCards(1);
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public List<DamageCard> getCardsOnHand() {
		return cardsOnHand;
	}

	public void setCardsOnHand(List<DamageCard> cardsOnHand) {
		this.cardsOnHand = cardsOnHand;
	}

	public void drawCards(int cardCount) {
		setCardsOnHand(this.deck.drawRandom(cardCount));		
	}
}
