package com.plumtreegroup.twitterreader;

import java.util.ArrayList;
import org.json.JSONObject;
import com.plumtreegroup.twitterreader.application.TwitterApplication;
import reader.TweetReader;
import twitter4j.Twitter;
import util.Constants;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TweetsActivity extends ListActivity{
	
	private Button buttonLogout;
	private TwitterListAdapter adapter;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonLogout = (Button) findViewById(R.id.btnLogout1);
  		buttonLogout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				logoutFromTwitter();
			}
		});

	
		Twitter t = ((TwitterApplication)getApplication()).getTwitter();
	
		ArrayList<JSONObject> jobs = TweetReader.retrieveSpecificUsersTweets(t);
	
		adapter = new TwitterListAdapter(this,jobs);
		setListAdapter(adapter);
		
    	}
	

	private void logoutFromTwitter() {
		
		SharedPreferences prefs = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.clear();
		editor.commit();
		Log.v("TAG", "Again activity start");
		Intent t = new Intent(TweetsActivity.this,AuthActivity.class);
		startActivity(t);
	     
		
	}

}
