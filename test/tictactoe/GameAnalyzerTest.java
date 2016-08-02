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

    private void simulateGame(int... moves) {
        for (int move : moves) {
            GamePlayer[] order = new GamePlayer[2];
            if (moves.length % 2 == 0) {
                order[0] = playerOne;
                order[1] = playerTwo;
            } else {
                order[0] = playerTwo;
                order[1] = playerOne;
            }
            if (record.getAllMoves().size() % 2 == 0) {
                record.newMove(move, order[0]);
            } else {
                record.newMove(move, order[1]);
            }
        }
    }

    @Test
    public void testBlockedRow() {
        simulateGame(1, 2);
        assertEquals(true, analyzer.isRowBlocked(new int[] {0, 1, 2}));
    }

    @Test
    public void testUnblockedRow() {
        simulateGame(1, 3, 2);
        assertEquals(false, analyzer.isRowBlocked(new int[] {0, 1, 2}));
    }

    @Test
    public void testZeroOccupiedRow() {
        assertEquals(0, analyzer.getRowOccupancy(new int[] {0, 1, 2}));
    }

    @Test
    public void testOneOccupiedRow() {
        simulateGame(1);
        assertEquals(1, analyzer.getRowOccupancy(new int[] {0, 1, 2}));
    }

    @Test
    public void testTwoOccupiedRow() {
        simulateGame(1, 2);
        assertEquals(2, analyzer.getRowOccupancy(new int[] {0, 1 ,2}));
    }

    @Test
    public void testScoreOneEmptySpaceDraw() {
        simulateGame(4, 1, 5, 3, 6, 2, 0, 8);
        assertEquals(0, computer.scoreMinMax(7, record));
    }

    @Test
    public void testScoreOneEmptySpaceWin() {
        simulateGame(4, 1, 5, 3, 6, 2, 0, 7);
        assertEquals(1, computer.scoreMinMax(8, record));
    }

}
