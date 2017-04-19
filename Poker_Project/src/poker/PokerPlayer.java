package poker;
import java.util.Random;
import java.util.Scanner;

public class PokerPlayer {

	protected HandOfCards playerHand;
	protected String name;
	protected int chips;
	protected boolean inHand = true;

	public PokerPlayer(DeckOfCards cardDeck, String playerName){
		playerHand = new HandOfCards(cardDeck);
		chips = 20;
		//System.out.println("What is your name?");
		name = playerName;
	}
	
	public String prompt() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	public void bet(int chipsBet) {
		if (chips > 0) {
			chips -= chipsBet;
		}
		else {
			System.out.println("Bankrupt");
		}
	}
	
	public void chipsWon(int noOfChips) {
		chips += noOfChips;
	}

	//this is a new method for discarding the cards
//	public int discard(){
//		//declare a new random number generator
//		Random rand = new Random();
//		//variable to store how many cards need to be discarded
//		int noDiscardCards = 0;
//		//array for storing the positions of the discarded cards
//		int [] discardCards = new int [3];
//		
//		//Loop which runs the random generator against the discard probability for each card and decides which ones to discard
//		//I don't need to check that no more than 3 cards will be discarded because my getDiscardProbability() ensures that does not happen.
//		for(int i = 0; i < HandOfCards.HAND_SIZE; i++){
//				int randomNumber = rand.nextInt(99) + 1;
//				// Test Purposes
//				// System.out.println("Rand = " + randomNumber + "   <  Discard Probability = " + playerHand.getDiscardProbability(i));
//				if(randomNumber < playerHand.getDiscardProbability(i)){
//					
//					discardCards[noDiscardCards] = i;
//					noDiscardCards++;
//				}
//		}
//		//discard the cards using the HandOfCards method which takes in the array of discarded card indexes
//		playerHand.discardCards(discardCards, noDiscardCards);
//		return noDiscardCards;
//	}
	
	
	
	public int getChipsToBet() {
		boolean correctInput = false;
		int chipsBet = 0;
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
					bet(chipsBet); // take chips away from player
					System.out.println("> You have bet " + chipsBet + " chip(s)");
					return chipsBet;
				}
			}
			catch (NumberFormatException e){ // String entered is not a valid integer
				System.out.println("Bet must be a number!");
			}

		} while (!correctInput);
		return chipsBet;
	}
	
	
	public boolean fold(int lastBet) {
		System.out.println("\nWould you like to fold? (The cost to call is " + lastBet +" chips)");
		String input = prompt();

		do {
			if (input.equals("y") || input.equals("Y")) {
				System.out.println("You have folded\n");
				return true;
			}
			else if (input.equals("n") || input.equals("N")) {
				return false;
			}
			else {
				System.out.println("Wrong input");
			}
		} while (true);
	}
	
	public void discard(){
		// Deals with Human Player
		boolean correctInput = false;
		String input = "";
		String [] cards = input.split(" ");
		int [] discard = new int[cards.length];

		do {
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
					}	
					correctInput = true;
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid input (must be integers)");
				}
			}

		} while (!correctInput);

		for (int i1 = 0; i1 < discard.length; i1++) {
			System.out.println(discard[i1]);
		}
	}
	
	
	
	

	public static void main(String [ ] args)
	{
		DeckOfCards theDeck = new DeckOfCards();
		theDeck.shuffle();
		HumanPokerPlayer player = new HumanPokerPlayer(theDeck,"Luke");
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
