package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import edu.udacity.mou.Joker;
import edu.udacity.mou.jokerviewer.JokerViewerActivity;


public class MainActivity extends AppCompatActivity {

    private SimpleIdlingResource idlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void tellJoke(View view) {
        JokeTeller jokeTeller = new JokeTeller();
        jokeTeller.execute();
    }

    private void onRetrievedJoke(String joke) {
        if(idlingResource != null) {
            idlingResource.setIdleState(true);
        }
        Intent intent = new Intent(this, JokerViewerActivity.class);
        intent.putExtra(JokerViewerActivity.JOKE_EXTRA, joke);
        startActivity(intent);
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }

        return idlingResource;
    }

    private class JokeTeller extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            return Joker.tell();
        }

        @Override
        protected void onPostExecute(String joke) {
            onRetrievedJoke(joke);
        }
    }

}
