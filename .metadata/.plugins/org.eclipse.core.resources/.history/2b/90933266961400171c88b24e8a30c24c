package poker;

public class HandOfCards {

	//constant to represent the number of cards in a hand(5)
	static public final int HAND_SIZE = 5;
	//Array to hold the playing card objects representing the hand
	

	static private final int ROYAL_FLUSH = 9000000;
	static private final int STRAIGHT_FLUSH = 8000000;
	static private final int FOUR_OF_A_KIND = 7000000;
	static private final int FULL_HOUSE = 6000000;
	static private final int FLUSH = 5000000;
	static private final int STRAIGHT = 4000000;
	static private final int THREE_OF_A_KIND = 3000000;
	static private final int TWO_PAIR = 2000000;
	static private final int ONE_PAIR = 1000000;
	static private final int HIGH_HAND = 0;
	static private final int PRIORITY0 = (int)Math.pow(14, 4);
	static private final int PRIORITY1 = (int)Math.pow(14, 3);
	static private final int PRIORITY2 = (int)Math.pow(14, 2);
	static private final int PRIORITY3 = (int)Math.pow(14, 1);
	static private final double REMAINING_CARDS = 47;


	private PlayingCard[] hand = new PlayingCard[HAND_SIZE];
	private DeckOfCards deck;

	//Constructor for the hand of playing cards
	public HandOfCards(DeckOfCards cardDeck ){
		deck = cardDeck;
		for(int i = 0;i<HAND_SIZE;i++){
			hand[i] = deck.dealNext();
		}
		this.sort();
	}
	
	public PlayingCard getCard(int cardNumber){
		return hand[cardNumber];
	}
	
	public void discardCards(int[] discarded, int noDiscardedCards){
		for(int i = 0; i<noDiscardedCards;i++){
		System.out.println("Discarding: " + hand[discarded[i]]);
		deck.returnCard(hand[discarded[i]]);
		hand[discarded[i]] = deck.dealNext();
		}
		
		this.sort();
	}

	//the sort method which sorts the cards in the hand in ascending order of gameValue in order to recognize hands
	private void sort(){
		for (int i=0;i<HAND_SIZE;i++){
			for (int j=1;j<(HAND_SIZE-i);j++){
				if(hand[j-1].getGameValue() > hand[j].getGameValue()){
					PlayingCard temp = hand[j-1];
					hand[j-1] = hand[j];
					hand[j] = temp;	
				}
			}
		}
	}

	//simple toString method for the handOfCards 
	public String toString(){
		String str = "";
		for(int i = 0; i < HAND_SIZE; i++){
			str += this.hand[i] + " ";
		}
		return str;

	}

	//method to check if the hand is a royal flush
	boolean isRoyalFlush(){
		int cardValue = 10;
		int counter = 0;
		for (int i = 0; i < HAND_SIZE; i++) {
			if (hand[i].getGameValue() == cardValue && hand[0].getSuit() == hand[i].getSuit()) {
				counter++;
			}
			cardValue++;
		}
		if (counter == 5) {
			return true;
		}
		return false;
	}

	//method to check if the hand is a straight flush by checking first if it is a straight and then also a flush
	boolean isStraightFlush(){
		int straightCount = 0;
		int suitCount = 0;
		boolean straight = false;
		boolean suit = false;
		if(!isRoyalFlush()){
			if((hand[4].getGameValue()==14)&&(hand[0].getGameValue()==2)){
				for (int i=0;i<HAND_SIZE-2;i++){
					if((hand[i].getGameValue() - hand[i+1].getGameValue())== -1){
						straightCount++;
					}
				}

				if (straightCount == 3){
					straight = true;
				}
			}
			else{
				for (int i=0;i<HAND_SIZE-1;i++){
					if((hand[i].getGameValue() - hand[i+1].getGameValue())== -1){
						straightCount++;
					}
				}

				if (straightCount == 4){
					straight = true;
				}
			}

			for (int i=1;i<HAND_SIZE;i++){
				if(hand[0].getSuit()==hand[i].getSuit()){
					suitCount++;
				}
			}

			if (suitCount == 4){
				suit = true;
			}

			if (suit&&straight){
				return true;
			}

		}
		return false;
	}

