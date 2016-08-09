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

    public int[][] getHorizontalRows() {
        int[][] horizontalRows = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                horizontalRows[i][j] = (i * numRows) + j;
            }
        }
        return horizontalRows;
    }

    public int[][] getVerticalRows() {
        int[][] verticalRows = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                verticalRows[i][j] = i + (j * numRows);
            }
        }
        return verticalRows;
    }

    public int[][] getDiagonalRows() {
        int[][] diagonalRows = new int[2][numRows];
            for (int j = 0; j < numRows; j++) {
            diagonalRows[0][j] = (numRows + 1) * j;
            diagonalRows[1][j] = (numRows - 1) * (j + 1);
        }
        return diagonalRows;
    }

    public void setRows() {
        rows = new int[(numRows + 1) * 2][numRows];
        System.arraycopy(getHorizontalRows(), 0, rows, 0, numRows);
        System.arraycopy(getVerticalRows(), 0, rows, numRows, numRows);
        System.arraycopy(getDiagonalRows(), 0, rows, 2 * numRows, 2);
    }

    public int[][] getRows() {
        return rows;
    }

    public boolean isSpaceInRow(int space, int[] row) {
        for (int rowSpace : row) {
            if (space == rowSpace) {
                return true;
            }
        }
        return false;
    }

    public int[][] getRowsWithSpace(int space) {
        ArrayList<int[]> rowsWithSpace = new ArrayList<int[]>();
        for (int[] row : rows) {
            if (isSpaceInRow(space, row)) {
                rowsWithSpace.add(row);
            }
        }
        return rowsWithSpace.toArray(new int[rowsWithSpace.size()][numRows]);
    }

}
