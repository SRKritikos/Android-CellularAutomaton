package sl.on.ca.comp208.gameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import sl.on.ca.comp208.gameoflife.colors.ColorPaletteBuilder;
import sl.on.ca.comp208.gameoflife.colors.ColorPaletteFactory;
import sl.on.ca.comp208.gameoflife.patternproducers.PatternFactory;

public class MainActivity extends AppCompatActivity {

    private DrawView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (DrawView) findViewById(R.id.canvas);
        ColorPaletteBuilder colorPaletteBuilder = new ColorPaletteBuilder(this);
        PatternFactory patternFactory = new PatternFactory();
        ColorPaletteFactory colorPaletteFactory = new ColorPaletteFactory(colorPaletteBuilder);
        this.view.setColorPaletteFactory(colorPaletteFactory);
        this.view.setPatternFactory(patternFactory);
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
            case R.id.createGliderBtn:
            case R.id.createGliderGunBtn:
                view.setPatternProducer(item.getItemId());
                break;
            case R.id.blackWhiteColorBtn:
            case R.id.invertedColorBtn:
            case R.id.christmasColorBtn:
            case R.id.rainbowColorBtn:
                view.setCanvasColors(item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }
}
