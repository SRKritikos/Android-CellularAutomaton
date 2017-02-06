package sl.on.ca.comp208.gameoflife;

/**
 * Created by Steven on 2/1/2017.
 */

public class GameOfLife implements IRuleImplementor {
    AutomatonHelper automatonHelper;

    public GameOfLife(AutomatonHelper automatonHelper) {
        this.automatonHelper = automatonHelper;
    }

    private boolean determineLife(int numberOfNeighbours, boolean isAlive) {
        if (isAlive && (numberOfNeighbours < 2 || numberOfNeighbours > 3) ) {
            isAlive = false;
        } else if (isAlive && (numberOfNeighbours == 3 || numberOfNeighbours == 2) ) {
            isAlive = true;
        } else if (!isAlive && numberOfNeighbours == 3) {
            isAlive = true;
        }
        return isAlive;
    }

    @Override
    public boolean[][] applyRule(boolean[][] board, int numberOfRows, int numberOfColumns) {
        boolean[][] newBoard = new boolean[numberOfRows][numberOfColumns];
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                int numberOfNeighbours = this.automatonHelper.getNeighbourCount(board, row, col, numberOfRows, numberOfColumns);
                newBoard[row][col] = this.determineLife(numberOfNeighbours, board[row][col]);
            }
        }
        return newBoard;
    }
}
