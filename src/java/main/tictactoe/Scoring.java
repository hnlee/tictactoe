package tictactoe;

import java.util.Hashtable;

/**
 * Created by hanalee on 8/17/16.
 */
public interface Scoring {

    Hashtable<Integer, Integer> scoreNextMoves(GameRecord record);

}
