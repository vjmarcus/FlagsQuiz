package com.freshappbooks.flagsquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView textViewResult, textViewBestResult;
    public static final String TAG = "MyApp";
    public static final String MY_PREF_NAME = "com.freshappbooks.flagsquiz_MY_PREF_NAME";
    private int bestResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        int result = QuizActivity.rightAnswerCounter;
        Log.d(TAG, "onCreate: " + result);
        textViewResult = findViewById(R.id.textView_result);
        textViewBestResult = findViewById(R.id.textView_best_result);
        SharedPreferences prefs = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        bestResult = prefs.getInt("best", 0);
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE).edit();
        editor.putInt("last", result);
        if (bestResult < result) {
            editor.putInt("best", result);
            textViewBestResult.setText("Ваш лучший результат: " + result);
        } else {

        }
        editor.apply();

        textViewResult.setText("Ваш результат: " + result);
        textViewBestResult.setText("Ваш лучший результат: " + bestResult);
    }

    public void onClickAnotherGame(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
}
