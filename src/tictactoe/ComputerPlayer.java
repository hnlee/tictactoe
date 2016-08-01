package tictactoe;

import java.util.ArrayList;

/**
 * Created by hanalee on 8/1/16.
 */
public class ComputerPlayer {
    public ArrayList<Integer> getEmptySpaces(GameRecord record) {
        return record.getBoard().getSpaces();
    }
}
