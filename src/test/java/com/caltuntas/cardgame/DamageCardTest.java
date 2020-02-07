package com.caltuntas.cardgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class DamageCardTest {

	@Test
	public void createDamageCardWithManaCost() {
		DamageCard damageCard = new DamageCard(4);
		assertEquals(4, damageCard.getManaCost());
	}

}
