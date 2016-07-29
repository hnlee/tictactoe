package tictactoe;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import java.util.Collections;

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
        playerTwo = new GamePlayer();
    }

    @Test
    public void testNewMove() {
        record.newMove(1, playerOne);
        assertEquals(1, record.getLastMove());
        assertEquals(playerOne, record.getLastPlayer());
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

    @Test
    public void testWhoPlayedMove() {
        record.newMove(1, playerOne);
        assertEquals(playerOne, record.whoPlayedMove(1));
    }

    @Test
    public void testGetAllMoves() {
        record.newMove(1, playerOne);
        record.newMove(2, playerTwo);
        ArrayList<Integer> allMoves = new ArrayList<Integer>();
        allMoves.add(1);
        allMoves.add(2);
        Collections.sort(allMoves);
        assertEquals(allMoves, record.getAllMoves());
    }
}
