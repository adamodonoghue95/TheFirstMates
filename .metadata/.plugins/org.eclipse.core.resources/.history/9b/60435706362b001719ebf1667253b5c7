package poker;

import java.util.ArrayList;

public class HandOfPoker {

	protected int currentCall = 0;
	private ArrayList<PokerPlayer> pokerPlayers = new ArrayList<PokerPlayer>();
	private DeckOfCards handDeck;
	protected int pot = 0; // Amount of chips stored in the pot

	public HandOfPoker(ArrayList<PokerPlayer> players, DeckOfCards deck) {
		pokerPlayers = players;
		handDeck = deck;
		//attempt to deal
		//		for(PokerPlayer p : pokerPlayers){
		//			p.playerHand = new HandOfCards(deck);
		//		}
		System.out.println("New Deal:\n");
	}

	public int noOfPlayers() {
		int noOfPlayers = 0;
		for (int i = 0; i < pokerPlayers.size(); i++) {
			if (pokerPlayers.get(i).inHand) {
				noOfPlayers++;
			}
		}
		return noOfPlayers;
	}

	public String printChips(String username) {
		String chips="";
		for (int i = 0; i < pokerPlayers.size(); i++) {
			chips+="\n" + pokerPlayers.get(i).name + " has " + pokerPlayers.get(i).getChips() + " chip(s)";
		}
		return chips;
	}
	
	public String firstRoundOfBetting(TwitterBot tbot, String content, String username) {
		String output = "";
		if(content.contains(tbot.BOT_ID)){
			if(content.contains("yes") || content.contains("Yes")){

			}
			else if (content.contains("no") || content.contains("No")){

			}
			else{
				tbot.tweet("@"+username+" You entered the wrong input, please reply with Yes or No");
			}
		}
		
		return output;
	}
	

	public String roundOfBetting(TwitterBot tbot, String content) {
		currentCall = 0;
		int lastToRaise = 0; // index of player that raised last
		String output = "";

		//reset players last bets
		for(PokerPlayer player : pokerPlayers){
			player.resetBet();
		}

		for (int i = 0; i < pokerPlayers.size(); i++) {
			PokerPlayer player = pokerPlayers.get(i);
			if (player.inHand) {

				if (!player.fold(currentCall, tbot, content)) { // Gives option to fold

					int chipsRaised = player.getChipsToRaise();
					player.lastBet = currentCall + chipsRaised; // Updates players last bet to be call plus their raise
					currentCall = player.lastBet; //Update current call to equal last players bet
					pot += player.lastBet; //Update pot
					if (chipsRaised > 0) { // Check for last raise
						if(chipsRaised == player.getChips() +chipsRaised){
							lastToRaise = i;
							output = "> " + player.name + " goes all in! " + chipsRaised + " chip(s)";
						}
						else{
							output = "> " + player.name + " raises by " + chipsRaised + " chip(s)";
						}
						lastToRaise = i;
					}
					else {
						if(currentCall == currentCall + player.getChips()){
							output = "> " + player.name + " goes all in! " + currentCall + " chip(s)";
						}
						else{
							output = "> " + player.name + " called with " + currentCall + " chip(s)";
						}
					}
					//System.out.println("POT = " + pot);
				}
				else {
					pokerPlayers.get(i).inHand = false;
					output = "> " + player.name + " folds";				
				}
			}
		}

		for (int j = 0; j < lastToRaise; j++) {
			PokerPlayer player = pokerPlayers.get(j);
			int costToCall = currentCall - player.lastBet; // Calculates cost of player taking original bet into account

			if (player.inHand) {				
				if (!player.fold(costToCall, tbot, content)) { // Gives option to fold
					//player.bet(costToCall); // Updates players chips
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
		
		return output;
	}

	public void returnHands() {
		// Loop to return all cards from each player
		for (int i = 0; i < pokerPlayers.size(); i++) {
			PokerPlayer player = pokerPlayers.get(i);
			if(player.inHand){
				player.returnCards(handDeck);
			}
		}
	}

	public void discardCards() {

		// Loop to get the discard from each player
		for (int i = 0; i < pokerPlayers.size(); i++) {
			PokerPlayer player = pokerPlayers.get(i);
			if(player.inHand){
				player.discard();
			}
		}
	}

	// Removes any players that want to fold

	public String printHumanHand() {
		String handStr = "";

		for(PokerPlayer player : pokerPlayers){
			//if human player is still in hand code will execute
			if(player.getClass().getName().equals("poker.PokerPlayer") && player.inHand){
				HandOfCards hand = player.playerHand;
				System.out.println("\nYour Current Hand: -> " + hand.getHandType());
				handStr += hand.getHandType() + "\n";
				for (int i = 0; i < HandOfCards.HAND_SIZE; i++) {
					switch(hand.getCard(i).getGameValue()) {
					case 14:
						//System.out.print(i + ". Ace of ");
						handStr += i + ". Ace of ";
						break;
					case 2:
						//System.out.print(i + ". Two of ");
						handStr += i + ". Two of ";
						break;
					case 3:
						//System.out.print(i + ". Three of ");
						handStr += i + ". Three of ";

						break;
					case 4:
						//System.out.print(i + ". Four of ");
						handStr += i + ". Four of ";

						break;
					case 5:
						//System.out.print(i + ". Five of ");
						handStr += i + ". Five of ";
						break;
					case 6:
						//System.out.print(i + ". Six of ");
						handStr += i + ". Six of ";
						break;
					case 7:
						//System.out.print(i + ". Seven of ");
						handStr += i + ". Seven of ";
						break;
					case 8:
						//System.out.print(i + ". Eight of ");
						handStr += i + ". Eight of ";
						break;
					case 9:
						//System.out.print(i + ". Nine of ");
						handStr += i + ". Nine of ";
						break;
					case 10:
						//System.out.print(i + ". Ten of ");
						handStr += i + ". Ten of ";
						break;
					case 11:
						//System.out.print(i + ". Jack of ");
						handStr += i + ". Jack of ";
						break;
					case 12:
						//System.out.print(i + ". Queen of ");
						handStr += i + ". Queen of ";
						break;
					case 13:
						//System.out.print(i + ". King of ");
						handStr += i + ". King of ";
						break;
					}

					for (int j = 0; j < 4; j++) {
						if (hand.getCard(i).getSuit() == 'H') {
							//System.out.println("Hearts");
							handStr += "Hearts\n";
							break;
						}
						else if (hand.getCard(i).getSuit() == 'S') {
							//System.out.println("Spades");
							handStr += "Spades\n";
							break;
						}
						else if (hand.getCard(i).getSuit() == 'D') {
							//System.out.println("Diamonds");
							handStr += "Diamonds\n";
							break;
						}
						else if (hand.getCard(i).getSuit() == 'C') {
							//System.out.println("Clubs");
							handStr += "Clubs\n";

							break;
						}
					}
				}
			}
		}
		return handStr;
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
