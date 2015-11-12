package app.sixpts;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.widget.EditText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Map;


/**
 * Created by paulkowa on 11/3/15.
 */
public class Data extends Activity {
    private String _selectedLot;
    private int _month, _day, _hour, _minute, _amp;
    private EditText _sugLotField, _sugRateField, _curRateField;
    private HashMap<String, HashMap<String, Integer>> _map;
    private AssetManager _assMan;
    private String[] _files;


    /**
     *
     */
    public Data(EditText sugLotField, EditText sugRateField, EditText curRateField, Context context) {
        _map = new HashMap<String, HashMap<String, Integer>>();
        _assMan = context.getAssets();
        buildData();

        _selectedLot = "Jarvis A";
        _sugLotField = sugLotField;
        _sugRateField = sugRateField;
        _curRateField = curRateField;

        Calendar c = Calendar.getInstance();
        _hour = c.get(Calendar.HOUR_OF_DAY);
        _minute = c.get(Calendar.MINUTE);
        _amp = c.get(Calendar.AM_PM);
        _month = c.get(Calendar.MONTH);
        _day = c.get(Calendar.DAY_OF_WEEK);

        if ((_minute >= 15 && _minute <= 30) || (_minute <= 45 && _minute >= 30)) { _minute = 30; }
        else if(_minute > 45 && _minute < 60) {
            _minute = 0;
            _hour += 1;
        }
        else { _minute = 0; }
        if (_amp == 1 || _hour == 12) { _hour = _hour - 12; }
    }

    public void updateRating() {
        String suggestion = "";
        int sugRat = 0;
        int curRat = 0;

        /**
         *Using _sugRateField and _sugLotField to display values for testing
         */
        String[] selected = _selectedLot.split(" ");
        try {
            _sugRateField.setText(String.valueOf(_assMan.list("files").length));
            _sugLotField.setText(String.valueOf(_assMan.list("files")[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Error occurs in either buildData or findBest
         */
        //_curRateField.setText(_map.get(selected[0] + selected[1]).get("011200"));

        //findBest(suggestion, sugRat, curRat);

        //_sugLotField.setText(_selectedLot.split(" ")[0] + _selectedLot.split(" ")[1]);
        //_sugLotField.setText(_month + " " + _day + " " + _hour + " " + _minute + " " + _amp);
        //_sugLotField.setText(_selectedLot.split(" ")[0] + _selectedLot.split(" ")[1]);
        //_sugLotField.setText(suggestion);
        //_sugRateField.setText(String.valueOf(sugRat));
        //_curRateField.setText(String.valueOf(curRat));

        //_curRateField.setText(_map.get(_selectedLot).get("011200"));
    }

    public void buildData() {

        try {
            _files = _assMan.list("files");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < _files.length; ++i) {
            Scanner scan;
            HashMap<String, Integer> hash;
            try {
                scan = new Scanner(_assMan.open(_files[i]));
                hash = new HashMap<String, Integer>();

                while (scan.hasNext()) {
                    String[] current = scan.nextLine().split(" ");
                    hash.put(current[0], Integer.valueOf(current[1]));
                }
                _map.put(_files[i].split(".")[0], hash);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void findBest(String sugLot, int sugRat, int curRat) {
        String[] selected = _selectedLot.split(" ");

        if (selected.length == 2) {
            curRat = _map.get(selected[0] + selected[1]).get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp));
            sugRat = _map.get(selected[0] + selected[1]).get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp));
        }
        else {
            curRat = _map.get(_selectedLot).get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp));
            sugRat = _map.get(_selectedLot).get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp));
        }

        sugLot = _selectedLot;

        for (Map.Entry<String, HashMap<String, Integer>> entry : _map.entrySet()) {
            if(entry.getValue().get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp)) < sugRat) {
                sugRat = entry.getValue().get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp));
                sugLot = entry.getKey();
            }
        }
    }

    /**
     *
     * @param selectedLot
     */
    public void setLot(String selectedLot) {
        _selectedLot = selectedLot;
        updateRating();
        //_sugLotField.setText(_month + _day + _hour + _minute + _amp);
    }

    /**
     *
     * @param month
     */
    public void setMonth(int month) {
        _month = month;
        updateRating();
        //_sugLotField.setText(_month + _day + _hour + _minute + _amp);
    }

    /**
     *
     * @param day
     */
    public void setDay(int day) {
        _day = day;
        updateRating();
        //_sugLotField.setText(_month + _day + _hour + _minute + _amp);
    }

    /**
     *
     * @param hour
     */
    public void setHour(int hour) {
        _hour = hour;
        updateRating();
        //_sugLotField.setText(_month + _day + _hour + _minute + _amp);
    }

    /**
     *
     * @param minute
     */
    public void setMinute(int minute) {
        _minute = minute;
        updateRating();
        //_sugLotField.setText(_month + _day + _hour + _minute + _amp);
    }

    /**
     *
     * @param amp
     */
    public void setAmp(int amp) {
        _amp = amp;
        updateRating();
        //_sugLotField.setText(_month + _day + _hour + _minute + _amp);
    }

    public void set_sugLotField(EditText lotField) {
        _sugLotField = lotField;
    }

    public void set_curRateField(EditText lotField) {
        _curRateField = lotField;
    }

    public void set_sugRateField(EditText lotField) {
        _sugRateField = lotField;
    }

    /**
     *
     * @return selected lot
     */
    public String getSelectedLot() {
        return _selectedLot;
    }

    /**
     *
     * @return month
     */
    public int getMonth() { return _month; }

    /**
     *
     * @return day
     */
    public int getDay() {
        return _day;
    }

    /**
     *
     * @return hour
     */
    public int getHour() {
        return _hour;
    }

    /**
     *
     * @return minute
     */
    public int getMinute() { return _minute; }

    /**
     *
     * @return amp
     */
    public int getAmp() { return _amp; }
}
