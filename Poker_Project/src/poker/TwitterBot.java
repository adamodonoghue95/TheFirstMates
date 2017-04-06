package poker;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

	private String CONSUMER_KEY;
	private  String CONSUMER_SECRET;
	private  String ACCESS_TOKEN;
	private  String ACCESS_TOKEN_SECRET;
	
	Twitter twitter;


	public void authentication() {
		try{
			this.load_keys();
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

	public static void main(String args[]) throws Exception{
		TwitterBot tbot = new TwitterBot();
		tbot.authentication();
//		tbot.tweet("FIDDY BUCKS");
		
		System.out.println(tbot.ACCESS_TOKEN);
		System.out.println(tbot.ACCESS_TOKEN_SECRET);
		System.out.println(tbot.CONSUMER_KEY);
		System.out.println(tbot.CONSUMER_SECRET);

	}


}
