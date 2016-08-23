package tictactoe.analyzer;

import tictactoe.player.GamePlayer;
import tictactoe.record.MoveHistory;

import java.util.Hashtable;

/**
 * Created by hanalee on 8/17/16.
 */
public interface Scorer {

    int scoreMove(MoveHistory record, int emptySpace, GamePlayer player);

    Hashtable<Integer, Integer> scoreNextMoves(MoveHistory record);

}
