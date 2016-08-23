package tictactoe;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by hanalee on 7/28/16.
 */
public class CommandLineUI implements GameUI {

    private PrintStream outputStream;
    private InputStream inputStream;
    private Scanner scanner;

    public CommandLineUI() {
        this.inputStream = System.in;
        this.outputStream = System.out;
        this.scanner = new Scanner(inputStream);
    }

    public CommandLineUI(InputStream input, OutputStream output) {
        this.inputStream = input;
        this.outputStream = new PrintStream(output);
        this.scanner = new Scanner(inputStream);
    }

    private String generateBoardElementsAsString(String space,
                                                 String separator,
                                                 int multiplier) {
        String[] spaces = new String[multiplier];
        Arrays.fill(spaces, space);
        String spacesWithSeparators = String.join(separator, spaces);
        return spacesWithSeparators;
    }

    public String generateBoardAsString(MoveHistory record) {
        int numRows = record.getBoard().getNumRows();
        String boardSpaces = generateBoardElementsAsString(" %s ", "|", numRows);
        String boardRows = generateBoardElementsAsString("---", "+", numRows);
        String board = generateBoardElementsAsString(boardSpaces + "\n",
                boardRows + "\n",
                numRows);
        return board;
    }

    public String convertBoardToString(MoveHistory record) {
        int numRows = record.getBoard().getNumRows();
        Object[] labels = new String[(numRows * numRows)];
        for (int space = 0; space < (numRows * numRows); space++) {
            labels[space] = String.format("%d", space);
        }
        Hashtable<GamePlayer, ArrayList<Integer>> movesByPlayer = record.getMovesByPlayer();
        if (movesByPlayer != null) {
            for (GamePlayer player : movesByPlayer.keySet()) {
                for (int move : movesByPlayer.get(player)) {
                    labels[move] = player.getMarker().toString();
                }
            }
        }
        return String.format(generateBoardAsString(record), labels);
    }

    public void displayMessage(String message) {
        outputStream.println(message);
    }

    public void displayBoard(MoveHistory record) {
        outputStream.print(convertBoardToString(record));
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public String displayPrompt(String prompt) {
        displayMessage(prompt);
        return getUserInput();
    }

    public void displayTitle() {
        displayMessage("Tic Tac Toe");
    }

    public void displayMoveNumber(int moveNumber) {
        displayMessage(String.format("Move #%d", moveNumber + 1));
    }

    public void displayEnding() {
        displayMessage("Game over");
    }

    public String promptMove() {
        return displayPrompt("Enter move");
    }

    public void displayError(int errorCode) {
        if (errorCode == 1) {
            displayMessage("Invalid move");
        } else if (errorCode == 2) {
            displayMessage("Move must be an integer");
        } else {
            displayMessage("Something went wrong");
        }
    }

    public void displayWin(GamePlayer player) {
        displayMessage(String.format("%s wins", player.getMarker()));
    }

    public void displayTie() {
        displayMessage("Tie");
    }

}
