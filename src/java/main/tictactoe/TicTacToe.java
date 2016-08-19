package tictactoe;

/**
 * Created by hanalee on 8/15/16.
 */
public class TicTacToe {

    public static void main(String[] args) {
        GameUI ui = new CommandLineUI();
        Board board = new SquareBoard(3);
        MoveHistory record = new GameRecord(board);
        GamePlayer playerOne = new HumanPlayer(new StringMarker("X"), ui);
        GamePlayer playerTwo = new ComputerPlayer(new StringMarker("O"));

        GameControlCenter game = new GameControlCenter(ui, board,
                playerOne, playerTwo);
        game.start();
        game.run();
    }

}
