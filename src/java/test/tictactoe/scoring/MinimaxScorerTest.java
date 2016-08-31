package tictactoe.scoring;

import org.junit.Before;
import org.junit.Test;
import tictactoe.Simulator;
import tictactoe.board.Board;
import tictactoe.board.SquareBoard;
import tictactoe.player.GamePlayer;
import tictactoe.player.MockGamePlayer;
import tictactoe.player.StringMarker;
import tictactoe.record.GameRecord;
import tictactoe.record.MoveHistory;
import tictactoe.rules.StandardRules;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hanalee on 8/25/16.
 */
public class MinimaxScorerTest {
    private StandardRules rules;
    private Board board;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private MoveHistory record;
    private MinimaxScorer scorer;

    @Before
    public void setUp() {
        board = new SquareBoard(3);
        playerOne = new MockGamePlayer();
        playerTwo = new MockGamePlayer();
        record = new GameRecord(board, playerOne, playerTwo);
        rules = new StandardRules();
        scorer = new MinimaxScorer(rules);
    }

    @Test
    public void testGetEmptySpacesFromEmptyBoard() {
        List<Integer> emptySpaces = record.getSpaces();
        assertEquals(emptySpaces, scorer.getEmptySpaces(record));
    }

    @Test
    public void testGetEmptySpacesFromFullBoard() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 7, 8);
        List<Integer> emptySpaces = new ArrayList<Integer>();
        assertEquals(emptySpaces, scorer.getEmptySpaces(record));
    }

    @Test
    public void testGetEmptySpacesFromUnfinishedBoard() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0);
        List<Integer> emptySpaces = new ArrayList<Integer>();
        emptySpaces.add(7);
        emptySpaces.add(8);
        assertEquals(emptySpaces, scorer.getEmptySpaces(record));
    }

        @Test
    public void testScoreTyingSpaceWithOneMoveLeft() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 8);
        assertEquals(0, scorer.scoreMove(record, 7, playerOne));
    }

    @Test
    public void testScoreWinningSpaceWithOneMoveLeft() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 7);
        assertEquals(1, scorer.scoreMove(record, 8, playerOne));
    }

    @Test
    public void testScoreWinningSpaceWithReversePlayerOrder() {
        record = new GameRecord(board, playerTwo, playerOne);
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 7);
        assertEquals(1, scorer.scoreMove(record, 8, playerTwo));
    }

    @Test
    public void testGetNextPlayerInMidGame() {
        Simulator.simulateGame(record, 1);
        assertEquals(playerTwo, scorer.getNextPlayer(record));
        record.newMove(2, playerTwo);
        assertEquals(playerOne, scorer.getNextPlayer(record));
    }

    @Test
    public void testGetNextPlayerInStartOfGame() {
        assertEquals(playerOne, scorer.getNextPlayer(record));
    }

    @Test
    public void testScoreLosingSpace() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0);
        assertEquals(-1, scorer.scoreMove(record, 7, playerTwo));
    }

    @Test
    public void testScoreWinningSpaceWithTwoMovesLeft() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 7);
        assertEquals(1, scorer.scoreMove(record, 0, playerTwo));
    }

    @Test
    public void testScoreTyingSpaceWithTwoMovesLeft() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 7);
        assertEquals(0, scorer.scoreMove(record, 8, playerTwo));
    }

    @Test
    public void testScoreWinningSpaceAfterFourMoves() {
        Simulator.simulateGame(record,
                4, 1, 5, 0);
        assertEquals(1, scorer.scoreMove(record, 3, playerOne));
    }

    @Test
    public void testScoreLosingSpaceAfterThreeMoves() {
        Simulator.simulateGame(record,
                4, 1, 5);
        assertEquals(-1, scorer.scoreMove(record, 0, playerTwo));
    }

    @Test
    public void testScoreNextMovesWhenZeroMoves() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 7, 8);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        assertEquals(scores, scorer.scoreNextMoves(record));
    }

    @Test
    public void testScoreNextMovesWhenOneMove() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 7);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        scores.put(8, 1);
        assertEquals(scores, scorer.scoreNextMoves(record));
    }

    @Test
    public void testScoreNextMovesInMidGame() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 2, 6);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        scores.put(0, 0);
        scores.put(7, -1);
        scores.put(8, 1);
        assertEquals(scores, scorer.scoreNextMoves(record));
    }

    @Test
    public void testForkingMove() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 7);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        scores.put(0, 1);
        scores.put(2, 0);
        scores.put(6, 0);
        scores.put(8, 0);
        assertEquals(scores, scorer.scoreNextMoves(record));
    }

    @Test
    public void testBlockForkingMove() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        scores.put(0, -1);
        scores.put(2, 0);
        scores.put(7, -1);
        scores.put(8, -1);
        assertEquals(scores, scorer.scoreNextMoves(record));
    }

}
