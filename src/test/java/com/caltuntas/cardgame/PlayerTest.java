package com.caltuntas.cardgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void createPlayerWithHealthAndMana() {
		Player player = new Player(30,0);
		assertEquals(30, player.getHealth());
		assertEquals(0, player.getMana());
	}

}