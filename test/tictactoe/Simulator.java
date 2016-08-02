package tictactoe;

/**
 * Created by hanalee on 8/2/16.
 */
public class Simulator {

    public static void simulateGame(GamePlayer playerOne,
                                    GamePlayer playerTwo,
                                    GameRecord record,
                                    int... moves) {
        for (int move : moves) {
            if (record.getAllMoves().size() % 2 == 0) {
                record.newMove(move, playerOne);
            } else {
                record.newMove(move, playerTwo);
            }
        }

    }

}
