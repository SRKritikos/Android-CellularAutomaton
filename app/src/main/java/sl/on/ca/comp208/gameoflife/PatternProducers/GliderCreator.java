package sl.on.ca.comp208.gameoflife.patternproducers;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Steven on 2/9/2017.
 */

public class GliderCreator implements IPatternProducer {
    final int GLIDER_MAX_COL = 1;
    final int GLIDER_MIN_COL = -1;
    final int GLIDER_MAX_ROW = 1;
    final int GLIDER_MIN_ROW = -1;

    @Override
    public AtomicBoolean[][] drawPatternOnGrid(AtomicBoolean[][] grid, int rowTouched, int colTouched, int numberOfRows, int numberOfColumns) {
        boolean isInsideColBounds = this.determineColumnBounds(colTouched, numberOfColumns);
        boolean isInsideRowBounds = this.determineRowBounds(rowTouched, numberOfRows);
        if (isInsideColBounds && isInsideRowBounds) {
            grid[rowTouched][colTouched - 1].set(true);
            grid[rowTouched + 1][colTouched].set(true);
            grid[rowTouched + 1 ][colTouched + 1].set(true);
            grid[rowTouched][colTouched + 1].set(true);
            grid[rowTouched - 1][colTouched + 1].set(true);
        }
        return grid;
    }

    @Override
    public boolean determineColumnBounds(int colTouched, int numberOfColumns) {
        if (colTouched + GLIDER_MAX_COL >= numberOfColumns) {
            return false;
        }
        if (colTouched + GLIDER_MIN_COL < 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean determineRowBounds(int rowTouched, int numberOfRows) {
        if (rowTouched + GLIDER_MAX_ROW >= numberOfRows) {
            return false;
        }
        if (rowTouched + GLIDER_MIN_ROW < 0) {
            return  false;
        }
        return  true;
    }
}
