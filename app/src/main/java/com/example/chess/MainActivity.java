package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playButtonClicked(View v) {
        Intent intent = new Intent(this, GameStart.class);
        startActivity(intent);
    }

    public void gamePlaybackClicked(View v) {
        Intent intent = new Intent(this, GamePlayback.class);
        startActivity(intent);
    }
}