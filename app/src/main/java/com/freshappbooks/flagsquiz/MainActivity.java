package com.freshappbooks.flagsquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button startGameButton;
    private int bestResult;
    private int lastResult;
    public static final String BANNER = "ca-app-pub-7831171328659727/8682651059";
    public static final String INTERSTITIAL = "ca-app-pub-7831171328659727/6526921694";

    TextView textViewBestResult, textViewLastResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewBestResult = findViewById(R.id.textView_best_result);
        textViewLastResult = findViewById(R.id.textView_last_result);
        startGameButton = findViewById(R.id.start_game_button);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences prefs = getSharedPreferences(GameOverActivity.MY_PREF_NAME, MODE_PRIVATE);
        lastResult = prefs.getInt("last", 0);
        bestResult = prefs.getInt("best", 0);


        textViewLastResult.setText("Ваш прошлый результат: " + lastResult);
        textViewBestResult.setText("Ваш лучший результат: " + bestResult);
    }
}
