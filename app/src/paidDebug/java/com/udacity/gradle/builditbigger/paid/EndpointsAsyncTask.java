package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;

import com.concavenp.nanodegree.androidlib.JokeActivity;
import com.concavenp.nanodegree.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

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
            // This is the code that will simulate talking with the Google Deployed backend
            MyApi.Builder builder = new MyApi.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        // Unused for now, but leaving in for later
        context = params[0].first;
        String name = params[0].second;

        try {

            // Get the joke from the GCE
            result = myApiService.getJoke().execute().getJoke();

        } catch (IOException e) {

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
        context.startActivity(intent);

    }

}

