package tictactoe;

import tictactoe.record.MoveHistory;

/**
 * Created by hanalee on 8/2/16.
 */
public class Simulator {

    public static MoveHistory simulateGame(MoveHistory record,
                                    int... moves) {
        for (int move : moves) {
            record.newMove(move);
        }
        return record;
    }

}
