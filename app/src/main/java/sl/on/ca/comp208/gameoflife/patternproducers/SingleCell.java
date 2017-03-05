package sl.on.ca.comp208.gameoflife.patternproducers;

import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Steven on 3/4/2017.
 */

public class SingleCell implements IPatternProducer {
    @Override
    public AtomicBoolean[][] drawPatternOnGrid(AtomicBoolean[][] grid, int rowTouched, int colTouched, int numberOfRows, int numberOfColumns) {
        boolean isInColumnBounds = this.determineColumnBounds(colTouched, numberOfColumns);
        boolean isInRowBounds = this.determineRowBounds(rowTouched, numberOfRows);
        if (isInColumnBounds && isInRowBounds) {
            grid[rowTouched][colTouched].set(true);
        }
        return grid;
    }

    @Override
    public boolean determineColumnBounds(int colTouched, int numberOfColumns) {
        if (colTouched > 0 && colTouched < numberOfColumns - 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean determineRowBounds(int rowTouched, int numberOfRows) {
        if (rowTouched > 0 && rowTouched < numberOfRows - 1) {
            return true;
        }
        return false;
    }
}
