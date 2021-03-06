package tictactoe;

import org.junit.Test;
import org.junit.Before;
import tictactoe.board.Board;
import tictactoe.board.Board;
import tictactoe.player.ComputerPlayer;
import tictactoe.player.GamePlayer;
import tictactoe.player.MockGamePlayer;
import tictactoe.record.MoveHistory;
import tictactoe.rules.StandardRules;
import tictactoe.rules.StatusChecker;
import tictactoe.scoring.MinimaxScorer;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanalee on 8/8/16.
 */
public class AllPossibleGames {
    private ComputerPlayer computer;
    private Board board;
    private MoveHistory record;
    private MinimaxScorer scorer;
    private StatusChecker rules;

    @Before
    public void setUp() {
        board = new Board(3);
        rules = new StandardRules(board);
        scorer = new MinimaxScorer(rules);
        computer = new ComputerPlayer(scorer);
        record = new MoveHistory(board.getNumRows());
    }

    private boolean runGame(MoveHistory gameRecord) {
        List<Integer> emptySpaces = scorer.getEmptySpaces(gameRecord);
        ArrayList<Boolean> outcomes = new ArrayList<Boolean>();
        for (int space : emptySpaces) {
            MoveHistory newRecord = gameRecord.copyRecord();
            newRecord.newMove(space);
            if (rules.isGameWon(newRecord)) {
                outcomes.add(false);
                continue;
            }
            if (rules.isGameTied(newRecord)) {
                outcomes.add(true);
                continue;
            }
            computer.move(newRecord);
            if (rules.isGameTied(newRecord) || rules.isGameWon(newRecord)) {
                outcomes.add(true);
            } else {
                outcomes.add(runGame(newRecord));
            }
        }
        return !outcomes.contains(false);
    }

    @Test
    public void testAllPossibleGames() {
        boolean outcome = runGame(record);
        assertTrue(outcome);
    }
}
