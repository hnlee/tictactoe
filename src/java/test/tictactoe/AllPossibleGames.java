package tictactoe;

import org.junit.Test;
import org.junit.Before;
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
    private GamePlayer opponent;
    private StatusChecker analyzer;

    @Before
    public void setUp() {
        PlayerMarker xMarker = new StringMarker("X");
        PlayerMarker oMarker = new StringMarker("O");
        board = new SquareBoard(3);
        record = new GameRecord(board);
        computer = new ComputerPlayer(oMarker, record);
        opponent = new MockGamePlayer(xMarker);
        analyzer = new GameAnalyzer();
        record.setPlayers(opponent, computer);
    }

    private boolean runGame(MoveHistory gameRecord) {
        List<Integer> emptySpaces = analyzer.getEmptySpaces(gameRecord);
        ArrayList<Boolean> outcomes = new ArrayList<Boolean>();
        for (int space : emptySpaces) {
            MoveHistory newRecord = gameRecord.copyRecord();
            newRecord.newMove(space, opponent);
            if (analyzer.isGameWon(newRecord)) {
                outcomes.add(false);
                continue;
            }
            if (analyzer.isGameTied(newRecord)) {
                outcomes.add(true);
                continue;
            }
            int move = computer.move();
            newRecord.newMove(move, computer);
            if (analyzer.isGameTied(newRecord) || analyzer.isGameWon(newRecord)) {
                outcomes.add(true);
            } else {
                outcomes.add(runGame(newRecord));
            }
        }
        return !outcomes.contains(false);
    }

//    @Test
//    public void testAllPossibleGames() {
//        boolean outcome = runGame(record);
//        assertTrue(outcome);
//    }
}
