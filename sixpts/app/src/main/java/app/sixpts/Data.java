package app.sixpts;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Calendar;


/**
 * Created by paulkowa on 11/3/15.
 */
public class Data extends AppCompatActivity {
    private String _selectedLot, _suggestedLot;
    private int _month, _day, _hour, _minute, _amp;
    private EditText _sugLotField, _sugRateField, _curRateField;
    private Scanner _scan;
    private HashMap<String, String> _map;


    /**
     *
     */
    public Data(EditText sugLotField, EditText sugRateField, EditText curRateField) {
        Calendar c = Calendar.getInstance();
        _hour = c.get(Calendar.HOUR_OF_DAY);
        _minute = c.get(Calendar.MINUTE);
        _amp = c.get(Calendar.AM_PM);
        _month = c.get(Calendar.MONTH);
        _day = c.get(Calendar.DAY_OF_WEEK);
        _selectedLot = "Jarvis A";
        _sugLotField = sugLotField;
        _sugRateField = sugRateField;
        _curRateField = curRateField;

        if ((_minute >= 15 && _minute <= 30) || (_minute <= 45 && _minute >= 30)) { _minute = 30; }
        else if(_minute > 45 && _minute < 60) {
            _minute = 0;
            _hour += 1;
        }
        else { _minute = 0; }
        if (_amp == 1 || _hour == 12) { _hour = _hour - 12; }
    }
    public void updateRating() {
        //_sugLotField.setText(_selectedLot.split(" ")[0] + _selectedLot.split(" ")[1]);
        _sugLotField.setText(_month + " " + _day + " " + _hour + " " + _minute + " " + _amp);
    }

    private void setSpinners() {

    }

    /**
    public void updateLot() throws IOException {
        _scan = new Scanner(getAssets().open(_selectedLot + ".txt"));
        while (_scan.hasNext()) {
            String[] line = _scan.nextLine().split("\t");
            _map.put(hash(line[0], line[1], line[2], line[3], null), line[4]);
        }
        updateRating();
    }
    **/

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
     * @return suggested lot
     */
    public String getSuggestedLot() { return _suggestedLot; }

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
