package sl.on.ca.comp208.gameoflife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

/**
 * Created by srostantkritikos06 on 1/30/2017.
 */
public class DrawView extends SurfaceView  {
    private Paint blackPaint;
    private int numberOfColumns;
    private int numberOfRows;
    private int cellHeight;
    private int cellWidth;
    private boolean[][] grid;
    private IRuleImplementor ruleImplementor;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.numberOfColumns = 30;
        this.numberOfRows = 30;
        this.grid = new boolean[numberOfRows][numberOfColumns];
        this.grid[3][6] = true;
        this.grid[5][5] = true;
        this.grid[5][6] = true;
        this.grid[5][7] = true;
        this.grid[4][7] = true;

        this.grid[15][15] = true;
        this.grid[15][16] = true;
        this.grid[15][17] = true;
    }


    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        int width = getWidth();
        int height = getHeight();
        this.cellHeight = getHeight() / numberOfRows;
        this.cellWidth = getWidth() / numberOfColumns;
        this.grid = this.ruleImplementor.applyRule(grid, numberOfRows, numberOfColumns);
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                if (grid[row][col]) {
                    Log.i("Draw", "DRAWING RECT NOW");
                    canvas.drawRect(row * cellWidth, col * cellHeight,
                            (row + 1) * cellWidth, (col + 1) * cellHeight,
                            blackPaint);
                }
            }
        }

        for (int i = 1; i < numberOfColumns; i++) {
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
        }

        for (int i = 1; i < numberOfRows; i++) {
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
        }

    }

    public void nextGeneration() {
        setWillNotDraw(false);
        invalidate();
    }

    public void setRuleImplementor(IRuleImplementor ruleImplementor) {
        this.ruleImplementor = ruleImplementor;
    }
}
