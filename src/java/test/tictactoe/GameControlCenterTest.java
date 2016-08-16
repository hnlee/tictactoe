package tictactoe;

/**
 * Created by hanalee on 7/28/16.
 */


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.io.ByteArrayOutputStream;

public class GameControlCenterTest {
    private GameControlCenter game;
    private MockInputStream input;
    private ByteArrayOutputStream output;
    private MockGamePlayer firstPlayer;
    private MockGamePlayer secondPlayer;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private MockUI ui;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        input = new MockInputStream();
        ui = new MockUI(input, output);
        game = new GameControlCenter(ui);

        PlayerMarker xMarker = new StringMarker("X");
        PlayerMarker oMarker = new StringMarker("O");
        firstPlayer = new MockGamePlayer(xMarker);
        secondPlayer = new MockGamePlayer(oMarker);
        humanPlayer = new HumanPlayer(xMarker);
        computerPlayer = new ComputerPlayer(oMarker);
    }

    @Test
    public void testUIExists() {
        assertNotNull(game.getUI());
        assertEquals("start", game.getStatus());
    }

    @Test
    public void testSetUpGame() {
        game.setUp(3, firstPlayer, secondPlayer);
        assertNotNull(game.getBoard());
        assertNotNull(game.getPlayer(1));
        assertNotNull(game.getPlayer(2));
        assertNotNull(game.getRecord());
        assertNotNull(game.getAnalyzer());
        assertEquals("ready", game.getStatus());
    }

    @Test
    public void testGetMove() {
        game.setUp(3, firstPlayer, secondPlayer);
        int move = game.getMove(game.getPlayer(1));
        assertTrue(move >= 0);
        assertTrue(move < 9);
    }

    @Test
    public void testUpdateMove() {
        game.setUp(3, firstPlayer, secondPlayer);
        GameRecord record = game.getRecord();
        game.updateMove(firstPlayer);
        game.updateMove(secondPlayer);
        assertEquals(secondPlayer, record.getLastPlayer());
    }

    @Test
    public void testAnalyzeGameInProgress() {
        game.setUp(3, firstPlayer, secondPlayer);
        GameRecord record = game.getRecord();
        record.newMove(1, firstPlayer);
        game.analyzeBoard();
        assertEquals("playing", game.getStatus());
    }

    @Test
    public void testAnalyzeTiedGame() {
        game.setUp(3, firstPlayer, secondPlayer);
        Simulator.simulateGame(game.getPlayer(1),
                game.getPlayer(2),
                game.getRecord(),
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        game.analyzeBoard();
        assertEquals("tie", game.getStatus());
    }

    @Test
    public void testAnalyzeWonGame() {
        game.setUp(3, firstPlayer, secondPlayer);
        Simulator.simulateGame(game.getPlayer(1),
                game.getPlayer(2),
                game.getRecord(),
                4, 1, 5, 3, 2, 8, 6);
        game.analyzeBoard();
        assertEquals("win", game.getStatus());
    }


    @Test
    public void testSetUpHumanVsComputer() {
        game.setUp(3, humanPlayer, computerPlayer);
        assertTrue(game.getPlayer(2) instanceof ComputerPlayer);
        assertTrue(game.getPlayer(1) instanceof HumanPlayer);
    }

    @Test
    public void testSetUpComputerVsHuman() {
        game.setUp(3, computerPlayer, humanPlayer);
        assertTrue(game.getPlayer(1) instanceof ComputerPlayer);
        assertTrue(game.getPlayer(2) instanceof HumanPlayer);
    }

    @Test
    public void testRunGameWithComputer() {
        game.setUp(3, firstPlayer, computerPlayer);
        game.run();
        assertEquals("finish", game.getStatus());
    }

    @Test
    public void testGetHumanMove() {
        game.setUp(3, humanPlayer, secondPlayer);
        input.setInputStream("0");
        int move = game.getMove(humanPlayer);
        assertEquals(0, move);
    }

    @Test
    public void testUpdateHumanMove() {
        game.setUp(3, humanPlayer, secondPlayer);
        GameRecord record = game.getRecord();
        input.setInputStream("0");
        game.updateMove(humanPlayer);
        assertEquals(0, record.getLastMove());
        assertEquals(humanPlayer, record.getLastPlayer());
    }

    @Test
    public void testDisplayBoardAfterMove() {
        game.setUp(3, firstPlayer, secondPlayer);
        game.updateMove(firstPlayer);
        String boardString = game.getBoard().toString();
        assertTrue(output.toString().endsWith(boardString));
    }

    @Test
    public void testDisplayBoardAtGameStart() {
        game.setUp(3, firstPlayer, secondPlayer);
        String boardString = game.getBoard().toString();
        assertTrue(output.toString().endsWith(boardString));
    }

    @Test
    public void testDisplayTitleAtGameStart() {
        game.setUp(3, firstPlayer, secondPlayer);
        assertTrue(output.toString().startsWith("Tic Tac Toe\n"));
    }

    @Test
    public void testDisplayMoveNumber() {
        game.setUp(3, firstPlayer, secondPlayer);
        game.updateMove(firstPlayer);
        assertTrue(output.toString().contains("Move #1"));
    }

    @Test
    public void testInvalidHumanMove() {
        game.setUp(3, firstPlayer, secondPlayer);
        GamePlayer playerOne = game.getPlayer(1);
        input.setInputStream("9\n0");
        game.updateMove(humanPlayer);
        assertTrue(output.toString().contains("Invalid move"));
    }

}
