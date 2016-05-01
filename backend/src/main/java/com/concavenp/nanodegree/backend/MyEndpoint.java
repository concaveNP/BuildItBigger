/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.concavenp.nanodegree.backend;

import com.concavenp.nanodegree.jokes.Joke;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.nanodegree.concavenp.com",
    ownerName = "backend.nanodegree.concavenp.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    /**
     * Retrieve a joke from the endpoint
     */
    @ApiMethod(name = "getJoke")
    public MyJoke getJoke() {
        MyJoke response = new MyJoke();

        // Get the joke string
        String joke = new Joke().getJoke();

        response.setJoke(joke);

        return response;
    }

}

