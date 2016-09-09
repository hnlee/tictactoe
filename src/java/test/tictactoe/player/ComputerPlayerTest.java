package tictactoe.player;

import org.junit.Before;
import org.junit.Test;
import tictactoe.Simulator;
import tictactoe.board.SquareBoard;
import tictactoe.record.MoveHistory;
import tictactoe.rules.StandardRules;
import tictactoe.rules.StatusChecker;
import tictactoe.scoring.MinimaxScorer;
import tictactoe.scoring.Scorer;

import static org.junit.Assert.*;
import java.util.Hashtable;


/**
 * Created by hanalee on 8/1/16.
 */
public class ComputerPlayerTest {

    private ComputerPlayer computer;
    private SquareBoard board;
    private MoveHistory record;
    private Scorer scorer;
    private StatusChecker rules;

    @Before
    public void setUp() {
        board = new SquareBoard(3);
        rules = new StandardRules(board);
        scorer = new MinimaxScorer(rules);
        computer = new ComputerPlayer(scorer);
    }

    @Test
    public void testMakeWinningMove() {
        record = new MoveHistory(board.getNumRows());
        Simulator.simulateGame(record,
                1, 4, 8, 5, 2);
        computer.move(record);
        assertTrue(rules.isGameWon(record));
    }

    @Test
    public void testMoveOnEmptyBoard() {
        record = new MoveHistory(board.getNumRows());
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        for (int space = 0; space < 9; space++) {
            scores.put(space, 0);
        }
        assertEquals(scores, scorer.scoreNextMoves(record));
        computer.move(record);
        assertEquals(1, record.getAllMoves().size());
    }

}
