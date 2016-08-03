package tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by hanalee on 8/2/16.
 */
public class GameAnalyzerTest {
    private GameAnalyzer analyzer;
    private GameBoard board;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private GameRecord record;

    @Before
    public void setUp() {
        board = new GameBoard(3);
        playerOne = new GamePlayer();
        playerTwo = new GamePlayer();
        record = new GameRecord(board);
        analyzer = new GameAnalyzer(board, playerOne, playerTwo);
    }

    @Test
    public void testStartAnalyzer() {
        assertNotNull(analyzer);
    }

    @Test
    public void testBlockedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1, 2);
        assertEquals(true, analyzer.isRowBlocked(record,
                new int[] {0, 1, 2}));
    }

    @Test
    public void testUnblockedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1, 3, 2);
        assertEquals(false, analyzer.isRowBlocked(record,
                new int[] {0, 1, 2}));
    }

    @Test
    public void testZeroOccupiedRow() {
        assertEquals(0, analyzer.getRowOccupancy(record,
                new int[] {0, 1, 2}));
    }

    @Test
    public void testOneOccupiedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1);
        assertEquals(1, analyzer.getRowOccupancy(record,
                                                 new int[] {0, 1, 2}));
    }

    @Test
    public void testTwoOccupiedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1, 2);
        assertEquals(2, analyzer.getRowOccupancy(record,
                                                 new int[] {0, 1 ,2}));
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
        assertEquals(0, analyzer.scoreMinMax(record, 7));
    }

    @Test
    public void testScoreWinningSpaceWithOneMoveLeft() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0, 7);
        assertEquals(1, analyzer.scoreMinMax(record, 8));
    }

    @Test
    public void testScoreWinningSpaceWithReversePlayerOrder() {
        Simulator.simulateGame(playerTwo, playerOne, record,
                4, 1, 5, 3, 6, 2, 0, 7);
        assertEquals(1, analyzer.scoreMinMax(record, 8));
    }

    @Test
    public void testScoreLosingSpace() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0);
        assertEquals(-1, analyzer.scoreMinMax(record, 7));
    }

    @Test
    public void testScoreWinningSpaceWithTwoMovesLeft() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 7);
        assertEquals(1, analyzer.scoreMinMax(record, 0));
    }

    @Test
    public void testScoreTyingSpaceWithTwoMovesLeft() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 7);
        assertEquals(0, analyzer.scoreMinMax(record, 8));
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
        assertEquals(1, analyzer.scoreMinMax(record, 3));
    }

    @Test
    public void testScoreLosingSpaceAfterThreeMoves() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5);
        assertEquals(-1, analyzer.scoreMinMax(record, 0));
    }

}
