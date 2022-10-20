package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.model.Game;

public class GameScreen extends AppCompatActivity {
//    private static final int NUM_ROWS = 6;
//    private static final int NUM_COLS = 15;
    Game game = Game.getGameInstance();
    public  Button[][] grid = new Button[game.getMAP_ROW()][game.getMAP_COLUMN()];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        showStats();
        populateButtons();

    }

    private void showStats() {
        Game game = Game.getGameInstance();
        TextView scans = findViewById(R.id.tvScans);
        TextView found = findViewById(R.id.tvMinesFound);
        found.setText(getString(R.string.found_mines) + game.getFound() + " of " + game.getNumMines());
        scans.setText(getString(R.string.scans_used, game.getNumScans()));
    }

    private void populateButtons() {
        Game game = Game.getGameInstance();
        game.setMap();
        TableLayout table = findViewById(R.id.tableForButtons);
        for (int row = 0; row < game.getMAP_ROW(); row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < game.getMAP_COLUMN(); col++){
                Button button = new Button(this);
                grid[row][col] = button;
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                int finalRow = row;
                int finalCol = col;
                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        gridButtonClicked(grid[finalRow][finalCol], finalRow, finalCol);
                        showStats();
                    }
                });
                tableRow.addView(button);

            }
        }
    }

    private void gridButtonClicked(Button button, int row, int col){
        Game game = Game.getGameInstance();
        game.checkMap(row,col);
        String score = game.getSquareScore(row,col);
        button.setText(score);
        Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, GameScreen.class);
    }
}
