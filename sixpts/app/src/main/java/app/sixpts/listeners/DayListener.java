package app.sixpts.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;

import app.sixpts.Data;

/**
 * Created by paulkowa on 11/3/15.
 */
public class DayListener implements AdapterView.OnItemSelectedListener {
    private Spinner _spin;
    private Data _data;
    private HashMap<String, Integer> _map;

    public DayListener() {}
    /**
     * @param spin
     * @param data
     */
    public DayListener(Spinner spin, Data data) {
        _data = data;
        _spin = spin;
        _map = new HashMap<String, Integer>();
        for(int i = 1; i < 8; ++i) {
            switch(i) {
                case 1:
                    _map.put("Sunday", 1);
                    break;
                case 2:
                    _map.put("Monday", 2);
                    break;
                case 3:
                    _map.put("Tuesday", 3);
                    break;
                case 4:
                    _map.put("Wednesday", 4);
                    break;
                case 5:
                    _map.put("Thursday", 5);
                    break;
                case 6:
                    _map.put("Friday", 6);
                    break;
                case 7:
                    _map.put("Saturday", 7);
                    break;
            }
        }
    }

    /**
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _data.setDay(_map.get((String) _spin.getSelectedItem()));
    }

    /**
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
