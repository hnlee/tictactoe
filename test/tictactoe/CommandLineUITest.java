package tictactoe;

import oracle.jrockit.jfr.StringConstantPool;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by hanalee on 8/8/16.
 */
public class CommandLineUITest {
    private ByteArrayOutputStream output;
    private CommandLineUI ui;
    private GameBoard board;
    private GameRecord record;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        ui = new CommandLineUI();
        board = new GameBoard(3);
        record = new GameRecord(board);
        playerOne = new GamePlayer("X");
        playerTwo = new GamePlayer("O");
    }

    @Test
    public void testDisplayMessage() {
        String message = "Tic Tac Toe";
        ui.displayMessage(message);
        assertEquals(message + "\n", output.toString());
    }

    @Test
    public void generateFormattableStringForBoard() {
        String boardString =  " %s | %s | %s \n" +
                              "---+---+---\n" +
                              " %s | %s | %s \n" +
                              "---+---+---\n" +
                              " %s | %s | %s \n";
        assertEquals(boardString, ui.generateBoardAsString(record));
    }

    @Test
    public void generateFormattableStringForSmallBoard() {
        String boardString =  " %s | %s \n" +
                              "---+---\n" +
                              " %s | %s \n";
        GameBoard twoByTwoBoard = new GameBoard(2);
        GameRecord twoByTwoRecord = new GameRecord(twoByTwoBoard);
        assertEquals(boardString, ui.generateBoardAsString(twoByTwoRecord));
    }

    @Test
    public void testConvertEmptyBoardIntoString() {
        String boardString = " 0 | 1 | 2 \n" +
                             "---+---+---\n" +
                             " 3 | 4 | 5 \n" +
                             "---+---+---\n" +
                             " 6 | 7 | 8 \n";
        assertEquals(boardString, ui.convertBoardToString(record));
    }

    @Test
    public void testConvertSmallEmptyBoardIntoString() {
        String boardString = " 0 | 1 \n" +
                             "---+---\n" +
                             " 2 | 3 \n";
        GameBoard twoByTwoBoard = new GameBoard(2);
        GameRecord twoByTwoRecord = new GameRecord(twoByTwoBoard);
        assertEquals(boardString, ui.convertBoardToString(twoByTwoRecord));
    }

    @Test
    public void testConvertNonEmptyBoardIntoString() {
        String boardString = " 0 | O | 2 \n" +
                             "---+---+---\n" +
                             " O | X | X \n" +
                             "---+---+---\n" +
                             " 6 | 7 | 8 \n";
        Simulator.simulateGame(playerOne,
                playerTwo,
                record,
                4, 1, 5, 3);
        assertEquals(boardString, ui.convertBoardToString(record));
    }

    @Test
    public void testDisplayEmptyBoard() {
        String boardString = " 0 | 1 | 2 \n" +
                             "---+---+---\n" +
                             " 3 | 4 | 5 \n" +
                             "---+---+---\n" +
                             " 6 | 7 | 8 \n";
        ui.displayBoard(record);
        assertEquals(boardString, output.toString());
    }

    @Test
    public void testDisplayNonEmptyBoard() {
        String boardString = " 0 | O | 2 \n" +
                "---+---+---\n" +
                " O | X | X \n" +
                "---+---+---\n" +
                " 6 | 7 | 8 \n";
        Simulator.simulateGame(playerOne,
                playerTwo,
                record,
                4, 1, 5, 3);
        ui.displayBoard(record);
        assertEquals(boardString, output.toString());
    }
}
