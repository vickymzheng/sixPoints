package app.sixpts.TaskHandlers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by paulkowa on 12/6/15.
 */
public class HttpGet extends AsyncTask<Void, Integer, String> {
    Context context;
    TextView textView;
    Button button;
    ProgressDialog progressDialog;

    public HttpGet(Context context, TextView textView, Button button) {
        this.context = context;
        this.textView = textView;
        this.button = button;
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
        Toast.makeText(context, result,Toast.LENGTH_LONG).show();
        button.setEnabled(true);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        //textView.setText("Download in Progress...");
    }
}
