package sl.on.ca.comp208.gameoflife;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import sl.on.ca.comp208.gameoflife.GameRandomizer.GameRandomizer;
import sl.on.ca.comp208.gameoflife.colors.ColorPaletteBuilder;
import sl.on.ca.comp208.gameoflife.colors.ColorPaletteFactory;
import sl.on.ca.comp208.gameoflife.patternproducers.PatternFactory;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private DrawView view;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (DrawView) findViewById(R.id.canvas);
        this.sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        this.sensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        ColorPaletteBuilder colorPaletteBuilder = new ColorPaletteBuilder(this);
        PatternFactory patternFactory = new PatternFactory();
        ColorPaletteFactory colorPaletteFactory = new ColorPaletteFactory(colorPaletteBuilder);
        GameRandomizer gameRandomizer = new GameRandomizer();
        this.view.setGameRandomizer(gameRandomizer);
        this.view.setColorPaletteFactory(colorPaletteFactory);
        this.view.setPatternFactory(patternFactory);
        this.view.setPatternProducer(R.id.createSingleCellBtn);
        this.view.setCanvasColors(R.id.blackWhiteColorBtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.startPauseBtn:
                if (item.getTitle().toString().equals("Start")) {
                    view.startTimer();
                    item.setTitle(R.string.pause_game);
                    item.setIcon(R.drawable.ic_action_pause);
                } else if (item.getTitle().toString().equals("Pause")) {
                    view.stopTimer();
                    item.setTitle(R.string.start_game);
                    item.setIcon(R.drawable.ic_action_play);
                }
                break;
            case R.id.resetGameBtn:
                view.stopAndRestart();
                break;
            case R.id.createTopLeftGliderBtn:
            case R.id.createTopRightGliderBtn:
            case R.id.createBotLeftGliderBtn:
            case R.id.createBotRightGliderBtn:
            case R.id.createGliderGunBtn:
            case R.id.createSingleCellBtn:
                view.setPatternProducer(item.getItemId());
                break;
            case R.id.blackWhiteColorBtn:
            case R.id.invertedColorBtn:
            case R.id.christmasColorBtn:
            case R.id.rainbowColorBtn:
                view.setCanvasColors(item.getItemId());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        double amountDeviceMoved = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        final double moveThreshold = 15D;
        if ( amountDeviceMoved > moveThreshold) {
            this.view.randomizeBoard();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.sensorManager.registerListener(this, this.sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.sensorManager.unregisterListener(this);
    }
}
