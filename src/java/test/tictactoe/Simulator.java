package tictactoe;

import tictactoe.player.GamePlayer;
import tictactoe.record.MoveHistory;

/**
 * Created by hanalee on 8/2/16.
 */
public class Simulator {

    public static MoveHistory simulateGame(MoveHistory record,
                                    int... moves) {
        GamePlayer playerOne = record.getPlayerOne();
        GamePlayer playerTwo = record.getPlayerTwo();
        for (int move : moves) {
            if (record.getAllMoves().size() % 2 == 0) {
                record.newMove(move, playerOne);
            } else {
                record.newMove(move, playerTwo);
            }
        }
        return record;
    }

}
