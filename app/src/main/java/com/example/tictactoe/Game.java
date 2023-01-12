package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    private Board b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Button playAgainBtn = findViewById(R.id.playAgain_Btn);
        Button homeBtn = findViewById(R.id.home_Btn);
        TextView playerTurn = findViewById(R.id.player_txt);

        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);

        String[] playerName = getIntent().getStringArrayExtra("PLAYER_NAMES");
        //display who's turn it is
        assert playerName != null;
        if (playerName[0].equals("")){

            playerTurn.setText("P1's turn");
        }else{
            playerTurn.setText((playerName[0] + "'s turn"));
        }

        b = findViewById(R.id.board);
        b.setUpGame(playAgainBtn, homeBtn, playerTurn,playerName);
    }

    public void homeBtnOnClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void playAgainBtnOnClick(View view){
        b.resetGame();
        b.invalidate();
    }
}