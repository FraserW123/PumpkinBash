package com.example.assignment3.model;
import java.util.Random;

public class Game {
    int MAP_ROW ;
    int MAP_COLUMN;
    private int numMines;
    boolean[][] minePlaced;


    public int[][] map;
    public int[][] squareScores;


    private static Game instance;
    private int scans;
    private int found = 0;
    Random random = new Random();


    private Game(int row, int col, int mines){

        setNumMines(mines);
        setMapSize(row, col);
        map = new int[MAP_ROW][MAP_COLUMN];
        squareScores = new int[MAP_ROW][MAP_COLUMN];
        minePlaced = new boolean[MAP_ROW][MAP_COLUMN];
        setMap();
    }
    public static Game getGameInstance(int row, int col, int mines){
        if(instance == null){
            instance = new Game(row,col,mines);
        }
        return instance;
    }



    public int getBoardSize(){return MAP_COLUMN*MAP_ROW;}
    public int getFound(){ return found; }
    public int getNumScans(){ return scans; }

    public void setMapSize(int row, int col){
        MAP_ROW = row;
        MAP_COLUMN = col;
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


