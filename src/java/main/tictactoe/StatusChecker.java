package tictactoe;

import java.util.List;

/**
 * Created by hanalee on 8/17/16.
 */
public interface StatusChecker {

    boolean isGameWon(MoveHistory record);

    boolean isGameTied(MoveHistory record);

    boolean isValidMove(int move, MoveHistory record);

}
