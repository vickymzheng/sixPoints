package app.sixpts.TaskHandlers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import app.sixpts.Data;

/**
 * Created by paulkowa on 12/9/15.
 */
public class ReadFiles  extends AsyncTask<Void, Integer, String> {
    Context context;
    ProgressDialog progressDialog;
    AssetManager assMan;
    HashMap<String, HashMap<String, Integer>> map;
    Data data;
    String[] files;

    public ReadFiles(Context context, Data data) {
        this.context = context;
        this.data = data;
        map = new HashMap<String, HashMap<String, Integer>>();

    }

    @Override
    protected String doInBackground(Void... params) {
        int i = 0;
        buildData();
        data.setMap(map);
        synchronized (this) {
            while (i < 10) {
                try {
                    wait(10);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Setup Complete";
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Setting up lot data...");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String result){
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
    }


    /**
     * Builds map of all historical lot data from Assests files
     */
    private void buildData() {
        String line = "";
        String[] lines = new String[0];
        assMan = context.getAssets();

        try {
            files = assMan.list("files");

            for(int i = 0; i < files.length; i++) {

                HashMap<String, Integer> values = new HashMap<String, Integer>();
                InputStream input = assMan.open("files/" + files[i]);
                int size = input.available();
                byte[] buffer = new byte[size];
                input.read(buffer);
                input.close();
                line = new String(buffer);
                lines = line.split("\n");
                int end;

                for(int n = 0; n < lines.length; n++) {
                    end = lines[n].indexOf(" ");
                    values.put(String.valueOf(lines[n].substring(0, end)), Integer.valueOf(lines[n].substring(end + 1, lines[n].length())));
                }

                map.put(files[i].substring(0, files[i].indexOf(".")), values);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


