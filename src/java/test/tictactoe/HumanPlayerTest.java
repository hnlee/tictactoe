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
        human = new HumanPlayer(new StringMarker("X"));
        input = new MockInputStream();
        output = new ByteArrayOutputStream();
        ui = new CommandLineUI(input, output);
    }

    @Test
    public void testMove() {
        input.setInputStream("0");
        int move = human.move(ui);
        assertEquals(0, move);
    }

}
