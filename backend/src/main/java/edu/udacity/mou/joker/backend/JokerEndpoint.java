/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package edu.udacity.mou.joker.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokerApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.joker.mou.udacity.edu",
                ownerName = "backend.joker.mou.udacity.edu",
                packagePath = ""
        )
)
public class JokerEndpoint {

    private String getJoke() {
        switch((int) Math.floor(Math.random() * 5)) {
            case 0: return "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all.";
            case 1: return "Anton, do you think I’m a bad mother? - My name is Paul.";
            case 2: return "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.";
            case 3: return "I heard women love a man in uniform. Can’t wait to start working at McDonalds.";
            case 4: return "It is so cold outside I saw a politician with his hands in his own pockets.";
        }

        return "";
    }

    @ApiMethod(name = "joke", httpMethod = ApiMethod.HttpMethod.GET)
    public MyBean joke() {
        MyBean response = new MyBean();
        response.setData(getJoke());
        return response;
    }

}
