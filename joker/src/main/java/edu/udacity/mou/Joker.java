package edu.udacity.mou;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

import edu.udacity.mou.joker.backend.jokerApi.JokerApi;


public class Joker {

    public static String tell() {
        return getJoke();
    }

    private static String getJoke() {
        try {
            return new JokerApi.Builder(new NetHttpTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    }).build()
                    .joke()
                    .execute()
                    .getData();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
