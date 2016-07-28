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
    public void testCreateBoard() {
        game.setBoard(3);
        assertNotNull(game.getBoard());
    }

    @Test
    public void testCreateUI() {
        game.setUI();
        assertNotNull(game.getUI());
    }

    @Test
    public void testCreatePlayers() {
        game.setPlayers();
        assertNotNull(game.getPlayer(1));
        assertNotNull(game.getPlayer(2));
    }

}
