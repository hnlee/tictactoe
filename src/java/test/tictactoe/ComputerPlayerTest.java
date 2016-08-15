package tictactoe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Hashtable;


/**
 * Created by hanalee on 8/1/16.
 */
public class ComputerPlayerTest {

    private ComputerPlayer computer;
    private GameBoard board;
    private GameRecord record;
    private GamePlayer opponent;
    private GameAnalyzer analyzer;

    @Before
    public void setUp() {
        computer = new ComputerPlayer(new StringMarker("X"));
        board = new GameBoard(3);
        record = new GameRecord(board);
        opponent = new MockGamePlayer("O");
    }

    @Test
    public void testMakeWinningMove() {
        Simulator.simulateGame(opponent, computer, record,
                1, 4, 8, 5, 2);
        analyzer = new GameAnalyzer(opponent, computer);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        assertEquals(3, computer.move(analyzer, record));
    }

    @Test
    public void testMoveOnEmptyBoard() {
        analyzer = new GameAnalyzer(computer, opponent);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        for (int space = 0; space < 9; space++) {
            scores.put(space, 0);
        }
        assertEquals(scores, analyzer.scoreNextMoves(record));
        assertTrue(board.getSpaces().contains(computer.move(analyzer, record)));
    }

}
