package sl.on.ca.comp208.gameoflife.automatons;

import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Steven on 2/5/2017.
 */

public class AutomatonHelper {

    public AutomatonHelper() {
    }

    protected int getNeighbourCount(AtomicBoolean[][] board, int row, int col, int numberOfRows, int numberOfColumns) {
        int numberOfNeighbours = 0;
        //TODO handle all edge cases;
        /**
         * This can be done by determining where the cell is in terms of the board;
         * Once you know where the cell is - call appropriate function.
         */
        if ( (row > 0) && (row < numberOfRows - 1) && (col > 0) && (col < numberOfColumns - 1) ) {
            if (board[row - 1][col - 1].get()) {
                numberOfNeighbours++;
            }
            if (board[row][col - 1].get()) {
                numberOfNeighbours++;
            }
            if (board[row + 1][col - 1].get()) {
                numberOfNeighbours++;
            }
            if (board[row - 1][col].get()) {
                numberOfNeighbours++;
            }
            if (board[row + 1][col].get()) {
                numberOfNeighbours++;
            }
            if (board[row - 1][col + 1].get()) {
                numberOfNeighbours++;
            }
            if (board[row][col + 1].get()) {
                numberOfNeighbours++;
            }
            if (board[row + 1][col + 1].get()) {
                numberOfNeighbours++;
            }
        }
        return numberOfNeighbours;
    }
}
