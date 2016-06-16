package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.concavenp.nanodegree.androidlib.JokeActivity;
import com.concavenp.nanodegree.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by dave on 5/2/16.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    /**
     * The logging tag string to be associated with log data for this class
     */
    private static final String TAG = EndpointsAsyncTask.class.getSimpleName();

    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {

        String result;

        if(myApiService == null) {  // Only do this once

            // This is the code that will talk with the Google Deployed backend
            MyApi.Builder builder = new MyApi.Builder( AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-1301.appspot.com/_ah/api/");

            Log.d(TAG, "-----------------------------------------");
            Log.d(TAG, "Service path: " + builder.getServicePath());
            Log.d(TAG, "Root URL: " + builder.getRootUrl());
            Log.d(TAG, "Application Name: " + builder.getApplicationName());
            Log.d(TAG, "-----------------------------------------");

            myApiService = builder.build();
        }

        // Unused for now, but leaving in for later
        context = params[0].first;
        String name = params[0].second;

        try {

            // Get the joke from the GCE
            result = myApiService.getJoke().execute().getJoke();

        } catch (IOException e) {

            Log.d(TAG, "exception while performing API service call for joke: " + e.toString());

            result = e.getMessage();

        }

        // Log the data
        Log.d(TAG, "getJoke result: " + result);

        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        // Create and start the joke activity by giving the GCE result
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_DATA, result);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}

