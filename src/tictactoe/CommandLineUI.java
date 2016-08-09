package tictactoe;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hanalee on 7/28/16.
 */
public class CommandLineUI implements GameUI {

    private String generateBoardElementsAsString(String space,
                                                 String separator,
                                                 int multiplier) {
        String[] spaces = new String[multiplier];
        Arrays.fill(spaces, space);
        String spacesWithSeparators = String.join(separator, spaces);
        return spacesWithSeparators;
    }

    public String generateBoardAsString(GameRecord record) {
        int numRows = record.getBoard().getNumRows();
        String boardSpaces = generateBoardElementsAsString(" %s ", "|", numRows);
        String boardRows = generateBoardElementsAsString("---", "+", numRows);
        String board = generateBoardElementsAsString(boardSpaces + "\n",
                boardRows + "\n",
                numRows);
        return board;
    }

    public String convertBoardToString(GameRecord record) {
        int numRows = record.getBoard().getNumRows();
        Object[] labels = new String[(numRows * numRows)];
        for (int n = 0; n < (numRows * numRows); n++) {
            labels[n] = String.format("%d", n);
        }
        return String.format(generateBoardAsString(record), labels);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

}
