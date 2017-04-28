package poker;

import java.util.HashMap;

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
				System.out.println("level of : " + username + ", " + playerMap.get(username));
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
				System.out.println("level of : " + username + ", " + playerMap.get(username));
				// TODO foldSection (do you want to fold?)
				String wrongInputMessage = "@" + username + " \nWrong input. Please tweet 'yes' or 'no'";
				tweet = introStr + "\n" + gm.humanInputForFold(content);
				tbot.tweet(tweet);
				
				if(!tweet.contains(wrongInputMessage)){
					setStage(3);
				}
			}

			//LEVEL 3: ASKS HUMAN FOR INITIAL BET AND PRINT PLAYERS ACTIONS
			else if (playerMap.get(username) == 3) {
				System.out.println("level of : " + username + ", " + playerMap.get(username));
				// TODO initialBetSection ( what do you want to bet/raise? )
				String tweet = introStr + "\n" + gm.raiseSection(content);
				System.out.println(tweet);
				tbot.tweet(tweet);
				// If = 0, no need to match any raise -> increase two stages;
				if (gm.getLastToRaise() == 0) {
					setStage(5); // Skip to discardSection
				}
				else {
					setStage(4); // Go to matchSection
				}
			}
			
			//LEVEL 4: DEALS WITH MATCHING RAISES (IF THERE IS ANY)
			else if (playerMap.get(username) == 4) {
				// TODO
				System.out.println("level of : " + username + ", " + playerMap.get(username));
				String errorMsg = "@" + username + " \nWrong input. Please tweet 'yes' or 'no'";
				String tweet = introStr + "\n" + gm.matchSection(content);
				
				if (!tweet.contains(errorMsg)) { // Obtained correct input -> levelUP 
					tbot.tweet(tweet);
					tweet = introStr + "\n" + "Which cards would you like to discard? (e.g 0 2)";
					tbot.tweet(tweet);
					setStage(5);
					System.out.println("Leveled up to " + playerMap.get(username));
					System.out.println(playerMap);
				}
				else {
					tbot.tweet(introStr + "\n" + errorMsg);
				}
			}
			
			//LEVEL 5: DEALS WITH DISCARDING CARDS
			else if (playerMap.get(username) == 5) {
				// TODO 
				System.out.println("level of : " + username + ", " + playerMap.get(username));
				String errorMsg = "@" + username + " \nWrong input.";
				String tweet = gm.discardSection(content);
				
				// Update intro message to new discarded hand
				introStr = "@" + username + " " + gm.returnHumanHand();
				
				// Add intro message on after discard method so that hand is updated
				tweet = introStr + "\n" + tweet;
				
				if (!tweet.contains(errorMsg)) { // Obtained correct input -> levelUP 
					// TODO
					tweet += "\n How much would you like raise?" ;
					tbot.tweet(tweet);
					setStage(6);
				}
				else {
					// Tweets error message and stays on same level
					tbot.tweet(introStr + "\n" + tweet);
				}
			}
			
			//LEVEL 6: SECOND BETTING STAGE 
			else if (playerMap.get(username) == 5) {
				System.out.println("level of : " + username + ", " + playerMap.get(username));
				// TODO initialBetSection ( what do you want to bet/raise? )
				String tweet = introStr + "\n" + gm.raiseSection(content);
				System.out.println(tweet);
				tbot.tweet(tweet);
				// If = 0, no need to match any raise -> increase two stages;
				if (gm.getLastToRaise() == 0) {
					setStage(8); // Skip to decideWinner
				}
				else {
					setStage(7); // Go to matchSection
				}
			}
			
			//LEVEL 7: SECOND MATCH STAGE
			else if (playerMap.get(username) == 7) {
				// TODO
				System.out.println("level of : " + username + ", " + playerMap.get(username));
				String errorMsg = "@" + username + " \nWrong input. Please tweet 'yes' or 'no'";
				String tweet = introStr + "\n" + gm.matchSection(content);
				
				if (!tweet.contains(errorMsg)) { // Obtained correct input -> levelUP 
					tbot.tweet(tweet);
					tweet = introStr + "\n" + "Which cards would you like to discard? (e.g 0 2)";
					tbot.tweet(tweet);
					setStage(8);
					System.out.println("Leveled up to " + playerMap.get(username));
					System.out.println(playerMap);
				}
				else {
					tbot.tweet(introStr + "\n" + errorMsg);
				}
			}
			//LEVEL 8:
			else if (playerMap.get(username) == 8) {
				// TODO
				System.out.println("level of : " + username + ", " + playerMap.get(username));
				String tweet = gm.decideWinner();
				tbot.tweet(tweet);
				setStage(1); // Restart at level 1
			}
		}

		//		String output = ("@"+username + " Welcome to FM Poker");
		//		tbot.tweet(output);
	}
	public void onTrackLimitationNotice(int arg0) {}
	public void onStallWarning(StallWarning arg0) {}


}



