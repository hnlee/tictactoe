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
    }

    @Test
    public void testSetUpGame() {
        game.setUp();
        assertNotNull(game.getBoard());
        assertNotNull(game.getPlayer(1));
        assertNotNull(game.getPlayer(2));
        assertNotNull(game.getRecord());
    }

    @Test
    public void testGetMove() {
        game.setUp();
        assertEquals(3, game.getMove(game.getPlayer(1)));
    }

    @Test
    public void testUpdateMove() {
        game.setUp();
        GameRecord record = game.getRecord();
        game.updateMove(1);
        game.updateMove(2);
        assertEquals(2, record.getLastMove());
    }

    @Test
    public void testInvalidMove() {
        game.setUp();
        GameRecord record = game.getRecord();
        game.updateMove(2);
        game.updateMove(1);
        game.updateMove(2);
        assertEquals(1, record.getLastMove());
    }

}
