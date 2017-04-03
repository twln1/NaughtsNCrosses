package com.example.twln1.naughtsncrosses;

import android.widget.Button;

import java.util.ArrayList;

public class Game {
    Position position = new Position();
    public String winner;
    public boolean gameOver = false;

    protected void move(int index) {
        position = position.move(index);
    }

    public boolean makeMove(int id, ArrayList<Button> buttons) {
        buttons.get(id).setText("" + position.turn);
        move(id);
        if (!position.gameOver()) {
            int bestMove = position.bestPossibleMove();
            buttons.get(bestMove).setText("" + position.turn);
            move(bestMove);
        }
        if (position.gameOver()) {
            gameOver = true;
            if (position.isWin('x')) {
                winner = "X";
            }
            if (position.isWin('O')) {
                winner = "O";
            }
            else {
                winner = "DRAW, nobody";
            }

        }
        return gameOver;
    }
}
