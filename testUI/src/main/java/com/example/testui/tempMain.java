package com.example.testui;

import com.example.testui.model.Game;

import java.util.Scanner;
public class tempMain {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("What size of board?");
        System.out.println("(1) 4x6");
        System.out.println("(2) 5x10");
        System.out.println("(3) 6x15");
        choice = scanner.nextInt();


        System.out.println("How many mines to play with?");
        System.out.println("(1) 6");
        System.out.println("(2) 10");
        System.out.println("(3) 15");
        System.out.println("(4) 20");
        int mines = scanner.nextInt();

        Game game = Game.getGameInstance(choice, mines);

        while(!game.foundAllMines()){
            System.out.println("row?");
            int row = scanner.nextInt();
            System.out.println("col?");
            int col = scanner.nextInt();
            game.checkMap(row,col);
            game.getBoard();
            System.out.println("Scans: "+game.getNumScans());
            System.out.println("Mines found " + game.getFound());


        }
        System.out.println("You found all the mines!");

    }
}