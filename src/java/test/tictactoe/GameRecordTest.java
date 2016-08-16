package tictactoe;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Hashtable;
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
        board = new GameBoard(3);
        record = new GameRecord(board);
        PlayerMarker xMarker = new StringMarker("X");
        PlayerMarker oMarker = new StringMarker("O");
        playerOne = new MockGamePlayer(xMarker);
        playerTwo = new MockGamePlayer(oMarker);
    }

    @Test
    public void testGetBoard() {
        assertEquals(board, record.getBoard());
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

    @Test
    public void testGetLastPlayerAfterZeroMoves() {
        assertNull(record.getLastPlayer());
    }

    @Test
    public void testGetLastPlayerAfterOneMove() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                               1);
        assertEquals(playerOne, record.getLastPlayer());
    }

    @Test
    public void testGetLastPlayerAfterTwoMoves() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                               1, 2);
        assertEquals(playerTwo, record.getLastPlayer());
    }

    @Test
    public void testGetMovesByPlayer() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                               1, 2);
        Hashtable<GamePlayer, ArrayList<Integer>> movesByPlayer;
        movesByPlayer = new Hashtable<GamePlayer, ArrayList<Integer>>();
        movesByPlayer.put(playerOne, new ArrayList<Integer>());
        movesByPlayer.put(playerTwo, new ArrayList<Integer>());
        movesByPlayer.get(playerOne).add(1);
        movesByPlayer.get(playerTwo).add(2);
        assertEquals(movesByPlayer, record.getMovesByPlayer());
    }

    @Test
    public void testCopyRecord() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                1, 2);
        GameRecord newRecord = record.copyRecord();
        assertNotEquals(newRecord, record);
        assertEquals(newRecord.getMovesByPlayer().keySet(),
                     record.getMovesByPlayer().keySet());
    }

    @Test
    public void testAddNewMoveToCopy() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                1, 2);
        GameRecord newRecord = record.copyRecord();
        assertEquals(newRecord.getMovesByPlayer().get(playerOne),
                     record.getMovesByPlayer().get(playerOne));
        newRecord.newMove(0, playerOne);
        assertNotEquals(newRecord.getMovesByPlayer().get(playerOne),
                        record.getMovesByPlayer().get(playerOne));
        assertEquals(2, newRecord.getMovesByPlayer().get(playerOne).size());
        assertEquals(1, record.getMovesByPlayer().get(playerOne).size());
    }
}
