package app.sixpts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import app.sixpts.listeners.DayListener;
import app.sixpts.listeners.HourListener;
import app.sixpts.listeners.LotListener;
import app.sixpts.listeners.MinuteListener;
import app.sixpts.listeners.MonthListener;
import app.sixpts.listeners.apmListener;

public class Main extends AppCompatActivity {
    Spinner _lotSpinner;
    Spinner _daySpinner;
    Spinner _hourSpinner;
    Spinner _minuteSpinner;
    Spinner _apmSpinner;
    Spinner _monthSpinner;
    EditText _currentLot;
    EditText _currentRating;
    EditText _suggestedLot;
    EditText _suggestedRating;
    Data _data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        _currentLot = (EditText) findViewById(R.id.curLotValueText);
        _currentRating = (EditText) findViewById(R.id.ratingValueText);
        _suggestedLot = (EditText) findViewById(R.id.sugLotValueText);
        _suggestedRating = (EditText) findViewById(R.id.rateValueText);
        _data = new Data(_suggestedLot, _suggestedRating, _currentRating);

        _monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.month_array, R.layout.monthspinner_layout);
        _monthSpinner.setAdapter(monthAdapter);
        _monthSpinner.setOnItemSelectedListener(new MonthListener(_monthSpinner, _data));

        _daySpinner = (Spinner) findViewById(R.id.daySpinner);
        ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this, R.array.day_array, R.layout.dayspinner_layout);
        _daySpinner.setAdapter(dayAdapter);
        _daySpinner.setOnItemSelectedListener(new DayListener(_daySpinner, _data));

        _hourSpinner = (Spinner) findViewById(R.id.hourSpinner);
        ArrayAdapter hourAdapter = ArrayAdapter.createFromResource(this, R.array.hour_array, R.layout.hourspinner_layout);
        _hourSpinner.setAdapter(hourAdapter);
        _hourSpinner.setOnItemSelectedListener(new HourListener(_hourSpinner, _data));

        _minuteSpinner = (Spinner) findViewById(R.id.minuteSpinner);
        ArrayAdapter minuteAdapter = ArrayAdapter.createFromResource(this, R.array.minute_array, R.layout.minutespinner_layout);
        _minuteSpinner.setAdapter(minuteAdapter);
        _minuteSpinner.setOnItemSelectedListener(new MinuteListener(_minuteSpinner, _data));

        _apmSpinner = (Spinner) findViewById(R.id.apmSpinner);
        ArrayAdapter apmAdapter = ArrayAdapter.createFromResource(this, R.array.apm_array, R.layout.apmspinner_layout);
        _apmSpinner.setAdapter(apmAdapter);
        _apmSpinner.setOnItemSelectedListener(new apmListener(_apmSpinner, _data));

        _lotSpinner = (Spinner) findViewById(R.id.mapSpinner);
        ArrayAdapter mapAdapter = ArrayAdapter.createFromResource(this, R.array.map_array, R.layout.lotspinner_layout);
        _lotSpinner.setAdapter(mapAdapter);
        _lotSpinner.setOnItemSelectedListener(new LotListener(_lotSpinner, _data, _currentLot));

        _data.updateRating();
    }
}
