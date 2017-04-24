package poker;

import java.util.ArrayList;

public class GameOfPoker {
	private static ArrayList<PokerPlayer> gamePlayers = new ArrayList<PokerPlayer>();
	private DeckOfCards deck = new DeckOfCards();
	private int STATUS_ID;

	public GameOfPoker(int numberOfPlayers) {
		
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
		for (int i = 0; i < gamePlayers.size(); i++) {
			PokerPlayer player = gamePlayers.get(i);
			if(player.inHand){
				player.newHand(deck);
			}
		}
	}

	public static void main(String [] args) {
		System.out.println("Welcome to TheFirstMates Poker Game!\n");

		GameOfPoker game = new GameOfPoker(4);

		System.out.println("\nLet's Play Poker " + gamePlayers.get(0).name + "!");
		while(game.checkGameState()){
						
			HandOfPoker hand = new HandOfPoker(gamePlayers, game.deck);
			hand.printChips();

			hand.printHumanHand();

			do {
				// Let players fold and start betting
				System.out.println("\nFIRST ROUND OF BETTING\n-----------------");
				hand.roundOfBetting();
				
				// Skips next round of betting if only one player left
				System.out.println(hand.noOfPlayers());
				if (hand.noOfPlayers() == 1) break;
				
				//Let players discard their cards
				hand.discardCards();
				hand.printHumanHand();
				
				//Complete second round of betting
				System.out.println("\nSECOND ROUND OF BETTING\n-----------------");
				hand.roundOfBetting();
				
			} while(false);

			//Show Cards
			hand.showCards();
			hand.decideWinner();
			
			// Lets players give all cards back and dealt new hands
			hand.returnHands();
			
			// Reset and shuffle deck
			game.deck.reset();
			game.deck.shuffle();
			
			// Deal new Hands for next round
			game.dealNewHands();
		}

		//hand.printChips();


	}

}
