package tictactoe;

/**
 * Created by hanalee on 7/28/16.
 */


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import tictactoe.board.Board;
import tictactoe.board.SquareBoard;
import tictactoe.player.*;
import tictactoe.rules.StandardRules;
import tictactoe.rules.StatusChecker;
import tictactoe.ui.MockUI;

import java.util.Arrays;

public class GameControlCenterTest {
    private GameControlCenter game;
    private GamePlayer firstPlayer;
    private GamePlayer secondPlayer;
    private HumanPlayer humanPlayer;
    private MockUI ui;
    private Board board;
    private StatusChecker rules;

    @Before
    public void setUp() {
        ui = new MockUI();
        board = new SquareBoard(3);
        rules = new StandardRules(board);
        firstPlayer = new MockGamePlayer();
        secondPlayer = new MockGamePlayer();
        humanPlayer = new HumanPlayer(ui);
    }

    @Test
    public void testStart() {
        game = new GameControlCenter(ui, firstPlayer, secondPlayer, rules);
        game.start();
        assertTrue(ui.isGameStarted());
        assertTrue(ui.isBoardDisplayed());
    }

    @Test
    public void testMakeMove() {
        game = new GameControlCenter(ui, firstPlayer, secondPlayer, rules);
        game.makeMove(firstPlayer);
        game.makeMove(secondPlayer);
        assertEquals(2, game.getRecord().getLastPlayer());
    }

    @Test
    public void testAnalyzeGameInProgress() {
        game = new GameControlCenter(ui, firstPlayer, secondPlayer, rules);
        game.makeMove(firstPlayer);
        game.analyzeBoard();
        assertFalse(ui.isGameWon());
        assertFalse(ui.isGameTied());
    }

    @Test
    public void testAnalyzeTiedGame() {
        game = new GameControlCenter(ui, firstPlayer, secondPlayer, rules);
        Simulator.simulateGame(game.getRecord(),
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        game.analyzeBoard();
        assertTrue(ui.isGameTied());
    }

    @Test
    public void testAnalyzeWonGame() {
        game = new GameControlCenter(ui, firstPlayer, secondPlayer, rules);
        Simulator.simulateGame(game.getRecord(),
                4, 1, 5, 3, 2, 8, 6);
        game.analyzeBoard();
        assertTrue(ui.isGameWon());
    }

    @Test
    public void testUpdateHumanMove() {
        firstPlayer = humanPlayer;
        game = new GameControlCenter(ui, firstPlayer, secondPlayer, rules);
        ui.setInputs(Arrays.asList("0"));
        game.makeMove(humanPlayer);
        assertEquals(1, game.getRecord().getLastPlayer());
    }

    @Test
    public void testDisplayMoveNumAndBoardAfterMove() {
        game = new GameControlCenter(ui, firstPlayer, secondPlayer, rules);
        game.makeMove(firstPlayer);
        assertTrue(ui.isMoveUpdated());
        assertTrue(ui.isBoardDisplayed());
    }

    @Test
    public void testDisplayTitleAndBoardAtGameStart() {
        game = new GameControlCenter(ui, firstPlayer, secondPlayer, rules);
        game.start();
        assertTrue(ui.isGameStarted());
        assertTrue(ui.isBoardDisplayed());
    }

    @Test
    public void testOutOfRangeHumanInput() {
        firstPlayer = humanPlayer;
        game = new GameControlCenter(ui, firstPlayer, secondPlayer, rules);
        ui.setInputs(Arrays.asList("9", "0"));
        game.makeMove(humanPlayer);
        assertEquals(1, ui.getErrorCode());
    }

    @Test
    public void testHumanInputInOccupiedSpace() {
        firstPlayer = humanPlayer;
        game = new GameControlCenter(ui, firstPlayer, secondPlayer, rules);
        ui.setInputs(Arrays.asList("0", "0", "1"));
        game.makeMove(humanPlayer);
        game.makeMove(humanPlayer);
        assertEquals(1, ui.getErrorCode());
    }

}
