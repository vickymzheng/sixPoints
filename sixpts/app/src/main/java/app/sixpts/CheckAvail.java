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

import org.w3c.dom.Text;

import app.sixpts.listeners.DayListener;
import app.sixpts.listeners.HourListener;
import app.sixpts.listeners.LotListener;
import app.sixpts.listeners.MinuteListener;
import app.sixpts.listeners.MonthListener;
import app.sixpts.listeners.apmListener;

public class CheckAvail extends AppCompatActivity {
    Spinner _monthSpinner, _daySpinner, _hourSpinner,
            _minuteSpinner, _apmSpinner, _lotSpinner;
    TextView _bestLot, _selectedLot;
    ProgressBar _selectedBar, _suggestedBar;
    Data _data;
    Button _rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_avail);
        _bestLot = (TextView) findViewById(R.id.lotText);
        _selectedLot = (TextView) findViewById(R.id.selectedText);
        _lotSpinner = (Spinner) findViewById(R.id.mapSpinner);
        _rating = (Button) findViewById(R.id.rateButton);
        _selectedBar = (ProgressBar) findViewById(R.id.currentLotBar);
        _suggestedBar = (ProgressBar) findViewById(R.id.suggestedLotBar);
        _data = new Data( _rating, _bestLot, _selectedLot, _selectedBar, _suggestedBar, _lotSpinner, CheckAvail.this);
        setUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check_availability, menu);
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

    public void setUp() {

        _rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _data.getSelectedView().setText(_data.getSelectedLot());
                _data.getRankings().execute();

            }
        });

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
        _lotSpinner.setOnItemSelectedListener(new LotListener(_lotSpinner, _data));
    }
}
