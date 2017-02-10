package sl.on.ca.comp208.gameoflife.patternproducers;

/**
 * Created by Steven on 2/8/2017.
 */

public class GliderGunCreator implements IPatternProducer{
    final int GLIDER_MAX_COL = 17;
    final int GLIDER_MIN_COL = -18;
    final int GLIDER_MAX_ROW = 3;
    final int GLIDER_MIN_ROW = -5;

    @Override
    public boolean[][] drawPatternOnGrid(boolean[][] grid, int rowTouched, int colTouched,
                                         int numberOfRows , int numberOfColumns) {
        boolean isInsideColBounds = this.determineColumnBounds(rowTouched, numberOfColumns);
        boolean isInsideRowBounds = this.determineRowBounds(colTouched, numberOfRows);
        if (isInsideColBounds && isInsideRowBounds) {
            this.drawLeftSide(grid, colTouched, rowTouched);
            this.drawRightSide(grid, colTouched, rowTouched);
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

    private void drawRightSide(boolean[][] grid, int row, int col) {
        grid[row - 1][col + 2] = true;
        grid[row - 2][col + 2] = true;
        grid[row - 3][col + 2] = true;
        grid[row - 1][col + 3] = true;
        grid[row - 2][col + 3] = true;
        grid[row - 3][col + 3] = true;
        grid[row][col + 4] = true;
        grid[row - 4][col + 4] = true;
        grid[row][col + 6] = true;
        grid[row + 1][col + 6] = true;
        grid[row - 4][col + 6] = true;
        grid[row - 5][col + 6] = true;
        grid[row - 3][col + 16] = true;
        grid[row - 3][col + 17] = true;
        grid[row - 2][col + 16] = true;
        grid[row - 2][col + 17] = true;
    }

    private void drawLeftSide(boolean[][] grid, int row, int col) {
        grid[row][col - 1] = true;
        grid[row][col - 2] = true;
        grid[row - 1][col - 2] = true;
        grid[row + 1][col - 2] = true;
        grid[row - 2][col - 3] = true;
        grid[row + 2][col - 3] = true;
        grid[row][col - 4] = true;
        grid[row - 3][col - 5] = true;
        grid[row - 3][col - 6] = true;
        grid[row - 2][col - 7] = true;
        grid[row - 1][col - 8] = true;
        grid[row][col - 8] = true;
        grid[row + 1][col - 8] = true;
        grid[row + 2][col - 7] = true;
        grid[row + 3][col - 6] = true;
        grid[row + 3][col - 5] = true;
        grid[row - 1][col - 17] = true;
        grid[row - 1][col - 18] = true;
        grid[row][col - 17] = true;
        grid[row][col - 18] = true;
    }
}
