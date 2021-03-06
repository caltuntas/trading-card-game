package com.caltuntas.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	private List<DamageCard> cards = new ArrayList<DamageCard>();
	
	public static Deck createWithManaCosts(int ...manaCosts) {
		Deck deck = new Deck();
		for (int i = 0; i < manaCosts.length; i++) {
			int manaCost = manaCosts[i];
			DamageCard card = new DamageCard(manaCost);			
			deck.add(card);
		}
		return deck;
	}
	
	public static Deck copy(Deck originalDeck) {
		Deck copyDeck = new Deck();
		for (DamageCard originalCard : originalDeck.getCards()) {
			DamageCard copyCard = new DamageCard(originalCard.getManaCost());			
			copyDeck.add(copyCard);
		}
		return copyDeck;
	}
	

	public int getDamageCardCount() {
		return cards.size();
	}

	public void add(DamageCard ...cards) {
		for (int i = 0; i < cards.length; i++) {
			DamageCard card = cards[i];
			this.cards.add(card);		
		}
	}

	public List<DamageCard> drawRandom(int cardCount) {
		List<DamageCard> selectedCards = new ArrayList<DamageCard>();
		if(this.getDamageCardCount()<=0) {
			return selectedCards;
		}
		while (getDamageCardCount()>0 && selectedCards.size()!=cardCount) {
			Random random = new Random();
			int selectedCardIndex = random.nextInt(this.cards.size());
			DamageCard selectedCard = this.cards.get(selectedCardIndex);
			selectedCards.add(selectedCard);			
			this.cards.remove(selectedCard);
		}
		return selectedCards;
	}

	public List<DamageCard> getCards() {
		return cards;
	}


	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		String deckString = "Deck [hashCode()=" + hashCode() + "]";
		stringBuilder.append(deckString);
		String newLine = System.getProperty("line.separator");
		stringBuilder.append(newLine);
		for (DamageCard damageCard : cards) {
			stringBuilder.append(damageCard.toString());
			stringBuilder.append(newLine);
		}
		return stringBuilder.toString();
	}
}
