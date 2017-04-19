package poker;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.FilterQuery;
import twitter4j.ResponseList;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
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

	private static  String CONSUMER_KEY = "UIZBccUM2LOyjYpia65Z9yeyS";
	private static String CONSUMER_SECRET = "mDpio22pTE1qX2yn2nHaQNSr30htSV4QiyDynuaP9ViuWfHZpH";
	private static String ACCESS_TOKEN = "849639962328825859-uzQlvvcwxWC6IHuru0SFoGJqdP9Fm6u";
	private static String ACCESS_TOKEN_SECRET = "w36Xh2cpskF5bLHjLbK9rIBEVINXQX9qYB9IfRYeJn3F7";
	
	private static String HASHTAG = "DealMeInFMPoker";
	private static String BOT_ID = "@FirstMatesPoker";
	Twitter twitter;
	ConfigurationBuilder builder;
	static ConfigurationBuilder builder1;


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
			twitter.updateStatus(post);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	public void tweetSearch(){
		builder1 = new ConfigurationBuilder();
		builder1.setOAuthConsumerKey(CONSUMER_KEY);
		builder1.setOAuthConsumerSecret(CONSUMER_SECRET);
		builder1.setOAuthAccessToken(ACCESS_TOKEN);
		builder1.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		TwitterStream twitterStream = new TwitterStreamFactory(builder1.build()).getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {}
            public void onDeletionNotice(StatusDeletionNotice arg0) {}
            public void onScrubGeo(long arg0, long arg1) {}

            public void onStatus(Status status) {
                User user = status.getUser();
                
                // gets Username
                String username = status.getUser().getScreenName();
                System.out.println(username);
                String profileLocation = user.getLocation();
                System.out.println(profileLocation);
                long tweetId = status.getId(); 
                System.out.println(tweetId);
                String content = status.getText();
                System.out.println(content +"\n");
              
            }
            public void onTrackLimitationNotice(int arg0) {}
			public void onStallWarning(StallWarning arg0) {}
        };
        
        FilterQuery fq = new FilterQuery();
    
        String keywords[] = {HASHTAG, BOT_ID};

        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq);  
    }


	public static void main(String args[]) throws Exception{
		TwitterBot tbot = new TwitterBot();
		tbot.authentication();
		//tbot.tweet("TEST");
		tbot.tweetSearch();
		
	}
}
