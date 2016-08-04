package tictactoe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

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
        computer = new ComputerPlayer();
        board = new GameBoard(3);
        record = new GameRecord(board);
        opponent = new GamePlayer();
    }

    @Test
    public void testMakeWinningMove() {
        Simulator.simulateGame(opponent, computer, record,
                4, 1, 5, 2);
        analyzer = new GameAnalyzer(opponent, computer);
        assertEquals(3, computer.move(analyzer, record));
    }

}