	//method to check if the hand is has four cards of the same kind
	boolean isFourOfAKind(){
		if(!isRoyalFlush()&&!isStraightFlush()){
			if(hand[0].getGameValue()==hand[3].getGameValue()||hand[1].getGameValue()==hand[4].getGameValue()){
				return true;
			}
		}
		return false;
	} 

	//method to check for a full house, in other words a pair of cards and 3 other cards of the same facevalue
	boolean isFullHouse(){
		if(!isRoyalFlush()&&!isStraightFlush()&&!isFourOfAKind()){
			if(hand[0].getGameValue()==hand[1].getGameValue()&&hand[2].getGameValue()==hand[4].getGameValue()){
				return true;
			}
			else if(hand[0].getGameValue()==hand[2].getGameValue() && hand[3].getGameValue()==hand[4].getGameValue()){
				return true;
			}
		}
		return false;
	}

	//method to check for a flush i.e. all the same suit
	boolean isFlush(){
		int suitCount = 0;
		if(!isRoyalFlush()&&!isStraightFlush()&&!isFourOfAKind()&&!isFullHouse()){
			for (int i=1;i<HAND_SIZE;i++){
				if(hand[0].getSuit()==hand[i].getSuit()){
					suitCount++;
				}
			}

			if (suitCount == 4){
				return true;
			}
		}
		return false;
	} 

	//method to check for a straight
	boolean isStraight(){
		int straightCount = 0;
		if(!isRoyalFlush()&&!isStraightFlush()&&!isFourOfAKind()&&!isFullHouse()&&!isFlush()){
			if((hand[4].getGameValue()==14)&&(hand[0].getGameValue()==2)){
				for (int i=0;i<HAND_SIZE-2;i++){
					if((hand[i].getGameValue() - hand[i+1].getGameValue())== -1){
						straightCount++;
					}
				}

				if (straightCount == 3){
					return true;
				}
			}

			else{

				for (int i=0;i<HAND_SIZE-1;i++){
					if((hand[i].getGameValue() - hand[i+1].getGameValue())== -1){
						straightCount++;
					}
				}

				if (straightCount == 4){
					return true;
				}
			}
		}
		return false;
	}

	//method to check if the hand has 3 cards with the same face value
	boolean isThreeOfAKind(){
		if(!isRoyalFlush()&&!isStraightFlush()&&!isFourOfAKind()&&!isFullHouse()&&!isFlush()&&!isStraight()){
			if(hand[0].getGameValue()==hand[2].getGameValue()||hand[1].getGameValue()==hand[3].getGameValue()||hand[2].getGameValue()==hand[4].getGameValue()){
				return true;
			}
		}
		return false;
	}

	//method to check if the hand contains two different pairs of cards
	boolean isTwoPair(){
		if(!isRoyalFlush()&&!isStraightFlush()&&!isFourOfAKind()&&!isFullHouse()&&!isFlush()&&!isStraight()&&!isThreeOfAKind()){
			if(hand[0].getGameValue()==hand[1].getGameValue()&&hand[2].getGameValue()==hand[3].getGameValue()){
				return true;
			}
			else if(hand[0].getGameValue()==hand[1].getGameValue()&&hand[3].getGameValue()==hand[4].getGameValue()){
				return true;
			}
			else if(hand[1].getGameValue()==hand[2].getGameValue()&&hand[3].getGameValue()==hand[4].getGameValue()){
				return true;
			}
		}
		return false;
	}

