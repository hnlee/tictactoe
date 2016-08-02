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
            GamePlayer[] order = new GamePlayer[2];
            if (moves.length % 2 == 0) {
                order[0] = playerOne;
                order[1] = playerTwo;
            } else {
                order[0] = playerTwo;
                order[1] = playerOne;
            }
            if (record.getAllMoves().size() % 2 == 0) {
                record.newMove(move, order[0]);
            } else {
                record.newMove(move, order[1]);
            }
        }

    }

}
