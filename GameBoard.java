package tictactoe;

import java.util.ArrayList;

public class GameBoard {
    private ArrayList<String> spaces;

    GameBoard() {
        spaces = new ArrayList<String>();
        for (int n = 0; n < 9; n++) {
            spaces.add("");
        }
    }

    public ArrayList<String> getBoard() {
        return spaces;
    }

    public String getSpace(int space) {
        return spaces.get(space);
    }
    
    public void move(int space, String marker) {
        spaces.set(space, marker);
    }
}
