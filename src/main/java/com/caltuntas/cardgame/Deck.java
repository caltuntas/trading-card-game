package com.caltuntas.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	private List<DamageCard> cards = new ArrayList<DamageCard>();

	public int getDamageCardCount() {
		return cards.size();
	}

	public void add(DamageCard card) {
		this.cards.add(card);		
	}

	public List<DamageCard> receiveRandom(int cardCount) {
		List<DamageCard> selectedCards = new ArrayList<DamageCard>();
		while (selectedCards.size()!=cardCount) {
			Random random = new Random();
			int selectedCardIndex = random.nextInt(this.cards.size());
			DamageCard selectedCard = this.cards.get(selectedCardIndex);
			if(!selectedCards.contains(selectedCard)) {
				selectedCards.add(selectedCard);			
			}
		}
		return selectedCards;
	}

}
