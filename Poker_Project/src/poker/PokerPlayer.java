package poker;
import java.util.Random;
import java.util.Scanner;

public class PokerPlayer {

	protected HandOfCards playerHand;
	protected String name;
	protected int chips;
	protected boolean inHand = true;
	protected int lastBet = 0;

	public PokerPlayer(DeckOfCards cardDeck, String playerName){
		playerHand = new HandOfCards(cardDeck);
		chips = 20;
		//System.out.println("What is your name?");
		name = playerName;
	}

	public String prompt() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		input = input.trim();
		return input;
	}

	//	public void bet(int chipsBet) {
	//		if (this.chips > 0) {
	//			this.chips -= chipsBet;
	//		}
	//		else {
	//			System.out.println("Bankrupt");
	//		}
	//	}

	public void chipsWon(int noOfChips) {
		chips += noOfChips;
	}

	public void returnCards(DeckOfCards deck) {
		// returns all cards back to the deck
		for (int i = 0; i < playerHand.HAND_SIZE; i++) {
			deck.returnCard(playerHand.getCard(i));
		}

	}

	public void newHand(DeckOfCards deck) {
		// Gives player new hand
		playerHand = new HandOfCards(deck);
	}

	public int getChipsToRaise() {
		boolean correctInput = false;
		int chipsBet = 0;
		if(chips<1){
			System.out.println("You do not have any chips to raise with");
		}
		else{
			do {
				System.out.println("How much would you like to raise by? (< " + chips + ")");
				try {
					chipsBet = Integer.parseInt(prompt());

					if (chipsBet > chips) { // Cannot bet more than chips held by player
						System.out.println("You only have " + chips + " chip(s)");
					}
					else if (chipsBet < 0) {
						System.out.println("Must be a positive number!");
					}

					else {
						this.chips -= chipsBet;
						return chipsBet;
					}
				}
				catch (NumberFormatException e){ // String entered is not a valid integer
					System.out.println("Bet must be a number!");
				}

			} while (!correctInput);
		}
		return chipsBet;
	}

	public boolean fold(int costToCall, TwitterBot tbot, String content) {
		String input = content;
		if(costToCall >= this.chips){
			System.out.println("\nWould you like to fold? (To call you must go all in,  " + costToCall  +" chips)");
			tbot.tweet("\nWould you like to fold? (To call you must go all in,  " + costToCall  +" chips)");
		}

		else{
			System.out.println("\nWould you like to fold? (The cost to call is " + costToCall  +" chips)");
			tbot.tweet("\nWould you like to fold? (The cost to call is " + costToCall  +" chips)");

		}

		do {
			if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
				return true;
			}
			else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
				if(costToCall > this.chips) {
					this.chips = 0;
				}
				else {
					this.chips -= costToCall;
				}
				return false;
			}
			else {
				System.out.println("Wrong input. Please tweet 'yes' or 'no'");
			}
		} while (true);
	}

	public int getChips() {
		return chips;
	}

	public void discard(){
		// Deals with Human Player
		boolean correctInput = false;
		String input = "";
		String [] cards = input.split(" ");
		int [] discard = new int[cards.length];

		do {
			boolean inRange = true; // checks if input is an index in hand (e.g between 0-4)
			System.out.println("What cards would you like to discard? (e.g 0 2 3)");
			input = prompt();
			cards = input.split(" ");
			discard = new int[cards.length];

			if (discard.length > 3) {
				System.out.println("Maximum cards you can discard is three");
			}
			else {
				try {
					// Assigns and parses discarded cards to integers
					for (int i = 0; i < cards.length; i++) {
						discard[i] = Integer.parseInt(cards[i]);
						if (discard[i] < 0 || discard[i] > 4) {
							inRange = false;
						}
					}	

					if (inRange) {
						correctInput = true;
					}
					else {
						System.out.println("Index enetered is out of range, must be between 0-4");
						correctInput = false;
					}
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid input (must be integers)");
				}
			}

		} while (!correctInput);

		playerHand.discardCards(discard, discard.length);	
	}

	public void resetBet(){
		lastBet = 0;
	}

	public static void main(String [ ] args)
	{
		DeckOfCards theDeck = new DeckOfCards();
		theDeck.shuffle();
		PokerPlayer player = new PokerPlayer(theDeck,"Luke");
		System.out.println("Hello " + player.name);


		System.out.println(player.playerHand);
		for (int i = 0; i < HandOfCards.HAND_SIZE; i++) {
			System.out.print(player.playerHand.getDiscardProbability(i) + "% ");
		}
		System.out.println();
		player.discard();
		System.out.println(player.playerHand);


	}
}
