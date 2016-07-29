package tictactoe;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {
    private int numRows;
    private ArrayList<Integer> spaces;
    private int[][] rows;

    GameBoard(int numRows) {
        this.numRows = numRows;
        this.spaces = new ArrayList<Integer>();
        for (int space = 0; space < numRows * numRows; space++) {
            this.spaces.add(space);
        }
        Collections.sort(spaces);
        setRows();
    }

    public int getNumRows() {
        return numRows;
    }

    public ArrayList<Integer> getSpaces() { return spaces; }

    public void setRows() {
        rows = new int[2 * (numRows + 1)][numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                rows[i][j] = (i * numRows) + j;
            }
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                rows[i + numRows][j] = i + (j * numRows);
            }
        }
        for (int j = 0; j < numRows; j++) {
            rows[rows.length - 2][j] = (numRows + 1) * j;
            rows[rows.length - 1][j] = (numRows - 1) * (j + 1);
        }
    }

    public int[][] getRows() {
        return rows;
    }
}
