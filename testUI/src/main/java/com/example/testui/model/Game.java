package com.example.testui.model;
import java.util.Random;

public class Game {
    int MAP_ROW ;
    int MAP_COLUMN;
    public int numMines;
    boolean[] minePlaced;


    public int[] map;
    public int[] squareScores;

    private int scans;
    private int found = 0;
    Random random = new Random();


    public Game(int size, int mines){
        setMapSize(size);
        setNumMines(mines);

        map = new int[MAP_COLUMN*MAP_ROW];
        squareScores = new int[MAP_COLUMN*MAP_ROW];
        minePlaced = new boolean[MAP_COLUMN*MAP_ROW];
        setMap();

    }

    public int getBoardSize(){return MAP_COLUMN*MAP_ROW;}
    public int getFound(){ return found; }
    public int getNumScans(){ return scans; }

    public void setMapSize(int choice){
        switch (choice) {
            case 1:
                MAP_ROW = 4;
                MAP_COLUMN = 6;
                break;
            case 2:
                MAP_ROW = 5;
                MAP_COLUMN = 10;
                break;
            case 3:
                MAP_ROW = 6;
                MAP_COLUMN = 15;
                break;
            default:
                System.out.println("invalid choice defaulting to 4x6");
                MAP_ROW = 4;
                MAP_COLUMN = 6;
                break;
        }
    }


    public void setNumMines(int mines){

        switch (mines) {
            case 1:
                numMines = 6;
                break;
            case 2:
                numMines = 10;
                break;
            case 3:
                numMines = 15;
                break;
            case 4:
                numMines = 20;
                break;
            default:
                System.out.println("Invalid choice defaulting to 6 mines");
                numMines = 6;
        }
        //numMines = mines;



    }

    public void getBoard(){
        System.out.println("board size = " + squareScores.length);
        for(int i = 0; i<squareScores.length; i++){
            if(i >= MAP_COLUMN && i%MAP_COLUMN == 0){
                System.out.println();
            }
            if(map[i] != 0 && !minePlaced[i]){
                System.out.print("* ");
            }
            else{
                System.out.print(squareScores[i] + " ");
            }
        }
        System.out.println();
    }

    public void setMap(){

        for(int i = 0; i<numMines; i++){
            int setIndex = random.nextInt(MAP_COLUMN*MAP_ROW);
            while(map[setIndex] != 0){
                setIndex = random.nextInt(MAP_COLUMN*MAP_ROW);
                //System.out.println("Repeat number replaced with " + setIndex);
            }
            //System.out.println("Placed mine at position " + setIndex);
            minePlaced[setIndex] = true;
            map[setIndex] = 1;
        }
    }

    public boolean foundAllMines(){
        return found == numMines;
    }

    public void checkMap(int index){
        int count = 0;

        if(index > 23){
            System.out.println("Position set to zero since invalid");
            index = 0;
        }

        int row = index;
        int col = index;

        if(index <=5){
            row = 0;
        }
        else{
            while(row%MAP_COLUMN != 0){
                row -= 1;
            }
            while(col-MAP_COLUMN >= 0){
                col -= MAP_COLUMN;
            }
        }

        System.out.println("The index is in row " + row/MAP_COLUMN);
        System.out.println("The index is in column " + col);
        if(map[index] != 0 && minePlaced[index]){
            System.out.println("Mine found on this square!");
            minePlaced[index] = false;
            found++;
        }


        // counting in the rows
        for(int i = row; i<(row+6); i++){
            if(map[i] != 0 && i!=index){
                count++;
            }
        }
        // counting in the columns
        for(int i = col; i<map.length; i+=6){
            if(map[i] != 0 && i!=index){
                count++;
            }
        }

        scans++;
        squareScores[index] = count;
        System.out.println("there are " + count + " mines near this square");

    }
}
