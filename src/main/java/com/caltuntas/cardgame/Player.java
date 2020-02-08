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
		if(getCardsOnHand().size()<=0 && deck.getDamageCardCount()<=0) {
			this.health--;
			return;
		}
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
		getCardsOnHand().addAll(this.deck.drawRandom(cardCount));		
	}

	public void damage(DamageCard card) {
		int healthAfterDamage = getHealth() - card.getManaCost();
		setHealth(healthAfterDamage);		
	}

	public DamageCard playWith(int cardIndex) {
		DamageCard cardToPlay = cardsOnHand.get(cardIndex);		
		int currentMana = getMana() - cardToPlay.getManaCost();
		setMana(currentMana);
		this.getCardsOnHand().remove(cardToPlay);
		return cardToPlay;
	}

	public boolean canPlay() {
		for (DamageCard damageCard : cardsOnHand) {
			if(getMana()>=damageCard.getManaCost())
				return true;
		}
		return false;
	}
	
	//TODO: check if played card's mana cost is greater than player's current mana
}
