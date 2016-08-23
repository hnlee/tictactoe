package tictactoe.player;

import org.junit.Before;
import org.junit.Test;
import tictactoe.Simulator;
import tictactoe.board.SquareBoard;
import tictactoe.record.GameRecord;
import tictactoe.record.MoveHistory;
import tictactoe.analyzer.GameAnalyzer;
import tictactoe.analyzer.Scorer;

import static org.junit.Assert.*;
import java.util.Hashtable;


/**
 * Created by hanalee on 8/1/16.
 */
public class ComputerPlayerTest {

    private ComputerPlayer computer;
    private SquareBoard board;
    private MoveHistory record;
    private GamePlayer opponent;
    private Scorer analyzer;

    @Before
    public void setUp() {
        StringMarker xMarker = new StringMarker("X");
        StringMarker oMarker = new StringMarker("O");
        board = new SquareBoard(3);
        analyzer = new GameAnalyzer();
        computer = new ComputerPlayer(xMarker, analyzer);
        opponent = new MockGamePlayer(oMarker);

    }

    @Test
    public void testGetAnalyzer() {
        assertNotNull(computer.getAnalyzer());
    }

    @Test
    public void testMakeWinningMove() {
        record = new GameRecord(board, opponent, computer);
        Simulator.simulateGame(record,
                1, 4, 8, 5, 2);
        computer.move(record);
        assertEquals(3, record.getLastMove());
    }

    @Test
    public void testMoveOnEmptyBoard() {
        record = new GameRecord(board, computer, opponent);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        for (int space = 0; space < 9; space++) {
            scores.put(space, 0);
        }
        assertEquals(scores, analyzer.scoreNextMoves(record));
        computer.move(record);
        assertEquals(computer, record.getLastPlayer());
    }

}
