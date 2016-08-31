package tictactoe.player;

/**
 * Created by hanalee on 8/10/16.
 */

import org.junit.Before;
import org.junit.Test;
import tictactoe.board.Board;
import tictactoe.board.SquareBoard;
import tictactoe.record.GameRecord;
import tictactoe.record.MoveHistory;
import tictactoe.ui.MockUI;

import java.util.Arrays;
import static org.junit.Assert.*;


public class HumanPlayerTest {
    HumanPlayer human;
    MockGamePlayer opponent;
    MockUI ui;
    MoveHistory record;
    Board board;

    @Before
    public void setUp() {
        ui = new MockUI();
        human = new HumanPlayer(ui);
        opponent = new MockGamePlayer();
        board = new SquareBoard(3);
        record = new GameRecord(board, human, opponent);
    }

    @Test
    public void testMove() {
        ui.setInputs(Arrays.asList("0"));
        human.move(record);
        assertEquals(human, record.getLastPlayer());
    }

    @Test
    public void testCharInput() {
        ui.setInputs(Arrays.asList("a", "0"));
        human.move(record);
        assertEquals(2, ui.getErrorCode());
    }

}
