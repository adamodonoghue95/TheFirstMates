package poker;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.User;



public class SL implements StatusListener {

	String username, content; 
	long tweet_ID;
	
	public SL(){
		
	}
	

	public void onException(Exception arg0) {}
	public void onDeletionNotice(StatusDeletionNotice arg0) {}
	public void onScrubGeo(long arg0, long arg1) {}

	public void onStatus(Status status) {
		User user = status.getUser();
		// gets Username
		username = status.getUser().getScreenName();
		System.out.println(username);

		tweet_ID = status.getId(); 
		System.out.println(tweet_ID);

		content = status.getText();
		System.out.println(content +"\n");
	}
	public void onTrackLimitationNotice(int arg0) {}
	public void onStallWarning(StallWarning arg0) {}

}



