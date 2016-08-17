package tictactoe;

/**
 * Created by hanalee on 8/17/16.
 */
public interface Endgame {

    boolean isGameWon(GameRecord record);

    boolean isGameTied(GameRecord record);

}
