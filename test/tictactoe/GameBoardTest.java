package tictactoe;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

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
    public void testTwoByTwoRows() {
        GameBoard board = new GameBoard(2);
        int[][] rows = {{0, 1}, {2, 3}, {0, 2}, {1, 3}, {0, 3}, {1, 2}};
        assertArrayEquals(rows[0], board.getRows()[0]);
        assertArrayEquals(rows[3], board.getRows()[3]);
        assertArrayEquals(rows[4], board.getRows()[4]);
        assertArrayEquals(rows[5], board.getRows()[5]);
        assertArrayEquals(rows, board.getRows());
    }

    @Test
    public void testThreeByThreeRows() {
        GameBoard board = new GameBoard(3);
        int[][] rows = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}};
        assertArrayEquals(rows[0], board.getRows()[0]);
        assertArrayEquals(rows[5], board.getRows()[5]);
        assertArrayEquals(rows[6], board.getRows()[6]);
        assertArrayEquals(rows[7], board.getRows()[7]);
    }
}
