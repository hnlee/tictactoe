package tictactoe;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class GameControlCenter {
    GameBoard board;

    GameControlCenter() {
        board = new GameBoard();        
    }

    boolean isWon() {
        int[][] rows = board.getRows();
        int numRows = board.getNumRows();
        boolean victory = false;
        
        for (int[] row : rows) {
            String[] markers = new String[numRows];
            for (int i = 0; i < numRows; i++) {
                markers[i] = board.getSpace(row[i]);     
            }
            Set<String> unique = new HashSet<String>(Arrays.asList(markers));
            if (unique.size() == 1) {
                victory = true;
            }
        }

        return victory;
    }

}
