package tictactoe.record;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import tictactoe.Simulator;
import tictactoe.board.Board;
import tictactoe.board.SquareBoard;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Collections;
import java.util.List;

/**
 * Created by hanalee on 7/28/16.
 */
public class MoveHistoryTest {

    private Board board;
    private MoveHistory record;

    @Before
    public void setUp() {
        board = new SquareBoard(3);
        record = new MoveHistory(board.getNumRows());
    }

    @Test
    public void testNewMove() {
        record.newMove(1);
        assertEquals(1, record.getLastPlayer());
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

    @Test
    public void testWhoPlayedMove() {
        record.newMove(1);
        Integer playerNum = 1;
        assertEquals(playerNum, record.whoPlayedMove(1).get());
    }

    @Test
    public void testGetAllMoves() {
        record.newMove(1);
        record.newMove(2);
        List<Integer> allMoves = new ArrayList<Integer>();
        allMoves.add(1);
        allMoves.add(2);
        Collections.sort(allMoves);
        assertEquals(allMoves, record.getAllMoves());
    }

    @Test
    public void testGetLastPlayerAfterZeroMoves() {
        assertEquals(0, record.getLastPlayer());
    }

    @Test
    public void testGetLastPlayerAfterOneMove() {
        Simulator.simulateGame(record, 1);
        assertEquals(1, record.getLastPlayer());
    }

    @Test
    public void testGetLastPlayerAfterTwoMoves() {
        Simulator.simulateGame(record, 1, 2);
        assertEquals(2, record.getLastPlayer());
    }

    @Test
    public void testGetMovesByPlayer() {
        Simulator.simulateGame(record, 1, 2);
        Hashtable<Integer, List<Integer>> movesByPlayer;
        movesByPlayer = new Hashtable<Integer, List<Integer>>();
        movesByPlayer.put(1, new ArrayList<Integer>());
        movesByPlayer.put(2, new ArrayList<Integer>());
        movesByPlayer.get(1).add(1);
        movesByPlayer.get(2).add(2);
        assertEquals(movesByPlayer, record.getMovesByPlayer());
    }

    @Test
    public void testCopyRecord() {
        Simulator.simulateGame(record, 1, 2);
        MoveHistory newRecord = record.copyRecord();
        assertNotEquals(newRecord, record);
        assertEquals(newRecord.getMovesByPlayer().get(1),
                     record.getMovesByPlayer().get(1));
        assertEquals(newRecord.getMovesByPlayer().get(2),
                     record.getMovesByPlayer().get(2));
    }

    @Test
    public void testAddNewMoveToCopy() {
        Simulator.simulateGame(record, 1, 2);
        MoveHistory newRecord = record.copyRecord();
        newRecord.newMove(0);
        assertNotEquals(newRecord.getMovesByPlayer().get(1),
                        record.getMovesByPlayer().get(1));
        assertEquals(2, newRecord.getMovesByPlayer().get(1).size());
        assertEquals(1, record.getMovesByPlayer().get(2).size());
    }
}
