package tictactoe;

import java.util.Random;

/**
 * Created by hanalee on 8/15/16.
 */
public class MockGamePlayer implements GamePlayer {

    private Random random;
    private StringMarker marker;

    public MockGamePlayer(StringMarker marker) {
        this.random = new Random();
        this.marker = marker;
    }

    public StringMarker getMarker() {
        return marker;
    }

    public void move(MoveHistory record) {
        int numRows = record.getBoard().getNumRows();
        int move = random.nextInt(numRows * numRows);
        boolean validate = record.newMove(move, this);
    }

}
