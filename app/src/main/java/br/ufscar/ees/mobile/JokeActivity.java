package br.ufscar.ees.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class JokeActivity extends AppCompatActivity {

    private TextView jokeLabel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        setupViews();
        fetchRandomJoke();
    }

    private void fetchRandomJoke() {
        new FetchRandomJoke().now(
            new FetchRandomJoke.Listener() {

                @Override public void onSuccess(Joke randomJoke) {
                    updateJoke(randomJoke.getTitle());
                }

                @Override public void onFail() {
                    reportFailure();
                }
            }
        );
    }

    private void updateJoke(final String title) {
        runOnUiThread(
            new Runnable() {
                @Override public void run() {
                    jokeLabel.setText(title);
                }
            }
        );
    }

    private void reportFailure() {
        Toast.makeText(this, R.string.toast_joke_error, LENGTH_SHORT).show();
    }

    private void setupViews() {
        jokeLabel = findViewById(R.id.labelJoke);

        findViewById(R.id.buttonFetchAnother)
            .setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        fetchRandomJoke();
                    }
                }
            );

    }
}
