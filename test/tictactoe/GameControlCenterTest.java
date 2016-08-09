package tictactoe;

/**
 * Created by hanalee on 7/28/16.
 */


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class GameControlCenterTest {
    private GameControlCenter game;

    @Before
    public void setUp() {
        game = new GameControlCenter();
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
        boolean validate;
        validate = game.updateMove(1, playerOne);
        validate = game.updateMove(2, playerTwo);
        assertEquals(2, record.getLastMove());
        assertTrue(validate);
    }

    @Test
    public void testInvalidMove() {
        game.setUp(3);
        GameRecord record = game.getRecord();
        GamePlayer playerOne = game.getPlayer(1);
        GamePlayer playerTwo = game.getPlayer(2);
        boolean validate;
        validate = game.updateMove(2, playerOne);
        validate = game.updateMove(1, playerTwo);
        validate = game.updateMove(2, playerOne);
        assertEquals(1, record.getLastMove());
        assertFalse(validate);
    }

    @Test
    public void testAnalyzeGameInProgress() {
        game.setUp(3);
        GamePlayer playerOne = game.getPlayer(1);
        game.updateMove(1, playerOne);
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
    public void testRunGame() {
        game.setUp(3);
        game.setPlayers(new String[] {"Auto", "Computer"});
        game.run();
        assertEquals("finish", game.getStatus());
    }

}
