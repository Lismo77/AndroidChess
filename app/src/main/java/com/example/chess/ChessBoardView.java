package com.example.chess;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chess.objects.ChessBoard;
import com.example.chess.objects.Piece;
import com.example.chess.objects.Space;

public class ChessBoardView extends View {

    private final int whiteSpaceColor;
    private final int filledSpaceColor;

    private final Paint paint = new Paint();

    private int cellSize = getWidth()/8;

    private ChessBoard gameBoard;
    private Piece selectedPiece = null;
    private Space[] currentMove = new Space[2];
    public boolean gameOver = false;

    private Bitmap wpawn = BitmapFactory.decodeResource(getResources(), R.drawable.wpawn);
    private Bitmap wknight = BitmapFactory.decodeResource(getResources(), R.drawable.wknight);
    private Bitmap wbishop = BitmapFactory.decodeResource(getResources(), R.drawable.wbishop);
    private Bitmap wrook = BitmapFactory.decodeResource(getResources(), R.drawable.wrook);
    private Bitmap wqueen = BitmapFactory.decodeResource(getResources(), R.drawable.wqueen);
    private Bitmap wking = BitmapFactory.decodeResource(getResources(), R.drawable.wking);
    private Bitmap bpawn = BitmapFactory.decodeResource(getResources(), R.drawable.bpawn);
    private Bitmap bknight = BitmapFactory.decodeResource(getResources(), R.drawable.bknight);
    private Bitmap bbishop = BitmapFactory.decodeResource(getResources(), R.drawable.bbishop);
    private Bitmap brook = BitmapFactory.decodeResource(getResources(), R.drawable.brook);
    private Bitmap bqueen = BitmapFactory.decodeResource(getResources(), R.drawable.bqueen);
    private Bitmap bking = BitmapFactory.decodeResource(getResources(), R.drawable.bking);

    public ChessBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        gameBoard = new ChessBoard();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ChessBoardView, 0, 0);

        try {
            whiteSpaceColor = a.getInteger(R.styleable.ChessBoardView_whiteSpaceColor, 0);
            filledSpaceColor = a.getInteger(R.styleable.ChessBoardView_filledSpaceColor, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimension/8;

        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawSpaces(canvas);
        drawPieces(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            if (!gameOver) {
                int row = (int) Math.floor((y / cellSize));
                int column = (int) Math.floor(x / cellSize);

                if (currentMove[0] == null) {
                    if (gameBoard.board[row][column].getPiece() != null) {
                        if (gameBoard.board[row][column].getPiece().getColor() == gameBoard.turn) {
                            selectedPiece = gameBoard.board[row][column].getPiece();
                            currentMove[0] = gameBoard.board[row][column];
                        }
                    }
                } else {
                    if (gameBoard.board[row][column].getPiece() != null) {
                        if (gameBoard.board[row][column].getPiece().getColor() == gameBoard.turn) {
                            selectedPiece = gameBoard.board[row][column].getPiece();
                            currentMove[0] = gameBoard.board[row][column];
                        } else {
                            currentMove[1] = gameBoard.board[row][column];
                            if (gameBoard.attemptMove(currentMove) == 0) {
                                gameOver = gameBoard.checkmate(gameBoard.turn);
                                currentMove[0] = null;
                                selectedPiece = null;
                            }
                            currentMove[1] = null;
                        }
                    } else {
                        currentMove[1] = gameBoard.board[row][column];
                        if (gameBoard.attemptMove(currentMove) == 0) {
                            gameOver = gameBoard.checkmate(gameBoard.turn);
                            currentMove[0] = null;
                            selectedPiece = null;
                        }
                        currentMove[1] = null;
                    }
                }


                invalidate();
                return true;
            }
        }

        return false;
    }

    private void drawSpaces(Canvas canvas) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard.board[i][j].isFilled()) {
                    paint.setColor(filledSpaceColor);
                } else {
                    paint.setColor(whiteSpaceColor);
                }
                canvas.drawRect(j*cellSize,i*cellSize, (j+1)*cellSize, (i+1)*cellSize, paint);
            }
        }
    }

    private void drawPieces(Canvas canvas) {
        Bitmap wpawnres = Bitmap.createScaledBitmap(wpawn, (int)(cellSize*.85), (int)(cellSize*.85), true);
        Bitmap wknightres = Bitmap.createScaledBitmap(wknight, (int)(cellSize*.85), (int)(cellSize*.85), true);
        Bitmap wbishopres = Bitmap.createScaledBitmap(wbishop, (int)(cellSize*.85), (int)(cellSize*.85), true);
        Bitmap wrookres = Bitmap.createScaledBitmap(wrook, (int)(cellSize*.85), (int)(cellSize*.85), true);
        Bitmap wqueenres = Bitmap.createScaledBitmap(wqueen, (int)(cellSize*.85), (int)(cellSize*.85), true);
        Bitmap wkingres = Bitmap.createScaledBitmap(wking, (int)(cellSize*.85), (int)(cellSize*.85), true);
        Bitmap bpawnres = Bitmap.createScaledBitmap(bpawn, (int)(cellSize*.85), (int)(cellSize*.85), true);
        Bitmap bknightres = Bitmap.createScaledBitmap(bknight, (int)(cellSize*.85), (int)(cellSize*.85), true);
        Bitmap bbishopres = Bitmap.createScaledBitmap(bbishop, (int)(cellSize*.85), (int)(cellSize*.85), true);
        Bitmap brookres = Bitmap.createScaledBitmap(brook, (int)(cellSize*.85), (int)(cellSize*.85), true);
        Bitmap bqueenres = Bitmap.createScaledBitmap(bqueen, (int)(cellSize*.85), (int)(cellSize*.85), true);
        Bitmap bkingres = Bitmap.createScaledBitmap(bking, (int)(cellSize*.85), (int)(cellSize*.85), true);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard.board[i][j].getPiece() != null) {
                    Piece p = gameBoard.board[i][j].getPiece();
                    if (p.equals(selectedPiece)) {
                        paint.setColor(Color.GRAY);
                        canvas.drawRect(j*cellSize,i*cellSize, (j+1)*cellSize, (i+1)*cellSize, paint);
                    }
                    if (p.getColor().equals("White")) {
                        switch(p.getType()) {
                            case "pawn" :
                                canvas.drawBitmap(wpawnres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                            case "knight" :
                                canvas.drawBitmap(wknightres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                            case "bishop" :
                                canvas.drawBitmap(wbishopres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                            case "rook" :
                                canvas.drawBitmap(wrookres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                            case "queen" :
                                canvas.drawBitmap(wqueenres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                            case "king" :
                                canvas.drawBitmap(wkingres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                        }
                    } else {
                        switch(p.getType()) {
                            case "pawn" :
                                canvas.drawBitmap(bpawnres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                            case "knight" :
                                canvas.drawBitmap(bknightres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                            case "bishop" :
                                canvas.drawBitmap(bbishopres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                            case "rook" :
                                canvas.drawBitmap(brookres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                            case "queen" :
                                canvas.drawBitmap(bqueenres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                            case "king" :
                                canvas.drawBitmap(bkingres,j*cellSize+(int)(cellSize*.1),i*cellSize+(int)(cellSize*.1), null);
                                break;
                        }
                    }
                }
            }
        }
    }
}
