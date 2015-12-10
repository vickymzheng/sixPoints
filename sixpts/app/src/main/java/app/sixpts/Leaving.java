package app.sixpts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import app.sixpts.R;
import app.sixpts.TaskHandlers.HttpPush;
import app.sixpts.listeners.DayListener;
import app.sixpts.listeners.HourListener;
import app.sixpts.listeners.LotListener;
import app.sixpts.listeners.MinuteListener;
import app.sixpts.listeners.MonthListener;
import app.sixpts.listeners.apmListener;

public class Leaving extends AppCompatActivity {
    Spinner monthSpinner, daySpinner, hourSpinner,
            minuteSpinner, apmSpinner, lotSpinner;
    Data data;
    Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaving);
        setUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leaving, menu);
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
        checkoutButton = (Button) findViewById(R.id.outButton);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkoutButton.setEnabled(false);
                HttpPush httpPush = new HttpPush(Leaving.this, Leaving.this, Leaving.this, checkoutButton);
                httpPush.execute();
            }
        });

        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.month_array, R.layout.monthspinner_layout);
        monthSpinner.setAdapter(monthAdapter);
        monthSpinner.setOnItemSelectedListener(new MonthListener(monthSpinner, data));

        daySpinner = (Spinner) findViewById(R.id.daySpinner);
        ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this, R.array.day_array, R.layout.dayspinner_layout);
        daySpinner.setAdapter(dayAdapter);
        daySpinner.setOnItemSelectedListener(new DayListener(daySpinner, data));

        hourSpinner = (Spinner) findViewById(R.id.hourSpinner);
        ArrayAdapter hourAdapter = ArrayAdapter.createFromResource(this, R.array.hour_array, R.layout.hourspinner_layout);
        hourSpinner.setAdapter(hourAdapter);
        hourSpinner.setOnItemSelectedListener(new HourListener(hourSpinner, data));

        minuteSpinner = (Spinner) findViewById(R.id.minuteSpinner);
        ArrayAdapter minuteAdapter = ArrayAdapter.createFromResource(this, R.array.minute_array, R.layout.minutespinner_layout);
        minuteSpinner.setAdapter(minuteAdapter);
        minuteSpinner.setOnItemSelectedListener(new MinuteListener(minuteSpinner, data));

        apmSpinner = (Spinner) findViewById(R.id.apmSpinner);
        ArrayAdapter apmAdapter = ArrayAdapter.createFromResource(this, R.array.apm_array, R.layout.apmspinner_layout);
        apmSpinner.setAdapter(apmAdapter);
        apmSpinner.setOnItemSelectedListener(new apmListener(apmSpinner, data));

        lotSpinner = (Spinner) findViewById(R.id.mapSpinner);
        ArrayAdapter mapAdapter = ArrayAdapter.createFromResource(this, R.array.map_array, R.layout.lotspinner_layout);
        lotSpinner.setAdapter(mapAdapter);
        lotSpinner.setOnItemSelectedListener(new LotListener(lotSpinner, data));
    }
}
