package poker;

import java.util.HashMap;
import java.util.Random;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class SL implements StatusListener {

	String username, content; 
	long tweet_ID;
	GameOfPoker gm;

	HashMap<String, Integer> playerMap = new HashMap<String, Integer>();

	public void onException(Exception arg0) {}
	public void onDeletionNotice(StatusDeletionNotice arg0) {}
	public void onScrubGeo(long arg0, long arg1) {}

	public void setStage(int stage) {
		if (playerMap.containsKey(username)) { // Increment player level
			playerMap.put(username, stage);
		}
	}

	public void onStatus(Status status) {
		
		//Initialise Variables
		TwitterBot tbot = new TwitterBot();
		User user = status.getUser();
		username = status.getUser().getScreenName();
		tweet_ID = status.getId(); 
		content = status.getText();
		
		Random rand = new Random();

		//Delete the @FirstMatesPoker and the white space after from content, leaves 
		// us with just the content of the tweet
		content = content.substring(tbot.BOT_ID.length()+1); 


		//Listener conditions
		//IF THE BOT HASHTAG IS TWEETED
		if(status.toString().contains(tbot.HASHTAG)){
//			username = status.getUser().getScreenName();
//			tweet_ID = status.getId(); 
//			content = status.getText();
			gm = new GameOfPoker(4, username);
			System.out.println(username);
			System.out.println(tweet_ID);
			System.out.println(content +"\n");
			System.out.println(playerMap.toString());

			if (playerMap.containsKey(username)) {
				tbot.tweet("@"+username+"\nYou are already participating in a game buddy");
			}
			else {
				playerMap.put(username, 0);
				tbot.tweet(gm.welcomeSection(username));

				setStage(1);
			}	
		}

		// IF THE BOTS @ IS TWEETED
		if(status.toString().contains(tbot.BOT_ID) || playerMap.get(username) >= 1){
			System.out.println(username);
			System.out.println(tweet_ID);
			System.out.println(content +"\n");
			String introStr = "@" + username + " " + gm.returnHumanHand();

			//LEVEL 1: TWEET THE INTRO TWEETS, ASK WHETHER THEY WANT TO FOLD OR NOT
			if (playerMap.get(username) == 1) {
				String [] tweets = gm.introSection();
				for (int i = 0; i < tweets.length; i++) {
					System.out.println(introStr + " \n" + tweets[i]);
					tbot.tweet(introStr + " \n" + tweets[i]);
				}
				setStage(2);
			}

			//LEVEL 2: CHECK THE FOLD INPUT AND ASK HOW MUCH THEY WANT TO RAISE
			else if (playerMap.get(username) == 2) {
				String tweet = "";

				String errorMsg = "Wrong input. Please tweet 'yes' or 'no'";
				tweet = introStr + "\n" + gm.foldSection(content);
				
				if (gm.noOfPlayers() == 1) { // Only one player left
					setStage(9);	// Jump to decide winner
				}
				
				if(!tweet.contains(errorMsg)){
					tbot.tweet(tweet);
					setStage(3);
				}
				else {
					int num = rand.nextInt(100);
					System.out.println();
					tbot.tweet(introStr + "\nError " + num + ": "  + errorMsg);
				}
			}

			//LEVEL 3: ASKS HUMAN FOR INITIAL BET AND PRINT PLAYERS ACTIONS
			else if (playerMap.get(username) == 3) {
				String tweet = introStr + "\n" + gm.betSection(content);
				String errorMsg = "Bet error";
				System.out.println(tweet + "\nlastRaise " + gm.getLastToRaise());
				
				// Error handling
				if (tweet.contains(errorMsg)) {
					tbot.tweet(tweet);
				}
				// If = 0, no need to match any raise -> increase two stages;
				else if (gm.getLastToRaise() == 0) {
					tweet += "\nWhich cards would you like to discard? (e.g 0 2)";
					tbot.tweet(tweet);
					setStage(5); // Skip to discardSection
				}
				else {
					tbot.tweet(tweet);
					setStage(4); // Go to matchSection
				}
				
			}
			
			//LEVEL 4: DEALS WITH MATCHING RAISES (IF THERE IS ANY)
			else if (playerMap.get(username) == 4) {
				String errorMsg = "Wrong input. Please tweet 'yes' or 'no'";
				String tweet = introStr + gm.matchSection(content);
				
				if (!tweet.contains(errorMsg)) { // Obtained correct input -> levelUP 
					tbot.tweet(tweet);
					tweet = introStr + "\n" + "Which cards would you like to discard? (e.g 0 2)";
					tbot.tweet(tweet);
					setStage(5);
				}
				else {
					int num = rand.nextInt(100);
					tbot.tweet(introStr + "\nError " + num + ": "  + tweet);
				}
			}
			
			//LEVEL 5: DEALS WITH DISCARDING CARDS
			else if (playerMap.get(username) == 5) {
				String errorMsg = "Discard error";
				String tweet = gm.discardSection(content);
				
				// Update intro message to new discarded hand
				introStr = "@" + username + " " + gm.returnHumanHand();
				
				// Add intro message on after discard method so that hand is updated
				tweet = introStr + "\n" + tweet;
				
				if (!tweet.contains(errorMsg)) { // Obtained correct input -> levelUP 
					// TODO
					tweet += "\nWould you like to fold?";
					tbot.tweet(tweet);
					setStage(6);
				}
				else {
					// Error handling
					int num = rand.nextInt(100);
					tweet += "\nWhich cards would you like to discard? (e.g 0 2)";
					tbot.tweet(introStr + "\nError " + num + ": "  + tweet);				
				}
			}
			
			//LEVEL 6: CHECK THE FOLD INPUT AND ASK HOW MUCH THEY WANT TO RAISE
			else if (playerMap.get(username) == 6) {
				String tweet = "";

				String errorMsg = "Wrong input. Please tweet 'yes' or 'no'";
				tweet = introStr + "\n" + gm.foldSection(content);
				
				if (gm.noOfPlayers() == 1) { // Only one player left
					setStage(8);	// Jump to decide winner
				}
				
				if(!tweet.contains(errorMsg)){
					tbot.tweet(tweet);
					setStage(7);
				}
				else {
					int num = rand.nextInt(100);
					System.out.println();
					tbot.tweet(introStr + "\nError " + num + ": "  + errorMsg);
				}
			}
			
			//LEVEL 7: SECOND BETTING STAGE 
			else if (playerMap.get(username) == 7) {

				String tweet = introStr + "\n" + gm.betSection(content);
				System.out.println(tweet);
				tbot.tweet(tweet);
				// If = 0, no need to match any raise -> increase two stages;
				if (gm.getLastToRaise() == 0 || gm.noOfPlayers() == 1) {
					setStage(9); // Skip to decideWinner
				}
				else {
					setStage(8); // Go to matchSection
				}
			}
			
			//LEVEL 8: SECOND MATCH STAGE
			else if (playerMap.get(username) == 8) {

				String errorMsg = "Wrong input. Please tweet 'yes' or 'no'";
				String tweet = introStr + "\n" + gm.matchSection(content);
				
				if (!tweet.contains(errorMsg)) { // Obtained correct input -> levelUP 
					tbot.tweet(tweet);
					setStage(9);
				}
				else {
					int num = rand.nextInt(100);
					tbot.tweet(introStr + "\nError " + num + ": "  + tweet);
				}
			}
			
			//LEVEL 9:
			else if (playerMap.get(username) == 9) {

				String tweet = gm.decideWinner();
				tbot.tweet(tweet);
								
				gm.dealNewHands(); // Deal new hands for all players
				setStage(1); // Restart new hand at level 1
			}
		}

	}
	public void onTrackLimitationNotice(int arg0) {}
	public void onStallWarning(StallWarning arg0) {}


}



