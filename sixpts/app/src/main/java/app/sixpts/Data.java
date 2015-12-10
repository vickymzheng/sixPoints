package app.sixpts;

import android.app.Activity;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import app.sixpts.CustomObjects.Tuple;

import java.util.HashMap;
import java.util.Calendar;



/**
 * Created by paulkowa on 11/3/15.
 */
public class Data extends Activity {
    private Context context;
    private String _selectedLot;
    private int _month, _day, _hour, _minute, _amp;
    private HashMap<String, HashMap<String, Integer>> _map;
    private TextView suggestedLot, selectedLot;
    private ProgressBar selectedBar, suggestedBar;
    private Spinner selectedLotSpinner;
    private Tuple tuple;

    public Data() {
        setTime();
    }

    public Data(TextView suggestedLot, TextView selectedLot, ProgressBar selectedBar, ProgressBar suggestedBar, Spinner selectedLotSpinner, Context context) {
        // Set class variables
        this.context = context;
        this.suggestedBar = suggestedBar;
        this.selectedBar = selectedBar;
        this.suggestedLot = suggestedLot;
        this.selectedLot = selectedLot;
        this.selectedLotSpinner = selectedLotSpinner;

        setTime();

    }

    private void setTime() {
        // Set spinner values to current day / time
        Calendar c = Calendar.getInstance();
        _hour = c.get(Calendar.HOUR_OF_DAY);
        _minute = c.get(Calendar.MINUTE);
        _amp = c.get(Calendar.AM_PM);
        _month = c.get(Calendar.MONTH);
        _day = c.get(Calendar.DAY_OF_WEEK);

        // Round time to nearest half hour and set AM/PM
        if ((_minute >= 15 && _minute <= 30) || (_minute <= 45 && _minute >= 30)) { _minute = 30; }
        else if(_minute > 45 && _minute < 60) {
            _minute = 0;
            _hour += 1;
        }
        else { _minute = 0; }
        if (_amp == 1 || _hour == 12) { _hour = _hour - 12; }
    }

    public void setLot(String selectedLot) { _selectedLot = selectedLot; }

    public void setMonth(int month) { _month = month; }

    public void setDay(int day) { _day = day; }

    public void setHour(int hour) { _hour = hour; }

    public void setMinute(int minute) { _minute = minute; }

    public void setAmp(int amp) { _amp = amp; }

    public void setMap(HashMap<String, HashMap<String, Integer>> map) { _map = map; }

    public void setTuple(Tuple tuple) { this.tuple = tuple; }

    public String getSelectedLot() { return _selectedLot; }

    public TextView getSelectedView() { return selectedLot;}

    public int getMonth() { return _month; }

    public int getDay() { return _day; }

    public int getHour() {
        return _hour;
    }

    public int getMinute() { return _minute; }

    public int getAmp() { return _amp; }

    public Tuple getTuple() { return tuple; }

    public HashMap<String, HashMap<String, Integer>> getMap() { return _map; }

}
