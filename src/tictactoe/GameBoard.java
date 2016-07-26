package tictactoe;

import java.util.ArrayList;

public class GameBoard {
    private ArrayList<String> spaces;
    private int numRows;

    GameBoard() {
        numRows = 3;
        spaces = new ArrayList<String>();
        for (int n = 0; n < (numRows * numRows); n++) {
            spaces.add("");
        }
        
    }

    public ArrayList<String> getBoard() {
        return spaces;
    }

    public int getNumRows() {
        return numRows;
    }

    public int[][] getRows() {
        int[][] rows = new int[(2 * (numRows + 1))][numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                rows[i][j] = 3 * i + j;
            }
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                rows[(i + numRows)][j] = 3 * j + i;
            }
        }
        for (int j = 0; j < numRows; j++) {
            rows[2 * numRows][j] = j * (numRows + 1);
            rows[2 * numRows + 1][j] = (j + 1) * 
                                         (numRows - 1);
        }
        return rows;
    }

    public String getSpace(int space) {
        return spaces.get(space);
    }
    
    public void move(int space, String marker) {
        spaces.set(space, marker);
    }
}