	//method to check id the hand has a single pair of cards
	boolean isOnePair(){
		if(!isRoyalFlush()&&!isStraightFlush()&&!isFourOfAKind()&&!isFullHouse()&&!isFlush()&&!isStraight()&&!isThreeOfAKind()&&!isTwoPair()){
			if(hand[0].getGameValue()==hand[1].getGameValue()||hand[1].getGameValue()==hand[2].getGameValue()||hand[2].getGameValue()==hand[3].getGameValue()||hand[3].getGameValue()==hand[4].getGameValue()){
				return true;
			}
		}
		return false;
	} 

	//method which simply checks that no better hand exists, if this is the case then the hand is simple a highHand
	boolean isHighHand(){
		if(!isRoyalFlush()&&!isStraightFlush()&&!isFourOfAKind()&&!isFullHouse()&&!isFlush()&&!isStraight()&&!isThreeOfAKind()&&!isTwoPair()&&!isOnePair()){
			return true;
		}
		return false;
	}

	private boolean isPartOfFourOfAKind(int cardPosition){
		int count = 0;
		for(int i = 0; i<HAND_SIZE;i++){
			if(hand[cardPosition].getGameValue()==hand[i].getGameValue())count++;
		}
		if(count==4)return true;
		else return false;
	}

	private boolean isPartOfThreeOfAKind(int cardPosition){
		int count = 0;
		for(int i = 0; i<HAND_SIZE;i++){
			if(hand[cardPosition].getGameValue()==hand[i].getGameValue())count++;
		}
		if(count==3)return true;
		else return false;
	}

	private boolean isPartOfPair(int cardPosition){
		int count = 0;
		for(int i = 0; i<HAND_SIZE;i++){
			if(hand[cardPosition].getGameValue()==hand[i].getGameValue())count++;
		}
		if(count==2)return true;
		else return false;
	}
	
	//Method to recognise if a hand can be turned into a straight
	private boolean isBustedStraight() {
		int diff = 0;
		if (!isStraight()) {
			if (isBustCardInStraight(0)) {
				for (int i = 1; i < 4; i++) {
					diff += hand[i+1].getGameValue() - hand[i].getGameValue();
				}
			}
			else if (isBustCardInStraight(3)) {
				diff += hand[2].getGameValue() - hand[1].getGameValue();
				
				diff += hand[1].getGameValue() - hand[0].getGameValue();
				diff += hand[0].getGameValue() - hand[4].getFaceValue();
			}
			else if (isBustCardInStraight(4)) {
				for (int i = 0; i < 4; i++) {
					diff += hand[i+1].getGameValue() - hand[i].getGameValue();
				}
			}
			
			// if difference is 4 then the hand is an inside straight
			// if difference is 3 then the hand is an outside straight
			if (diff == 4 || diff == 3) {
				return true;
			}
			
			else if (isOnePair() && diff == 2) {
				return true;
			}
			
			else return false;
		}
		else {
			return false;
		}
	}

	private boolean isBustCardInStraight(int cardPosition) {
		int Card1Difference = hand[1].getGameValue()-hand[0].getGameValue();
		int Card2Difference = hand[4].getGameValue()-hand[3].getGameValue();
		
		//This checks if the busted card is part of a one pair as well. If this is the case 1 but not both of the cards should be discarded.
		//I achieved this using cardPosition+1 and the modulus HAND_SIZE(5) deals with the arrayOutOfBounds exception
		if(isOnePair()&& isPartOfPair((cardPosition+1)%HAND_SIZE)){
			return true;	
		}
		
		// Checks if cardPosition is the busted card (the busted card can only be at the top or bottom of the hand)
		else if(hand[4].getGameValue()==14 &&(hand[0].getGameValue()==2||hand[0].getGameValue()==3)){
			
			if(hand[0].getGameValue()==2&& hand[1].getGameValue()==3 && hand[2].getGameValue()==5 &&cardPosition ==3){
				return true;
			}

			else if(hand[0].getGameValue()==2 && hand[1].getGameValue()==4 && hand[2].getGameValue()==5 &&cardPosition ==3 ){
				return true;
			}

			else if(hand[0].getGameValue()==3&& hand[1].getGameValue()==4 && hand[2].getGameValue()==5 &&cardPosition ==3){
				return true;
			}

			else return false;
		}

		else{
			if(cardPosition == 0 && Card1Difference == Card2Difference){
				return true;
			}

			else if (cardPosition == 4 && Card2Difference > Card1Difference){
				return true;
			}

			else if (cardPosition == 0 && Card1Difference > Card2Difference){
				return true;
			}
			else return false;
		}
	}



