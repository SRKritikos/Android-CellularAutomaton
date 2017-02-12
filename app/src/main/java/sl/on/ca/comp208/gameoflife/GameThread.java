package sl.on.ca.comp208.gameoflife;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

import sl.on.ca.comp208.gameoflife.automatons.IRuleImplementor;
import sl.on.ca.comp208.gameoflife.colors.ColorPalette;

/**
 * Created by Steven on 2/8/2017.
 */

public class GameThread extends Thread {
    private IRuleImplementor ruleImplementor;
    private SurfaceHolder holder;
    private boolean[][] grid;
    private int numberOfColumns;
    private int numberOfRows;
    private int height;
    private int width;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private ColorPalette colorPalette;

    public GameThread(IRuleImplementor ruleImplementor, SurfaceHolder holder,
                      int width, int height, int numberOfRows, int numberOfColumns,
                      ColorPalette colorPalette) {
        this.ruleImplementor = ruleImplementor;
        this.holder = holder;
        this.height = height;
        this.width = width;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.colorPalette = colorPalette;
        this.bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
        this.bitmapCanvas =  new Canvas(this.bitmap);
    }

    @Override
    public void run() {
        super.run();
        this.ruleImplementor.applyRule(grid, numberOfRows, numberOfColumns);
        this.drawGridOnBitmapCanvas();
        Canvas canvas = this.holder.lockCanvas();
        if (canvas != null) {
            synchronized (holder) {
                this.drawBitmapOnHolderCanvas(canvas);
                holder.unlockCanvasAndPost(canvas);
                this.grid = this.ruleImplementor.getNextGeneration();
            }
        }
    }

    private void drawGridOnBitmapCanvas() {
        int cellWidth = this.width / this.numberOfRows;
        int cellHeight = this.height / this.numberOfColumns;
        for (int row = 0; row < this.numberOfRows; row++) {
            for (int col = 0; col < this.numberOfColumns; col++) {
                int cellState = this.ruleImplementor.shouldDraw(row, col);
                if (cellState == 1) {
                    this.bitmapCanvas.drawRect(row * cellWidth, col * cellHeight,
                            (row + 1) * cellWidth, (col + 1) * cellHeight,
                            this.colorPalette.getRectColor());
                } else if (cellState == 0)  {
                    this.bitmapCanvas.drawRect(row * cellWidth, col * cellHeight,
                            (row + 1) * cellWidth, (col + 1) * cellHeight,
                            this.colorPalette.getCanvasColor());
                }
            }
        }
    }

    private void drawBitmapOnHolderCanvas(Canvas canvas) {
        canvas.drawColor(this.colorPalette.getCanvasColor().getColor());
        canvas.drawBitmap(this.bitmap, 1, 1, null);
    }

    @Deprecated
    public void initializeGrid(boolean[][] grid) {
        this.drawPatternOnGrid(grid);
        this.start();
    }

    public void setColorPalette(ColorPalette colorPalette) {
        this.colorPalette = colorPalette;
    }

    public void resetGame() {
        this.interrupt();
        this.grid = new boolean[numberOfRows][numberOfColumns];
        this.bitmapCanvas.drawColor(Color.WHITE);
        this.start();
    }

    public void drawPatternOnGrid(boolean[][] patternGrid) {
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                if (patternGrid[row][col]) {
                    this.grid[row][col] = true;
                }
            }
        }
        this.start();
    }
}
