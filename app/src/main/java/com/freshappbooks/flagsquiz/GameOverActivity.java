package com.freshappbooks.flagsquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView textViewResult;
    public static final String TAG = "MyApp";
    public static final String MY_PREF_NAME = "com.freshappbooks.flagsquiz_MY_PREF_NAME";
    private int bestResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        int result = QuizActivity.numberOfRightAnswer;
        Log.d(TAG, "onCreate: " + result);
        textViewResult = findViewById(R.id.textView_result);
        if (result< 10) {
            textViewResult.setText("Ваш результат: Новичок");
        } else if (result < 20 && result > 10) {
            textViewResult.setText("Ваш результат: Среднячок");
        } else {
            textViewResult.setText("Ваш результат: Гений!");
        }
        SharedPreferences prefs = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        bestResult = prefs.getInt("best", 0);
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE).edit();

        editor.putInt("last", 12);
        if (bestResult < result) {
            editor.putInt("best", result);
        }
        editor.apply();
    }
}
