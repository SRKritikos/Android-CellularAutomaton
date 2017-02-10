package sl.on.ca.comp208.gameoflife;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private int numberOfColumns;
    private int numberOfRows;
    private int height;
    private int width;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;


    public GameThread(IRuleImplementor ruleImplementor, SurfaceHolder holder,
                      int width, int height, int numberOfRows, int numberOfColumns) {
        this.ruleImplementor = ruleImplementor;
        this.holder = holder;
        this.height = height;
        this.width = width;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.blackPaint = new Paint();
        this.whitePaint = new Paint();
        this.blackPaint.setColor(Color.BLACK);
        this.whitePaint.setColor(Color.WHITE);
        this.whitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
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
                            this.blackPaint);
                } else if (cellState == 0)  {
                    this.bitmapCanvas.drawRect(row * cellWidth, col * cellHeight,
                            (row + 1) * cellWidth, (col + 1) * cellHeight,
                            this.whitePaint);
                }
            }
        }
    }

    public void drawBitmapOnHolderCanvas(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(this.bitmap, 1, 1, this.blackPaint);
    }

    public void initializeGrid(boolean[][] grid) {
        this.interrupt();
        this.grid = grid;
        this.ruleImplementor.applyRule(this.grid, numberOfRows, numberOfColumns);
        this.drawGridOnBitmapCanvas();
        Canvas canvas = this.holder.lockCanvas();
        this.drawBitmapOnHolderCanvas(canvas);
        holder.unlockCanvasAndPost(canvas);
    }
}
