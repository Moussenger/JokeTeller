package edu.udacity.mou.jokerviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokerViewerActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker_viewer);

        TextView jokeText = (TextView) findViewById(R.id.joke_text);
        jokeText.setText(String.format(getString(R.string.joke_template), getJoke()));

    }

    private String getJoke() {
        return getIntent().getStringExtra(JOKE_EXTRA);
    }
}
