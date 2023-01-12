package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class player_setup extends AppCompatActivity {
    private EditText p1;
    private EditText p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_setup);
        p1 = findViewById(R.id.player1Name_txt);
        p2 = findViewById(R.id.player2Name_txt);
    }

    public void submitBtnOnClick(View view){
        String p1Name = p1.getText().toString();
        String p2Name = p2.getText().toString();
        Intent intent = new Intent(this, Game.class);
        intent.putExtra("PLAYER_NAMES", new String[]{p1Name, p2Name});
        startActivity(intent);
    }
}