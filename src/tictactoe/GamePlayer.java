package tictactoe;

import java.util.Random;

/**
 * Created by hanalee on 7/28/16.
 */
public class GamePlayer {
    public int move(int numRows) {
        Random random = new Random();
        return random.nextInt(numRows * numRows);
    }
    public int move(GameAnalyzer analyzer, GameRecord record) {
        return 0;
    };
}