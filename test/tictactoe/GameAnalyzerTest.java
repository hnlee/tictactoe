package tictactoe;

import org.junit.Before;
import org.junit.Test;
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
        analyzer = new GameAnalyzer(board, playerOne, playerTwo, record);
    }

    @Test
    public void testStartAnalyzer() {
        assertNotNull(analyzer);
    }

    @Test
    public void testBlockedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1, 2);
        assertEquals(true, analyzer.isRowBlocked(new int[] {0, 1, 2}));
    }

    @Test
    public void testUnblockedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1, 3, 2);
        assertEquals(false, analyzer.isRowBlocked(new int[] {0, 1, 2}));
    }

    @Test
    public void testZeroOccupiedRow() {
        assertEquals(0, analyzer.getRowOccupancy(new int[] {0, 1, 2}));
    }

    @Test
    public void testOneOccupiedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1);
        assertEquals(1, analyzer.getRowOccupancy(new int[] {0, 1, 2}));
    }

    @Test
    public void testTwoOccupiedRow() {
        Simulator.simulateGame(playerOne, playerTwo, record, 1, 2);
        assertEquals(2, analyzer.getRowOccupancy(new int[] {0, 1 ,2}));
    }

    @Test
    public void testIsWinInWonGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                               4, 1, 5, 3, 6, 2, 0, 7, 8);
        assertTrue(analyzer.isGameWon());
    }

    @Test
    public void testIsNotWinInTiedGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                               4, 1, 5, 3, 6, 2, 0, 8, 7);
        assertFalse(analyzer.isGameWon());
    }

    @Test
    public void testIsNotWinInUnfinishedGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                               4, 1, 5, 3, 6, 2, 0);
        assertFalse(analyzer.isGameWon());
    }

    @Test
    public void testIsTieInTiedGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        assertTrue(analyzer.isGameTied());
    }

    @Test
    public void testIsNotTieInWonGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0, 7, 8);
        assertFalse(analyzer.isGameTied());
    }

    @Test
    public void testIsNotTieInUnfinishedGame() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                4, 1, 5, 3, 6, 2, 0);
        assertFalse(analyzer.isGameWon());
    }

    @Test
    public void testScoreOneEmptySpaceDraw() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                               4, 1, 5, 3, 6, 2, 0, 8);
        assertEquals(0, analyzer.scoreMinMax(7));
    }

    @Test
    public void testScoreOneEmptySpaceWin() {
        Simulator.simulateGame(playerOne, playerTwo, record,
                               4, 1, 5, 3, 6, 2, 0, 7);
        assertEquals(1, analyzer.scoreMinMax(8));
    }

}
