package com.caltuntas.cardgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameFactoryTest {

	@Test
	public void createGame() {
		GameFactory factory = new GameFactory();
		Game game = factory.create();
		assertNotNull(game.getPlayer1());
		assertNotNull(game.getPlayer1());
	}

}
