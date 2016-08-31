package tictactoe.rules;

import org.junit.Before;
import org.junit.Test;
import tictactoe.Simulator;
import tictactoe.board.Board;
import tictactoe.board.SquareBoard;
import tictactoe.player.GamePlayer;
import tictactoe.player.MockGamePlayer;
import tictactoe.player.StringMarker;
import tictactoe.record.GameRecord;
import tictactoe.record.MoveHistory;
import static org.junit.Assert.*;

/**
 * Created by hanalee on 8/25/16.
 */
public class StandardRulesTest {
    private StandardRules rules;
    private Board board;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private MoveHistory record;

    @Before
    public void setUp() {
        board = new SquareBoard(3);
        playerOne = new MockGamePlayer();
        playerTwo = new MockGamePlayer();
        record = new GameRecord(board, playerOne, playerTwo);
        rules = new StandardRules();
    }

    @Test
    public void testBlockedRow() {
        Simulator.simulateGame(record, 1, 2);
        assertEquals(2, rules.getRowPlayers(record,
                new int[]{0, 1, 2}).size());
    }

    @Test
    public void testUnblockedRow() {
        Simulator.simulateGame(record, 1, 3, 2);
        assertEquals(1, rules.getRowPlayers(record,
                new int[]{0, 1, 2}).size());
    }

    @Test
    public void testZeroOccupiedRow() {
        assertEquals(0, rules.getRowOccupancy(record,
                new int[]{0, 1, 2}));
    }

    @Test
    public void testOneOccupiedRow() {
        Simulator.simulateGame(record, 1);
        assertEquals(1, rules.getRowOccupancy(record,
                new int[]{0, 1, 2}));
    }

    @Test
    public void testTwoOccupiedRow() {
        Simulator.simulateGame(record, 1, 2);
        assertEquals(2, rules.getRowOccupancy(record,
                new int[]{0, 1, 2}));
    }

    @Test
    public void testIsWinInWonGame() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 7, 8);
        assertTrue(rules.isGameWon(record));
    }

    @Test
    public void testIsNotWinInTiedGame() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        assertFalse(rules.isGameWon(record));
    }

    @Test
    public void testIsNotWinInUnfinishedGame() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0);
        assertFalse(rules.isGameWon(record));
    }

    @Test
    public void testIsTieInTiedGame() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        assertTrue(rules.isGameTied(record));
    }

    @Test
    public void testIsNotTieInWonGame() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 7, 8);
        assertFalse(rules.isGameTied(record));
    }

    @Test
    public void testIsNotTieInUnfinishedGame() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0);
        assertFalse(rules.isGameWon(record));
    }

}
