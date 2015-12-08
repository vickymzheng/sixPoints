package app.sixpts.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import app.sixpts.Data;

/**
 * Created by paulkowa on 11/3/15.
 */
public class LotListener implements AdapterView.OnItemSelectedListener {
    Spinner _spin;
    Data _data;

    /**
     * @param spin
     * @param data
     *
     */
    public LotListener(Spinner spin, Data data) {
        _data = data;
        _spin = spin;
    }

    /**
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _data.setLot((String) _spin.getSelectedItem());
        _data.updateRating();

    }

    /**
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        _data.setLot((String) _spin.getSelectedItem());
    }
}
