package com.freshappbooks.flagsquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startGameButton;
    private int bestResult;
    private int lastResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGameButton = findViewById(R.id.start_game_button);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences prefs = getSharedPreferences(GameOverActivity.MY_PREF_NAME, MODE_PRIVATE);
        bestResult = prefs.getInt("best", 0);
        lastResult = prefs.getInt("last", 0);
    }
}
