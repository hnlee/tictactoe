package tictactoe;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Created by hanalee on 8/8/16.
 */
public class AllPossibleGames {
    private ComputerPlayer computer;
    private GameBoard board;
    private GameRecord record;
    private GamePlayer opponent;
    private GameAnalyzer analyzer;

    @Before
    public void setUp() {
        PlayerMarker xMarker = new StringMarker("X");
        PlayerMarker oMarker = new StringMarker("O");
        computer = new ComputerPlayer(oMarker);
        opponent = new MockGamePlayer(xMarker);
        board = new GameBoard(3);
        record = new GameRecord(board);
        analyzer = new GameAnalyzer(opponent, computer);
    }

    private boolean runGame(GameRecord gameRecord) {
        ArrayList<Integer> emptySpaces = analyzer.getEmptySpaces(gameRecord);
        ArrayList<Boolean> outcomes = new ArrayList<Boolean>();
        for (int space : emptySpaces) {
            GameRecord newRecord = gameRecord.copyRecord();
            newRecord.newMove(space, opponent);
            if (analyzer.isGameWon(newRecord)) {
                outcomes.add(false);
                continue;
            }
            if (analyzer.isGameTied(newRecord)) {
                outcomes.add(true);
                continue;
            }
            int move = computer.move(analyzer, newRecord);
            newRecord.newMove(move, computer);
            if (analyzer.isGameTied(newRecord) || analyzer.isGameWon(newRecord)) {
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
