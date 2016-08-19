package tictactoe;

import java.util.Random;

/**
 * Created by hanalee on 8/15/16.
 */
public class MockGamePlayer implements GamePlayer {

    private Random random;
    private PlayerMarker marker;

    public MockGamePlayer(PlayerMarker marker) {
        this.random = new Random();
        this.marker = marker;
    }

    public PlayerMarker getMarker() {
        return marker;
    }

    public void move(MoveHistory record) {
        int numRows = record.getBoard().getNumRows();
        int move = random.nextInt(numRows * numRows);
        boolean validate = record.newMove(move, this);
    }

}
