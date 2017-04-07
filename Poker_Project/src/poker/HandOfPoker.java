package poker;

import java.util.ArrayList;

public class HandOfPoker {

	private ArrayList<PokerPlayer> pokerPlayers = new ArrayList<PokerPlayer>();

	public HandOfPoker(ArrayList<PokerPlayer> players) {
		pokerPlayers = players;
		System.out.println("New Deal:\n");
	}

	public void printChips() {
		for (int i = 0; i < pokerPlayers.size(); i++) {
			System.out.println("> " + pokerPlayers.get(i).name + " has " + pokerPlayers.get(i).chips + " chip(s)");
		}
	}

	public void printHumanHand() {
		HandOfCards hand = pokerPlayers.get(0).playerHand;
		for (int i = 0; i < hand.HAND_SIZE; i++) {
			switch(hand.getCard(i).getFaceValue()) {
			case 1:
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
