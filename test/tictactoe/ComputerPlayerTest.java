package tictactoe;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Created by hanalee on 8/1/16.
 */
public class ComputerPlayerTest {

    @Test
    public void testGetEmptySpacesOnEmptyBoard() {
        ComputerPlayer computer = new ComputerPlayer();
        GameBoard board = new GameBoard(3);
        GameRecord record = new GameRecord(board);
        ArrayList<Integer> emptySpaces = new ArrayList<Integer>();
        for (int space = 0; space < 9; space++) {
            emptySpaces.add(space);
        }
        assertEquals(emptySpaces, computer.getEmptySpaces(record));
    }
}
