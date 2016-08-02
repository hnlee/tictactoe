package tictactoe;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameBoardTest {

    @Test
    public void testCreateTwoByTwoBoard() {
        GameBoard board = new GameBoard(2);
        assertNotNull(board);
        assertEquals(2, board.getNumRows());
        assertEquals(4, board.getSpaces().size());
    }

    @Test
    public void testCreateThreeByThreeBoard() {
        GameBoard board = new GameBoard(3);
        assertEquals(3, board.getNumRows());
        assertEquals(9, board.getSpaces().size());
    }

    @Test
    public void testGetHorizontalRows() {
        GameBoard board = new GameBoard(2);
        int[][] rows = {{0, 1}, {2, 3}};
        assertArrayEquals(rows, board.getHorizontalRows());
    }

    @Test
    public void testGetVerticalRows() {
        GameBoard board = new GameBoard(2);
        int[][] rows = {{0, 2}, {1, 3}};
        assertArrayEquals(rows, board.getVerticalRows());
    }

    @Test
    public void testGetDiagonalRows() {
        GameBoard board = new GameBoard(2);
        int[][] rows = {{0, 3}, {1, 2}};
        assertArrayEquals(rows, board.getDiagonalRows());
    }

    @Test
    public void testTwoByTwoRows() {
        GameBoard board = new GameBoard(2);
        int[][] rows = {{0, 1}, {2, 3}, {0, 2}, {1, 3}, {0, 3}, {1, 2}};
        assertArrayEquals(rows, board.getRows());
    }

    @Test
    public void testThreeByThreeRows() {
        GameBoard board = new GameBoard(3);
        int[][] rows = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}};
        assertArrayEquals(rows, board.getRows());
    }

    @Test
    public void testSpaceInRow() {
        GameBoard board = new GameBoard(3);
        assertTrue(board.isSpaceInRow(0, new int[] {0, 1, 2}));
    }

    @Test
    public void testSpaceNotInRow() {
        GameBoard board = new GameBoard(3);
        assertFalse(board.isSpaceInRow(0, new int[] {3, 4, 5}));
    }

    @Test
    public void testGetRowsWithSpaceOne() {
        GameBoard board = new GameBoard(3);
        int[][] rowsWithOne = new int[][] {{0, 1, 2}, {1, 4, 7}};
        assertArrayEquals(rowsWithOne, board.getRowsWithSpace(1));
    }

    @Test
    public void testGetRowsWithSpaceZero() {
        GameBoard board = new GameBoard(3);
        int[][] rowsWithZero = new int[][] {{0, 1, 2}, {0, 3, 6}, {0, 4, 8}};
        assertArrayEquals(rowsWithZero, board.getRowsWithSpace(0));
    }
}
