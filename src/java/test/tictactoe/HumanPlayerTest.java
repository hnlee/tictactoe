package tictactoe;

/**
 * Created by hanalee on 8/10/16.
 */

import org.junit.Before;
import org.junit.Test;
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
        human = new HumanPlayer(new StringMarker("X"), ui);
        opponent = new MockGamePlayer(new StringMarker("O"));
        board = new SquareBoard(3);
        record = new GameRecord(board, human, opponent);
    }

    @Test
    public void testMove() {
        ui.setInputs(Arrays.asList("0"));
        human.move(record);
        assertEquals(0, record.getLastMove());
    }

}
