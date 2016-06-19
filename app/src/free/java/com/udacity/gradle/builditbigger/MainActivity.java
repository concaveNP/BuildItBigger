package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (ProgressBar)findViewById(R.id.progressBar);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

                showJoke();

            }
        });

        requestNewInterstitial();

    }

    @Override
    protected void onStart() {

        super.onStart();

        // Starting of the activity means our spinner should not yet be visible
        spinner.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;

        }

        return super.onOptionsItemSelected(item);

    }

    /**
     * Callback for the Joke button.
     *
     * @param view - The Joke button
     */
    public void tellJoke(View view){

        spinner.setVisibility(View.VISIBLE);

        // Check to see if an add is ready to go
        if (mInterstitialAd.isLoaded()) {

            // Show the add
            mInterstitialAd.show();

        } else {

            showJoke();

        }

    }

    private void showJoke() {

        requestNewInterstitial();

        // TODO: see if I really need this toast...
//        // Show the please purchase message
//        Toast.makeText(MainActivity.this, getString(R.string.toast_text), Toast.LENGTH_LONG).show();

        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Unused for now"));

    }

    private void requestNewInterstitial() {

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);

    }

}
