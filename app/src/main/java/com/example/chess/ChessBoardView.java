package com.example.chess;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chess.objects.ChessBoard;
import com.example.chess.objects.Piece;

public class ChessBoardView extends View {

    private final int whiteSpaceColor;
    private final int filledSpaceColor;

    private final Paint paint = new Paint();

    private int cellSize = getWidth()/8;

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
        paint.setAntiAlias(true);

        ChessBoard cb = new ChessBoard();
        drawSpaces(canvas, cb);
        drawPieces(canvas, cb);
    }

    private void drawGameBoard(Canvas canvas) {
    }

    private void drawSpaces(Canvas canvas, ChessBoard gameBoard) {
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

    private void drawPieces(Canvas canvas, ChessBoard gameBoard) {
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
