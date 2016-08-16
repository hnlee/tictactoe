package tictactoe;

import java.util.List;

/**
 * Created by hanalee on 8/16/16.
 */
public interface Board {

    int getNumRows();

    List<Integer> getSpaces();

    int[][] getRows();

    boolean isSpaceInRow(int space, int[] row);

    int[][] getRowsWithSpace(int space);

}
