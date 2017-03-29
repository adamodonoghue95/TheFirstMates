package poker;

import java.util.Random;
import java.util.ArrayList;

public class DeckOfCards {

	//Instance variables of deck include a pointer to the top of deck
	//and 2 array lists to hold cards that can be dealt and discarded cards
	private int topOfDeck = 0;
	private ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
	private ArrayList<PlayingCard> discardDeck = new ArrayList<PlayingCard>();

	//Constructor for the DeckOfCards object which creates an empty deck and then fills it with the cards of each suit
	public DeckOfCards(){
		char s = PlayingCard.HEARTS;
		int z = 0;

		for(int a = 0; a<4;a++){
			switch (a) {
			case 0:  
				s = PlayingCard.HEARTS;
				break;
			case 1:  
				s = PlayingCard.DIAMONDS;
				break;
			case 2:  
				s = PlayingCard.CLUBS;
				break;
			case 3:  
				s = PlayingCard.SPADES;
				break;
			}

			//inner loop which contains three incrementing (and decrementing) variables to create the cards
			//from ACE-2-3-....-QUEEN-KING of each suit
			int x = 0;
			int fVal = 0;
			int gVal = 14;
			for(x=0;x<13;x++){
				deck.add(new PlayingCard(PlayingCard.typeFaces[x], s, fVal, gVal));
				fVal++;
				z++;

				//because of the unusual case where Ace has the highest gamevalue this if statement 
				//resets gVal to 2 so it is correct for the rest of the cards
				if(gVal == 14){
					gVal=2;
				}
				else{
					gVal++;
				}
			}
		}
	}

	//dealNext method which deals the card at the top of the deck 
	synchronized public PlayingCard dealNext(){
		
		
		//Check to see if the deck is empty and no more cards are available to be dealt
		if (deck.isEmpty()){
			System.out.println("Deck is empty please reset or reshuffle the deck deal another card");
			return null;
		}
		PlayingCard Card = deck.remove(topOfDeck);
		return Card;
	}

	//shuffle method which shuffles the deck by choosing two random positions in the deck and swaps the cards in those positions
	public void shuffle(){
		Random rand = new Random();
		for(int i = 0; i<(deck.size()*deck.size());i++){
			int a = rand.nextInt(deck.size());
			int b = rand.nextInt(deck.size());
			PlayingCard temp = deck.get(a);
			deck.set(a, deck.get(b));
			deck.set(b, temp);
		}
	}

	//reset method joins the deck of cards still available with the discarded cards and shuffles the deck again
	public void reset(){
		deck.addAll(discardDeck);
		shuffle();
	}
	
	//returnCard method simply discards a playingCard
	public void returnCard(PlayingCard discarded){
		discardDeck.add(discarded);
	}


	//main method for testing the methods defined above
	public static void main(String [ ] args)
	{
		System.out.println("Create a deck of Cards");
		DeckOfCards Luke = new DeckOfCards();
		System.out.println("Print out the deck:");
		System.out.println(Luke.deck);
		System.out.println("\nShuffle the deck");
		Luke.shuffle();
		System.out.println("Print out the shuffled deck");
		System.out.println(Luke.deck);
		
		System.out.println("\nDeal three cards and discard them");
		for(int a=0;a<3;a++){
		//Deal 1 card and assign it to a variable
		PlayingCard dealt = Luke.dealNext();
		//Print out this card
		System.out.println(dealt);
		//Return this card to the deck as a discarded card
		Luke.returnCard(dealt);
		}
		
		
		
		System.out.println("\nPrint out the deck to show that the three cards are no longer available to be dealt");
		System.out.println(Luke.deck);
		System.out.println("\nReset the deck which returns the discarded card/s to the deck and shuffles");
		Luke.reset();
		System.out.println("Print out the reset deck");
		System.out.println(Luke.deck);
	}
}

