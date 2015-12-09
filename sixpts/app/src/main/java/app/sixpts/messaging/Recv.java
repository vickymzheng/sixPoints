package app.sixpts.messaging;

import android.os.AsyncTask;
import android.os.Build;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Recv {
    InputStream in;
    String urlString;
    String outputJSON;
    byte[] buff;

    public Recv() {
        // blank initializer
    }
    public void executeGet(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            new myTask().execute();
        }
    }

    public void setLot(String s){
        urlString = s;
    }

    public String returnGet(){
        return outputJSON;
    }

    public void dostuff() throws IOException {
        URL url = null;
        //"http://home.mdrewitt.com:3000/api/products/"
        url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

    }
    class myTask extends AsyncTask<Void,Void,Void>
    {


        protected void onPreExecute() {
            //display progress dialog.


        }
        protected Void doInBackground(Void... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try{
                //http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt
                //http://142.105.162.72:3000/api/products
                URL url = new URL(urlString);
                connection = (HttpURLConnection)url.openConnection();
//            connection.setInstanceFollowRedirects(false);
//           connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                outputJSON = buffer.toString();
            }
            catch (MalformedURLException e) {

                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
/*        finally{
            if(connection != null){
                connection.disconnect();
            }

            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
*/
            return null;
        }



        protected void onPostExecute(Void result) {
            // dismiss progress dialog and update ui
        }
    }

    public byte[] getStream(){
        return buff;
    }
}
