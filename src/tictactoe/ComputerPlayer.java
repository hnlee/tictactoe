package tictactoe;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by hanalee on 8/1/16.
 */
public class ComputerPlayer extends GamePlayer {
    public ArrayList<Integer> getEmptySpaces(GameRecord record) {
        ArrayList<Integer> allSpaces = record.getBoard().getSpaces();
        ArrayList<Integer> allMoves = record.getAllMoves();
        ArrayList<Integer> emptySpaces = new ArrayList<Integer>();
        for (int space : allSpaces) {
            if (!allMoves.contains(space)) {
                emptySpaces.add(space);
            }
        }
        return emptySpaces;
    }
}
