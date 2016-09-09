package tictactoe.scoring;

import tictactoe.record.MoveHistory;
import tictactoe.rules.StatusChecker;

import java.util.*;

/**
 * Created by hanalee on 8/25/16.
 */
public class MinimaxScorer implements Scorer {

    private StatusChecker rules;

    public MinimaxScorer(StatusChecker rules) {
        this.rules = rules;
    }

    public int scoreMove(MoveHistory record,
                  int emptySpace) {
        MoveHistory nextGameState = record.copyRecord();
        nextGameState.newMove(emptySpace);
        if (rules.isGameWon(nextGameState)) {
            return 1;
        }
        if (rules.isGameTied(nextGameState)) {
            return 0;
        }
        Hashtable<Integer, Integer> nextScores = scoreNextMoves(nextGameState);
        int nextScore = Collections.max(nextScores.values());
        return -1 * nextScore;
    }

    public Hashtable<Integer, Integer> scoreNextMoves(MoveHistory record) {
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        List<Integer> emptySpaces = getEmptySpaces(record);
        for (int emptySpace : emptySpaces) {
            scores.put(emptySpace, scoreMove(record, emptySpace));
        }
        return scores;
    }

    public List<Integer> getEmptySpaces(MoveHistory record) {
        List<Integer> allSpaces = rules.getSpaces();
        List<Integer> allMoves = record.getAllMoves();
        List<Integer> emptySpaces = new ArrayList<Integer>();
        for (int space : allSpaces) {
            if (!allMoves.contains(space)) {
                emptySpaces.add(space);
            }
        }
        return emptySpaces;
    }

}
