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

    public void move(MoveHistory record) {
        boolean validate = false;
        while (!validate) {
            String playerInput = ui.promptMove();
            int move = Integer.parseInt(playerInput);
            validate = record.newMove(move, this);
            if (!validate) {
                ui.displayError("invalid");
            }
        }
    }

}
