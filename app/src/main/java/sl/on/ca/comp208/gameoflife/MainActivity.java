package sl.on.ca.comp208.gameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import sl.on.ca.comp208.gameoflife.PatternProducers.PatternFactory;
import sl.on.ca.comp208.gameoflife.PatternProducers.IPatternProducer;

public class MainActivity extends AppCompatActivity {

    private DrawView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (DrawView) findViewById(R.id.canvas);
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
                } else if (item.getTitle().toString().equals("Pause")) {
                    view.stopTimer();
                    item.setTitle(R.string.start_game);
                }
                break;
            case R.id.createGliderBtn:
            case R.id.createGliderGunBtn:
                IPatternProducer patternProducer = PatternFactory.getInstance(item.getItemId());
                view.setPatternProducer(patternProducer);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
