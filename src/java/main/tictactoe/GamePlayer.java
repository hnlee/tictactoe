package tictactoe;

import java.util.Random;

/**
 * Created by hanalee on 7/28/16.
 */
public interface GamePlayer {

    PlayerMarker getMarker();

    int move(MoveHistory record);

}