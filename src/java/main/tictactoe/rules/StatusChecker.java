package tictactoe.rules;

import tictactoe.board.Board;
import tictactoe.record.MoveHistory;
import java.util.List;

/**
 * Created by hanalee on 8/17/16.
 */
public interface StatusChecker {

    Board getBoard();

    List<Integer> getSpaces();

    boolean isGameWon(MoveHistory record);

    boolean isGameTied(MoveHistory record);

}
