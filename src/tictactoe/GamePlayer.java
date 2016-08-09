package tictactoe;

import java.util.Random;

/**
 * Created by hanalee on 7/28/16.
 */
public class GamePlayer {
    private String marker;

    GamePlayer(String marker) {
        this.marker = marker;
    }

    public String getMarker() {
        return marker;
    }

    public int move(int numRows) {
        Random random = new Random();
        return random.nextInt(numRows * numRows);
    }
    public int move(GameAnalyzer analyzer, GameRecord record) {
        return 0;
    };
}