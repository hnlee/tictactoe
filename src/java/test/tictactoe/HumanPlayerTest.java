package tictactoe;

/**
 * Created by hanalee on 8/10/16.
 */

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;


public class HumanPlayerTest {
    HumanPlayer human;
    MockGamePlayer opponent;
    MockInputStream input;
    ByteArrayOutputStream output;
    GameUI ui;
    MoveHistory record;
    Board board;

    @Before
    public void setUp() {
        input = new MockInputStream();
        output = new ByteArrayOutputStream();
        ui = new MockUI(input, output);
        human = new HumanPlayer(new StringMarker("X"), ui);
        opponent = new MockGamePlayer(new StringMarker("O"));
        board = new SquareBoard(3);
        record = new GameRecord(board, human, opponent);
    }

    @Test
    public void testMove() {
        input.setInputStream("0");
        human.move(record);
        assertEquals(0, record.getLastMove());
    }

}
