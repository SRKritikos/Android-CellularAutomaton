package sl.on.ca.comp208.gameoflife.patternproducers;

/**
 * Created by Steven on 3/6/2017.
 */

public abstract class GliderProducer implements IPatternProducer {
    protected final int GLIDER_MAX_COL = 1;
    protected final int GLIDER_MIN_COL = -1;
    protected final int GLIDER_MAX_ROW = 1;
    protected final int GLIDER_MIN_ROW = -1;

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
}
