package tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by hanalee on 8/15/16.
 */
public class TicTacToeTest {

    private Random random;

    private String generateInput() {
        String[] inputSequence = new String[9];
        ArrayList<Integer> range = new ArrayList<Integer>();
        for (int n = 0; n < inputSequence.length; n++) {
            range.add(n);
        }
        for (int n = 0; range.size() > 0; n++) {
            int index = random.nextInt(range.size());
            int move = range.remove(index);
            inputSequence[n] = Integer.toString(move);
        }
        return String.join("\n", inputSequence);
    }

    @Before
    public void setUp() {
        random = new Random();
    }

    @Test
    public void testStartAndFinishGame() {
        OutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        MockInputStream input = new MockInputStream();
        System.setIn(input);
        input.setInputStream(generateInput());
        String[] args = {};
        TicTacToe.main(args);
        String messages = output.toString();
        assertTrue(messages.endsWith("Game over.\n"));
    }
}
