package tictactoe;

/**
 * Created by hanalee on 7/28/16.
 */


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class GameControlCenterTest {

    @Test
    public void testCreateBoard() {
        GameControlCenter game = new GameControlCenter();
        game.setBoard(3);
        assertNotNull(game.getBoard());
    }



}
