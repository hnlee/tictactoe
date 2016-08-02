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

    @Before
    public void setUp() {
        computer = new ComputerPlayer();
        board = new GameBoard(3);
        record = new GameRecord(board);
        opponent = new GamePlayer();
    }

    @Test
    public void testGetEmptySpacesOnEmptyBoard() {
        ArrayList<Integer> emptySpaces = new ArrayList<Integer>();
        for (int space = 0; space < 9; space++) {
            emptySpaces.add(space);
        }
        assertEquals(emptySpaces, computer.getEmptySpaces(record));
    }

    @Test
    public void testGetEmptySpacesOnFullBoard() {
        for (int space = 0; space < 9; space++) {
            record.newMove(space, computer);
        }
        ArrayList<Integer> emptySpaces = new ArrayList<Integer>();
        assertEquals(new ArrayList<Integer>(), computer.getEmptySpaces(record));
    }


}
