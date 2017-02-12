package sl.on.ca.comp208.gameoflife.automatons;

/**
 * Created by Steven on 2/1/2017.
 */

public class GameOfLife implements IRuleImplementor {
    private AutomatonHelper automatonHelper;
    private boolean[][] currentGeneration;
    private boolean[][] nextGeneration;

    public GameOfLife(AutomatonHelper automatonHelper) {
        this.automatonHelper = automatonHelper;
    }

    @Override
    public int shouldDraw( int row, int col) {
        int cellState = -1;
        if (this.currentGeneration == null) {
            //TODO?
        }
        boolean isAliveNow = this.currentGeneration[row][col];
        boolean isAliveNextGen = this.nextGeneration[row][col];
        if (isAliveNextGen) {
            cellState = 1;
        } else if (isAliveNow && !isAliveNextGen) {
            cellState = 0;
        }
        return cellState;
    }

    @Override
    public void applyRule(boolean[][] grid, int numberOfRows, int numberOfColumns) {
        this.currentGeneration = grid;
        this.nextGeneration = new boolean[numberOfRows][numberOfColumns];
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                int numberOfNeighbours = this.automatonHelper.getNeighbourCount(this.currentGeneration, row, col, numberOfRows, numberOfColumns);
                this.nextGeneration[row][col] = this.determineLife(numberOfNeighbours, this.currentGeneration[row][col]);
            }
        }
    }

    @Override
    public boolean[][] getNextGeneration() {
        return this.nextGeneration;
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
}
