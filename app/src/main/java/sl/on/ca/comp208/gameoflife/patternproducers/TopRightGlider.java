package sl.on.ca.comp208.gameoflife.patternproducers;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Steven on 3/6/2017.
 */

public class TopRightGlider extends GliderProducer{
    @Override
    public AtomicBoolean[][] drawPatternOnGrid(AtomicBoolean[][] grid, int rowTouched, int colTouched, int numberOfRows, int numberOfColumns) {
        boolean isInsideColBounds = this.determineColumnBounds(colTouched, numberOfColumns);
        boolean isInsideRowBounds = this.determineRowBounds(rowTouched, numberOfRows);
        if (isInsideColBounds && isInsideRowBounds) {
            grid[rowTouched][colTouched + 1].set(true);
            grid[rowTouched + 1][colTouched].set(true);
            grid[rowTouched - 1][colTouched - 1].set(true);
            grid[rowTouched - 1][colTouched].set(true);
            grid[rowTouched - 1][colTouched + 1].set(true);
        }
        return grid;
    }
}
