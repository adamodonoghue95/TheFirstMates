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

	public int getChipsToRaise(String output) {
		int chipsBet = 0;
		if(chips<1){
			//System.out.println("You do not have any chips to raise with");
			output += "You do not have any chips to raise with";
		}
		else{
			//System.out.println("How much would you like to raise by? (< " + chips + ")");
			output += "How much would you like to raise by? (< " + chips + ")";
			try {
				chipsBet = Integer.parseInt(prompt());

				if (chipsBet > chips) { // Cannot bet more than chips held by player
					//System.out.println("You only have " + chips + " chip(s)");
					output += "You only have " + chips + " chip(s)";
				}
				else if (chipsBet < 0) {
					//System.out.println("Must be a positive number!");
					output +="Must be a positive number!";
				}

				else {
					this.chips -= chipsBet;
					return chipsBet;
				}
			}
			catch (NumberFormatException e){ // String entered is not a valid integer
				System.out.println("Bet must be a number!");
				output +="Bet must be a number!";				
			}
		}
		return chipsBet;
	}
	
	public boolean fold(int costToCall, String content) {
		if (content.equalsIgnoreCase("y") || content.equalsIgnoreCase("yes")) {
			return true;
		}
		else if (content.equalsIgnoreCase("n") || content.equalsIgnoreCase("no")) {
			if(costToCall > this.chips) {
				this.chips = 0;
			}
			else {
				this.chips -= costToCall;
			}
			return false;
		}
		return true;
	}
	
	public String match(String content, int currentCall){
		String output = "";

		if(inHand){
			int costToCall = currentCall - lastBet;
			if(!(content.equalsIgnoreCase("y") || content.equalsIgnoreCase("yes") || content.equalsIgnoreCase("n") || content.equalsIgnoreCase("no"))) {
				output = "@" + name + " \nWrong input. Please tweet 'yes' or 'no'";
			}
			else if (!fold(costToCall, content)){
				output += name + " matches with " + costToCall + " chip(s)\n";
			}
		}

		return output;
	}

	public int getChips() {
		return chips;
	}
	
	public String discard(String content){
		String output = "";
		//PokerPlayer human = pokerPlayers.get(0);

		if(inHand){
			//System.out.println("In the discard level");
			//boolean correctInput = true;
			boolean inRange = true;
			//String input = "";
			System.out.println("Content = " + content);
			String [] cards = content.split(" ");
			int [] discard = new int[cards.length];
			
			if (discard.length > 3) {
				System.out.println("Maximum cards you can discard is three");
				output = "@" + name + " \nWrong input. Maximum cards you can discard is 3";
				return output;
			}
			
			else {
				try {
					// Assigns and parses discarded cards to integers
					for (int i = 0; i < cards.length; i++) {
						System.out.println("Cards = " + cards[i]);
						
						discard[i] = Integer.parseInt(cards[i]);
						
						System.out.println("Discard = " + discard[i]);
						
						if (discard[i] < 0 || discard[i] > 4) {
							inRange = false;
							break;
						}
					}	

					if (inRange) {
						playerHand.discardCards(discard, discard.length);
						output = "Discard Successful";
						return output;
					}
					else {
						System.out.println("Index entered is out of range, must be between 0-4");
						output = "@" + name + " \nWrong input. Index entered is out of range, must be between 0-4";
						return output;
						//correctInput = false;
					}
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid input (must be integers)");
					output = "@" + name + " \nWrong input. Invalid input (must be integers)";
					return output;
				}
			}
		}
		return output;
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
		//player.discard();
		System.out.println(player.playerHand);


	}
}
