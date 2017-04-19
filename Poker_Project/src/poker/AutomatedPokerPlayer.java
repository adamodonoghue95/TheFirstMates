package poker;

import java.util.Random;

public class AutomatedPokerPlayer extends PokerPlayer{
		
	public AutomatedPokerPlayer(DeckOfCards deck, String botName) {
		super(deck, botName);
	}
	
	// returns amount of chips to bet according to hand held
	public int getChipsToRaise() {
		// TODO
		Random rand = new Random();
		int num = rand.nextInt(1);
		if (getChips() > 2 && num == 0) {
			return 2;
		}
		else {
			return 0;
		}
	}
	
	// returns whether Bot folds or not
	public boolean fold(int lastBet) {
		Random rand = new Random();
		int num = rand.nextInt(4);
		// TODO
		if (getChips() <= lastBet || num == 0) {
			return true;
		}
		else {
			return false;
		}	
	}
	
	//this is a new method for discarding the cards
		public void discard(){
			//declare a new random number generator
			Random rand = new Random();
			//variable to store how many cards need to be discarded
			int noDiscardCards = 0;
			//array for storing the positions of the discarded cards
			int [] discardCards = new int [3];
			
			//Loop which runs the random generator against the discard probability for each card and decides which ones to discard
			//I don't need to check that no more than 3 cards will be discarded because my getDiscardProbability() ensures that does not happen.
			for(int i = 0; i < HandOfCards.HAND_SIZE; i++){
					int randomNumber = rand.nextInt(99) + 1;
					// Test Purposes
					// System.out.println("Rand = " + randomNumber + "   <  Discard Probability = " + playerHand.getDiscardProbability(i));
					if(randomNumber < playerHand.getDiscardProbability(i)){
						
						discardCards[noDiscardCards] = i;
						noDiscardCards++;
					}
			}
			//discard the cards using the HandOfCards method which takes in the array of discarded card indexes
			playerHand.discardCards(discardCards, noDiscardCards);
			//return noDiscardCards;
		}

}
