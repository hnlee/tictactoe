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
    MockInputStream input;
    ByteArrayOutputStream output;
    GameUI ui;

    @Before
    public void setUp() {
        input = new MockInputStream();
        output = new ByteArrayOutputStream();
        ui = new MockUI(input, output);
        human = new HumanPlayer(new StringMarker("X"), ui);
    }

    @Test
    public void testMove() {
        input.setInputStream("0");
        int move = human.move();
        assertEquals(0, move);
    }

}
