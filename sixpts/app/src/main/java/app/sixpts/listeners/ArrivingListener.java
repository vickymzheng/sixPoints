package app.sixpts.listeners;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.app.AppCompatActivity;
import app.sixpts.Data;

/**
 * Created by paulkowa on 11/3/15.
 */
public class ArrivingListener extends AppCompatActivity implements AdapterView.OnClickListener {
    Data _data;

    /**
     * @param data
     */
    public ArrivingListener(Data data) {
        _data = data;
    }

    @Override
    public void onClick(View v) {
        Intent arriving = new Intent("app.sixpts.Arriving");
        startActivity(arriving);
    }
}
