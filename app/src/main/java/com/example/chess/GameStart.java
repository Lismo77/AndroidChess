package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameStart extends AppCompatActivity {

    private ChessBoardView cbv;
    private TextView gameStatus;
    private Button playAgainButton;
    private Button recordGameButton;
    private Button homeButton;
    private Button drawButton;
    private Button resignButton;
    private Button aiButton;
    private Button undoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_start);

        cbv = (ChessBoardView) findViewById(R.id.chess_board_view);
        gameStatus = (TextView) findViewById(R.id.gameStatus);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        recordGameButton = (Button) findViewById(R.id.recordGameButton);
        homeButton = (Button) findViewById(R.id.homeButton);
        drawButton = (Button) findViewById(R.id.drawButton);
        resignButton = (Button) findViewById(R.id.resignButton);
        aiButton = (Button) findViewById(R.id.aiButton);
        undoButton = (Button) findViewById(R.id.undoButton);
        undoButton.setEnabled(false);
        cbv.setWidgets(gameStatus, playAgainButton, recordGameButton, homeButton, drawButton, resignButton, aiButton, undoButton);

    }

    public void playAgainButtonClicked(View v) {
        cbv.resetGame();
        cbv.invalidate();
    }

    public void homeButtonClicked(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void resignButtonClicked(View v) {
        cbv.resigned();
    }

    public void drawButtonClicked(View v) {
        cbv.drawn();
    }

    public void undoButtonClicked(View v) {
        cbv.undo();
    }

    public void aiButtonClicked(View v) {
        cbv.randomMove();
    }

}