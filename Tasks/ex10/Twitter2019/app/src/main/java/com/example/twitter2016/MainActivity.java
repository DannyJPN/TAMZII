package com.example.twitter2016;

import android.app.Activity;
import android.os.Bundle;
import java.util.Date;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Twitter twitter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO
        // WORK AROUND
        // sitova komunikace ma byt v separatnim vlakne
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Thread networkcom = new Thread()
        {
            public void run()
            {
                configureTwitter();
                 sendTweet();
            }
        };







        // ucet - https://twitter.com/tamz2_projekty



        // 1. pridat UI pro posilani tweetu
        //    dokumentace - http://twitter4j.org/en/
        // 2. zobrazit tweety pomoci ListView
        //    http://www.vogella.com/articles/AndroidListView/article.html


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


    //http://twitter4j.org/en/configuration.html
    public void configureTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("oEJEAxEPjNgWb0O35QNVaA")
                .setOAuthConsumerSecret("2TyiPmQMpnYHPE3S8ITkIQWld5fjk6jQ5eGfTsG8kg")
                .setOAuthAccessToken("927024486-4X07W3nTicx2SG0dTccqsNzraAyT1G8Ffc4VvNqN")
                .setOAuthAccessTokenSecret("neehbYt9lBY6o29UdcMLsZ1Zs9vVLPPncOpivLoyXtA");

        //cb.setUseSSL(true);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }


    //http://twitter4j.org/en/code-examples.html
    public void sendTweet() {

        Date d = new Date();
        CharSequence s  = DateFormat.format("yyyy-MM-dd hh:mm:ss", d.getTime());

        String latestStatus = "Test message " + s.toString();

        try {
            Status status = twitter.updateStatus(latestStatus);
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            // oops
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

}
