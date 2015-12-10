package app.sixpts.TaskHandlers;
import java.lang.Runnable;
import java.util.Set;
import java.util.Iterator;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import app.sixpts.CheckAvail;
import app.sixpts.Data;
import app.sixpts.CustomObjects.Tuple;


/**
 * Created by paulkowa on 12/6/15.
 */
public class SetRankings extends AsyncTask<Void, Integer, String> {
    Context context;
    ProgressDialog progressDialog;
    Activity checkActivity;
    CheckAvail checkAvail;
    Button button;
    Data data;

    public SetRankings(Context context, Button button, Activity checkActivity, CheckAvail checkAvail, Data data) {
        this.context = context;
        this.button = button;
        this.checkActivity = checkActivity;
        this.checkAvail = checkAvail;
        this.data = data;
    }

    @Override
    protected String doInBackground(Void... params) {
        int i = 0;
        synchronized (this) {
            data.setTuple(findBest());
            while (i < 10) {
                try {
                    wait(50);
                    i++;
                    checkActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            checkAvail.setBestLot(data.getTuple().getSuggestedLot());
                            checkAvail.setSelectedBar(data.getTuple().getSelectedLotValue());
                            checkAvail.setSuggestedBar(data.getTuple().getSuggestedLotValue());
                            checkAvail.updateSelectedBarText();
                            checkAvail.updateSuggestedBarText();
                        }
                    });

                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Update complete...";
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Retrieving data...");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result){
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        button.setEnabled(true);
        progressDialog.hide();
        this.cancel(true);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
    }

    private Tuple findBest() {
        String[] selected = data.getSelectedLot().split(" ");
        Tuple stats = new Tuple();
        stats.setSelectedLot(data.getSelectedLot());

        if (selected.length == 2) {
            stats.setSelectedLotValue(data.getMap().get(selected[0] + selected[1]).get(String.valueOf(data.getMonth()) + String.valueOf(data.getDay()) + String.valueOf(data.getHour()) + String.valueOf(data.getMinute()) + String.valueOf(data.getAmp())));
        }
        else {
            stats.setSelectedLotValue(data.getMap().get(data.getSelectedLot()).get(String.valueOf(data.getMonth()) + String.valueOf(data.getDay()) + String.valueOf(data.getHour()) + String.valueOf(data.getMinute()) + String.valueOf(data.getAmp())));
        }

        Set<String> lots = data.getMap().keySet();
        Iterator lotIter = lots.iterator();
        // Iterate through lot lists
        while(lotIter.hasNext()) {
            String current = lotIter.next().toString();
            int value = data.getMap().get(current).get(String.valueOf(data.getMonth()) + String.valueOf(data.getDay()) + String.valueOf(data.getHour()) + String.valueOf(data.getMinute()) + String.valueOf(data.getAmp()));
            if(value < stats.getSuggestedLotValue()) {
                stats.setSuggestedLotValue(value);
                stats.setSuggestedLot(current);
            }
            /**
             Set dates = data.getMap().get(current).keySet();
             Iterator dateIter = dates.iterator();
             // Iterate through key values in lot lists
             while (dateIter.hasNext()) {
             String time = dateIter.next().toString();
             if (data.getMap().get(current).get(time) < stats.getSuggestedLotValue()) {
             stats.setSuggestedLotValue(data.getMap().get(current).get(time));
             stats.setSuggestedLot(current);
             }
             }
             **/
        }
        return stats;
    }
}