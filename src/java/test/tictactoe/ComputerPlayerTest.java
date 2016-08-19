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
    private SquareBoard board;
    private MoveHistory record;
    private GamePlayer opponent;
    private Scorer analyzer;

    @Before
    public void setUp() {
        PlayerMarker xMarker = new StringMarker("X");
        PlayerMarker oMarker = new StringMarker("O");
        board = new SquareBoard(3);
        record = new GameRecord(board);
        analyzer = new GameAnalyzer();
        computer = new ComputerPlayer(xMarker);
        opponent = new MockGamePlayer(oMarker);
    }

    @Test
    public void testGetAnalyzer() {
        assertNotNull(computer.getAnalyzer());
    }

    @Test
    public void testMakeWinningMove() {
        record.setPlayers(opponent, computer);
        Simulator.simulateGame(record,
                1, 4, 8, 5, 2);
        assertEquals(3, computer.move(record));
    }

    @Test
    public void testMoveOnEmptyBoard() {
        record.setPlayers(computer, opponent);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        for (int space = 0; space < 9; space++) {
            scores.put(space, 0);
        }
        assertEquals(scores, analyzer.scoreNextMoves(record));
        assertTrue(board.getSpaces().contains(computer.move(record)));
    }

}
