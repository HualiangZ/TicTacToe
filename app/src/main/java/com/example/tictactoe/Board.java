package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Board extends View {

    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winningLineColor;
    private int cellSize;
    private boolean winLine;
    private final gameLogic game;
    private final Paint paint = new Paint();

    public Board(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        game = new gameLogic();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.Board,0,0);

        try {
            boardColor = a.getInteger(R.styleable.Board_boardColor,0);
            XColor = a.getInteger(R.styleable.Board_XColor,0);
            OColor = a.getInteger(R.styleable.Board_OColor ,0);
            winningLineColor = a.getInteger(R.styleable.Board_winningLineColor,0);

        }finally {
            a.recycle();
        }

    }
 //test
    @Override
    protected void onMeasure(int width, int height){
        //get user phone smallest dimensions to calculate board size
        super.onMeasure(width,height);
        int dimensions = Math.min(getMeasuredHeight(), getMeasuredWidth());
        cellSize = dimensions/3;
        setMeasuredDimension(dimensions,dimensions);
    }

    @Override
    //draw the board
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawBoard(canvas);

        drawMarkers(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();


        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN){
            int row = (int)Math.ceil(y/cellSize);
            int col = (int)Math.ceil(x/cellSize);
            if(!winLine){
                if (game.updateBoard(row, col)){
                    invalidate();
                    if(game.winner()){
                        winLine= true;
                        invalidate();
                    }
                    //update player turn
                    if (game.getPlayer()%2 ==0){
                        game.setPlayer(game.getPlayer()-1);
                    }else{
                        game.setPlayer(game.getPlayer()+1);
                    }
                }
            }

            invalidate();
            return true;
        }
        return false;
    }

    private void drawBoard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);

        for(int i=1; i<3; i++){
            canvas.drawLine(cellSize*i,0,cellSize*i,canvas.getWidth(), paint);
        }

        for(int j=1; j<3; j++){
            canvas.drawLine(0,cellSize*j, canvas.getWidth(),cellSize*j, paint);
        }
    }

    private void drawMarkers(Canvas canvas){
        for (int i=0; i<3;i++){
            for (int j=0; j<3;j++){
                if(game.getGameBoard()[i][j]!=0){
                    if(game.getGameBoard()[i][j] == 1){
                        drawX(canvas, i, j);
                    }
                    else{
                        drawO(canvas, i , j);
                    }
                }
            }
        }
    }

    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(XColor);

        canvas.drawLine((float)((col+1)*cellSize-cellSize*0.2),
                (float)((row*cellSize+cellSize*0.2)),
                (float)(col*cellSize+cellSize*0.2),
                (float)((row+1)*cellSize-cellSize*0.2), paint);
        canvas.drawLine((float)(col*cellSize+cellSize*0.2),
                (float)(row*cellSize+cellSize*0.2),
                (float)((col+1)*cellSize-cellSize*0.2),
                (float)((row+1)*cellSize-cellSize*0.2), paint);
    }
    private void drawO(Canvas canvas, int row, int col){
        paint.setColor(OColor);

        canvas.drawOval((float)(col*cellSize+cellSize*0.2),
                (float)(row*cellSize+cellSize*0.2),
                (float)((col*cellSize+cellSize)-cellSize*0.2),
                (float)((row*cellSize+cellSize)-cellSize*0.2),paint);
    }

    public void setUpGame(Button playAgain, Button home, TextView dispPlayer, String[] name){
        game.setPlayAgainBtn(playAgain);
        game.setHomeBtn(home);
        game.setPlayerTurn(dispPlayer);
        if (!name[0].equals("") && !name[1].equals("")){
            game.setPlayerName(name);
        }
    }

    public void resetGame(){
        game.resetGame();
        winLine = false;
    }
}
