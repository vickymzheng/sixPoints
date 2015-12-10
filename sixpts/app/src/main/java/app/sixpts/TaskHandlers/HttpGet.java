package app.sixpts.TaskHandlers;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import app.sixpts.Arriving;
import app.sixpts.CustomObjects.HttpResult;
import app.sixpts.Data;

/**
 * Created by paulkowa on 12/6/15.
 */
public class HttpGet extends AsyncTask<Void, Integer, String> {
    Context context;
    String httpUrl, JSON_STRING;
    Button button;
    Activity arrivingActivity;
    Arriving arriving;
    HttpResult result;
    Data data;

    public HttpGet(Context context, Activity arrivingActivity, Arriving arriving, Data data, Button button) {
        this.context = context;
        this.button = button;
        this.arrivingActivity = arrivingActivity;
        this.arriving = arriving;
        this.data = data;
        result = new HttpResult();
    }

    @Override
    protected String doInBackground(Void... params) {
        int i = 0;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((JSON_STRING = bufferedReader.readLine()) != null) {
                stringBuilder.append(JSON_STRING + "\n");
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            result.setResult(stringBuilder.toString().trim());
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Connection Failed...";
    }

    @Override
    protected void onPreExecute() {
        httpUrl = "http://sharingreligion.com/sixpoints/readServer.php?lot=" +  fixLotName(data.getSelectedLot()) + "&type=in";
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String result){
        arrivingActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                arriving.setOpeningBar(Integer.valueOf(HttpGet.this.result.getResult()));
                arriving.setOpeningText(Integer.valueOf(HttpGet.this.result.getResult()));
            }
        });

        //Toast.makeText(context, result,Toast.LENGTH_SHORT).show();
        button.setEnabled(true);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
    }

    private String fixLotName(String lot) {
        if (lot.contains(" ")) {
            String[] name = lot.split(" ");
            return name[0] + name[1];
        }
        return lot;
    }
}
