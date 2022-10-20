package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.assignment3.model.Game;

public class OptionsScreen extends AppCompatActivity {

    int mines = 0;
    int row = 0;
    int col = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_screen);


        createMinesRadioButtons();
        createBoardSizeRadioButtons();
        setSaveChanges();
    }

    private void createBoardSizeRadioButtons() {

        RadioGroup group = findViewById(R.id.radio_board_size);
        String [] boardOptions = getResources().getStringArray(R.array.board_size_options);

        //create buttons
        for(int i = 0; i<boardOptions.length; i++){
            String  boardSize = boardOptions[i];

            RadioButton button = new RadioButton(this);
            button.setText(boardSize);

            //TODO: Set on click callbacks
            button.setOnClickListener(v -> {
                Toast.makeText(OptionsScreen.this, "Board will be "+boardSize,
                    Toast.LENGTH_SHORT).show();
                sizeofDimensions(boardSize);

            });

            //Add to radio group
            group.addView(button);
        }
    }

    private void createMinesRadioButtons() {

        RadioGroup group = findViewById(R.id.radio_group_mines);
        int [] num_mines = getResources().getIntArray(R.array.number_of_mines);

        //create buttons
        for(int i = 0; i<num_mines.length; i++){
            int numMine = num_mines[i];

            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.mines, numMine));

            //TODO: Set on click callbacks
            button.setOnClickListener(v -> {
                Toast.makeText(OptionsScreen.this, "You selected "+numMine,
                        Toast.LENGTH_SHORT).show();
                mines = numMine;
            });

            //Add to radio group
            group.addView(button);
        }
    }

    private void setSaveChanges() {
        //Game game = Game.getGameInstance();
        Button btn = findViewById(R.id.find_selected);
        btn.setOnClickListener(v -> {
            RadioGroup group_Mines = findViewById(R.id.radio_group_mines);
            int idOfSelectedMines = group_Mines.getCheckedRadioButtonId();
            RadioButton mineSelection = findViewById(idOfSelectedMines);


            RadioGroup group_board_size = findViewById(R.id.radio_board_size);
            int idOfSelectedBoardSize = group_board_size.getCheckedRadioButtonId();
            RadioButton boardSelection = findViewById(idOfSelectedBoardSize);

            if(mineSelection.getText() != null){
                String message = mineSelection.getText().toString();
                String message2 = boardSelection.getText().toString();
                Toast.makeText(OptionsScreen.this, "Adding game configuration: " + message +
                        " Board size: " + message2,
                        Toast.LENGTH_SHORT).show();
                Game game = Game.getGameInstance(row, col, mines);
            }
            else{
                Toast.makeText(OptionsScreen.this, "No option have been selected",
                        Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void sizeofDimensions(String size){
        if(size.equals("4 x 6")){
            row = 4;
            col = 6;
        }else if(size.equals("5 x 10")){
            row = 5;
            col = 10;
        }else if(size.equals("6 x 15")){
            row = 6;
            col = 10;
        }else{
            row = 2;
            col = 2;
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, OptionsScreen.class);
    }
}