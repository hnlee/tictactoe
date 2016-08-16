package tictactoe;

/**
 * Created by hanalee on 8/4/16.
 */
public class HumanPlayer implements GamePlayer {

    private PlayerMarker marker;

    public HumanPlayer(PlayerMarker marker) {
        this.marker = marker;
    }

    public PlayerMarker getMarker() {
        return marker;
    }

    public int move(GameUI ui) {
        String playerInput = ui.promptMove();
        return Integer.parseInt(playerInput);
    }

}
