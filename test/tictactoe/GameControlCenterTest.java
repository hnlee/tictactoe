package tictactoe;

/**
 * Created by hanalee on 7/28/16.
 */


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.LinkedList;

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
        game.setUp();
        assertNotNull(game.getBoard());
        assertNotNull(game.getPlayer(1));
        assertNotNull(game.getPlayer(2));
        assertNotNull(game.getRecord());
        assertEquals("ready", game.getStatus());
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
        GamePlayer playerOne = game.getPlayer(1);
        GamePlayer playerTwo = game.getPlayer(2);
        game.updateMove(1, playerOne);
        game.updateMove(2, playerTwo);
        assertEquals(2, record.getLastMove());
    }

    @Test
    public void testInvalidMove() {
        game.setUp();
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
        game.setUp();
        GamePlayer playerOne = game.getPlayer(1);
        game.updateMove(1, playerOne);
        game.analyzeBoard();
        assertEquals("playing", game.getStatus());
    }

    private LinkedList<Integer> moveList(int... moves) {
        LinkedList<Integer> moveList = new LinkedList<Integer>();
        for (int move : moves) {
            moveList.add(move);
        }
        return moveList;
    }

    private void simulateGame(GameControlCenter testGame, int...moves) {
        LinkedList<Integer> moveList = moveList(moves);
        int player = 1;
        for (int move : moveList) {
            game.updateMove(move, game.getPlayer(player));
            player++;
            if (player == 3) { player = 1; }
        }
    }

    @Test
    public void testAnalyzeTiedGame() {
        game.setUp();
        simulateGame(game, 4, 1, 5, 3, 6, 2, 0, 8, 7);
        game.analyzeBoard();
        assertEquals("tie", game.getStatus());
    }

    @Test
    public void testAnalyzeWonGame() {
        game.setUp();
        simulateGame(game, 4, 1, 5, 3, 2, 8, 6);
        game.analyzeBoard();
        assertEquals(true, game.isRowBlocked(new int[] {0, 1, 2}));
        assertEquals(false, game.isRowBlocked(new int[] {2, 4, 6}));
        assertEquals(2, game.getRowOccupancy(new int[] {0, 1, 2}));
        assertEquals(3, game.getRowOccupancy(new int[] {2, 4, 6}));
        assertEquals("win", game.getStatus());
    }

}
