package tictactoe;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by hanalee on 8/15/16.
 */
public class MockUI implements GameUI {

    private PrintStream outputStream;
    private InputStream inputStream;

    public MockUI(InputStream input, OutputStream output) {
        this.inputStream = input;
        this.outputStream = new PrintStream(output);
    }

    void displayMessage(String message) {
        outputStream.println(message);
    }

    void displayBoard(GameRecord record) {
        outputStream.print(record.getBoard());
    }

    String getUserInput() {
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLine();
    }

}
