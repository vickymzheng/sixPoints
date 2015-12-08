package app.sixpts.messaging;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by paulkowa on 11/27/15.
 */
public class Recv {
    InputStream in;
    byte[] buff;

    public Recv() throws IOException {
        try {
            dostuff();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void dostuff() throws IOException {
        URL url = null;
        url = new URL("http://home.mdrewitt.com:3000/api/products/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        /**try {
            in = new BufferedInputStream(urlConnection.getInputStream());
            //in.read(buff);
            //stupid android user defined method
            // Do something here
            // readStream(in);
        }
        finally{
            urlConnection.disconnect();
        }**/
    }

    public byte[] getStream(){
        return buff;
    }
}
