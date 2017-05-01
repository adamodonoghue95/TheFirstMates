package poker;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import twitter4j.FilterQuery;
import twitter4j.ResponseList;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterBot {

	 static  String CONSUMER_KEY = "UIZBccUM2LOyjYpia65Z9yeyS";
	 static String CONSUMER_SECRET = "mDpio22pTE1qX2yn2nHaQNSr30htSV4QiyDynuaP9ViuWfHZpH";
	 static String ACCESS_TOKEN = "849639962328825859-uzQlvvcwxWC6IHuru0SFoGJqdP9Fm6u";
	 static String ACCESS_TOKEN_SECRET = "w36Xh2cpskF5bLHjLbK9rIBEVINXQX9qYB9IfRYeJn3F7";
	
	static String HASHTAG = "#DealMeInFMPoker";
	static String EXIT_HASHTAG = "DealMeOutFMPoker";
	static String BOT_ID = "@FirstMatesPoker";
	Twitter twitter;
	ConfigurationBuilder builder;
	ConfigurationBuilder builder1;


	

	public void authentication() {
		try{
			//this.load_keys();
			builder = new ConfigurationBuilder();
			builder.setOAuthConsumerKey(CONSUMER_KEY);
			builder.setOAuthConsumerSecret(CONSUMER_SECRET);
			builder.setOAuthAccessToken(ACCESS_TOKEN);
			builder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
			Configuration configuration = builder.build();
			
			TwitterFactory factory = new TwitterFactory(configuration);
			this.twitter = factory.getInstance();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void load_keys(){
		try {
			FileInputStream fis=new FileInputStream("data"+File.separator+"keys.txt");
			BufferedReader br=new BufferedReader(new InputStreamReader(fis, "utf-8"));
			String line;
			while((line=br.readLine())!=null){
				String keys[]=line.trim().split("\t");
				this.ACCESS_TOKEN=keys[2];
				this.ACCESS_TOKEN_SECRET=keys[3];
				this.CONSUMER_KEY=keys[0];
				this.CONSUMER_SECRET=keys[1];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void tweet(String post){
		try {
			try {
				TimeUnit.SECONDS.sleep(6);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(this.twitter==null){
				this.authentication();
			}
			twitter.updateStatus(post);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	public long reply(String post, long stat_id){
		try {
			try {
				TimeUnit.SECONDS.sleep(6);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(this.twitter==null){
				this.authentication();
			}
			
			StatusUpdate reply = new StatusUpdate(post).inReplyToStatusId(stat_id);
			Status s = twitter.updateStatus(reply);
			return s.getId();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return (Long) null;
	}

	public static void main(String args[]) throws Exception{
		TwitterBot tbot = new TwitterBot();
		//tbot.authentication();
		//tbot.tweet("TEST");
		//tbot.tweetSearch();
		
	}
}
