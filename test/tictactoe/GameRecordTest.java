package tictactoe;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * Created by hanalee on 7/28/16.
 */
public class GameRecordTest {

    @Test
    public void testNewMove() {
        GameBoard board = new GameBoard();
        GameRecord record = new GameRecord(board);
        record.newMove(1);
        assertEquals(1, record.getLastMove());
    }

    @Test
    public void testMoveOutOfRange() {
        GameBoard board = new GameBoard();
        GameRecord record = new GameRecord(board);
        assertFalse(record.isValidMove(20));
    }

}
