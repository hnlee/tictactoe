package tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.Assert.*;

/**
 * Created by hanalee on 8/2/16.
 */
public class GameAnalyzerTest {
    private GameAnalyzer analyzer;
    private Board board;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private GameRecord record;

    @Before
    public void setUp() {
        board = new SquareBoard(3);
        PlayerMarker xMarker = new StringMarker("X");
        PlayerMarker oMarker = new StringMarker("O");
        playerOne = new MockGamePlayer(xMarker);
        playerTwo = new MockGamePlayer(oMarker);
        record = new GameRecord(board);
        analyzer = new GameAnalyzer(playerOne, playerTwo);
    }

    @Test
    public void testStartAnalyzer() {
        assertNotNull(analyzer);
    }

    @Test
    public void testBlockedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1, 2);
        assertEquals(2, analyzer.getRowPlayers(record,
                new int[]{0, 1, 2}).size());
    }

    @Test
    public void testUnblockedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1, 3, 2);
        assertEquals(1, analyzer.getRowPlayers(record,
                new int[]{0, 1, 2}).size());
    }

    @Test
    public void testZeroOccupiedRow() {
        assertEquals(0, analyzer.getRowOccupancy(record,
                new int[]{0, 1, 2}));
    }

    @Test
    public void testOneOccupiedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1);
        assertEquals(1, analyzer.getRowOccupancy(record,
                new int[]{0, 1, 2}));
    }

    @Test
    public void testTwoOccupiedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1, 2);
        assertEquals(2, analyzer.getRowOccupancy(record,
                new int[]{0, 1, 2}));
    }

    @Test
    public void testIsWinInWonGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0, 7, 8);
        assertTrue(analyzer.isGameWon(record));
    }

    @Test
    public void testIsNotWinInTiedGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        assertFalse(analyzer.isGameWon(record));
    }

    @Test
    public void testIsNotWinInUnfinishedGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0);
        assertFalse(analyzer.isGameWon(record));
    }

    @Test
    public void testIsTieInTiedGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        assertTrue(analyzer.isGameTied(record));
    }

    @Test
    public void testIsNotTieInWonGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0, 7, 8);
        assertFalse(analyzer.isGameTied(record));
    }

    @Test
    public void testIsNotTieInUnfinishedGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0);
        assertFalse(analyzer.isGameWon(record));
    }

    @Test
    public void testScoreTyingSpaceWithOneMoveLeft() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0, 8);
        assertEquals(0, analyzer.scoreMove(record, 7, playerOne));
    }

    @Test
    public void testScoreWinningSpaceWithOneMoveLeft() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0, 7);
        assertEquals(1, analyzer.scoreMove(record, 8, playerOne));
    }

    @Test
    public void testScoreWinningSpaceWithReversePlayerOrder() {
        Simulator.simulateGame(playerTwo, playerOne, record,
                4, 1, 5, 3, 6, 2, 0, 7);
        assertEquals(1, analyzer.scoreMove(record, 8, playerTwo));
    }

    @Test
    public void testGetNextPlayer() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1);
        assertEquals(playerTwo, analyzer.getNextPlayer(record));
        record.newMove(2, playerTwo);
        assertEquals(playerOne, analyzer.getNextPlayer(record));
    }

    @Test
    public void testScoreLosingSpace() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0);
        assertEquals(-1, analyzer.scoreMove(record, 7, playerTwo));
    }

    @Test
    public void testScoreWinningSpaceWithTwoMovesLeft() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 7);
        assertEquals(1, analyzer.scoreMove(record, 0, playerTwo));
    }

    @Test
    public void testScoreTyingSpaceWithTwoMovesLeft() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 7);
        assertEquals(0, analyzer.scoreMove(record, 8, playerTwo));
    }

    @Test
    public void testGetEmptySpacesOnBoard() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0, 8);
        ArrayList<Integer> emptySpaces = new ArrayList<Integer>();
        emptySpaces.add(7);
        assertEquals(emptySpaces, analyzer.getEmptySpaces(record));
    }

    @Test
    public void testScoreWinningSpaceAfterFourMoves() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 0);
        assertEquals(1, analyzer.scoreMove(record, 3, playerOne));
    }

    @Test
    public void testScoreLosingSpaceAfterThreeMoves() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5);
        assertEquals(-1, analyzer.scoreMove(record, 0, playerTwo));
    }

    @Test
    public void testScoreNextMovesInMidGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 2, 6);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        scores.put(0, 0);
        scores.put(7, -1);
        scores.put(8, 1);
        assertEquals(scores, analyzer.scoreNextMoves(record));
    }

    @Test
    public void testForkingMove() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 7);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        scores.put(0, 1);
        scores.put(2, 0);
        scores.put(6, 0);
        scores.put(8, 0);
        assertEquals(scores, analyzer.scoreNextMoves(record));
    }

    @Test
    public void testBlockForkingMove() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        scores.put(0, -1);
        scores.put(2, 0);
        scores.put(7, -1);
        scores.put(8, -1);
        assertEquals(scores, analyzer.scoreNextMoves(record));
    }
}
