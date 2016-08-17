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
    private Scanner scanner;


    public MockUI(InputStream input, OutputStream output) {
        this.inputStream = input;
        this.outputStream = new PrintStream(output);
        this.scanner =  new Scanner(inputStream);
    }

    public void displayMessage(String message) {
        outputStream.println(message);
    }

    public void displayBoard(MoveHistory record) {
        outputStream.print(record.getBoard());
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

}
