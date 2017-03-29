package poker;
import java.util.Random;

public class PokerPlayer {

	private HandOfCards playerHand;

	public PokerPlayer(DeckOfCards cardDeck ){
		playerHand = new HandOfCards(cardDeck);
	}

	//this is a new method for discarding the cards
	public int discard(){
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
				System.out.println("Rand = " + randomNumber + "   <  Discard Probability = " + playerHand.getDiscardProbability(i));
				if(randomNumber < playerHand.getDiscardProbability(i)){
					
					discardCards[noDiscardCards] = i;
					noDiscardCards++;
				}
		}
		//discard the cards using the HandOfCards method which takes in the array of discarded card indexes
		playerHand.discardCards(discardCards, noDiscardCards);
		return noDiscardCards;
	}

	public static void main(String [ ] args)
	{
		DeckOfCards theDeck = new DeckOfCards();
		theDeck.shuffle();
		PokerPlayer Luke = new PokerPlayer(theDeck);
		System.out.println(Luke.playerHand);
		System.out.print(Luke.playerHand.getDiscardProbability(0) + "% " + Luke.playerHand.getDiscardProbability(1) + "% " + Luke.playerHand.getDiscardProbability(2)+ "% " + Luke.playerHand.getDiscardProbability(3)+ "% " + Luke.playerHand.getDiscardProbability(4)+ "% \n");
		Luke.discard();
		System.out.println(Luke.playerHand);
	}
}
