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

	public void checkGameState(){
		for(int i=0; i<gamePlayers.size();i++){
			if(gamePlayers.get(i).chips<=0){
				gamePlayers.remove(i);
			}
			else{
				gamePlayers.get(i).inHand = true;
			}
		}
		if(gamePlayers.size()==1){
			System.out.println(gamePlayers.get(0).name + " is the winner!");
		}
	}

	public static void main(String [] args) {
		System.out.println("Welcome to TheFirstMates Poker Game!\n");

		GameOfPoker game = new GameOfPoker(4);

		System.out.println("\nLet's Play Poker " + gamePlayers.get(0).name + "!");
		for(int i = 0;i < 2; i++){
			System.out.println(gamePlayers);
			game.checkGameState();
			HandOfPoker hand = new HandOfPoker(gamePlayers);
			hand.printChips();

			System.out.println("\nYour Current Hand: -> " + gamePlayers.get(0).playerHand.getHandType());
			hand.printHumanHand();

			//hand.showCards();

			// Let players fold and start betting
			//hand.checkFold();
			hand.roundOfBetting();
			hand.discardCards();

			System.out.println("\nYour Current Hand: -> " + gamePlayers.get(0).playerHand.getHandType());
			hand.printHumanHand();
			//Complete second round of betting
			hand.roundOfBetting();

			//Show Cards
			System.out.println("\nYour Current Hand: -> " + gamePlayers.get(0).playerHand.getHandType());
			hand.showCards();
			hand.decideWinner();
		}

		//hand.printChips();


	}

}
