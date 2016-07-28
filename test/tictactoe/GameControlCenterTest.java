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
    }

    @Test
    public void testGetMove() {
        game.setPlayers();
        assertEquals(0, game.getMove(game.getPlayer(1)));
    }

    @Test
    public void testCreateRecord() {
        game.setRecord();
        assertNotNull(game.getRecord());
    }

}
