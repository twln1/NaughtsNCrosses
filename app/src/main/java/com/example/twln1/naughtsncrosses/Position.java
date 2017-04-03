package com.example.twln1.naughtsncrosses;

/**
 * Created by twln1 on 3/30/2017.
 */
import java.util.LinkedList;

public class Position {
    public int dim = 3;	// 3x3 board
    public char turn;	// x || o
    public char[] board;

    public Position(){
        this.board = "         ".toCharArray();
        this.turn = 'X';
    }

    public Position(String board){
        this.board = board.toCharArray();
        this.turn = 'X';
    }

    public Position(char[] board, char turn){
        //this.board = board;
        this.board = board.clone();
        this.turn = turn;
    }

    public Position(String string, char turn) {
        this.board = string.toCharArray();
        this.turn = turn;
    }

    public String toString(){
        return new String(board);
    }

    public Position move(int i){
        char[] cloneBoard = board.clone();
        cloneBoard[i] = turn;
        //return new Position(cloneBoard, turn == 'X' ? 'O' : 'X');
        if(turn == 'X')
            return new Position((cloneBoard), 'O');
        else
            return new Position((cloneBoard), 'X');
    }

    /***
     * @return	An array of the possible moves
     */
    public Integer [] possibleMoves(){
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 0; i < board.length; i++){
            if (board[i] == ' '){
                list.add(i);
            }
        }
        Integer[] array = new Integer[list.size()];
        list.toArray(array);
        return array;
    }

    /***
     * @param turn	Which player's turn are we testing
     * @param start	Starting position on the board
     * @param step	How many positions to move by
     * @return
     */
    public boolean checkLine(char turn, int start, int step){
        for (int i = 0; i < dim; i++){
            if(board[start + step*i] != turn){
                return false;
            }
        }
        return true;
    }
    /***
     *
     * @param turn	Which player are we testing a win for
     * @return		TRUE on a win condition
     */
    public boolean isWin(char turn){
        for(int i = 0; i < dim; i++){
            // Horizontal || Vertical win (e.g. 0 -> 3 -> 6)
            if(checkLine(turn, i*dim, 1) || checkLine(turn, i, dim))
                return true;
            // Diagonal win
            if ((checkLine(turn, dim-1, dim-1) || checkLine(turn, 0, dim+1)))
                return true;
        }
        return false;
    }

    /***
     * 		Method to calculate the minimax value of a move
     * @return the minimax value
     */
    public int minimax() {
        if(isWin('X'))
            return Integer.MAX_VALUE;
        if(isWin('O'))
            return Integer.MIN_VALUE;
        if(possibleMoves().length == 0)
            return 0;
        Integer minimaxVal = null;
        for(Integer index : possibleMoves()){
            Integer value = move(index).minimax();
            if(minimaxVal == null || turn == 'X' && minimaxVal < value || turn == 'O' && value < minimaxVal){
                minimaxVal = value;
            }
        }
		/*
		    Extra move, -1 penalty.
			Minimax is recursive so -1 each time for depth of search
		*/
        return minimaxVal + (turn == 'X' ? -1: 1);
    }

    public int bestPossibleMove() {
        Integer minimaxVal = null;
        int bestMove = -1;
        for(Integer index : possibleMoves()){
            Integer value = move(index).minimax();
            if(minimaxVal == null || turn == 'X' && minimaxVal < value || turn == 'O' && value < minimaxVal){
                minimaxVal = value;
                bestMove = index;
            }
        }
        return bestMove;
    }

    public boolean gameOver() {
        if(isWin('X') || isWin('O') || possibleMoves().length == 0)
            return true;
        return false;
    }

    public void clearBoard(){
        this.board = new char[8];

    }
}
