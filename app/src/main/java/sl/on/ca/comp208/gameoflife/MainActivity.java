package sl.on.ca.comp208.gameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private DrawView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (DrawView) findViewById(R.id.canvas);
        GliderGunCreator gliderGunCreator = new GliderGunCreator();
        view.setPatternProducer(gliderGunCreator);
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
                Log.i("activity", "Button clcicked");
                if (item.getTitle().toString().equals("Start")) {
                    Log.i("activity", item.getTitle().toString());
                    view.startTimer();
                    item.setTitle(R.string.pause_game);
                } else if (item.getTitle().toString().equals("Pause")) {
                    Log.i("activity", "PAUSE");
                    view.stopTimer();
                    item.setTitle(R.string.start_game);
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
