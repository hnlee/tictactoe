package tictactoe;

/**
 * Created by hanalee on 8/10/16.
 */

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;


public class HumanPlayerTest {

    @Test
    public void testMove() {
        HumanPlayer human = new HumanPlayer("X");
        MockInputStream input = new MockInputStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        GameUI ui = new CommandLineUI(input, output);
        input.setInputStream("0");
        int move = human.move(ui);
        assertEquals(0, move);
    }
}
