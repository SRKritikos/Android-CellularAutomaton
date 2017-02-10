package sl.on.ca.comp208.gameoflife;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;

import sl.on.ca.comp208.gameoflife.PatternProducers.IPatternProducer;

/**
 * Created by srostantkritikos06 on 1/30/2017.
 */
public class DrawView extends SurfaceView implements SurfaceHolder.Callback{
    Context context;
    private boolean isTimerRunning;
    private Timer timer;
    private GameThread gameThread;
    boolean[][] gridInitialState ;
    private IPatternProducer patternProducer;
    private final int NUMBER_OF_COLS = 100;
    private final int NUMBER_OF_ROWS = 100;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getHolder().addCallback(this);
        this.gridInitialState = new boolean[NUMBER_OF_ROWS][NUMBER_OF_COLS];
    }
    
//    private void drawGridLines() {
//        int width = getWidth();
//        int height = getHeight();
//        for (int i = 1; i < NUMBER_OF_COLS; i++) {
//            this.bitmapCanvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
//        }
//        for (int i = 1; i < NUMBER_OF_ROWS; i++) {
//            this.bitmapCanvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
//        }
//    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        AutomatonHelper automatonHelper = new AutomatonHelper();
        GameOfLife gameOfLife = new GameOfLife(automatonHelper);
        this.gameThread = new GameThread(gameOfLife, surfaceHolder, getWidth(), getHeight(), NUMBER_OF_ROWS, NUMBER_OF_COLS);
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
            int row = y / (getWidth() / NUMBER_OF_ROWS);
            int col = x / (getHeight() / NUMBER_OF_COLS) ;
            this.gridInitialState = this.patternProducer.drawPatternOnGrid(this.gridInitialState, row, col, NUMBER_OF_ROWS, NUMBER_OF_COLS);
            this.gameThread.initializeGrid(gridInitialState);
        }
        return super.onTouchEvent(event);
    }

    public void stopTimer() {
        this.timer.cancel();
        this.isTimerRunning = false;
    }

    public void startTimer() {
        this.timer = new Timer();
        Activity activity = (Activity) this.context;
        this.timer.schedule(new UIThreadTimerTask(activity, new IGoTime() {
            @Override
            public void go() {
                gameThread.start();
            }
        }), 0, 50);
        this.isTimerRunning = true;
    }

    public void setPatternProducer(IPatternProducer patternProducer) {
        this.patternProducer = patternProducer;
    }
}
