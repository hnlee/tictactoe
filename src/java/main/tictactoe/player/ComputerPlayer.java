package tictactoe.player;

import tictactoe.record.MoveHistory;
import tictactoe.scoring.Scorer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by hanalee on 8/1/16.
 */
public class ComputerPlayer implements GamePlayer {

    private StringMarker marker;
    private Scorer scorer;
    private Random random;

    public ComputerPlayer(StringMarker marker, Scorer scorer) {
        this.marker = marker;
        this.scorer = scorer;
        this.random = new Random();
    }

    public StringMarker getMarker() {
        return marker;
    }

    public Scorer getScorer(){ return scorer; }

    public void move(MoveHistory record) {
        Hashtable<Integer, Integer> nextMoves = scorer.scoreNextMoves(record);
        int bestScore = Collections.max(nextMoves.values());
        ArrayList<Integer> bestMoves = new ArrayList<Integer>();
        for (int nextMove : nextMoves.keySet()) {
            if (nextMoves.get(nextMove) == bestScore) {
                bestMoves.add(nextMove);
            }
        }
        int move = bestMoves.get(random.nextInt(bestMoves.size()));
        record.newMove(move, this);
    }
}
