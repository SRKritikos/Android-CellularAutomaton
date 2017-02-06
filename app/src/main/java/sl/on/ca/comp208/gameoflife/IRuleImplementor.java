package sl.on.ca.comp208.gameoflife;

/**
 * Created by Steven on 2/1/2017.
 */

public interface IRuleImplementor {
    boolean[][] applyRule(boolean[][] board, int numberOfRows, int numberOfColumns);
}
