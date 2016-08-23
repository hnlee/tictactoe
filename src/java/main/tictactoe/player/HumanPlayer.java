package tictactoe.player;

import tictactoe.record.MoveHistory;
import tictactoe.ui.GameUI;

/**
 * Created by hanalee on 8/4/16.
 */
public class HumanPlayer implements GamePlayer {

    private StringMarker marker;
    private GameUI ui;

    public HumanPlayer(StringMarker marker, GameUI ui) {
        this.marker = marker;
        this.ui = ui;
    }

    public StringMarker getMarker() {
        return marker;
    }

    public void move(MoveHistory record) {
        boolean validate = false;
        while (!validate) {
            String playerInput = ui.promptMove();
            try {
                int move = Integer.parseInt(playerInput);
                validate = record.isValidMove(move);
                if (validate) {
                    record.newMove(move, this);
                } else {
                    ui.displayError(1);
                }
            } catch (NumberFormatException e) {
                ui.displayError(2);
            }
        }
    }

}