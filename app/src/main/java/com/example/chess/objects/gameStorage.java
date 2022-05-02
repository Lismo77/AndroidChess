package com.example.chess.objects;

import java.util.ArrayList;

public class gameStorage {

    private static ArrayList<ArrayList> prevGames;
    //private ArrayList <ArrayList<ChessBoard>> prevGames;

    public gameStorage(){
        prevGames = new ArrayList<>();
    }

    public static void store(ArrayList gameStates){
        prevGames.add(gameStates);
    }
    public ArrayList getStorage(){
        return (ArrayList) prevGames;
    }

    public ArrayList forward(ArrayList gameStates) {
        return prevGames.get(prevGames.indexOf(gameStates) + 1);
    }
    public ArrayList backward(ArrayList gameStates) {
        return prevGames.get(prevGames.indexOf(gameStates) - 1);
    }
}
