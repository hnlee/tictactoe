package tictactoe;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by hanalee on 8/17/16.
 */
public interface Scorer {

    int scoreMove(MoveHistory record, int emptySpace, GamePlayer player);

    Hashtable<Integer, Integer> scoreNextMoves(MoveHistory record);

}
