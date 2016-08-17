package tictactoe;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by hanalee on 8/17/16.
 */
public interface MoveHistory {

    void setPlayers(GamePlayer playerOne, GamePlayer playerTwo);

    GamePlayer getPlayer(int playerNumber);

    Board getBoard();

    void newMove(int move, GamePlayer player);

    int getLastMove();

    GamePlayer getLastPlayer();

    GamePlayer whoPlayedMove(int move);

    List<Integer> getAllMoves();

    Hashtable<GamePlayer, ArrayList<Integer>> getMovesByPlayer();

    MoveHistory copyRecord();

}
