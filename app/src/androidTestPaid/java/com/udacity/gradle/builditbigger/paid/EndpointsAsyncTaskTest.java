package com.udacity.gradle.builditbigger.paid;

import android.annotation.TargetApi;
import android.test.AndroidTestCase;
import android.text.TextUtils;
import android.util.Pair;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by dave on 5/27/16.
 */
public class EndpointsAsyncTaskTest extends AndroidTestCase {

    private CountDownLatch signalDone;

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        signalDone = new CountDownLatch(1);

    }

    @Override
    protected void tearDown() throws Exception {

        super.tearDown();

        signalDone.countDown();

    }

    @TargetApi(16)
    public void testEndpointAsyncTask() throws InterruptedException {

        new EndpointsAsyncTask().execute(new Pair<>(getContext(), "Unused for now"));

        EndpointsAsyncTask task = new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                assertFalse(TextUtils.isEmpty(result));
            }
        };

        task.execute(new Pair<>(getContext(), "Unused for now"));

        // If not done in 10 seconds than let if through an exception
        signalDone.await(10, TimeUnit.SECONDS);

    }

}
