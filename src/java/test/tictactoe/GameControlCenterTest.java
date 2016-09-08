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
import tictactoe.record.MoveHistory;
import tictactoe.record.MoveHistory;
import tictactoe.rules.StandardRules;
import tictactoe.rules.StatusChecker;
import tictactoe.scoring.MinimaxScorer;
import tictactoe.scoring.Scorer;
import tictactoe.ui.MockUI;

import java.util.Arrays;

public class GameControlCenterTest {
    private GameControlCenter game;
    private GamePlayer firstPlayer;
    private GamePlayer secondPlayer;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private MockUI ui;
    private Board board;
    private StatusChecker rules;
    private Scorer scorer;
    private MoveHistory record;

    @Before
    public void setUp() {
        ui = new MockUI();

        board = new SquareBoard(3);
        rules = new StandardRules();
        scorer = new MinimaxScorer(rules);

        firstPlayer = new MockGamePlayer();
        secondPlayer = new MockGamePlayer();
        humanPlayer = new HumanPlayer(ui);
        computerPlayer = new ComputerPlayer(scorer);
    }

    @Test
    public void testStart() {
        record = new MoveHistory(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, rules);
        game.start();
        assertTrue(ui.isGameStarted());
        assertTrue(ui.isBoardDisplayed());
    }

    @Test
    public void testMakeMove() {
        record = new MoveHistory(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, rules);
        game.makeMove(firstPlayer);
        game.makeMove(secondPlayer);
        assertEquals(secondPlayer, record.getLastPlayer());
    }

    @Test
    public void testAnalyzeGameInProgress() {
        record = new MoveHistory(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, rules);
        record.newMove(1, firstPlayer);
        game.analyzeBoard();
        assertFalse(ui.isGameWon());
        assertFalse(ui.isGameTied());
    }

    @Test
    public void testAnalyzeTiedGame() {
        record = new MoveHistory(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, rules);
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        game.analyzeBoard();
        assertTrue(ui.isGameTied());
    }

    @Test
    public void testAnalyzeWonGame() {
        record = new MoveHistory(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, rules);
        Simulator.simulateGame(record,
                4, 1, 5, 3, 2, 8, 6);
        game.analyzeBoard();
        assertTrue(ui.isGameWon());
    }

    @Test
    public void testUpdateHumanMove() {
        firstPlayer = humanPlayer;
        record = new MoveHistory(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, rules);
        ui.setInputs(Arrays.asList("0"));
        game.makeMove(humanPlayer);
        assertEquals(humanPlayer, record.getLastPlayer());
    }

    @Test
    public void testDisplayMoveNumAndBoardAfterMove() {
        record = new MoveHistory(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, rules);
        game.makeMove(firstPlayer);
        assertTrue(ui.isMoveUpdated());
        assertTrue(ui.isBoardDisplayed());
    }

    @Test
    public void testDisplayTitleAndBoardAtGameStart() {
        record = new MoveHistory(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, rules);
        game.start();
        assertTrue(ui.isGameStarted());
        assertTrue(ui.isBoardDisplayed());

    }

    @Test
    public void testOutOfRangeHumanInput() {
        firstPlayer = humanPlayer;
        record = new MoveHistory(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, rules);
        ui.setInputs(Arrays.asList("9", "0"));
        game.makeMove(humanPlayer);
        assertEquals(1, ui.getErrorCode());
    }

    @Test
    public void testHumanInputInOccupiedSpace() {
        firstPlayer = humanPlayer;
        record = new MoveHistory(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, rules);
        ui.setInputs(Arrays.asList("0", "0", "1"));
        game.makeMove(humanPlayer);
        game.makeMove(humanPlayer);
        assertEquals(1, ui.getErrorCode());
    }

}
