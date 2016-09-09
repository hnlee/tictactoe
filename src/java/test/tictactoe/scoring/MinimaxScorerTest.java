package tictactoe.scoring;

import org.junit.Before;
import org.junit.Test;
import tictactoe.Simulator;
import tictactoe.board.Board;
import tictactoe.board.Board;
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
    private MoveHistory record;
    private MinimaxScorer scorer;

    @Before
    public void setUp() {
        board = new Board(3);
        record = new MoveHistory(board.getNumRows());
        rules = new StandardRules(board);
        scorer = new MinimaxScorer(rules);
    }

    @Test
    public void testGetEmptySpacesFromEmptyBoard() {
        List<Integer> emptySpaces = rules.getSpaces();
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
        assertEquals(0, scorer.scoreMove(record, 7));
    }

    @Test
    public void testScoreWinningSpaceWithOneMoveLeft() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0, 7);
        assertEquals(1, scorer.scoreMove(record, 8));
    }


    @Test
    public void testScoreLosingSpace() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 0);
        assertEquals(-1, scorer.scoreMove(record, 7));
    }

    @Test
    public void testScoreWinningSpaceWithTwoMovesLeft() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 7);
        assertEquals(1, scorer.scoreMove(record, 0));
    }

    @Test
    public void testScoreTyingSpaceWithTwoMovesLeft() {
        Simulator.simulateGame(record,
                4, 1, 5, 3, 6, 2, 7);
        assertEquals(0, scorer.scoreMove(record, 8));
    }

    @Test
    public void testScoreWinningSpaceAfterFourMoves() {
        Simulator.simulateGame(record,
                4, 1, 5, 0);
        assertEquals(1, scorer.scoreMove(record, 3));
    }

    @Test
    public void testScoreLosingSpaceAfterThreeMoves() {
        Simulator.simulateGame(record,
                4, 1, 5);
        assertEquals(-1, scorer.scoreMove(record, 0));
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
