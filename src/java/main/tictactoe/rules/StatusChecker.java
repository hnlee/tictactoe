package tictactoe.rules;

import tictactoe.record.MoveHistory;

/**
 * Created by hanalee on 8/17/16.
 */
public interface StatusChecker {

    boolean isGameWon(MoveHistory record);

    boolean isGameTied(MoveHistory record);

}
