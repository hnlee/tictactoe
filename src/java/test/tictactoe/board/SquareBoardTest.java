package tictactoe.board;

import static org.junit.Assert.*;
import org.junit.Test;
import tictactoe.board.SquareBoard;

public class SquareBoardTest {

    @Test
    public void testCreateTwoByTwoBoard() {
        SquareBoard board = new SquareBoard(2);
        assertNotNull(board);
        assertEquals(2, board.getNumRows());
        assertEquals(4, board.getSpaces().size());
    }

    @Test
    public void testCreateThreeByThreeBoard() {
        SquareBoard board = new SquareBoard(3);
        assertEquals(3, board.getNumRows());
        assertEquals(9, board.getSpaces().size());
    }

    @Test
    public void testGetHorizontalRows() {
        SquareBoard board = new SquareBoard(2);
        int[][] rows = {{0, 1}, {2, 3}};
        assertArrayEquals(rows, board.getHorizontalRows());
    }

    @Test
    public void testGetVerticalRows() {
        SquareBoard board = new SquareBoard(2);
        int[][] rows = {{0, 2}, {1, 3}};
        assertArrayEquals(rows, board.getVerticalRows());
    }

    @Test
    public void testGetDiagonalRows() {
        SquareBoard board = new SquareBoard(2);
        int[][] rows = {{0, 3}, {1, 2}};
        assertArrayEquals(rows, board.getDiagonalRows());
    }

    @Test
    public void testTwoByTwoRows() {
        SquareBoard board = new SquareBoard(2);
        int[][] rows = {{0, 1}, {2, 3}, {0, 2}, {1, 3}, {0, 3}, {1, 2}};
        assertArrayEquals(rows, board.getRows());
    }

    @Test
    public void testThreeByThreeRows() {
        SquareBoard board = new SquareBoard(3);
        int[][] rows = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}};
        assertArrayEquals(rows, board.getRows());
    }

    @Test
    public void testSpaceInRow() {
        SquareBoard board = new SquareBoard(3);
        assertTrue(board.isSpaceInRow(0, new int[] {0, 1, 2}));
    }

    @Test
    public void testSpaceNotInRow() {
        SquareBoard board = new SquareBoard(3);
        assertFalse(board.isSpaceInRow(0, new int[] {3, 4, 5}));
    }

    @Test
    public void testGetRowsWithSpaceOne() {
        SquareBoard board = new SquareBoard(3);
        int[][] rowsWithOne = new int[][] {{0, 1, 2}, {1, 4, 7}};
        assertArrayEquals(rowsWithOne, board.getRowsWithSpace(1));
    }

    @Test
    public void testGetRowsWithSpaceZero() {
        SquareBoard board = new SquareBoard(3);
        int[][] rowsWithZero = new int[][] {{0, 1, 2}, {0, 3, 6}, {0, 4, 8}};
        assertArrayEquals(rowsWithZero, board.getRowsWithSpace(0));
    }

    @Test
    public void testGetRowsWithSpaceFour() {
        SquareBoard board = new SquareBoard(3);
        int[][] rowsWithFour = new int[][] {{3, 4, 5}, {1, 4, 7},
                                            {0, 4, 8}, {2, 4, 6}};
        assertArrayEquals(rowsWithFour, board.getRowsWithSpace(4));
    }
}
