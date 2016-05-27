package com.concavenp.nanodegree.androidlib;

import android.content.Intent;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

@Keep
public class JokeActivity extends AppCompatActivity {

    /**
     * String used when creating the activity via intent.  This key will be used to retrieve the
     * Joke string passed in.
     */
    public static final String JOKE_DATA = "string_joke_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_joke);

        // Extract the joke string from the Extra Intent data passed in
        Intent intent = getIntent();
        final String joke = intent.getStringExtra(JOKE_DATA);

        // Set the joke text
        TextView textView = (TextView) findViewById(R.id.joke_TextView);
        textView.setText(joke);

    }

}
