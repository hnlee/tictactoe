package tictactoe;

/**
 * Created by hanalee on 8/4/16.
 */
public class HumanPlayer implements GamePlayer {

    private PlayerMarker marker;
    private GameUI ui;

    public HumanPlayer(PlayerMarker marker, GameUI ui) {
        this.marker = marker;
        this.ui = ui;
    }

    public PlayerMarker getMarker() {
        return marker;
    }

    public int move(MoveHistory record) {
        String playerInput = ui.promptMove();
        return Integer.parseInt(playerInput);
    }

}
