package com.caltuntas.cardgame;

public class InvalidCard extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1484924057776564962L;

	public InvalidCard(String message) {
		super(message);
	}
	
}
