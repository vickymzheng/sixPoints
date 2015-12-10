package app.sixpts.TaskHandlers;
import java.lang.Runnable;
import java.util.Set;
import java.util.Iterator;

import android.app.Activity;
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
        synchronized (this) {
            data.setTuple(findBest());
            checkActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    checkAvail.setBestLot(data.getTuple().getSuggestedLot());
                }
            });

            while (checkAvail.getSelectedBar() != data.getTuple().getSelectedLotValue() || checkAvail.getSuggestedBar() != data.getTuple().getSuggestedLotValue()) {
                try {
                    wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (checkAvail.getSelectedBar() < data.getTuple().getSelectedLotValue()) {
                    checkActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            checkAvail.setSelectedBar(checkAvail.getSelectedBar() + 1);
                            checkAvail.updateSelectedBarText();
                        }
                    });
                } else if (checkAvail.getSelectedBar() > data.getTuple().getSelectedLotValue()) {
                    checkActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            checkAvail.setSelectedBar(checkAvail.getSelectedBar() - 1);
                            checkAvail.updateSelectedBarText();
                        }
                    });
                }

                if (checkAvail.getSuggestedBar() < data.getTuple().getSuggestedLotValue()) {
                    checkActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            checkAvail.setSuggestedBar(checkAvail.getSuggestedBar() + 1);
                            checkAvail.updateSuggestedBarText();
                        }
                    });
                } else if (checkAvail.getSuggestedBar() > data.getTuple().getSuggestedLotValue()) {
                    checkActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            checkAvail.setSuggestedBar(checkAvail.getSuggestedBar() - 1);
                            checkAvail.updateSuggestedBarText();
                        }
                    });
                }
            }
        }
        return "Update complete...";
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result){
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        button.setEnabled(true);
        this.cancel(true);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
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
                stats.setSuggestedLot(fixLotName(current));
            }
        }
        return stats;
    }

    private String fixLotName(String lot) {
        if (lot.charAt(lot.length() - 1) > 64 && lot.charAt(lot.length() - 1) < 91) {
            return lot.substring(0, lot.length() - 1) + " " + lot.charAt(lot.length() - 1);
        }
        return lot;
    }
}