package poker;


import java.io.IOException;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterBot {

	static Keys keys = new Keys();

	private final String CONSUMER_KEY = keys.consumer_Key;
	private final String CONSUMER_SECRET = keys.consumerSecret;
	private final String ACCESS_TOKEN= keys.accessToken;
	private final String ACCESS_TOKEN_SECRET = keys.accessTokenSecret;
	
	Twitter twitter;


	public void authentication() {
		try{
			ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.setOAuthConsumerKey(CONSUMER_KEY);
			builder.setOAuthConsumerSecret(CONSUMER_SECRET);
			Configuration configuration = builder.build();
			TwitterFactory factory = new TwitterFactory(configuration);
			this.twitter = factory.getInstance();
			twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
			AccessToken oathAccessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
			twitter.setOAuthAccessToken(oathAccessToken);
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void tweet(String post){
		try {
			twitter.updateStatus(post);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception{
		TwitterBot tbot = new TwitterBot();
//		tbot.authentication();
//		tbot.tweet("FIDDY BUCKS");
		
		System.out.println(tbot.ACCESS_TOKEN);
		System.out.println(tbot.ACCESS_TOKEN_SECRET);
		System.out.println(tbot.CONSUMER_KEY);
		System.out.println(tbot.CONSUMER_SECRET);

	}


}
