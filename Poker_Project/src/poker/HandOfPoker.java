package poker;

import java.util.ArrayList;

public class HandOfPoker {

	protected int currentCall = 0;
	private ArrayList<PokerPlayer> pokerPlayers = new ArrayList<PokerPlayer>();
	protected int pot = 0; // Amount of chips stored in the pot

	public HandOfPoker(ArrayList<PokerPlayer> players) {
		pokerPlayers = players;
		System.out.println("New Deal:\n");
	}

	public void printChips() {
		for (int i = 0; i < pokerPlayers.size(); i++) {
			System.out.println("> " + pokerPlayers.get(i).name + " has " + pokerPlayers.get(i).getChips() + " chip(s)");
		}
	}

	public void roundOfBetting() {
		currentCall = 0;
		int lastToRaise = 0; // index of player that raised last
		int noOfFolds = 0; // Tracks number of folds
		ArrayList<Integer> currentBets = new ArrayList<Integer>();
		
		for(PokerPlayer player : pokerPlayers){
			player.resetBet();
		}

		for (int i = 0; i < pokerPlayers.size(); i++) {
			PokerPlayer player = pokerPlayers.get(i);

			if (i == 0) { // Prints only for human player
				System.out.println("\nWould you like to fold? (The cost to call is " + currentCall +" chips)");
			}

			if (player.inHand) {
				if (!player.fold(currentCall)) { // Gives option to fold
					int chipsRaised = player.getChipsToRaise();
					player.lastBet = currentCall + chipsRaised; // Updates players last bet to be call plus their raise
					currentCall = player.lastBet; //Update current call to equal last players bet
					pot += player.lastBet; //Update pot
					if (chipsRaised > 0) { // Check for last raise
						System.out.println("> " + player.name + " raises " + chipsRaised + " chip(s)");
						lastToRaise = i;
					}
					else {
						System.out.println("> " + player.name + " called with" + currentCall + " chip(s)" + player.inHand);
					}
					System.out.println("POT = " + pot);
				}
				else {
					pokerPlayers.get(i).inHand = false;
					System.out.println("> " + player.name + " folds" + player.inHand);				
				}
			}
		}

		for (int j = 0; j < lastToRaise; j++) {
			PokerPlayer player = pokerPlayers.get(j);
			int costToCall = currentCall - player.lastBet; // Calculates cost of player taking original bet into account
			if (j == 0) { // Prints only for human player
				System.out.println("\nWould you like to fold? (The cost to call is " + costToCall  +" chips)");
			}
			if (player.inHand) {				
				if (!player.fold(currentCall)) { // Gives option to fold
					player.bet(costToCall); // Updates players chips
					System.out.println("lastToRaise = " + lastToRaise + ", j = " + j);
					pot += costToCall; // Updates pot
					System.out.println("> " + player.name + " matches with " + costToCall + " chip(s)");
					System.out.println("POT = " + pot);
				}
				else{
					System.out.println("> " + player.name + " has folded");
					pokerPlayers.get(j).inHand = false;
				}
			}
		}

	}

	public void discardCards() {

		// Deals with Automated Player
		for (int i = 0; i < pokerPlayers.size(); i++) {
			PokerPlayer player = pokerPlayers.get(i);
			player.discard();
		}
	}

	// Removes any players that want to fold

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
		for(int z = 0; z < pokerPlayers.size(); z++){
			PokerPlayer player = pokerPlayers.get(z);

			if (player.inHand) {
				System.out.println("\n\n" + pokerPlayers.get(z).name + " Hand = " + player.playerHand.getHandType());
				System.out.println("SCORE = " + player.playerHand.getGameValue());
				for (int i = 0; i < HandOfCards.HAND_SIZE; i++) {
					switch(player.playerHand.getCard(i).getGameValue()) {
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
						if (player.playerHand.getCard(i).getSuit() == 'H') {
							System.out.println("Hearts");
							break;
						}
						else if (player.playerHand.getCard(i).getSuit() == 'S') {
							System.out.println("Spades");
							break;
						}
						else if (player.playerHand.getCard(i).getSuit() == 'D') {
							System.out.println("Diamonds");
							break;
						}
						else if (player.playerHand.getCard(i).getSuit() == 'C') {
							System.out.println("Clubs");
							break;
						}
					}
				}
			}

		}
	}

	public void decideWinner() {
		int winningHandScore = 0;
		int winningPlayer = 0;

		for(int z = 0; z < pokerPlayers.size();z++){
			PokerPlayer player = pokerPlayers.get(z);
			if(player.playerHand.getGameValue() > winningHandScore && player.inHand){
				winningHandScore = player.playerHand.getGameValue();
				winningPlayer = z;
			}
		}

		System.out.println("\n\n" + pokerPlayers.get(winningPlayer).name + " wins the pot: " + pot + " chips");
		pokerPlayers.get(winningPlayer).chipsWon(pot);


	}
}
