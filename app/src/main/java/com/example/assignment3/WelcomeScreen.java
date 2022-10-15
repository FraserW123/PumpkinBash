package com.example.assignment3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        setupNewGame();
    }

    private void setupNewGame() {
        Button btn = findViewById(R.id.btnMainMenu);
        btn.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeScreen.this, "Menu Commencing", Toast.LENGTH_SHORT).show();
                Intent intent = MainMenu.makeIntent(WelcomeScreen.this);
                startActivity(intent);
            }
        });
    }


}