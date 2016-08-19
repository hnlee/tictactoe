package tictactoe;

/**
 * Created by hanalee on 7/28/16.
 */


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class GameControlCenterTest {
    private GameControlCenter game;
    private MockInputStream input;
    private ByteArrayOutputStream output;
    private GamePlayer firstPlayer;
    private GamePlayer secondPlayer;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private MockUI ui;
    private Board board;
    private MoveHistory record;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        input = new MockInputStream();
        ui = new MockUI(input, output);

        PlayerMarker xMarker = new StringMarker("X");
        PlayerMarker oMarker = new StringMarker("O");
        firstPlayer = new MockGamePlayer(xMarker);
        secondPlayer = new MockGamePlayer(oMarker);
        humanPlayer = new HumanPlayer(xMarker, ui);
        computerPlayer = new ComputerPlayer(oMarker);

        board = new SquareBoard(3);
    }

    @Test
    public void testSetup() {
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);
        assertNotNull(game.getPlayerOne());
        assertNotNull(game.getPlayerTwo());
        assertNotNull(game.getRecord());
        assertNotNull(game.getAnalyzer());
        assertEquals("start", game.getStatus());
    }

    @Test
    public void testStart() {
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        game.start();
        assertEquals("ready", game.getStatus());
    }

    @Test
    public void testUpdateMove() {
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        MoveHistory record = game.getRecord();
        game.updateMove(firstPlayer);
        game.updateMove(secondPlayer);
        assertEquals(secondPlayer, record.getLastPlayer());
    }

    @Test
    public void testAnalyzeGameInProgress() {
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        MoveHistory record = game.getRecord();
        record.newMove(1, firstPlayer);
        game.analyzeBoard();
        assertEquals("playing", game.getStatus());
    }

    @Test
    public void testAnalyzeTiedGame() {
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        Simulator.simulateGame(game.getRecord(),
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        game.analyzeBoard();
        assertEquals("tie", game.getStatus());
    }

    @Test
    public void testAnalyzeWonGame() {
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        Simulator.simulateGame(game.getRecord(),
                4, 1, 5, 3, 2, 8, 6);
        game.analyzeBoard();
        assertEquals("win", game.getStatus());
    }


    @Test
    public void testSetUpHumanVsComputer() {
        firstPlayer = humanPlayer;
        secondPlayer = computerPlayer;
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        assertTrue(game.getPlayerTwo() instanceof ComputerPlayer);
        assertTrue(game.getPlayerOne() instanceof HumanPlayer);
    }

    @Test
    public void testSetUpComputerVsHuman() {
        firstPlayer = computerPlayer;
        secondPlayer = humanPlayer;
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        assertTrue(game.getPlayerOne() instanceof ComputerPlayer);
        assertTrue(game.getPlayerTwo() instanceof HumanPlayer);
    }

    @Test
    public void testRunGameWithComputer() {
        secondPlayer = computerPlayer;
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        game.run();
        assertEquals("finish", game.getStatus());
    }

    @Test
    public void testUpdateHumanMove() {
        firstPlayer = humanPlayer;
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        MoveHistory record = game.getRecord();
        input.setInputStream("0");
        game.updateMove(humanPlayer);
        assertEquals(0, record.getLastMove());
        assertEquals(humanPlayer, record.getLastPlayer());
    }

    @Test
    public void testDisplayBoardAfterMove() {
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        game.updateMove(firstPlayer);
        MoveHistory record = game.getRecord();
        String boardString = record.getBoard().toString();
        assertTrue(output.toString().endsWith(boardString));
    }

    @Test
    public void testDisplayBoardAtGameStart() {
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        MoveHistory record = game.getRecord();
        String boardString = record.getBoard().toString();
        game.start();
        assertTrue(output.toString().endsWith(boardString));
    }

    @Test
    public void testDisplayTitleAtGameStart() {
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        game.start();
        assertTrue(output.toString().startsWith("Tic Tac Toe\n"));
    }

    @Test
    public void testDisplayMoveNumber() {
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        game.updateMove(firstPlayer);
        assertTrue(output.toString().contains("Move #1"));
    }

    @Test
    public void testInvalidHumanMove() {
        firstPlayer = humanPlayer;
        record = new GameRecord(board);
        game = new GameControlCenter(ui, record, firstPlayer, secondPlayer);        input.setInputStream("9\n0");
        game.updateMove(humanPlayer);
        assertTrue(output.toString().contains("Invalid move"));
    }

}
