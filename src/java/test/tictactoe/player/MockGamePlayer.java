package tictactoe.player;

import tictactoe.record.MoveHistory;

import java.util.List;

public class MockGamePlayer implements GamePlayer {

    public void move(MoveHistory record) {
        List<Integer> moves = record.getAllMoves();
        int space = 0;
        while (!record.isValidMove(space)) {
            space++;
        }
        record.newMove(space);
    }

}
