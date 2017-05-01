package poker;

import java.util.Random;

public class AutomatedPokerPlayer extends PokerPlayer{

	static protected final int LARGE_BET = 5;
	static protected final int MEDIUM_BET = 4;
	static protected final int SMALL_BET = 2;

	public AutomatedPokerPlayer(DeckOfCards deck, String botName) {
		super(deck, botName);
	}

	// returns amount of chips to bet according to hand held
	public int getChipsToRaise() {
		Random rand = new Random();
		int num = rand.nextInt(99) + 1;
		int bet = 0;

		if (chips == 0) { // No chips to bet
			inHand = false;
			return 0;
		}
		else if(this.playerHand.getGameValue() >= HandOfCards.FULL_HOUSE){
			//if hand is very strong bet half of bank (70 percent of the time)
			if(num > 30){
				bet = chips/2;
				chips -= bet;
				return bet; 		
			}
			//30 percent of the time just bet 5 to not scare the other players
			else{
				if(chips < LARGE_BET){
					bet = chips;
					chips = 0;
					return bet;
				}
				else{
					bet = LARGE_BET;
					chips -= bet; 
					return bet;
				}
			}
		}
		else if(this.playerHand.getGameValue() >= HandOfCards.THREE_OF_A_KIND){
			//if hand is very strong bet 1/3 of bank (60 percent of the time)
			if(num > 40){
				bet = chips/3;
				chips -= bet;
				return bet; 		
			}
			//10 percent of the time simply check
			else if(num > 30){
				return 0;

			}
			//30 percent of the time just bet 5 to not scare the other players
			else{
				if(chips < MEDIUM_BET){
					bet = chips;
					chips = 0;
					return bet;
				}
				else{
					bet = MEDIUM_BET;
					chips -= bet; 
					return bet;
				}
			}
		}
		else if(this.playerHand.getGameValue() >= HandOfCards.ONE_PAIR){
			//if hand is very strong bet 1/4 of bank (70 percent of the time)
			if(num > 60){
				bet = chips/4;
				chips -= bet;
				return bet; 		
			}
			//50 percent of the time simply check
			else if(num > 10){
				return 0;

			}
			//30 percent of the time just bet 2 to not scare the other players
			else{
				if(chips<SMALL_BET){
					bet = chips;
					chips = 0;
					return bet;
				}
				else{
					bet = SMALL_BET;
					chips -= SMALL_BET; 
					return bet;
				}
			}
		}
		else{
			//if hand is very strong bet 1/4 of bank (70 percent of the time)
			if(num > 95){
				bet = chips/8;
				chips -= bet;
				return bet; 		
			}
			else if(num > 5){
				return 0;
			}

			//70 percent of the time just check
			else{
				if(chips<SMALL_BET){
					bet = chips;
					chips = 0;
					return bet;
				}
				else{
					bet = SMALL_BET;
					chips -= bet; 
					return bet;
				}
			}
		}
	}

	// returns whether Bot folds or not
	public boolean fold(int costToCall) {
		Random rand = new Random();
		int num = rand.nextInt(100);
		boolean fold = false;

		if(this.playerHand.getGameValue() >= HandOfCards.FULL_HOUSE){
			//If you have a strong hand
			fold = false;
		}
		else if(this.playerHand.getGameValue() >= HandOfCards.THREE_OF_A_KIND){
			if(costToCall > chips*(3/5)){
				if(num > 50){
					fold = false;
				}
				else{
					fold = true;
				}
			}
		}
		else if(this.playerHand.getGameValue() >= HandOfCards.TWO_PAIR){
			if(costToCall > chips*(1/2)){
				if(num > 50){
					fold = false;
				}
				else{
					fold = true;
				}
			}	
		}
		else if(this.playerHand.getGameValue() >= HandOfCards.ONE_PAIR){
			if(costToCall > chips*(1/3)){
				if(num > 35){
					fold = false;
				}
				else{
					fold = true;
				}
			}	
		}
		else if(this.playerHand.getGameValue() >= HandOfCards.HIGH_HAND){
			if(costToCall > chips*(1/5)){
				if(num > 25){
					fold = false;
				}
				else{
					fold = true;
				}
			}	
		}
		if(costToCall > chips && fold == false) {
			chips = 0;
			return fold;
		}
		else if(costToCall <= chips && fold == false) {
			chips -= costToCall;
			return fold;
		}
		return fold;
	}	

	public String match(int lastToRaise, int currentCall){
		String output = "";
		int costToCall = currentCall - lastBet; // Calculates cost of player taking original bet into account

		if (inHand) {				
			if (!fold(costToCall)) { // Gives option to fold
				System.out.println("> " + name + " matches with " + costToCall + " chip(s)");
				output+="" + name + " matches with " + costToCall + " chip(s)";
			}
			else{
				System.out.println("> " + name + " has folded");
				output+= name + " has folded";
				inHand = false;
			}
		}

		return output;
	}

	//this is a new method for discarding the cards
	public void discard(){
		Random rand = new Random();
		int noDiscardCards = 0;				//variable to store how many cards need to be discarded
		int [] discardCards = new int [3];	//array for storing the positions of the discarded cards

		//Loop which runs the random generator against the discard probability for each card and decides which ones to discard
		//I don't need to check that no more than 3 cards will be discarded because my getDiscardProbability() ensures that does not happen.
		for(int i = 0; i < HandOfCards.HAND_SIZE; i++){
			int randomNumber = rand.nextInt(99) + 1;

			if(randomNumber < playerHand.getDiscardProbability(i)) { // Determines whether the car is discarded or not
				discardCards[noDiscardCards] = i;
				noDiscardCards++;
			}
		}
		//discard the cards using the HandOfCards method which takes in the array of discarded card indexes
		System.out.println(this.name + " discards " + noDiscardCards + " cards");
		playerHand.discardCards(discardCards, noDiscardCards);
		//return noDiscardCards;
	}

}
