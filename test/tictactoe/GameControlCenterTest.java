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
        assertEquals(3, game.getMove(game.getPlayer(1)));
    }

    @Test
    public void testUpdateMove() {
        game.setUp(3);
        GameRecord record = game.getRecord();
        GamePlayer playerOne = game.getPlayer(1);
        GamePlayer playerTwo = game.getPlayer(2);
        game.updateMove(1, playerOne);
        game.updateMove(2, playerTwo);
        assertEquals(2, record.getLastMove());
    }

    @Test
    public void testInvalidMove() {
        game.setUp(3);
        GameRecord record = game.getRecord();
        GamePlayer playerOne = game.getPlayer(1);
        GamePlayer playerTwo = game.getPlayer(2);
        game.updateMove(2, playerOne);
        game.updateMove(1, playerTwo);
        game.updateMove(2, playerOne);
        assertEquals(1, record.getLastMove());
    }

    @Test
    public void testAnalyzeGameInProgress() {
        game.setUp(3);
        GamePlayer playerOne = game.getPlayer(1);
        game.updateMove(1, playerOne);
        assertEquals("playing", game.analyzeBoard());
    }

    @Test
    public void testAnalyzeTiedGame() {
        game.setUp(3);
        Simulator.simulateGame(game.getPlayer(1),
                game.getPlayer(2),
                game.getRecord(),
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        assertEquals("tie", game.analyzeBoard());
    }

    @Test
    public void testAnalyzeWonGame() {
        game.setUp(3);
        Simulator.simulateGame(game.getPlayer(1),
                game.getPlayer(2),
                game.getRecord(),
                4, 1, 5, 3, 2, 8, 6);
        assertEquals("win", game.analyzeBoard());
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
        game.setPlayers(new String[] {"Human", "Computer"});
        game.run();

    }
}
