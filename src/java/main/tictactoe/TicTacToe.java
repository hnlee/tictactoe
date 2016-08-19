package tictactoe;

/**
 * Created by hanalee on 8/15/16.
 */
public class TicTacToe {

    public static void main(String[] args) {
        GameUI ui = new CommandLineUI();
        Board board = new SquareBoard(3);
        GameAnalyzer analyzer = new GameAnalyzer();
        GamePlayer playerOne = new HumanPlayer(new StringMarker("X"), ui);
        GamePlayer playerTwo = new ComputerPlayer(new StringMarker("O"));
        MoveHistory record = new GameRecord(board, playerOne, playerTwo);

        GameControlCenter game = new GameControlCenter(ui, record, analyzer);
        game.start();
        game.run();
    }

}