	private boolean isBustedFlush(){
		int suitCount = 0;
		for (int i=1;i<HAND_SIZE;i++){
			if(hand[0].getSuit()==hand[i].getSuit()){
				suitCount++;
			}
		}

		if (suitCount == 3){
			return true;
		}
		return false;
	}

	private boolean isBustCardInFlush(int cardPosition){
		int sameSuit = 0;
		if(isBustedFlush()){
			for(int i = 0;i<HAND_SIZE;i++){
				if(hand[cardPosition].getSuit()== hand[i].getSuit()){
					sameSuit++;
				}
			}

			if(sameSuit<2){
				return true;
			}
			else return false;
		}
		return false;
	}

	private boolean isPartOfBottomThree(int cardPosition){
		int higherCards = 0;
		for(int i = 0;i<HAND_SIZE;i++){
			if(hand[cardPosition].getGameValue()< hand[i].getGameValue()){
				higherCards++;
			}
		}

		if (higherCards > 1)return true;
		else return false;

	}

	//This method calculates the gamevalue of the hand by assigning it a default value based on what type of hand it is and then adds value based on the specific cards
	public int getGameValue(){
		int val = 0;

		//For royal flush the default value is the only part of the game value as there is only one type of royal flush
		if(isRoyalFlush()){
			val += ROYAL_FLUSH;
			//			switch(hand[0].getSuit()){
			//			case 'D':
			//				val+=DIAMONDS;
			//			case 'H':
			//				val+=HEARTS;
			//			case 'S':
			//				val+=DIAMONDS;
			//			case 'C':
			//				val+=DIAMONDS;
			//			}
			return val;
		}

		//For straightflush the high card of the straight is used to detemine how strong a flush it is and the game value is adjusted accordingly
		else if (isStraightFlush()){
			if(hand[4].getGameValue() == 14&& hand[0].getGameValue()==2){
				val += hand[3].getGameValue()*PRIORITY0;
				val += hand[2].getGameValue()*PRIORITY1;
				val += hand[1].getGameValue()*PRIORITY2;
				val += hand[0].getGameValue()*PRIORITY3;
				val += hand[4].getGameValue();
			}
			else{
				val += hand[4].getGameValue()*PRIORITY0;
				val += hand[3].getGameValue()*PRIORITY1;
				val += hand[2].getGameValue()*PRIORITY2;
				val += hand[1].getGameValue()*PRIORITY3;
				val += hand[0].getGameValue();
			}
			val += STRAIGHT_FLUSH;
			return val;
		}

		//For four of a kind the gamevalue of the card that the player has four of decided how strong of this type of hand it is
		else if (isFourOfAKind()){
			val += hand[2].getGameValue();
			val += FOUR_OF_A_KIND;
			return val;
		}

		//The value of a fullhouse will be decided by gamevalue of the middle card (1 card of the three of a kind)
		else if (isFullHouse()){
			val += hand[2].getGameValue();
			val += FULL_HOUSE;
			return val;
		}

		//The value of all 5 cards in the flush will contribute to the value of the hand with the highest card contributing most, the 2nd contributing the 2nd most and so on
		else if (isFlush()){
			val += hand[4].getGameValue()*PRIORITY0;
			val += hand[3].getGameValue()*PRIORITY1;
			val += hand[2].getGameValue()*PRIORITY2;
			val += hand[1].getGameValue()*PRIORITY3;
			val += hand[0].getGameValue();
			val += FLUSH;

			return val;
		}

		//The value of all 5 cards in the straight will contribute to the value of the hand with the highest card contributing most, the 2nd contributing the 2nd most and so on
		else if (isStraight()){

			if(hand[4].getGameValue() == 14&& hand[0].getGameValue()==2){
				val += hand[3].getGameValue()*PRIORITY0;
				val += hand[2].getGameValue()*PRIORITY1;
				val += hand[1].getGameValue()*PRIORITY2;
				val += hand[0].getGameValue()*PRIORITY3;
				val += hand[4].getGameValue();
			}
			else{
				val += hand[4].getGameValue()*PRIORITY0;
				val += hand[3].getGameValue()*PRIORITY1;
				val += hand[2].getGameValue()*PRIORITY2;
				val += hand[1].getGameValue()*PRIORITY3;
				val += hand[0].getGameValue();
			}
			val += STRAIGHT;

			//			for(int i = 0; i<5;i++){
			//				switch(hand[i].getSuit()){
			//				case 'D':
			//					val+=DIAMONDS;
			//				case 'H':
			//					val+=HEARTS;
			//				case 'S':
			//					val+=DIAMONDS;
			//				case 'C':
			//					val+=DIAMONDS;
			//				}
			//			}

			return val;
		}

		//The value of a three of a kind will be decided by gamevalue of the middle card of the hand (1 card of the three of a kind)
		else if (isThreeOfAKind()){
			val += hand[2].getGameValue();
			val += THREE_OF_A_KIND;
			return val;
		}

		//The value of the twoPairHand is decided by the game value of the cards contained in the two pairs
		else if (isTwoPair()){
			if ( hand[0].getGameValue() == hand[1].getGameValue() && hand[2].getGameValue() == hand[3].getGameValue()){
				val += hand[2].getGameValue()*PRIORITY2;
				val += hand[0].getGameValue()*PRIORITY3;
				val += hand[4].getGameValue();
			}

			else if ( hand[0].getGameValue() == hand[1].getGameValue() && hand[3].getGameValue() == hand[4].getGameValue()){
				val += hand[3].getGameValue()*PRIORITY2;
				val += hand[0].getGameValue()*PRIORITY3;
				val += hand[2].getGameValue();
			}

			else if ( hand[1].getGameValue() == hand[2].getGameValue() && hand[3].getGameValue() == hand[4].getGameValue()){
				val += hand[3].getGameValue()*PRIORITY2;
				val += hand[1].getGameValue()*PRIORITY3;
				val += hand[0].getGameValue();
			}
			val += TWO_PAIR;
			return val;
		}

		//The value of the onePairHand is decided by the game value of the cards contained in the pair, the ohter cards in the hand are also taken into account to avoid any deadlock
		else if (isOnePair()){
			if ( hand[0].getGameValue() == hand[1].getGameValue() ){
				val += hand[0].getGameValue()*PRIORITY1;
				val += hand[4].getGameValue()*PRIORITY2;
				val += hand[3].getGameValue()*PRIORITY3;
				val += hand[2].getGameValue();
			}

			else if ( hand[1].getGameValue() == hand[2].getGameValue() ){
				val += hand[1].getGameValue()*PRIORITY1;
				val += hand[4].getGameValue()*PRIORITY2;
				val += hand[3].getGameValue()*PRIORITY3;
				val += hand[0].getGameValue();
			}

			else if ( hand[2].getGameValue() == hand[3].getGameValue() ){
				val += hand[2].getGameValue()*PRIORITY1;
				val += hand[4].getGameValue()*PRIORITY2;
				val += hand[1].getGameValue()*PRIORITY3;
				val += hand[0].getGameValue();
			}

			else if ( hand[3].getGameValue() == hand[4].getGameValue() ){
				val += hand[3].getGameValue()*PRIORITY1;
				val += hand[2].getGameValue()*PRIORITY2;
				val += hand[1].getGameValue()*PRIORITY3;
				val += hand[0].getGameValue();
			}
			val += ONE_PAIR;
			return val;
		}

		//The value of all 5 cards in the hand will contribute to the value of the HighHand with the highest card contributing most, the 2nd contributing the 2nd most and so on
		else if (isHighHand()){
			val += hand[4].getGameValue()*PRIORITY0;
			val += hand[3].getGameValue()*PRIORITY1;
			val += hand[2].getGameValue()*PRIORITY2;
			val += hand[1].getGameValue()*PRIORITY3;
			val += hand[0].getGameValue();
			val += HIGH_HAND;
			return val;
		}

		return val;

	}

