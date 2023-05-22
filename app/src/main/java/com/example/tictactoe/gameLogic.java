package com.example.tictactoe;

import android.view.View;
import android.widget.*;

public class gameLogic {
    private int[][] gameBoard;
    private int player=1;
    private Button playAgainBtn;
    private Button homeBtn;
    private TextView playerTurn;
    private String[] playerName = {"p1" , "p2"};
    //make it so user allow to place markers
    gameLogic(){
        gameBoard = new int[3][3];
        for (int i=0; i<3;i++){
            for (int j=0; j<3;j++){
                gameBoard[i][j] = 0;
            }
        }
    }

    public boolean updateBoard(int row, int col){
        //check if block is clear and if it is add a O or X
        if(gameBoard[row-1][col-1]==0){
            gameBoard[row-1][col-1] = player;
            if(player ==1){
                playerTurn.setText((playerName[1]+ "'s turn"));
            }else{
                playerTurn.setText((playerName[0]+ "'s turn"));
            }
            return true;
        }
        else{
            return false;
        }
    }

    //rest game
    public void resetGame(){
        for (int i=0; i<3;i++){
            for (int j=0; j<3;j++){
                gameBoard[i][j] = 0;
            }
        }
        player = 1;
        //find player
        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);
        playerTurn.setText((playerName[0] + "'s turn"));
    }

    //check for the winner
    public boolean winner(){
        boolean isWinner = false;
        int boardFilled = 0;

        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                if(gameBoard[r][c]!=0){
                    boardFilled++;
                }
            }
        }
        //check rows and see if all 3 spot is the same
        for(int r=0;r<3;r++){
            if(gameBoard[r][0] == gameBoard[r][1] &&gameBoard[r][0] == gameBoard[r][2] && gameBoard[r][0] !=0){
                isWinner = true;
            }
        }
        //check columes and see if all 3 spot is the same
        for(int c=0;c<3;c++){
            if(gameBoard[0][c] == gameBoard[1][c] &&gameBoard[0][c] == gameBoard[2][c] && gameBoard[0][c] !=0){
                isWinner = true;
            }
        }

        //check diagonals
        if(gameBoard[0][0] == gameBoard[1][1] &&gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] !=0){
            isWinner = true;
        }

        if(gameBoard[2][0] == gameBoard[1][1] &&gameBoard[2][0] == gameBoard[0][2] && gameBoard[2][0] !=0){
            isWinner = true;
        }

        if(isWinner){
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText((playerName[player-1]+ " Won"));
            return true;
        }else if (boardFilled ==9){
            playerTurn.setText(("Tie Game"));
            return true;
        }else{
            return false;
        }

    }

    public void setPlayAgainBtn(Button playAgainBtn) {
        this.playAgainBtn = playAgainBtn;
    }

    public void setHomeBtn(Button homeBtn) {
        this.homeBtn = homeBtn;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlayerName(String[] playerName) {
        this.playerName = playerName;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }
}
