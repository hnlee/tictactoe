package tictactoe.scoring;

import tictactoe.record.MoveHistory;

import java.util.Hashtable;

/**
 * Created by hanalee on 8/17/16.
 */
public interface Scorer {

    int scoreMove(MoveHistory record, int emptySpace);

    Hashtable<Integer, Integer> scoreNextMoves(MoveHistory record);

}
