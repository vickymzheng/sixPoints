package app.sixpts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;




public class Main extends AppCompatActivity {
    Button _arriving;
    Button _leaving;
    Button _checkAvail;

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
        //_currentLot = (EditText) findViewById(R.id.curLotValueText);
        //_currentRating = (EditText) findViewById(R.id.ratingValueText);
        //_suggestedLot = (EditText) findViewById(R.id.sugLotValueText);
        //_suggestedRating = (EditText) findViewById(R.id.rateValueText);
        _arriving = (Button) findViewById(R.id.arriving);
        _leaving = (Button) findViewById(R.id.leaving);
        _checkAvail = (Button) findViewById(R.id.checkAvail);


        //_data = new Data(_suggestedLot, _suggestedRating, _currentRating, getApplicationContext());

        _arriving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent arrive = new Intent("app.sixpts.Arriving");
                startActivity(arrive);
            }
        });

        _leaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leave = new Intent("app.sixpts.Leaving");
                startActivity(leave);
            }
        });

        _checkAvail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkAvail = new Intent("app.sixpts.CheckAvail");
                startActivity(checkAvail);
            }
        });

/**
        _leaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpGet httpGet = new HttpGet(Main.this, _suggestedLot, _leaving);
                httpGet.execute();
                _leaving.setEnabled(false);
            }
        });
**/
/**
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
 **/
    }
}
