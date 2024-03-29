/**
 * Used to setup the random pumpkins
 * and used to check how many pumpkins are in each row and column
 */
package com.example.assignment3.model;
import java.util.Random;

public class Game {
    int MAP_ROW = 0;
    int MAP_COLUMN = 0;
    private int numMines;
    private boolean[][] minePlaced;

    private int[][] map;
    private int[][] squareScores;

    private static Game instance;
    private int scans;
    private int found = 0;
    Random random = new Random();

    private Game(){

    }
    public static Game getGameInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public int getBoardSize(){return MAP_COLUMN*MAP_ROW;}

    public int getNumMines() {
        return numMines;
    }

    public int getSquareScore(int row, int col){
        return squareScores[row][col];
    }

    public int getFound(){ return found; }
    public boolean foundAllMines(){
        return found == numMines;
    }
    public int getNumScans(){ return scans; }
    public int getMAP_ROW(){
        return MAP_ROW;
    }
    public int getMAP_COLUMN(){
        return MAP_COLUMN;
    }

    public void setScans(int scans){this.scans = scans;}
    public void setFound(int found){this.found = found;}

    public void setMapSize(int row, int col){
        MAP_ROW = row;
        MAP_COLUMN = col;
        map = new int[MAP_ROW][MAP_COLUMN];
        squareScores = new int[MAP_ROW][MAP_COLUMN];
        minePlaced = new boolean[MAP_ROW][MAP_COLUMN];
    }

    public void setNumMines(int mines){
        numMines = mines;
    }

    public void setMap(){

        for(int i = 0; i<numMines; i++){
            int setMineRow = random.nextInt(MAP_ROW);
            int setMineCol = random.nextInt(MAP_COLUMN);
            while(map[setMineRow][setMineCol] != 0){
                setMineRow = random.nextInt(MAP_ROW);
                setMineCol = random.nextInt(MAP_COLUMN);

            }
            minePlaced[setMineRow][setMineCol] = true;
            map[setMineRow][setMineCol] = 1;
        }
    }

    public void checkMap(int row, int col){

        int count = 0;

        if(minePlaced[row][col]){ // found the mine
            found++;
            minePlaced[row][col] = false;

        }else{
            //search the column
            for(int i = 0; i<MAP_ROW; i++){
                if(map[i][col] == 1 && minePlaced[i][col]){
                    count++;
                }
            }
            //search row
            for(int i = 0; i<MAP_COLUMN; i++){
                if(map[row][i] == 1 && minePlaced[row][i]){
                    count++;
                }
            }
            if(squareScores[row][col] == 0 && map[row][col] != -1){ // to scan squares only once
                scans++;
            }
            map[row][col] = -1; // indicates square has been scanned
            squareScores[row][col] = count;


        }

    }

    public boolean mineWhere(int row, int col){
        if(minePlaced[row][col]){
            return true;
        }
        else{
            return false;
        }
    }

    public void deductScores(int row, int col){
        int score = squareScores[row][col];
        squareScores[row][col] = score - 1;
    }


}


