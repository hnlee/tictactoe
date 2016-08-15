package tictactoe;

import java.util.Random;

/**
 * Created by hanalee on 7/28/16.
 */
public interface GamePlayer {

    Random random = new Random();

    PlayerMarker getMarker();

    default int move(int numRows) {
        return random.nextInt(numRows * numRows);
    }

    default int move(GameAnalyzer analyzer, GameRecord record) {
        int numRows = record.getBoard().getNumRows();
        return random.nextInt(numRows * numRows);
    }

    default int move(GameUI ui) {
        return random.nextInt();
    }

}