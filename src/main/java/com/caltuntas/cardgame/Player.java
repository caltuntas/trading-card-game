package com.caltuntas.cardgame;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private int health;
	private int mana;
	private String name;
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
		if (getCardsOnHand().size() <= 0 && deck.getDamageCardCount() <= 0) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void drawCards(int cardCount) {
		List<DamageCard> cards = this.deck.drawRandom(cardCount);
		if (cards.size() + getCardsOnHand().size() <= 5) {
			getCardsOnHand().addAll(cards);
		}
	}

	public void hitBy(DamageCard card) {
		int healthAfterDamage = getHealth() - card.getManaCost();
		setHealth(healthAfterDamage);
	}

	public DamageCard playWith(int cardIndex) {
		if(!canPlayWith(cardIndex)) {
			throw new InvalidCard("Player doesn't have sufficient mana for the card.");
		}
		DamageCard cardToPlay = cardsOnHand.get(cardIndex);
		int currentMana = getMana() - cardToPlay.getManaCost();
		setMana(currentMana);
		this.getCardsOnHand().remove(cardToPlay);
		return cardToPlay;
	}
	
	public boolean canPlayWith(int cardIndex) {
		DamageCard cardToPlay = cardsOnHand.get(cardIndex);
		if(cardToPlay.getManaCost()>getMana()) {
			return false;			
		}
		return true;
	}

	public boolean canPlay() {
		for (DamageCard damageCard : cardsOnHand) {
			if (getMana() >= damageCard.getManaCost())
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		String playerString = "Player [name=" + name + ", health=" + health + ", mana=" + mana + ", cardsOnHand.size="
				+ cardsOnHand.size() +", deck.size="+ deck.getDamageCardCount() + ", hashCode()=" + hashCode() + "]";
		stringBuilder.append(playerString);
		String newLine = System.getProperty("line.separator");
		stringBuilder.append(newLine);
		for (DamageCard damageCard : cardsOnHand) {
			stringBuilder.append(damageCard.toString());
			stringBuilder.append(newLine);
		}

		stringBuilder.append(newLine);
		stringBuilder.append(getDeck().toString());

		return stringBuilder.toString();
	}

	// TODO: check if played card's mana cost is greater than player's current mana
}
