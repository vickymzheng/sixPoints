package app.sixpts.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import app.sixpts.Data;

/**
 * Created by paulkowa on 11/3/15.
 */
public class apmListener implements AdapterView.OnItemSelectedListener {
    Spinner _spin;
    Data _data;

    /**
     * @param spin
     * @param data
     */
    public apmListener(Spinner spin, Data data) {
        _data = data;
        _spin = spin;
        if(_data.getAmp() == 0) { _spin.setSelection(0); }
        else { _spin.setSelection(1); }
    }

    /**
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(((String)_spin.getSelectedItem()).equals("AM")) { _data.setAmp(0); }
        else { _data.setAmp(1); }

    }

    /**
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
