package tictactoe.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hanalee on 8/10/16.
 */
public class MockInputStream extends InputStream {

    private InputStream inputStream;

    public void setInputStream(String inputString) {
        inputStream = new ByteArrayInputStream(inputString.getBytes());
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

}
