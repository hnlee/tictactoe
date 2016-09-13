package tictactoe.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private int numRows;
    private int[][] grid;
    private List<Integer> spaces;

    public Board(int numRows) {
        this.numRows = numRows;
        grid = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numRows; j++)
                grid[i][j] = i * numRows + j;
    }

    public int getNumRows() {
        return numRows;
    }

    public List<Integer> getSpaces() {
        if (spaces == null) {
            spaces = new ArrayList<>();
            for (int i = 0; i < (numRows * numRows); i++) {
                spaces.add(i);
            }
        }
        return spaces;
    }

    public int[][] getRows() {
        int[][] rows = new int[numRows * 2 + 2][numRows];

        // Horizontal rows
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                rows[i][j] = grid[i][j];
                rows[i + numRows][j] = grid[j][i];
            }
            rows[numRows * 2][i] = grid[i][i];
            rows[numRows * 2 + 1][i] = grid[i][numRows - i - 1];
        }

        return rows;
    }

    public boolean isSpaceInRow(int space, int[] row) {
        return Arrays.stream(row)
                .anyMatch((rowSpace) -> rowSpace == space);
    }

}
