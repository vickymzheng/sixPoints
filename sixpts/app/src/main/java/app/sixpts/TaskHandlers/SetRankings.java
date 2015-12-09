package app.sixpts.TaskHandlers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import app.sixpts.Data;

/**
 * Created by paulkowa on 12/6/15.
 */
public class SetRankings extends AsyncTask<Void, Integer, String> {
    Context context;
    String selectedLot, suggestedLot;
    ProgressDialog progressDialog;
    HashMap map;
    String[] files;
    AssetManager assMan;
    Data data;
    Button button;

    public SetRankings(Context context, Data data, Button button) {
        this.context = context;
        this.data = data;
        suggestedLot = data.getSelectedLot();
        selectedLot = data.getSelectedLot();
        this.button = button;
        map = new HashMap<String, HashMap<String, Integer>>();
        assMan = context.getAssets();

    }

    @Override
    protected String doInBackground(Void... params) {
        int i = 0;
        synchronized (this) {
            while (i < 10) {
                try {
                    wait(100);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Download complete...";
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download in Progress...");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String result){
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        button.setEnabled(true);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        //textView.setText("Download in Progress...");
    }

    public void buildData() {

        try {
            files = assMan.list("files");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < files.length; ++i) {
            Scanner scan;
            HashMap<String, Integer> hash;
            try {
                scan = new Scanner(assMan.open(files[i]));
                hash = new HashMap<String, Integer>();

                while (scan.hasNext()) {
                    String[] current = scan.nextLine().split(" ");
                    hash.put(current[0], Integer.valueOf(current[1]));
                }
                map.put(files[i].split(".")[0], hash);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
/**
    private void findBest(String sugLot, int sugRat, int curRat) {
        String[] selected = _selectedLot.split(" ");

        if (selected.length == 2) {
            curRat = map.get(selected[0] + selected[1]).get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp));
            sugRat = map.get(selected[0] + selected[1]).get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp));
        }
        else {
            curRat = map.get(_selectedLot).get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp));
            sugRat = map.get(_selectedLot).get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp));
        }

        sugLot = _selectedLot;

        for (Map.Entry<String, HashMap<String, Integer>> entry : map.entrySet()) {
            if(entry.getValue().get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp)) < sugRat) {
                sugRat = entry.getValue().get(String.valueOf(_month) + String.valueOf(_day) + String.valueOf(_hour) + String.valueOf(_minute) + String.valueOf(_amp));
                sugLot = entry.getKey();
            }
        }
    }
 **/
}
