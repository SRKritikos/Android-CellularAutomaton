package sl.on.ca.comp208.gameoflife;

/**
 * Created by Steven on 2/8/2017.
 */

public interface IPatternProducer {
    boolean[][] drawOnGrid(boolean[][] grid, int rowTouched, int colTouched, int numberOfRows , int numberOfColumns);
}
