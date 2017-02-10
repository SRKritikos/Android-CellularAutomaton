package sl.on.ca.comp208.gameoflife.PatternProducers;

/**
 * Created by Steven on 2/8/2017.
 */

public interface IPatternProducer {
    boolean[][] drawPatternOnGrid(boolean[][] grid, int rowTouched, int colTouched, int numberOfRows , int numberOfColumns);
    boolean determineColumnBounds(int colTouched, int numberOfColumns);
    boolean determineRowBounds(int rowTouched, int numberOfRows);
}