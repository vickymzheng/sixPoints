package app.sixpts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import app.sixpts.R;
import app.sixpts.TaskHandlers.HttpGet;
import app.sixpts.listeners.LotListener;

public class Arriving extends AppCompatActivity {
    Data data;
    Spinner lotSpinner;
    Button arriveButton;
    ProgressBar openingBar;
    TextView openingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arriving);
        setUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_arriving, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUp() {
        data = new Data();

        openingBar = (ProgressBar) findViewById(R.id.openingBar);
        openingText = (TextView) findViewById(R.id.selectedLotProgressText);
        arriveButton = (Button) findViewById(R.id.arriveButton);

        arriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arriveButton.setEnabled(false);
                HttpGet httpGet = new HttpGet(Arriving.this, Arriving.this, Arriving.this, arriveButton);
                httpGet.execute();
            }
        });

        lotSpinner = (Spinner) findViewById(R.id.mapSpinner);
        ArrayAdapter mapAdapter = ArrayAdapter.createFromResource(this, R.array.map_array, R.layout.lotspinner_layout);
        lotSpinner.setAdapter(mapAdapter);
        lotSpinner.setOnItemSelectedListener(new LotListener(lotSpinner, data));
    }

    public void setOpeningBar(int progress) { openingBar.setProgress(progress); }
    public void setOpeningText(int progress) { openingText.setText(String.valueOf(progress));}


}
