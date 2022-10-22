/**
 * Provides the user with the choice to choose
 * the size of the board and the number of mines
 */
package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.model.Game;

public class OptionsScreen extends AppCompatActivity {

    private static int row = 6;
    private static int col = 15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_screen);

        createMinesRadioButtons();
        createBoardSizeRadioButtons();
        getBoardSize(this);
        getNumMines(this);
    }

    private void createBoardSizeRadioButtons() {
        RadioGroup group = findViewById(R.id.radio_board_size);
        String [] boardOptions = getResources().getStringArray(R.array.board_size_options);

        //create buttons
        for(int i = 0; i<boardOptions.length; i++){
            String  boardSize = boardOptions[i];

            RadioButton button = new RadioButton(this);
            button.setText(boardSize);

            button.setOnClickListener(v -> {
                Toast.makeText(OptionsScreen.this, "Board will be "+boardSize,
                    Toast.LENGTH_SHORT).show();
                sizeofDimensions(boardSize);
                saveBoardSize(boardSize);
            });

            //Add to radio group
            group.addView(button);
            System.out.println(boardSize + " vs " + getBoardSize(this));
            if(boardSize.equals(getBoardSize(this))){
                sizeofDimensions(boardSize);
                button.setChecked(true);
            }else{

                System.out.println("false");
            }
        }
        //group
    }

    private void saveBoardSize(String size) {
        SharedPreferences prefs = this.getSharedPreferences("AppSizePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Size", size);
        editor.apply();
    }
    static public String getBoardSize(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppSizePrefs", MODE_PRIVATE);
        return prefs.getString("Size", "");
    }

    private void createMinesRadioButtons() {
        Game game = Game.getGameInstance();
        RadioGroup group = findViewById(R.id.radio_group_mines);
        int [] num_mines = getResources().getIntArray(R.array.number_of_mines);

        //create buttons
        for(int i = 0; i<num_mines.length; i++){
            int numMine = num_mines[i];

            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.mines, numMine));

            button.setOnClickListener(v -> {
                Toast.makeText(OptionsScreen.this, "You selected "+numMine,
                        Toast.LENGTH_SHORT).show();
                game.setNumMines(numMine);
                saveNumMines(numMine);
            });

            //Add to radio group
            group.addView(button);

            // Select default button
            if(numMine == getNumMines(this)){
                game.setNumMines(numMine);
                button.setChecked(true);
            }

        }
    }

    private void saveNumMines(int mines) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Num mines", mines);
        editor.apply();
    }
    static public int getNumMines(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs",MODE_PRIVATE);
        return prefs.getInt("Num mines", 0);
    }
    public static int getRows(){
        return row;
    }
    public static int getCols(){
        return col;
    }

    static public void sizeofDimensions(String size){
        Game game = Game.getGameInstance();
        switch (size) {
            case "5 x 10":
                game.setMapSize(5, 10);
                row = 5;
                col = 10;
                break;
            case "6 x 15":
                game.setMapSize(6, 15);
                row = 6;
                col = 15;
                break;
            default:
                game.setMapSize(4, 6);
                row = 4;
                col = 6;


        }
    }


    public static Intent makeIntent(Context context){
        return new Intent(context, OptionsScreen.class);
    }


}