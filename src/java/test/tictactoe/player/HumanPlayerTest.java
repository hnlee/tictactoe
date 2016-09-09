package tictactoe.player;

/**
 * Created by hanalee on 8/10/16.
 */

import org.junit.Before;
import org.junit.Test;
import tictactoe.board.Board;
import tictactoe.board.Board;
import tictactoe.record.MoveHistory;
import tictactoe.ui.MockUI;

import java.util.Arrays;
import static org.junit.Assert.*;


public class HumanPlayerTest {
    private HumanPlayer human;
    private MockUI ui;
    private MoveHistory record;
    private Board board;

    @Before
    public void setUp() {
        ui = new MockUI();
        human = new HumanPlayer(ui);
        board = new Board(3);
        record = new MoveHistory(board.getNumRows());
    }

    @Test
    public void testMove() {
        ui.setInputs(Arrays.asList("0"));
        human.move(record);
        assertTrue(record.getAllMoves().contains(0));
    }

    @Test
    public void testCharInput() {
        ui.setInputs(Arrays.asList("a", "0"));
        human.move(record);
        assertEquals(2, ui.getErrorCode());
    }

}
