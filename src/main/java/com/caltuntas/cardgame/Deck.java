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
		while (selectedCards.size()!=cardCount) {
			Random random = new Random();
			int selectedCardIndex = random.nextInt(this.cards.size());
			DamageCard selectedCard = this.cards.get(selectedCardIndex);
			if(!selectedCards.contains(selectedCard)) {
				selectedCards.add(selectedCard);			
			}
		}
		cards.removeAll(selectedCards);
		return selectedCards;
	}

	public List<DamageCard> getCards() {
		return cards;
	}

	public void setCards(List<DamageCard> cards) {
		this.cards = cards;
	}

}
