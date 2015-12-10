package app.sixpts.TaskHandlers;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import app.sixpts.Data;
import app.sixpts.Leaving;
import app.sixpts.CustomObjects.HttpResult;

/**
 * Created by paulkowa on 12/6/15.
 */
public class HttpPush extends AsyncTask<Void, Integer, String> {
    Context context;
    String httpUrl, JSON_STRING;
    Button button;
    Activity leavingActivity;
    Leaving leaving;
    HttpResult result;
    Data data;

    public HttpPush(Context context, Activity leavingActivity, Leaving leaving, Data data, Button button) {
        this.context = context;
        this.button = button;
        this.leavingActivity = leavingActivity;
        this.leaving = leaving;
        this.data = data;
        result = new HttpResult();
    }

    @Override
    protected String doInBackground(Void... params) {
        int i = 0;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            /**
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            httpURLConnection.setFixedLengthStreamingMode(10000);
            PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
            out.print("0");
            out.close();
            **/
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((JSON_STRING = bufferedReader.readLine()) != null) {
                stringBuilder.append(JSON_STRING + "\n");
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return "Checkout Complete";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Connection Failed...";
    }

    @Override
    protected void onPreExecute() {
        httpUrl = "http://sharingreligion.com/sixpoints/writeServer.php?lot=" + fixLotName(data.getSelectedLot()) + "&type=in";
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String result){
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        //button.setEnabled(true);
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
