package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.assignment3.model.Game;

import java.util.concurrent.TimeUnit;

public class GameScreen extends AppCompatActivity {

    Game game = Game.getGameInstance();
    Button[][] buttons = new Button[OptionsScreen.getRows()][OptionsScreen.getCols()];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        configureGame();
        showStats();
        populateButtons();

    }



    private void showStats() {
        Game game = Game.getGameInstance();
        TextView scans = findViewById(R.id.tvScans);
        TextView found = findViewById(R.id.tvMinesFound);
        found.setText(getString(R.string.found_mines) + " " +game.getFound() + " of " + game.getNumMines());
        scans.setText(getString(R.string.scans_used, game.getNumScans()));
    }

    private void populateButtons() {
        Game game = Game.getGameInstance();


        //Button[][] grid = new Button[game.getMAP_ROW()][game.getMAP_COLUMN()];
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
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                final int finalRow = row;
                final int finalCol = col;
                button.setPadding(0, 0, 0,0);

                button.setOnClickListener(v -> {
                    scanAnimation(finalRow, finalCol);
                    gridButtonClicked(finalRow, finalCol);
                    showStats();
                    if(game.foundAllMines()){
                        alertMessage();

                    }
                });
                tableRow.addView(button);
                buttons[row][col] = button;

            }
        }
    }

    private void alertMessage(){
        FragmentManager manager = getSupportFragmentManager();
        AlertMessageFragment alert = new AlertMessageFragment();
        alert.show(manager, "AlertMessage");

    }

    private void configureGame(){
        Game game = Game.getGameInstance();

        game.setNumMines(OptionsScreen.getNumMines(this));
        OptionsScreen.sizeofDimensions(OptionsScreen.getBoardSize(this));
        if(game.getMAP_ROW() == 0 && game.getMAP_COLUMN() == 0){
            System.out.println("Nothing selected");
            game.setMapSize(4,6);
        }
        if(game.getNumMines() == 0){
            game.setNumMines(6);
        }
        game.setScans(0);
        game.setFound(0);
        game.setMap();

    }

    private void gridButtonClicked(int row, int col){
        boolean isNotMine = true;
        Button button = buttons[row][col];
        lockButtonSizes();
        if(game.mineWhere(row, col)) {
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            isNotMine = false;
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.halloween_pumpkin);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();

            button.setBackground(new BitmapDrawable(resource, scaledBitmap));


            refreshDisplay(row, col);

        }

        game.checkMap(row, col);

        int score = game.getSquareScore(row, col);
        if(isNotMine){
            System.out.println("this happened");
            button.setText(String.valueOf(score));
        }
    }

    private void refreshDisplay(int row, int col)  {
        for(int refresh_row = 0; refresh_row < game.getMAP_ROW(); refresh_row++) {
            int score = game.getSquareScore(refresh_row,col);
            if(score > 0 && refresh_row != row){

                game.deductScores(refresh_row, col);
                buttons[refresh_row][col].setText(String.valueOf(score-1));

            }
            //Animation animation= AnimationUtils.loadAnimation(this, R.anim.blink_anim);
            //buttons[refresh_row][col].startAnimation(animation);
        }

        for (int refresh_col = 0; refresh_col < game.getMAP_COLUMN(); refresh_col++) {
            int score = game.getSquareScore(row,refresh_col);
            if(score > 0 && refresh_col != col){

                game.deductScores(row, refresh_col);
                buttons[row][refresh_col].setText(String.valueOf(score-1));
            }
            //Animation animation= AnimationUtils.loadAnimation(this, R.anim.blink_anim);
            //buttons[row][refresh_col].startAnimation(animation);
        }

    }

    private void scanAnimation(int row, int col){
        int count = 0;
        for(int refresh = 1; refresh < game.getMAP_COLUMN(); refresh++) {

            Animation animation= AnimationUtils.loadAnimation(this, R.anim.blink_anim);
            animation.setStartOffset(count);
            if(row+refresh < game.getMAP_ROW()){
                buttons[row+refresh][col].startAnimation(animation);
            }
            if(row-refresh >= 0){
                buttons[row-refresh][col].startAnimation(animation);
            }
            if(col + refresh < game.getMAP_COLUMN()){
                buttons[row][refresh+col].startAnimation(animation);
            }
            if(col-refresh >= 0){
                buttons[row][col-refresh].startAnimation(animation);
            }
            count += 500;
        }

    }

    private void lockButtonSizes() {
        for(int row = 0; row < game.getMAP_ROW(); row++){
            for(int col = 0; col < game.getMAP_COLUMN(); col++){
                Button button = buttons[row][col];
                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, GameScreen.class);
    }
}
