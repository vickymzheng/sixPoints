package app.sixpts.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.HashMap;
import java.lang.String;
import app.sixpts.Data;

/**
 * Created by paulkowa on 11/3/15.
 */
public class MonthListener implements AdapterView.OnItemSelectedListener {
    private Data _data;
    private Spinner _spin;
    private HashMap<String, Integer> _map;

    public MonthListener() {}
    public MonthListener(Spinner spin, Data data) {
        _data = data;
        _spin = spin;
        _spin.setSelection(_data.getMonth());
        _map = new HashMap<String, Integer>();
        for(int i = 0; i < 12; ++i) {
            switch (i) {
                case 0:
                    _map.put("January", 0);
                    break;
                case 1:
                    _map.put("February", 1);
                    break;
                case 2:
                    _map.put("March", 2);
                    break;
                case 3:
                    _map.put("April", 3);
                    break;
                case 4:
                    _map.put("May", 4);
                    break;
                case 5:
                    _map.put("June", 5);
                    break;
                case 6:
                    _map.put("July", 6);
                    break;
                case 7:
                    _map.put("August", 7);
                    break;
                case 8:
                    _map.put("September", 8);
                    break;
                case 9:
                    _map.put("October", 9);
                    break;
                case 10:
                    _map.put("November", 10);
                    break;
                case 11:
                    _map.put("December", 11);
                    break;
            }
        }
    }

    /**
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _data.setMonth(_map.get((String)_spin.getSelectedItem()));
    }

    /**
     *
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
