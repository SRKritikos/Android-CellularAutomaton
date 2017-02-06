package sl.on.ca.comp208.gameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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
        AutomatonHelper automatonHelper = new AutomatonHelper();
        GameOfLife gameOfLife = new GameOfLife(automatonHelper);
        view.setRuleImplementor(gameOfLife);
        this.resetScreenTimer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("A2");
        menu.add(Menu.NONE,1, Menu.NONE, "Rule");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    public void resetScreenTimer() {
        Timer timer = new Timer();
        timer.schedule(new UIThreadTimerTask(this, new IGoTime() {
            @Override
            public void go() {
                view.nextGeneration();
            }
        }), 0, 100);
    }
}