	public int getDiscardProbability(int cardPosition){
		int probability = 0;
		int count = 0;

		if(isRoyalFlush()){
			//No hand is better than a royal flush. Don't discard any card.
			probability = 0;
		}

		else if(isStraightFlush()){
			//The only improvement of
			if(hand[cardPosition].getGameValue()==9&&cardPosition == 0){
				probability = (int)(1/REMAINING_CARDS);
			}
		}

		else if(isFourOfAKind()){
			//Check if the card you are looking at is part of the four of a kind. If it is not then discard it to trick the opponent into thinking your hand is weaker than it is.
			if(!isPartOfFourOfAKind(cardPosition)){
				probability = 100;
			}
		}

		else if(isFullHouse()){
			if(!isPartOfThreeOfAKind(cardPosition)){
				//technically you could get rid of one of you're pair cards in order to go for a four of a kind. However its unlikely so hence has a small possibility
				probability = (int)((1/REMAINING_CARDS)*(100));
			}
		}

		else if(isFlush()){
			probability = 0;
		}

		else if(isStraight()){
			probability = 0;
		}


		else if(isThreeOfAKind()){
			if(!isPartOfThreeOfAKind(cardPosition)){
				probability = 100;
			}
		}

		else if(isTwoPair()){
			//Once again it makes the most sense to discard the remaining card that does not contribute to either of the two pairs
			//To make one pair into a three of a kind and also to make your hand appear weaker than it is to other opponents
			if(!isPartOfPair(cardPosition)){
				probability = 100;
			}
		}

		else if(isOnePair()){
			if(isBustedFlush()){
				//if you have a pair but one of the cards in the pair is the bust card in a potential flush then the player may or may not decide whether take a chance
				if(isPartOfPair(cardPosition)&&isBustCardInFlush(cardPosition)){
					probability = (int)((9/REMAINING_CARDS)*(100));
				}
				else if(!isPartOfPair(cardPosition)&&isBustCardInFlush(cardPosition)){
					probability = 100;
				}
			}
			
			//Also if you have a pair but one of the cards in the pair is the bust card in a potential straight then the player may or may not decide whether take a chance
			else if(isBustedStraight()){
				if(isPartOfPair(cardPosition)&&isBustCardInStraight(cardPosition)){
					probability = (int)((4/REMAINING_CARDS)*(100));
				}
			}
			else if(!isPartOfPair(cardPosition)){
				probability = 100;
			}
		}

		else if (isBustedFlush()){
			if(isBustCardInFlush(cardPosition)){
				probability = 100;
			}
		}

		else if (isBustedStraight()){
			if(isBustCardInStraight(cardPosition)){
				probability = 100;
			}
		}

		else if(isHighHand()){
			if(isPartOfBottomThree(cardPosition)){
				probability = 100;
			}
		}

		return probability;
	}

