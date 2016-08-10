package tictactoe;

import java.util.Random;

/**
 * Created by hanalee on 7/28/16.
 */
public class GamePlayer {
    private String marker;
    private Random random;

    GamePlayer(String marker) {
        this.marker = marker;
        this.random = new Random();
    }

    public String getMarker() {
        return marker;
    }

    public int move(int numRows) {
        return random.nextInt(numRows * numRows);
    }

    public int move(GameAnalyzer analyzer, GameRecord record) {
        int numRows = record.getBoard().getNumRows();
        return random.nextInt(numRows * numRows);
    };
}