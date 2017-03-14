package sl.on.ca.comp208.gameoflife.GameRandomizer;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Steven on 3/14/2017.
 */

public class GameRandomizer {
    private final int ROW_INDEX = 0;
    private final int COL_INDEX = 1;

    public void randomizeGame(AtomicBoolean[][] grid, int numberOfRows, int numberOfCols) {
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfCols; col++) {
                if (grid[row][col].get()) {
                    int[] nextRowCol = this.getNextRowColumn(row, col, numberOfRows, numberOfCols);
                    grid[ nextRowCol[ROW_INDEX] ][ nextRowCol[COL_INDEX] ].compareAndSet(false, true);
                    grid[row][col].set(false);
                }
            }
        }
    }

    private int[] getNextRowColumn(int row, int col, int numberOfRows, int numberOfCols) {
        Random random = new Random();
        int x = col;
        int y = row;
        int randNumber = random.nextInt(8) + 1;
        switch (randNumber) {
            case 1:
                y = row + 1;
                x = col + 1;
                break;
            case 2:
                y = row + 1;
                x = col - 1;
                break;
            case 3:
                y = row + 1;
                x = col;
                break;
            case 4:
                y = row - 1;
                x = col + 1;
                break;
            case 5:
                y = row - 1;
                x = col - 1;
                break;
            case 6:
                y = row - 1;
                x = col;
                break;
            case 7:
                y = row;
                x = col + 1;
                break;
            case 8:
                y = row;
                x = col - 1;
        }

        int[] rowCol = { row, col };
        if (y < numberOfRows && y > 0) {
            rowCol[ROW_INDEX] = y;
        } if (x < numberOfCols && x > 0) {
            rowCol[COL_INDEX] = x;
        }
        return  rowCol;
    }

}