	//main which deals a hand of cards and tells you which hand it is 
	public static void main(String [ ] args)
	{
		DeckOfCards deck = new DeckOfCards();
		deck.dealNext();
		deck.shuffle();
		HandOfCards hand = new HandOfCards(deck);
		//small stub for creating hands to test
//		hand.hand[0] = new PlayingCard("4",'H',4,4);
//		hand.hand[1] = new PlayingCard("5",'C',5,5);
//		hand.hand[2] = new PlayingCard("5",'H',5,5);
//		hand.hand[3] = new PlayingCard("2",'C',2,2);
//		hand.hand[4] = new PlayingCard("3",'D',3,3);
		hand.sort();

		
		//another stub just for creating random hands for testing
		HandOfCards hand1 = new HandOfCards(deck);
		hand1.hand[0] = new PlayingCard("9",'D',9,9);
		hand1.hand[1] = new PlayingCard("2",'H',2,2);
		hand1.hand[2] = new PlayingCard("J",'H',11,11);
		hand1.hand[3] = new PlayingCard("5",'H',5,5);
		hand1.hand[4] = new PlayingCard("6",'H',6,6);
		hand1.sort();
		
		HandOfCards hand2 = new HandOfCards(deck);
		//small stub for creating hands to test
		hand2.hand[0] = new PlayingCard("4",'H',4,4);
		hand2.hand[1] = new PlayingCard("5",'C',5,5);
		hand2.hand[2] = new PlayingCard("5",'H',5,5);
		hand2.hand[3] = new PlayingCard("2",'C',2,2);
		hand2.hand[4] = new PlayingCard("3",'D',3,3);
		hand2.sort();
		
		HandOfCards hand3 = new HandOfCards(deck);
		//small stub for creating hands to test
		hand3.hand[0] = new PlayingCard("8",'H',8,8);
		hand3.hand[1] = new PlayingCard("5",'C',5,5);
		hand3.hand[2] = new PlayingCard("5",'H',5,5);
		hand3.hand[3] = new PlayingCard("8",'C',8,8);
		hand3.hand[4] = new PlayingCard("8",'D',8,8);
		hand3.sort();

		System.out.println("Random Hand");
		System.out.println(hand);
		System.out.print(hand.getDiscardProbability(0) + "% " + hand.getDiscardProbability(1) + "% " + hand.getDiscardProbability(2)+ "% " + hand.getDiscardProbability(3)+ "% " + hand.getDiscardProbability(4)+ "% \n");

		if(hand.isRoyalFlush()){
			System.out.println("Royal Flush: " + hand.getGameValue());
		}
		else if(hand.isStraightFlush()){
			System.out.println("Straight Flush: " + hand.getGameValue());
		}
		else if(hand.isFourOfAKind()){
			System.out.println("Four of a kind: " + hand.getGameValue());
		}
		else if(hand.isFullHouse()){
			System.out.println("Full House: " + hand.getGameValue());
		}
		else if(hand.isFlush()){
			System.out.println("Flush: " + hand.getGameValue());
		}
		else if(hand.isStraight()){
			System.out.println("Straight: " + hand.getGameValue());
		}
		else if(hand.isThreeOfAKind()){
			System.out.println("Three of a kind: " + hand.getGameValue());
		}
		else if(hand.isTwoPair()){
			System.out.println("Two Pair: " + hand.getGameValue());
		}
		else if(hand.isOnePair()){
			System.out.println("One Pair: " + hand.getGameValue());
		}
		else if(hand.isHighHand()){
			System.out.println("High Hand: " + hand.getGameValue());
		}


		System.out.println("\nTest Hands(can be customised to test specific cases):\nBusted Flush:");
		System.out.println(""+hand1);
		System.out.print(hand1.getDiscardProbability(0) + "% " + hand1.getDiscardProbability(1) + "% " + hand1.getDiscardProbability(2)+ "% " + hand1.getDiscardProbability(3)+ "% " + hand1.getDiscardProbability(4)+ "% \n");


		if(hand1.isRoyalFlush()){
			System.out.println("Royal Flush: " + hand1.getGameValue());
		}
		else if(hand1.isStraightFlush()){
			System.out.println("Straight Flush: " + hand1.getGameValue());
		}
		else if(hand1.isFourOfAKind()){
			System.out.println("Four of a kind: " + hand1.getGameValue());
		}
		else if(hand1.isFullHouse()){
			System.out.println("Full House: " + hand1.getGameValue());
		}
		else if(hand1.isFlush()){
			System.out.println("Flush: " + hand1.getGameValue());
		}
		else if(hand1.isStraight()){
			System.out.println("Straight: " + hand1.getGameValue());
		}
		else if(hand1.isThreeOfAKind()){
			System.out.println("Three of a kind: " + hand1.getGameValue());
		}
		else if(hand1.isTwoPair()){
			System.out.println("Two Pair: " + hand1.getGameValue());
		}
		else if(hand1.isOnePair()){
			System.out.println("One Pair: " + hand1.getGameValue());
		}
		else if(hand1.isHighHand()){
			System.out.println("High hand: " + hand1.getGameValue());
		}
		
		System.out.println("\nBusted Straight (and a One Pair):");
		System.out.println(""+hand2);
		System.out.print(hand2.getDiscardProbability(0) + "% " + hand2.getDiscardProbability(1) + "% " + hand2.getDiscardProbability(2)+ "% " + hand2.getDiscardProbability(3)+ "% " + hand2.getDiscardProbability(4)+ "% \n");


		if(hand2.isRoyalFlush()){
			System.out.println("Royal Flush: " + hand2.getGameValue());
		}
		else if(hand2.isStraightFlush()){
			System.out.println("Straight Flush: " + hand2.getGameValue());
		}
		else if(hand2.isFourOfAKind()){
			System.out.println("Four of a kind: " + hand2.getGameValue());
		}
		else if(hand2.isFullHouse()){
			System.out.println("Full House: " + hand2.getGameValue());
		}
		else if(hand2.isFlush()){
			System.out.println("Flush: " + hand2.getGameValue());
		}
		else if(hand2.isStraight()){
			System.out.println("Straight: " + hand2.getGameValue());
		}
		else if(hand2.isThreeOfAKind()){
			System.out.println("Three of a kind: " + hand2.getGameValue());
		}
		else if(hand2.isTwoPair()){
			System.out.println("Two Pair: " + hand2.getGameValue());
		}
		else if(hand2.isOnePair()){
			System.out.println("One Pair: " + hand2.getGameValue());
		}
		else if(hand2.isHighHand()){
			System.out.println("High hand: " + hand2.getGameValue());
		}
		
		System.out.println("\nFull House:");
		System.out.println(""+hand3);
		System.out.print(hand3.getDiscardProbability(0) + "% " + hand3.getDiscardProbability(1) + "% " + hand3.getDiscardProbability(2)+ "% " + hand3.getDiscardProbability(3)+ "% " + hand3.getDiscardProbability(4)+ "% \n");


		if(hand3.isRoyalFlush()){
			System.out.println("Royal Flush: " + hand3.getGameValue());
		}
		else if(hand3.isStraightFlush()){
			System.out.println("Straight Flush: " + hand3.getGameValue());
		}
		else if(hand3.isFourOfAKind()){
			System.out.println("Four of a kind: " + hand3.getGameValue());
		}
		else if(hand3.isFullHouse()){
			System.out.println("Full House: " + hand3.getGameValue());
		}
		else if(hand3.isFlush()){
			System.out.println("Flush: " + hand3.getGameValue());
		}
		else if(hand3.isStraight()){
			System.out.println("Straight: " + hand3.getGameValue());
		}
		else if(hand3.isThreeOfAKind()){
			System.out.println("Three of a kind: " + hand3.getGameValue());
		}
		else if(hand3.isTwoPair()){
			System.out.println("Two Pair: " + hand3.getGameValue());
		}
		else if(hand3.isOnePair()){
			System.out.println("One Pair: " + hand3.getGameValue());
		}
		else if(hand3.isHighHand()){
			System.out.println("High hand: " + hand3.getGameValue());
		}
	}


}
