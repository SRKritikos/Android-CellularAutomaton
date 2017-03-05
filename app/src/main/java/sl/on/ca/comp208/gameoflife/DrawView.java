package sl.on.ca.comp208.gameoflife;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

import sl.on.ca.comp208.gameoflife.automatons.AutomatonHelper;
import sl.on.ca.comp208.gameoflife.automatons.GameOfLife;
import sl.on.ca.comp208.gameoflife.colors.ColorPalette;
import sl.on.ca.comp208.gameoflife.colors.ColorPaletteFactory;
import sl.on.ca.comp208.gameoflife.patternproducers.IPatternProducer;
import sl.on.ca.comp208.gameoflife.patternproducers.PatternFactory;

/**
 * Created by srostantkritikos06 on 1/30/2017.
 */
public class DrawView extends SurfaceView implements SurfaceHolder.Callback{
    Context context;
    private boolean isTimerRunning;
    private Timer timer;
    private GameThread gameThread;
    public static AtomicBoolean[][] currentGeneration;
    private IPatternProducer patternProducer;
    private final int NUMBER_OF_COLS = 100;
    private final int NUMBER_OF_ROWS = 100;
    private ColorPaletteFactory colorPaletteFactory;
    private PatternFactory patternFactory;
    private int currentColorBtn;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getHolder().addCallback(this);
        this.resetPatternGrid();
        this.timer = new Timer();
    }

    private void resetPatternGrid() {
        this.currentGeneration = new AtomicBoolean[NUMBER_OF_ROWS][NUMBER_OF_COLS];
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            for (int col = 0; col <NUMBER_OF_COLS; col++) {
                this.currentGeneration[row][col] = new AtomicBoolean(false);
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        startGame();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            int col = y / (getHeight() / NUMBER_OF_COLS);
            int row = x / (getWidth() / NUMBER_OF_ROWS) ;
            Log.i("Game Thread", "Row Col " + row + " " + col);
            this.currentGeneration = this.patternProducer
                    .drawPatternOnGrid(this.currentGeneration, row, col, NUMBER_OF_ROWS, NUMBER_OF_COLS);
            this.drawPatternOnGrid(currentGeneration);
            startGame();
        }
        return super.onTouchEvent(event);
    }

    public void stopTimer() {
        this.timer.cancel();
        this.isTimerRunning = false;
    }

    public void startTimer() {
        Activity activity = (Activity) this.context;
        this.timer = new Timer();
        this.timer.schedule(new UIThreadTimerTask(activity, new IGoTime() {
            @Override
            public void go() {
                if (!gameThread.isAlive()) {
                    startGame();
                }
            }
        }), 0, 50);
        this.isTimerRunning = true;
    }

    private void startGame() {
        AutomatonHelper automatonHelper = new AutomatonHelper();
        GameOfLife gameOfLife = new GameOfLife(automatonHelper);
        this.gameThread = new GameThread(currentGeneration, gameOfLife, getHolder(),
                getWidth(), getHeight(),
                NUMBER_OF_ROWS, NUMBER_OF_COLS,
                colorPaletteFactory.getInstance(currentColorBtn));
        this.gameThread.start();
    }

    public void setPatternFactory(PatternFactory patternFactory) {
        this.patternFactory = patternFactory;
    }

    public void setColorPaletteFactory(ColorPaletteFactory colorPaletteFactory) {
        this.colorPaletteFactory = colorPaletteFactory;
    }

    public void setPatternProducer(int btnId) {
        this.patternProducer = this.patternFactory.getInstance(btnId);
    }

    public void setCanvasColors(int btnId) {
        this.currentColorBtn = btnId;
    }

    public void stopAndRestart() {
        this.stopTimer();
        this.gameThread.interrupt();
        this.resetPatternGrid();
        startGame();
    }

    public void drawPatternOnGrid(AtomicBoolean[][] patternGrid) {
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            for (int col = 0; col < NUMBER_OF_COLS; col++) {
                if (patternGrid[row][col].get()) {
                    this.currentGeneration[row][col].compareAndSet(false, true);
                }
            }
        }
    }
}
