package com.caltuntas.cardgame;

public class DamageCard {
	private int manaCost;

	public DamageCard(int manaCost) {
		this.manaCost = manaCost;
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}

	@Override
	public String toString() {
		return "DamageCard [manaCost=" + manaCost + ", hashCode()=" + hashCode() + "]";
	}
}
