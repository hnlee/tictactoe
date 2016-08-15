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

    @Before
    public void setUp() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        input = new MockInputStream();
        game = new GameControlCenter(input, output);
    }

    @Test
    public void testUIExists() {
        assertNotNull(game.getUI());
        assertEquals("start", game.getStatus());
    }

    @Test
    public void testSetUpGame() {
        game.setUp(3);
        assertNotNull(game.getBoard());
        assertNotNull(game.getPlayer(1));
        assertNotNull(game.getPlayer(2));
        assertNotNull(game.getRecord());
        assertNotNull(game.getAnalyzer());
        assertEquals("ready", game.getStatus());
    }

    @Test
    public void testGetMove() {
        game.setUp(3);
        int move = game.getMove(game.getPlayer(1));
        assertTrue(move >= 0);
        assertTrue(move < 9);
    }

    @Test
    public void testUpdateMove() {
        game.setUp(3);
        GameRecord record = game.getRecord();
        GamePlayer playerOne = game.getPlayer(1);
        GamePlayer playerTwo = game.getPlayer(2);
        game.updateMove(playerOne);
        game.updateMove(playerTwo);
        assertEquals(playerTwo, record.getLastPlayer());
    }

    @Test
    public void testAnalyzeGameInProgress() {
        game.setUp(3);
        GamePlayer playerOne = game.getPlayer(1);
        GameRecord record = game.getRecord();
        record.newMove(1, playerOne);
        game.analyzeBoard();
        assertEquals("playing", game.getStatus());
    }

    @Test
    public void testAnalyzeTiedGame() {
        game.setUp(3);
        Simulator.simulateGame(game.getPlayer(1),
                game.getPlayer(2),
                game.getRecord(),
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        game.analyzeBoard();
        assertEquals("tie", game.getStatus());
    }

    @Test
    public void testAnalyzeWonGame() {
        game.setUp(3);
        Simulator.simulateGame(game.getPlayer(1),
                game.getPlayer(2),
                game.getRecord(),
                4, 1, 5, 3, 2, 8, 6);
        game.analyzeBoard();
        assertEquals("win", game.getStatus());
    }


    @Test
    public void testSetUpHumanVsComputer() {
        game.setUp(3);
        game.setPlayers(new String[] {"Human", "Computer"});
        assertTrue(game.getPlayer(2) instanceof ComputerPlayer);
        assertTrue(game.getPlayer(1) instanceof HumanPlayer);
    }

    @Test
    public void testSetUpComputerVsHuman() {
        game.setUp(3);
        game.setPlayers(new String[] {"Computer", "Human"});
        assertTrue(game.getPlayer(1) instanceof ComputerPlayer);
        assertTrue(game.getPlayer(2) instanceof HumanPlayer);
    }

    @Test
    public void testRunGameWithComputer() {
        game.setUp(3);
        game.setPlayers(new String[] {"Auto", "Computer"});
        game.run();
        assertEquals("finish", game.getStatus());
    }

    @Test
    public void testGetHumanMove() {
        game.setUp(3);
        game.setPlayers(new String[] {"Human", "Computer"});
        GamePlayer playerOne = game.getPlayer(1);
        input.setInputStream("0");
        int move = game.getMove(playerOne);
        assertEquals(0, move);
    }

    @Test
    public void testUpdateHumanMove() {
        game.setUp(3);
        game.setPlayers(new String[] {"Human", "Computer"});
        GamePlayer playerOne = game.getPlayer(1);
        GameRecord record = game.getRecord();
        input.setInputStream("0");
        game.updateMove(playerOne);
        assertEquals(0, record.getLastMove());
    }

    @Test
    public void testInvalidHumanMove() {
        game.setUp(3);
        game.setPlayers(new String[] {"Human", "Computer"});
        GamePlayer playerOne = game.getPlayer(1);
        GameRecord record = game.getRecord();
        input.setInputStream("0");
        game.updateMove(playerOne);
        input.setInputStream("1");
        game.updateMove(playerOne);
        input.setInputStream("0");
        game.updateMove(playerOne);
        assertEquals(1, record.getLastMove());
    }

}
