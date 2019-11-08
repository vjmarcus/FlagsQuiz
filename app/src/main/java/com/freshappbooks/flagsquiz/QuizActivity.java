package com.freshappbooks.flagsquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    public static final String TAG = "MyApp";

    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        layout = findViewById(R.id.constraint_layout);
        setRandomBackground();
    }

    public void setRandomBackground() {
        Random rand = new Random();
        int random = rand.nextInt(6);
            switch (random) {
                case 0:
                    layout.setBackgroundColor(getResources().getColor(R.color.color0));
                    break;
                case 1:
                    layout.setBackgroundColor(getResources().getColor(R.color.color1));
                    break;
                case 2:
                    layout.setBackgroundColor(getResources().getColor(R.color.color2));
                    break;
                case 3:
                    layout.setBackgroundColor(getResources().getColor(R.color.color3));
                    break;
                case 4:
                    layout.setBackgroundColor(getResources().getColor(R.color.color4));
                    break;
                case 5:
                    layout.setBackgroundColor(getResources().getColor(R.color.color5));
                    break;
                default:
                    layout.setBackgroundColor(getResources().getColor(R.color.color0));
                    break;
            }
    }
}
