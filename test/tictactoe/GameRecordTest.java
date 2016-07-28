package tictactoe;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * Created by hanalee on 7/28/16.
 */
public class GameRecordTest {

    private GameBoard board;
    private GameRecord record;

    @Before
    public void setUp() {
        board = new GameBoard();
        record = new GameRecord(board);
    }

    @Test
    public void testNewMove() {
        record.newMove(1);
        assertEquals(1, record.getLastMove());
    }

    @Test
    public void testValidMove() {
        assertTrue(record.isValidMove(1));
    }

    @Test
    public void testMoveOutOfRange() {
        assertFalse(record.isValidMove(20));
    }

    @Test
    public void testMoveInOccupiedSpace() {
        record.newMove(1);
        assertFalse(record.isValidMove(1));
    }

}
