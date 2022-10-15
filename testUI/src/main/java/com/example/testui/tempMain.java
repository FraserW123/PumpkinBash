package com.example.testui;

import com.example.testui.model.Game;

import java.util.Scanner;
import java.util.Random;
public class tempMain {
    public static void main(String[] args){
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();


        game.setMap();
        while(!game.foundAllMines()){
            System.out.println("Select a position (0-24)");
            int choice = scanner.nextInt();
            game.checkMap(choice);
            game.getBoard();

        }
        System.out.println("You found all the mines!");


        //System.out.println("Hello world " + indexSet);
    }
}