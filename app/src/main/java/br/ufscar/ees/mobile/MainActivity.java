package br.ufscar.ees.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonFindNewJoke)
            .setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        goToNewJoke();
                    }
                }
            );
    }

    private void goToNewJoke() {
        Intent toNewJoke = new Intent(this, JokeActivity.class);
        startActivity(toNewJoke);
    }
}
