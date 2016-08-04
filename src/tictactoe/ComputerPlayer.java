package tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by hanalee on 8/1/16.
 */
public class ComputerPlayer extends GamePlayer {

    public int move(GameAnalyzer analyzer, GameRecord record) {
        Hashtable<Integer, Integer> nextMoves = analyzer.scoreNextMoves(record);
        int bestScore = Collections.max(nextMoves.values());
        ArrayList<Integer> bestMoves = new ArrayList<Integer>();
        for (int nextMove : nextMoves.keySet()) {
            if (nextMoves.get(nextMove) == bestScore) {
                bestMoves.add(nextMove);
            }
        }
        int index = 0;
        if (bestMoves.size() > 1) {
            Random random = new Random();
            index = random.nextInt(bestMoves.size());
        }
        return bestMoves.get(index);
    }
}
