package tictactoe;

/**
 * Created by hanalee on 8/2/16.
 */
public class Simulator {

    public static void simulateGame(MoveHistory record,
                                    int... moves) {
        GamePlayer playerOne = record.getPlayer(1);
        GamePlayer playerTwo = record.getPlayer(2);
        for (int move : moves) {
            if (record.getAllMoves().size() % 2 == 0) {
                record.newMove(move, playerOne);
            } else {
                record.newMove(move, playerTwo);
            }
        }
    }

}
