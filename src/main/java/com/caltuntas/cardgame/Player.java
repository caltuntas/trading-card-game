package com.caltuntas.cardgame;

public class Player {
	private int health;
	private int mana;

	public Player(int health, int mana) {
		this.health = health;
		this.mana = mana;
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

}
