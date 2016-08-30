package tictactoe.player;

import tictactoe.record.MoveHistory;

import java.util.List;

public class MockGamePlayer implements GamePlayer {
    public void move(MoveHistory record) {
        List<Integer> spaces = record.getSpaces();
        if (!spaces.isEmpty()) {
            record.newMove(spaces.get(0), this);
        }
    }
}
