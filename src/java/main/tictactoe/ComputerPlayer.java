package tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by hanalee on 8/1/16.
 */
public class ComputerPlayer implements GamePlayer {

    private PlayerMarker marker;
    private Scorer analyzer;

    public ComputerPlayer(PlayerMarker marker) {
        this.marker = marker;
        this.analyzer = new GameAnalyzer();
    }

    public PlayerMarker getMarker() {
        return marker;
    }

    public Scorer getAnalyzer(){ return analyzer; }

    @Override
    public int move(MoveHistory record) {
        Hashtable<Integer, Integer> nextMoves = analyzer.scoreNextMoves(record);
        int bestScore = Collections.max(nextMoves.values());
        ArrayList<Integer> bestMoves = new ArrayList<Integer>();
        for (int nextMove : nextMoves.keySet()) {
            if (nextMoves.get(nextMove) == bestScore) {
                bestMoves.add(nextMove);
            }
        }
        if (bestMoves.size() == 1) {
            return bestMoves.get(0);
        }
        Random random = new Random();
        return bestMoves.get(random.nextInt(bestMoves.size()));
    }
}
