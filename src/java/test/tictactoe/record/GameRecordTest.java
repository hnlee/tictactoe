package tictactoe.record;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import tictactoe.Simulator;
import tictactoe.board.Board;
import tictactoe.board.SquareBoard;
import tictactoe.player.GamePlayer;
import tictactoe.player.MockGamePlayer;
import tictactoe.player.StringMarker;
import tictactoe.record.GameRecord;
import tictactoe.record.MoveHistory;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Collections;

/**
 * Created by hanalee on 7/28/16.
 */
public class GameRecordTest {

    private Board board;
    private GameRecord record;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;

    @Before
    public void setUp() {
        board = new SquareBoard(3);
        playerOne = new MockGamePlayer();
        playerTwo = new MockGamePlayer();
        record = new GameRecord(board, playerOne, playerTwo);
    }

    @Test
    public void testNewMove() {
        record.newMove(1, playerOne);
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
        assertEquals(playerOne, record.whoPlayedMove(1).get());
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
        Simulator.simulateGame(record, 1);
        assertEquals(playerOne, record.getLastPlayer());
    }

    @Test
    public void testGetLastPlayerAfterTwoMoves() {
        Simulator.simulateGame(record, 1, 2);
        assertEquals(playerTwo, record.getLastPlayer());
    }

    @Test
    public void testGetMovesByPlayer() {
        Simulator.simulateGame(record, 1, 2);
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
        Simulator.simulateGame(record, 1, 2);
        MoveHistory newRecord = record.copyRecord();
        assertNotEquals(newRecord, record);
        assertEquals(newRecord.getMovesByPlayer().keySet(),
                     record.getMovesByPlayer().keySet());
    }

    @Test
    public void testAddNewMoveToCopy() {
        Simulator.simulateGame(record, 1, 2);
        MoveHistory newRecord = record.copyRecord();
        assertEquals(newRecord.getMovesByPlayer().get(playerOne),
                     record.getMovesByPlayer().get(playerOne));
        newRecord.newMove(0, playerOne);
        assertNotEquals(newRecord.getMovesByPlayer().get(playerOne),
                        record.getMovesByPlayer().get(playerOne));
        assertEquals(2, newRecord.getMovesByPlayer().get(playerOne).size());
        assertEquals(1, record.getMovesByPlayer().get(playerOne).size());
    }
}
