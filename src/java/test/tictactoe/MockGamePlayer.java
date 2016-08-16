package tictactoe;

/**
 * Created by hanalee on 8/15/16.
 */
public class MockGamePlayer implements GamePlayer {

    private PlayerMarker marker;

    public MockGamePlayer(PlayerMarker marker) {
        this.marker = marker;
    }

    public PlayerMarker getMarker() {
        return marker;
    }

}
