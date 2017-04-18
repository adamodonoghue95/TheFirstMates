package poker;

import java.util.ArrayList;

public class HandOfPoker {

	protected int lastBet = 0;
	private ArrayList<PokerPlayer> pokerPlayers = new ArrayList<PokerPlayer>();
	protected int pot = 0; // Amount of chips stored in the pot

	public HandOfPoker(ArrayList<PokerPlayer> players) {
		pokerPlayers = players;
		System.out.println("New Deal:\n");
	}

	public void printChips() {
		for (int i = 0; i < pokerPlayers.size(); i++) {
			System.out.println("> " + pokerPlayers.get(i).name + " has " + pokerPlayers.get(i).chips + " chip(s)");
		}
	}

	public void roundOfBetting() {
		HumanPokerPlayer human = (HumanPokerPlayer)pokerPlayers.get(0);

		// Deals with Human Player
		lastBet = human.getChipsToBet();
		pot += lastBet;
		System.out.println("> Pot = " + pot);


		// Deals with Automated Players
		for (int i = 1; i < pokerPlayers.size(); i++) {
			AutomatedPokerPlayer bot = (AutomatedPokerPlayer)pokerPlayers.get(i);
			//first check if the bot is going to fold or call
			if(!bot.fold(lastBet)){
				//bot has called the last bet
				pot += lastBet;
				//see how many chips the bot wants to raise by, if any
				int chipsBet = bot.getChipsToBet(); // gets chips bet by bot
				//pokerPlayers.get(i).bet(chipsBet);
				if(chipsBet == 0){
					System.out.println("> " + bot.name + " has called");
				}
				else{
					System.out.println("> " + bot.name + " has raised by "  + chipsBet + " chip(s)");
					lastBet = chipsBet;
					pot += lastBet;
				}
				// add chips to pot
				System.out.println("> Pot = " + pot);

			}
		}
	}

	public void discardCards() {
		HumanPokerPlayer human = (HumanPokerPlayer)pokerPlayers.get(0);

		// Deals with Human Player
		boolean correctInput = false;
		String input = "";
		String [] cards = input.split(" ");
		int [] discard = new int[cards.length];

		do {
			System.out.println("What cards would you like to discard? (e.g 0 2 3)");
			input = human.prompt();
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
		human.playerHand.discardCards(discard, discard.length);



		// Deals with Automated Player
		for (int i = 1; i < pokerPlayers.size(); i++) {
			AutomatedPokerPlayer bot = (AutomatedPokerPlayer)pokerPlayers.get(i);

		}
	}

	// Removes any players that want to fold
	public void checkFold() {
		HumanPokerPlayer human = (HumanPokerPlayer)pokerPlayers.get(0);

		// Deals with Human player
		if (human.fold()) {
			pokerPlayers.remove(0);
		}
		else {
			roundOfBetting();
		}

		// Deals with Automated Players
		for (int i = 1; i < pokerPlayers.size(); i++) {
			AutomatedPokerPlayer bot = (AutomatedPokerPlayer)pokerPlayers.get(i);
			// If bot.fold() returns true remove from hand
			if (bot.fold(lastBet)) {
				System.out.println("> " + bot.name + " has folded");
				pokerPlayers.remove(i);
			}
		}
	}

	public void printHumanHand() {
		HandOfCards hand = pokerPlayers.get(0).playerHand;

		for (int i = 0; i < HandOfCards.HAND_SIZE; i++) {
			switch(hand.getCard(i).getGameValue()) {
			case 14:
				System.out.print(i + ". Ace of ");
				break;
			case 2:
				System.out.print(i + ". Two of ");
				break;
			case 3:
				System.out.print(i + ". Three of ");
				break;
			case 4:
				System.out.print(i + ". Four of ");
				break;
			case 5:
				System.out.print(i + ". Five of ");
				break;
			case 6:
				System.out.print(i + ". Six of ");
				break;
			case 7:
				System.out.print(i + ". Seven of ");
				break;
			case 8:
				System.out.print(i + ". Eight of ");
				break;
			case 9:
				System.out.print(i + ". Nine of ");
				break;
			case 10:
				System.out.print(i + ". Ten of ");
				break;
			case 11:
				System.out.print(i + ". Jack of ");
				break;
			case 12:
				System.out.print(i + ". Queen of ");
				break;
			case 13:
				System.out.print(i + ". King of ");
				break;
			}

			for (int j = 0; j < 4; j++) {
				if (hand.getCard(i).getSuit() == 'H') {
					System.out.println("Hearts");
					break;
				}
				else if (hand.getCard(i).getSuit() == 'S') {
					System.out.println("Spades");
					break;
				}
				else if (hand.getCard(i).getSuit() == 'D') {
					System.out.println("Diamonds");
					break;
				}
				else if (hand.getCard(i).getSuit() == 'C') {
					System.out.println("Clubs");
					break;
				}
			}
		}
	}

	public void showCards() {
		for(int z = 0; z < pokerPlayers.size();z++){
			HandOfCards hand = pokerPlayers.get(z).playerHand;
			System.out.println("\n\n" + pokerPlayers.get(z).name + " Hand = " + hand.getHandType());
			System.out.println("SCORE = " + hand.getGameValue());
			for (int i = 0; i < HandOfCards.HAND_SIZE; i++) {
				switch(hand.getCard(i).getGameValue()) {
				case 14:
					System.out.print(i + ". Ace of ");
					break;
				case 2:
					System.out.print(i + ". Two of ");
					break;
				case 3:
					System.out.print(i + ". Three of ");
					break;
				case 4:
					System.out.print(i + ". Four of ");
					break;
				case 5:
					System.out.print(i + ". Five of ");
					break;
				case 6:
					System.out.print(i + ". Six of ");
					break;
				case 7:
					System.out.print(i + ". Seven of ");
					break;
				case 8:
					System.out.print(i + ". Eight of ");
					break;
				case 9:
					System.out.print(i + ". Nine of ");
					break;
				case 10:
					System.out.print(i + ". Ten of ");
					break;
				case 11:
					System.out.print(i + ". Jack of ");
					break;
				case 12:
					System.out.print(i + ". Queen of ");
					break;
				case 13:
					System.out.print(i + ". King of ");
					break;
				}

				for (int j = 0; j < 4; j++) {
					if (hand.getCard(i).getSuit() == 'H') {
						System.out.println("Hearts");
						break;
					}
					else if (hand.getCard(i).getSuit() == 'S') {
						System.out.println("Spades");
						break;
					}
					else if (hand.getCard(i).getSuit() == 'D') {
						System.out.println("Diamonds");
						break;
					}
					else if (hand.getCard(i).getSuit() == 'C') {
						System.out.println("Clubs");
						break;
					}
				}
			}
		}
	}

	public void decideWinner() {
		int winningHandScore = 0;
		int winningPlayer = 0;
		for(int z = 0; z < pokerPlayers.size();z++){
			HandOfCards hand = pokerPlayers.get(z).playerHand;
			if(hand.getGameValue()>winningHandScore){
				winningHandScore = hand.getGameValue();
				winningPlayer = z;
			}
		}
		
		System.out.println("\n\n" + pokerPlayers.get(winningPlayer).name + " wins the pot: " + pot + " chips");


	}
}
