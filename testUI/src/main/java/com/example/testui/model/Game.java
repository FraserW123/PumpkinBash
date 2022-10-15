package com.example.testui.model;
import java.util.Random;

public class Game {
    final int MAP_ROW = 3;
    final int MAP_COLUMN = 5;
    public int numMines = 2;
    boolean[] mineFound;
    public int[] map;
    public int[] squareScores;

    int found = 0;
    Random random = new Random();


    public Game(){
        mineFound = new boolean[]{true, true, true, true, true, true,
                                true, true, true, true, true, true,
                                true, true, true, true, true, true,
                                true, true, true, true, true, true,};

        map = new int[]{0,0,0,0,0,0,
                        0,0,0,0,0,0,
                        0,0,0,0,0,0,
                        0,0,0,0,0,0};


        squareScores = new int[]{0,0,0,0,0,0,
                                 0,0,0,0,0,0,
                                 0,0,0,0,0,0,
                                 0,0,0,0,0,0};
    }

    public void getBoard(){
        for(int i = 0; i<squareScores.length; i++){
            if(squareScores[i] == 1){
                System.out.print("* ");
            }
            else{
                System.out.print(squareScores[i] + " ");
            }
            if(i == (MAP_ROW+1)){
                System.out.println("");
            }

        }
        System.out.println("");
    }

    public void setMap(){

        for(int i = 0; i<numMines; i++){
            int setIndex = random.nextInt(6);
            while(map[setIndex] != 0){
                setIndex = random.nextInt(6);
            }
            System.out.println("Placed mine at position " + setIndex);
            mineFound[setIndex] = false;
            map[setIndex] = 1;
        }
    }

    public boolean foundAllMines(){
        return found == numMines;
    }

    public void checkMap(int index){
        int count = 0;
        if(map[index] != 0 && !mineFound[index]){
            System.out.println("Mine found on this square!");
            mineFound[index] = true;
            found++;
        }
        for(int i = 0; i<map.length; i++){
            if(map[i] != 0 && i!=index){
                //System.out.println("found a mine at square " + i);
                count++;
            }
        }
        squareScores[index] = count;
        System.out.println("there are " + count + " mines near this square");

    }
}
