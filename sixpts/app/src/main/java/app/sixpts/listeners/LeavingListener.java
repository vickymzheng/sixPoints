package app.sixpts.listeners;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import app.sixpts.Data;

/**
 * Created by paulkowa on 11/3/15.
 */
public class LeavingListener extends AppCompatActivity implements AdapterView.OnClickListener {
    Data _data;

    /**
     * @param data
     */
    public LeavingListener(Data data) {
        _data = data;
    }

    @Override
    public void onClick(View v) {
        Intent leaving = new Intent("app.sixpts.Leaving");
        startActivity(leaving);
    }
}
