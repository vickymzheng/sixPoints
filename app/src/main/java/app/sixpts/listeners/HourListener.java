package app.sixpts.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import app.sixpts.Data;

import static java.lang.Integer.valueOf;

/**
 * Created by paulkowa on 11/3/15.
 */
public class HourListener implements AdapterView.OnItemSelectedListener {
    Spinner _spin;
    Data _data;


    /**
     * @param spin
     * @param data
     */
    public HourListener(Spinner spin, Data data) {
        _data = data;
        _spin = spin;
        _spin.setSelection(_data.getHour());
    }

    /**
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _data.setHour(valueOf((String)_spin.getSelectedItem()));
    }

    /**
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
