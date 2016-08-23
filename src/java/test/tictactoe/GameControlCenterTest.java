package tictactoe;

/**
 * Created by hanalee on 7/28/16.
 */


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.Arrays;

public class GameControlCenterTest {
    private GameControlCenter game;
    private GamePlayer firstPlayer;
    private GamePlayer secondPlayer;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private MockUI ui;
    private Board board;
    private GameAnalyzer analyzer;
    private MoveHistory record;

    @Before
    public void setUp() {
        ui = new MockUI();

        board = new SquareBoard(3);
        analyzer = new GameAnalyzer();

        StringMarker xMarker = new StringMarker("X");
        StringMarker oMarker = new StringMarker("O");
        firstPlayer = new MockGamePlayer(xMarker);
        secondPlayer = new MockGamePlayer(oMarker);
        humanPlayer = new HumanPlayer(xMarker, ui);
        computerPlayer = new ComputerPlayer(oMarker, analyzer);
    }

    @Test
    public void testSetup() {
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        assertNotNull(game.getPlayerOne());
        assertNotNull(game.getPlayerTwo());
        assertNotNull(game.getRecord());
        assertNotNull(game.getAnalyzer());
    }

    @Test
    public void testStart() {
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        game.start();
        assertTrue(ui.isGameStarted());
        assertTrue(ui.isBoardDisplayed());
    }

    @Test
    public void testMakeMove() {
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        game.makeMove(firstPlayer);
        game.makeMove(secondPlayer);
        assertEquals(secondPlayer, record.getLastPlayer());
    }

    @Test
    public void testAnalyzeGameInProgress() {
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        record.newMove(1, firstPlayer);
        game.analyzeBoard();
        assertFalse(ui.isGameWon());
        assertFalse(ui.isGameTied());
    }

    @Test
    public void testAnalyzeTiedGame() {
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 8, 7);
        game.analyzeBoard();
        assertTrue(ui.isGameTied());
    }

    @Test
    public void testAnalyzeWonGame() {
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        Simulator.simulateGame(record,
                4, 1, 5, 3, 2, 8, 6);
        game.analyzeBoard();
        assertTrue(ui.isGameWon());
    }


    @Test
    public void testSetUpHumanVsComputer() {
        firstPlayer = humanPlayer;
        secondPlayer = computerPlayer;
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        assertTrue(game.getPlayerTwo() instanceof ComputerPlayer);
        assertTrue(game.getPlayerOne() instanceof HumanPlayer);
    }

    @Test
    public void testSetUpComputerVsHuman() {
        firstPlayer = computerPlayer;
        secondPlayer = humanPlayer;
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        assertTrue(game.getPlayerOne() instanceof ComputerPlayer);
        assertTrue(game.getPlayerTwo() instanceof HumanPlayer);
    }

    @Test
    public void testRunGameWithComputer() {
        secondPlayer = computerPlayer;
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        game.run();
        assertTrue(ui.isGameOver());
    }

    @Test
    public void testUpdateHumanMove() {
        firstPlayer = humanPlayer;
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        ui.setInputs(Arrays.asList("0"));
        game.makeMove(humanPlayer);
        assertEquals(0, record.getLastMove());
        assertEquals(humanPlayer, record.getLastPlayer());
    }

    @Test
    public void testDisplayMoveNumAndBoardAfterMove() {
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        game.makeMove(firstPlayer);
        assertTrue(ui.isMoveUpdated());
        assertTrue(ui.isBoardDisplayed());
    }

    @Test
    public void testDisplayTitleAndBoardAtGameStart() {
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        game.start();
        assertTrue(ui.isGameStarted());
        assertTrue(ui.isBoardDisplayed());

    }

    @Test
    public void testInvalidHumanMove() {
        firstPlayer = humanPlayer;
        record = new GameRecord(board, firstPlayer, secondPlayer);
        game = new GameControlCenter(ui, record, analyzer);
        ui.setInputs(Arrays.asList("9", "0"));
        game.makeMove(humanPlayer);
        assertTrue(ui.isError());
    }

}
