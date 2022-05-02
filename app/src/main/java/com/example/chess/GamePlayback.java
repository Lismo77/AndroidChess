package com.example.chess;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chess.objects.ChessBoard;

import java.util.ArrayList;

public class GamePlayback extends AppCompatActivity {

    private ChessBoardView cbv;
    private TextView gameStatus;
    private Button homeButton;
    private Button backButton;
    private Button forwardButton;
    private ArrayList<ChessBoard> gameStates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_playback);

        Log.d("testing", "36");
        gameStates.add(new ChessBoard());
        Log.d("testing", "37");

        cbv = (ChessBoardView) findViewById(R.id.chess_board_view);
        gameStatus = (TextView) findViewById(R.id.gameStatus);
        homeButton = (Button) findViewById(R.id.homeButton);
        backButton = (Button) findViewById(R.id.backwardGameButton);
        forwardButton = (Button) findViewById(R.id.forwardGameButton);
        Log.d("testing", "38");
        cbv.setPlayback(gameStates);
        Log.d("testing", "39");
        cbv.setPlaybackWidgets(gameStatus, homeButton, backButton, forwardButton);


    }

    public void homeButtonClicked(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void backClicked(View v) {
        cbv.backward();
    }

    public void forwardClicked(View v) {
        cbv.forward();
    }



}