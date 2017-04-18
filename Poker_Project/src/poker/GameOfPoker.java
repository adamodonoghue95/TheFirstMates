package poker;

import java.util.ArrayList;

public class GameOfPoker {
	private static ArrayList<PokerPlayer> players = new ArrayList<PokerPlayer>();
	private static ArrayList<PokerPlayer> inactivePlayers = new ArrayList<PokerPlayer>();
	private DeckOfCards deck = new DeckOfCards();
	
	public GameOfPoker(int numberOfPlayers) {
		players.add(new HumanPokerPlayer(deck));
		players.add(new AutomatedPokerPlayer(deck,"Jack"));
		players.add(new AutomatedPokerPlayer(deck,"Henry"));
		players.add(new AutomatedPokerPlayer(deck,"Ron"));

	}
	
	public static void main(String [] args) {
		System.out.println("Welcome to TheFirstMates Poker Game!\n");

		GameOfPoker game = new GameOfPoker(4);
		
		System.out.println("\nLet's Play Poker " + players.get(0).name + "!");
		HandOfPoker hand = new HandOfPoker(players);
		hand.printChips();
		
		System.out.println("\nYour Current Hand: -> " + players.get(0).playerHand.getHandType());
		hand.printHumanHand();
		
		// Let players fold and start betting
		hand.checkFold();
		hand.discardCards();
		
		System.out.println("\nYour Current Hand: -> " + players.get(0).playerHand.getHandType());
		hand.printHumanHand();
		//Complete second round of betting
		hand.checkFold();
		
		//Show Cards
		System.out.println("\nYour Current Hand: -> " + players.get(0).playerHand.getHandType());
		hand.showCards();
		hand.decideWinner();
		
		
		//hand.printChips();
		
		
	}
	
}
