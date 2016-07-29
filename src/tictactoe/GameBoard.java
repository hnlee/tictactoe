package tictactoe;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {
    private int numRows;
    private ArrayList<Integer> spaces;

    GameBoard(int numRows) {
        this.numRows = numRows;
        this.spaces = new ArrayList<Integer>();
        for (int space = 0; space < numRows * numRows; space++) {
            this.spaces.add(space);
        }
        Collections.sort(spaces);
    }

    public int getNumRows() {
        return numRows;
    }

    public ArrayList<Integer> getSpaces() { return spaces; }
}
