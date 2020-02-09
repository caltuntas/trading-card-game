package com.caltuntas.cardgame;

import java.util.Scanner;

public class TerminalView implements GameView {
	private Scanner scanner;
	public TerminalView() {
		scanner = new Scanner(System.in);
	}

	public String getCommand() {
		System.out.println("Enter your command: ");
		String command = scanner.nextLine();
		return command;
	}

	@Override
	public void show(String text) {
		System.out.println(text);		
	}

}
