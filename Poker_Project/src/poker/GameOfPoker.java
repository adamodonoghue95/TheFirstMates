package poker;

import java.util.ArrayList;

public class GameOfPoker {
	private static ArrayList<PokerPlayer> gamePlayers = new ArrayList<PokerPlayer>();
	private DeckOfCards deck = new DeckOfCards();
	private int STATUS_ID;
	String username;
	boolean GameState;
	HandOfPoker hand = new HandOfPoker(gamePlayers, this.deck);


	//CONSTRUCTOR FOR TWITTER BOT
	public GameOfPoker(int numberOfPlayers, String user) {
		username = user;
		gamePlayers.add(new PokerPlayer(deck, username));
		gamePlayers.add(new AutomatedPokerPlayer(deck,"Jack"));
		gamePlayers.add(new AutomatedPokerPlayer(deck,"Henry"));
		gamePlayers.add(new AutomatedPokerPlayer(deck,"Ron"));

	}

	// CONSTRUCTOR FOR TESTIGN GAMEPLAY
	public GameOfPoker(int numberOfPlayers){
		gamePlayers.add(new PokerPlayer(deck,"Luke"));
		gamePlayers.add(new AutomatedPokerPlayer(deck,"Jack"));
		gamePlayers.add(new AutomatedPokerPlayer(deck,"Henry"));
		gamePlayers.add(new AutomatedPokerPlayer(deck,"Ron"));
	}

	public boolean checkGameState(){
		for(int i=0; i<gamePlayers.size();i++){
			if(gamePlayers.get(i).getChips()<=0){
				gamePlayers.remove(i);
			}
			else{
				gamePlayers.get(i).inHand = true;
			}
		}
		if(gamePlayers.size()==1){
			System.out.println(gamePlayers.get(0).name + " is the winner!");
			return false;
		}

		else return true;
	}

	public void dealNewHands() {
		deck.reset();
		for (int i = 0; i < gamePlayers.size(); i++) {
			PokerPlayer player = gamePlayers.get(i);
			if(player.inHand){
				player.newHand(deck);
			}
		}
	}

	public String returnHumanHand() {
		return hand.printHumanHand();
	}


	/* GAME STATE SECTIONS*/

	//LEVEL 0
	public String welcomeSection(String username){
		String firstTweet;
		firstTweet = "@" + username + " " + returnHumanHand() + "\nWelcome to TheFirstMates Poker Game!\n";
		firstTweet += "Let's Play Poker " + gamePlayers.get(0).name + "!" ;
		return firstTweet;
	}

	//LEVEL 1
	public String [] introSection(){
		hand = new HandOfPoker(gamePlayers, this.deck);
		String [] tweets = new String[2];
		// Print chips
		tweets[0] = hand.printChips(gamePlayers.get(0).name);

		// Print fold question
		System.out.println("Would you like to fold? (y/ n to call, cost to call:" + hand.currentCall + ")");
		if(hand.currentCall >= gamePlayers.get(0).chips){
			tweets[1] = "\nWould you like to fold? (To call you must go all in,  " + hand.currentCall  +" chips)";
		}
		else{
			tweets[1] = "\nWould you like to fold? (The cost to call is " + hand.currentCall  +" chips)";
		}
		return tweets;
	}

	//LEVEL 2
	public String foldSection(String content){

		/* This method takes in the input to the would you like to fold question
		 * and checks the input. It then finishes with the appropriate string generated in the 
		 * player.fold method, whether it be wrong input, player has folded or to progress
		 * how much would you like to raise*/

		String tweet = " " + hand.toString() + "\n";
		tweet = hand.humanFold(content);
		System.out.println(tweet);
		return tweet;
	}

	//LEVEL3
	public String betSection(String content){
		String tweets= "";
		
		PokerPlayer human = gamePlayers.get(0);
		
		// Deals with Human betting
		tweets = hand.humanBet(content);

		// Deals with Automated betting
		if (!tweets.contains("Bet error")) { // Only adds automated players if no error has occured
			tweets += hand.automatedBet();		// Checks if automated players want to fold, if not call or raise
		}
		// If lastToRaise = 0, skips match section 
		if(hand.lastToRaise != 0){
			tweets += "Would you like to fold?(Call="+(hand.currentCall- human.lastBet)+")";
		}

		return tweets;
	}

	//LEVEL 4 
	public String matchSection(String content) {
		String tweet = "";
		
		tweet += hand.playersMatch(content);

		System.out.println(tweet);

		return tweet;
	}

	//LEVEL 5 
	public String discardSection(String content) {
		String tweet = "";
		System.out.println(hand.showHands());
		tweet += hand.discardCards(content);	// Discards human and automated player cards
		System.out.println("\n" + hand.showHands());
		System.out.println(tweet);

		return tweet;
	}
	
	// LEVEL 8
	public String decideWinner() {
		String tweet = "";
		
		tweet += hand.showHands();
		tweet += hand.decideWinner();
		
		return tweet;
	}
	
	public int getLastToRaise() {
		return hand.lastToRaise;
	}
	
	public int noOfPlayers() {
		return gamePlayers.size();
	}


	//	public void gamePlay(){
	//		
	//		/* First four lines are for the twitter output, need to fix the small game play
	//		 * errors first*/
	//		
	////		String firstTweet;
	////		String secondTweet;
	////		firstTweet = "@"+username+" Welcome to TheFirstMates Poker Game!\n";
	////		firstTweet+= "\nLet's Play Poker " + gamePlayers.get(0).name + "!";		
	////		tbot.tweet(firstTweet);
	//		
	//		
	//		System.out.println("Welcome to TheFirstMates Poker Game!\n");
	//		System.out.println("\nLet's Play Poker " + gamePlayers.get(0).name + "!");
	//		
	//		while(this.checkGameState()){
	//						
	//			HandOfPoker hand = new HandOfPoker(gamePlayers, this.deck);
	//			hand.printChips();
	//
	//			hand.printHumanHand()
	//
	//			do {
	//				// Let players fold and start betting
	//				System.out.println("\nFIRST ROUND OF BETTING\n-----------------");
	//				hand.roundOfBetting();
	//				
	//				// Skips next round of betting if only one player left
	//				//System.out.println(hand.noOfPlayers());
	//				if (hand.noOfPlayers() == 1){
	//					System.out.println("Nobody calls");
	//					break;
	//				}
	//				
	//				//Let players discard their cards
	//				hand.discardCards();
	//				hand.printHumanHand();
	//				
	//				//Complete second round of betting
	//				System.out.println("\nSECOND ROUND OF BETTING\n-----------------");
	//				hand.roundOfBetting();
	//				
	//				//Show cards
	//				hand.showCards();
	//				
	//			} while(false);
	//
	//			//Show Cards
	//			
	//			hand.decideWinner();
	//			
	//			// Lets players give all cards back and dealt new hands
	//			hand.returnHands();
	//			
	//			// Reset and shuffle deck
	//			this.deck.reset();
	//			this.deck.shuffle();
	//			
	//			// Deal new Hands for next round
	//			this.dealNewHands();
	//		}
	//
	//		//hand.printChips();
	//
	//
	//	}

	public static void main(String [] args) {

		GameOfPoker gm = new GameOfPoker(4);
		//gm.gamePlay();

	}

}
