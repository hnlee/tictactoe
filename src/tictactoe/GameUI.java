package tictactoe;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by hanalee on 7/28/16.
 */
public interface GameUI {

    void displayMessage(String message);

    void displayBoard(GameRecord record);

}
