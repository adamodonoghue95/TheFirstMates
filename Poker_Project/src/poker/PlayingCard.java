package poker;

import java.util.ArrayList;

public class PlayingCard {

	static public final char HEARTS = 'H'; 
	static public final char DIAMONDS = 'D';
	static public final char CLUBS = 'C';
	static public final char SPADES = 'S';
	static public final String[] typeFaces = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

	//Instance variables for a playing card which define it's characteristics such as type,suit and value
	private String type;	
	private char suit;
	private int faceValue;
	private int gameValue;

	//Constructor for the playing card object
	public PlayingCard(String t, char s, int fVal, int gVal ){
		type = t;
		suit = s;
		faceValue = fVal;
		gameValue = gVal;
	}

	//public accessor method for the suit of a card
	public char getSuit(){
		return this.suit;
	}

	//public accessor method for the faceValue of a card
	public int getFaceValue(){
		return this.faceValue;
	}

	//public accessor method for the gameValue of a card
	public int getGameValue(){
		return this.gameValue;
	}

	//toString method which returns as a string a letter representing
	//the type of the card and a letter representing the string
	public String toString(){
		final String a = type + suit;
		return a;
	}

	//main method of the class which fills populates an empty deck array with each type of card from a set of 52
	public static void main(String [ ] args)
	{
		ArrayList<PlayingCard> Deck = new ArrayList<PlayingCard>();
		char suit = PlayingCard.HEARTS;

		//outer loop that decides what the current suit of cards to be created is based on a simple switch statement
		for(int a = 0; a<4;a++){
			switch (a) {
			case 0:  
				suit = PlayingCard.HEARTS;
				break;
			case 1:  
				suit = PlayingCard.DIAMONDS;
				break;
			case 2:  
				suit = PlayingCard.CLUBS;
				break;
			case 3:  
				suit = PlayingCard.SPADES;
				break;
			}

			//inner loop which contains three incrementing (and decrementing) variables to create the cards
			//from ACE-2-3-....-QUEEN-KING of each suit
			int x = 0;
			int fVal = 0;
			int gVal = 14;
			for(x=0;x<13;x++){
				Deck.add(new PlayingCard(PlayingCard.typeFaces[x], suit, fVal, gVal));
				fVal++;

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
		//Print out the deck
			System.out.println(Deck);
	}


}
