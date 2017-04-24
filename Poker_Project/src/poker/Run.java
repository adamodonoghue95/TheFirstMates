package poker;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Run {
	
	public static void main(String[] args){
		TwitterBot tbot = new TwitterBot();
		ConfigurationBuilder builder1;
		builder1 = new ConfigurationBuilder();
		builder1.setOAuthConsumerKey(tbot.CONSUMER_KEY);
		builder1.setOAuthConsumerSecret(tbot.CONSUMER_SECRET);
		builder1.setOAuthAccessToken(tbot.ACCESS_TOKEN);
		builder1.setOAuthAccessTokenSecret(tbot.ACCESS_TOKEN_SECRET);
		
		TwitterStream twitterStream = new TwitterStreamFactory(builder1.build()).getInstance();
		
		FilterQuery fq = new FilterQuery();
	    
        String keywords[] = {tbot.HASHTAG, tbot.BOT_ID};

        fq.track(keywords);

        SL sl = new SL();
        
        twitterStream.addListener(sl);
        twitterStream.filter(fq);  
	}

}
