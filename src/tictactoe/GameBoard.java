package tictactoe;

import java.util.ArrayList;

public class GameBoard {
    private int numRows;
    private int[] spaces;

    GameBoard() {
        this.numRows = 3;
        this.spaces = new int[9];
    }

    GameBoard(int numRows) {
        this.numRows = numRows;
        this.spaces = new int[numRows * numRows];
    }

    public int getNumRows() {
        return numRows;
    }

    public int[] getSpaces() { return spaces; }
}
