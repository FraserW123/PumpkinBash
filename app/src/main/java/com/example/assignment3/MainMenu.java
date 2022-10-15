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

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        setupMainMenu();
        setupHelpScreen();
        setupOptionsScreen();
    }

    private void setupOptionsScreen() {
        Button btn = findViewById(R.id.btnOptions);
        btn.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenu.this, "Options Commencing", Toast.LENGTH_SHORT).show();
                Intent intent = OptionsScreen.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });
    }

    private void setupHelpScreen() {
        Button btn = findViewById(R.id.btnHelp);
        btn.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenu.this, "Help Commencing", Toast.LENGTH_SHORT).show();
                Intent intent = HelpScreen.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });
    }

    private void setupMainMenu() {
        Button btn = findViewById(R.id.btnNewGame);
        btn.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenu.this, "New Game Commencing", Toast.LENGTH_SHORT).show();
                Intent intent = GameScreen.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, MainMenu.class);
    }
}