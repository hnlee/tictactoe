package tictactoe;

import java.util.List;

/**
 * Created by hanalee on 8/17/16.
 */
public interface MoveHistory {

    Board getBoard();

    void newMove(int move, GamePlayer player);

    GamePlayer getLastPlayer();

    GamePlayer whoPlayedMove(int move);

    List<Integer> getAllMoves();

    GameRecord copyRecord();

}
