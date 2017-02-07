package sl.on.ca.comp208.gameoflife;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import java.util.Timer;

/**
 * Created by srostantkritikos06 on 1/30/2017.
 */
public class DrawView extends View {
    Context context;

    private Paint blackPaint;
    private int numberOfColumns;
    private int numberOfRows;
    private int cellHeight;
    private int cellWidth;
    private boolean[][] grid;
    private IRuleImplementor ruleImplementor;
    private Bitmap gridBitmap;
    private Canvas bitmapCanvas;
    private Paint whitePaint;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
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
        canvas.drawBitmap(this.gridBitmap, 1, 1, this.blackPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i("DrawView", "On size changes being called");
        super.onSizeChanged(w, h, oldw, oldh);
        this.gridBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        this.bitmapCanvas = new Canvas(this.gridBitmap);
        this.drawGridLines();
        this.resetScreenTimer();
    }


    public void setRuleImplementor(IRuleImplementor ruleImplementor) {
        this.ruleImplementor = ruleImplementor;
    }

    private void updateCanvas() {
        this.cellHeight = getHeight() / numberOfRows;
        this.cellWidth = getWidth() / numberOfColumns;
        this.ruleImplementor.applyRule(grid, numberOfRows, numberOfColumns);
        boolean notGoingToDraw = true;
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                int cellState = this.ruleImplementor.shouldDraw(row, col);
                if (cellState == 1) {
                    this.bitmapCanvas.drawRect(row * cellWidth, col * cellHeight,
                            (row + 1) * cellWidth, (col + 1) * cellHeight,
                            blackPaint);
                    notGoingToDraw = false;
                } else if (cellState == 0)  {
                    this.bitmapCanvas.drawRect(row * cellWidth, col * cellHeight,
                            (row + 1) * cellWidth, (col + 1) * cellHeight,
                            whitePaint);
                    notGoingToDraw = false;
                }

            }
        }
        setWillNotDraw(notGoingToDraw);
        invalidate();
        this.grid = this.ruleImplementor.getNextGeneration();
    }

    private void drawGridLines() {
        int width = getWidth();
        int height = getHeight();
        for (int i = 1; i < numberOfColumns; i++) {
            this.bitmapCanvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
        }
        for (int i = 1; i < numberOfRows; i++) {
            this.bitmapCanvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
        }
    }


    public void resetScreenTimer() {
        Timer timer = new Timer();
        Activity activity = (Activity) this.context;
        timer.schedule(new UIThreadTimerTask(activity, new IGoTime() {
            @Override
            public void go() {
                updateCanvas();
            }
        }), 0, 100);
    }
}
