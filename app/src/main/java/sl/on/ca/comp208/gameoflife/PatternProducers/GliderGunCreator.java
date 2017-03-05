package sl.on.ca.comp208.gameoflife.patternproducers;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Steven on 2/8/2017.
 */

public class GliderGunCreator implements IPatternProducer{
    final int GLIDER_MAX_COL = 3;
    final int GLIDER_MIN_COL = -5;
    final int GLIDER_MAX_ROW = 17;
    final int GLIDER_MIN_ROW = -18;

    @Override
    public AtomicBoolean[][] drawPatternOnGrid(AtomicBoolean[][] grid, int rowTouched, int colTouched,
                                         int numberOfRows , int numberOfColumns) {
        boolean isInsideColBounds = this.determineColumnBounds(rowTouched, numberOfColumns);
        boolean isInsideRowBounds = this.determineRowBounds(colTouched, numberOfRows);
        if (isInsideColBounds && isInsideRowBounds) {
            this.drawLeftSide(grid, rowTouched, colTouched);
            this.drawRightSide(grid, rowTouched, colTouched);
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

    private void drawRightSide(AtomicBoolean[][] grid, int row, int col) {
        grid[row - 1][col + 2].set(true);
        grid[row - 2][col + 2].set(true);
        grid[row - 3][col + 2].set(true);
        grid[row - 1][col + 3].set(true);
        grid[row - 2][col + 3].set(true);
        grid[row - 3][col + 3].set(true);
        grid[row][col + 4].set(true);
        grid[row - 4][col + 4].set(true);
        grid[row][col + 6].set(true);
        grid[row + 1][col + 6].set(true);
        grid[row - 4][col + 6].set(true);
        grid[row - 5][col + 6].set(true);
        grid[row - 3][col + 16].set(true);
        grid[row - 3][col + 17].set(true);
        grid[row - 2][col + 16].set(true);
        grid[row - 2][col + 17].set(true);
    }

    private void drawLeftSide(AtomicBoolean[][] grid, int row, int col) {
        grid[row][col - 1].set(true);
        grid[row][col - 2].set(true);
        grid[row - 1][col - 2].set(true);
        grid[row + 1][col - 2].set(true);
        grid[row - 2][col - 3].set(true);
        grid[row + 2][col - 3].set(true);
        grid[row][col - 4].set(true);
        grid[row - 3][col - 5].set(true);
        grid[row - 3][col - 6].set(true);
        grid[row - 2][col - 7].set(true);
        grid[row - 1][col - 8].set(true);
        grid[row][col - 8].set(true);
        grid[row + 1][col - 8].set(true);
        grid[row + 2][col - 7].set(true);
        grid[row + 3][col - 6].set(true);
        grid[row + 3][col - 5].set(true);
        grid[row - 1][col - 17].set(true);
        grid[row - 1][col - 18].set(true);
        grid[row][col - 17].set(true);
        grid[row][col - 18].set(true);
    }
}
