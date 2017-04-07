package poker;

import java.util.ArrayList;

public class HandOfPoker {
	
	private ArrayList<PokerPlayer> pokerPlayers = new ArrayList<PokerPlayer>();
	
	public HandOfPoker(ArrayList<PokerPlayer> players) {
		pokerPlayers = players;
		System.out.println("New Deal:\n");
	}
	
	public void printChips() {
		for (int i = 0; i < pokerPlayers.size(); i++) {
			System.out.println("> " + pokerPlayers.get(i).name + " has " + pokerPlayers.get(i).chips + " chip(s)");
		}
	}
	
	public void printHumanHand() {
		System.out.println(pokerPlayers.get(0).playerHand);
	}

}