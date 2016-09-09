package tictactoe.ui;

import tictactoe.board.Board;
import tictactoe.record.MoveHistory;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by hanalee on 7/28/16.
 */
public class CommandLineUI implements GameUI {
    private PrintStream outputStream;
    private InputStream inputStream;
    private Scanner scanner;
    private Hashtable<Integer, String> markers;

    public CommandLineUI() {
        this.inputStream = System.in;
        this.outputStream = System.out;
        this.scanner = new Scanner(inputStream);
        this.markers = new Hashtable<Integer, String>();
        this.setPlayerMarker(1, "X");
        this.setPlayerMarker(2, "O");
    }

    public CommandLineUI(InputStream input, OutputStream output) {
        this.inputStream = input;
        this.outputStream = new PrintStream(output);
        this.scanner = new Scanner(inputStream);
        this.markers = new Hashtable<Integer, String>();
    }

    public void setPlayerMarker(int player, String marker) {
        this.markers.put(player, marker);
    }

    private String generateBoardElementsAsString(String space,
                                                 String separator,
                                                 int multiplier) {
        String[] spaces = new String[multiplier];
        Arrays.fill(spaces, space);
        String spacesWithSeparators = String.join(separator, spaces);
        return spacesWithSeparators;
    }

    public String generateBoardAsString(Board board) {
        int numRows = board.getNumRows();
        String boardSpaces = generateBoardElementsAsString(" %s ", "|", numRows);
        String boardRows = generateBoardElementsAsString("---", "+", numRows);
        String boardGrid = generateBoardElementsAsString(boardSpaces + "\n",
                boardRows + "\n",
                numRows);
        return boardGrid;
    }

    public String convertBoardToString(Board board,
                                       MoveHistory record) {
        int numRows = board.getNumRows();
        Object[] labels = new String[(numRows * numRows)];
        for (int space = 0; space < (numRows * numRows); space++) {
            labels[space] = String.format("%d", space);
        }
        Hashtable<Integer, List<Integer>> movesByPlayer = record.getMovesByPlayer();
        if (movesByPlayer != null) {
            for (int player : movesByPlayer.keySet()) {
                for (int move : movesByPlayer.get(player)) {
                    labels[move] = String.format("%s", this.markers.get(player));
                }
            }
        }
        return String.format(generateBoardAsString(board), labels);
    }

    public void displayMessage(String message) {
        outputStream.println(message);
    }

    public void displayBoard(Board board,
                             MoveHistory record) {
        outputStream.print(convertBoardToString(board, record));
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

    public void displayWin(int player) {
        displayMessage(String.format("Player %d wins", player));
    }

    public void displayTie() {
        displayMessage("Tie");
    }

}
