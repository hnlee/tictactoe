package tictactoe;

import java.util.ArrayList;

public class GameBoard {
    ArrayList<String> GameBoard() {
        ArrayList<String> board = new ArrayList<String>();
        for (int n = 0; n < 9; n++) {
            board.add("");
        }
        return board;
    }

    public String getSpace(int space) {
        return board.get(space);
    }
    
    public void move(int space, String marker) {
        board.set(space, marker);
    }
}
