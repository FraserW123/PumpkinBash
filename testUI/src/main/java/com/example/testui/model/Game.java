package com.example.testui.model;
import java.util.Random;

public class Game {
    int MAP_ROW ;
    int MAP_COLUMN;
    private int numMines;
    boolean[][] minePlaced;


    public int[][] map;
    public int[][] squareScores;

    private int scans;
    private int found = 0;
    Random random = new Random();


    private Game(int size, int mines){

        setNumMines(mines);
        setMapSize(size);
        map = new int[MAP_ROW][MAP_COLUMN];
        squareScores = new int[MAP_ROW][MAP_COLUMN];
        minePlaced = new boolean[MAP_ROW][MAP_COLUMN];
        setMap();
    }
    public static Game getGameInstance(int size, int mines){
        return new Game(size, mines);
    }



    public int getBoardSize(){return MAP_COLUMN*MAP_ROW;}
    public int getFound(){ return found; }
    public int getNumScans(){ return scans; }

    public void setMapSize(int choice){
        if (choice == 1) {
            MAP_ROW = 4;
            MAP_COLUMN = 6;
        } else if (choice == 2) {
            MAP_ROW = 5;
            MAP_COLUMN = 10;
        } else if (choice == 3) {
            MAP_ROW = 6;
            MAP_COLUMN = 15;
        } else {
            System.out.println("invalid choice defaulting to 4x6");
            MAP_ROW = 4;
            MAP_COLUMN = 6;
        }
    }


    public void setNumMines(int mines){
        numMines = mines;
    }

    public void getBoard(){
        System.out.println("board size = " + getBoardSize());
        for(int row = 0; row<MAP_ROW; row++){
            for(int col = 0; col < MAP_COLUMN; col++){
                if(map[row][col] != 0 && !minePlaced[row][col]){
                    System.out.print("* ");
                }
                else{
                    System.out.print(squareScores[row][col] + " ");
                }
            }
            System.out.println();
        }

    }

    public void setMap(){

        for(int i = 0; i<numMines; i++){
            int setMineRow = random.nextInt(MAP_ROW);
            int setMineCol = random.nextInt(MAP_COLUMN);
            while(map[setMineRow][setMineCol] != 0){
                setMineRow = random.nextInt(MAP_ROW);
                setMineCol = random.nextInt(MAP_COLUMN);
                //System.out.println("Repeat number replaced with " + setIndex);
            }
            System.out.println("Placed mine at position " + setMineRow +" x " + setMineCol);
            minePlaced[setMineRow][setMineCol] = true;
            map[setMineRow][setMineCol] = 1;
        }
    }

    public boolean foundAllMines(){
        return found == numMines;
    }

    public void checkMap(int row, int col){

        int count = 0;

        if(minePlaced[row][col]){
            found++;
            minePlaced[row][col] = false;
        }else{
            //search the column
            for(int i = 0; i<MAP_ROW; i++){
                if(map[i][col] == 1){
                    count++;
                }
            }
            //search row
            for(int i = 0; i<MAP_COLUMN; i++){
                if(map[row][i] == 1){
                    count++;
                }
            }
            scans++;

        }
        System.out.println("count " + count);
        squareScores[row][col] = count;

    }


}


