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
    private GamePlayer playerOne;
    private GamePlayer playerTwo;

    @Before
    public void setUp() {
        board = new GameBoard();
        record = new GameRecord(board);
        playerOne = new GamePlayer();
    }

    @Test
    public void testNewMove() {
        record.newMove(1, playerOne);
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
        record.newMove(1, playerOne);
        assertFalse(record.isValidMove(1));
    }

}
