package sl.on.ca.comp208.gameoflife;

/**
 * Created by Steven on 2/1/2017.
 */

public interface IRuleImplementor {
    /**
     * Applies the implementation rule of the automaton and updates it's model
     *
     * This function should be used to populate the next generation of the automaton
     *
     * @param grid the current generation.
     * @param numberOfRows the number of rows of the grid.
     * @param numberOfColumns the number of columns of the grid.
     */
    void applyRule(boolean[][] grid, int numberOfRows, int numberOfColumns);

    /**
     * Determines if the cell at row, col on the next generation's grid should be drawn.
     *
     * @param row
     * @param col
     * @return 1 if the cell should now be alive, 0 if the cell now needs to die otherwise -1 indicating no change to be made.
     */
    int shouldDraw(int row, int col);

    /**
     * Get the grid state of the next generation
     * @return next generation's grid
     */
    boolean[][] getNextGeneration();

}
