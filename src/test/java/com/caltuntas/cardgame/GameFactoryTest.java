package com.caltuntas.cardgame;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class GameFactoryTest {

	@Test
	public void createGame() {
		GameFactory factory = new GameFactory();
		Game game = factory.create();
		int[] expectedManaCosts = {0,0,1,1,2,2,2,3,3,3,3,4,4,4,5,5,6,6,7,8};
		List<DamageCard> cards =  game.getStarterDeck().getCards();
		for (int i=0; i<cards.size(); i++) {
			DamageCard card = cards.get(i);
			assertEquals(expectedManaCosts[i], card.getManaCost());						
		}
		assertPlayer(game.getPlayer1());
		assertPlayer(game.getPlayer2());
	}

	private void assertPlayer(Player player) {
		assertNotNull(player);
		assertEquals(30, player.getHealth());
		assertEquals(0, player.getMana());
		assertEquals(17, player.getDeck().getCards().size());
		assertEquals(3, player.getCardsOnHand().size());
	}

}
