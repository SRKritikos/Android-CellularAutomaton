package sl.on.ca.comp208.gameoflife;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Steven on 2/8/2017.
 */

public class GameThread extends Thread {
    private IRuleImplementor ruleImplementor;
    private SurfaceHolder holder;
    private final Paint blackPaint;
    private final Paint whitePaint;
    private boolean[][] grid;
    private int numberOfColumns = 100;
    private int numberOfRows = 100;
    private int height;
    private int width;

    public GameThread(IRuleImplementor ruleImplementor, SurfaceHolder holder, int width, int height) {
        this.ruleImplementor = ruleImplementor;
        this.holder = holder;
        this.height = height;
        this.width = width;
        this.blackPaint = new Paint();
        this.whitePaint = new Paint();
        this.blackPaint.setColor(Color.BLACK);
        this.whitePaint.setColor(Color.WHITE);
        this.whitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void run() {
        super.run();
        this.ruleImplementor.applyRule(grid, numberOfRows, numberOfColumns);
        Canvas canvas = this.holder.lockCanvas();
        if (canvas != null) {
            synchronized (holder) {
                canvas.drawColor(Color.WHITE);
                this.drawGridOnCanvas(canvas);
                holder.unlockCanvasAndPost(canvas);
                this.grid = this.ruleImplementor.getNextGeneration();
            }
        }
    }

    private Canvas drawGridOnCanvas(Canvas canvas) {
        int cellWidth = this.width / this.numberOfColumns;
        int cellHeight = this.height / this.numberOfRows;
        for (int row = 0; row < this.numberOfRows; row++) {
            for (int col = 0; col < this.numberOfColumns; col++) {
//                int cellState = this.ruleImplementor.shouldDraw(row, col);
                if (grid[row][col]) {
                    canvas.drawRect(row * cellWidth, col * cellHeight,
                            (row + 1) * cellWidth, (col + 1) * cellHeight,
                            this.blackPaint);
                } else if (!grid[row][col])  {
                    canvas.drawRect(row * cellWidth, col * cellHeight,
                            (row + 1) * cellWidth, (col + 1) * cellHeight,
                            this.whitePaint);
                }
            }
        }
        return canvas;
    }

    public void initializeGrid(boolean[][] grid) {
        this.interrupt();
        this.grid = grid;
        Canvas canvas = this.holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        this.drawGridOnCanvas(canvas);
        holder.unlockCanvasAndPost(canvas);
    }
}
