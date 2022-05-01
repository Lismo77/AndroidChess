package com.example.chess.objects;

import java.util.ArrayList;

public class storage {
    private ArrayList <ArrayList<ChessBoard>> prevGames;

    public storage(ArrayList prevGames){
        prevGames = new ArrayList<ArrayList<ChessBoard>>();
    }

    private void store(ArrayList gameStates){
        prevGames.add(gameStates);
    }
    public ArrayList getStorage(){
        return prevGames;
    }

}
