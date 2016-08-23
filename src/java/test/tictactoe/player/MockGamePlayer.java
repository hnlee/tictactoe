package tictactoe.player;

import tictactoe.player.GamePlayer;
import tictactoe.player.StringMarker;
import tictactoe.record.MoveHistory;

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
        record.newMove(move, this);
    }

}
