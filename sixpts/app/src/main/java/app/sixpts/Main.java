package app.sixpts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;




public class Main extends AppCompatActivity {
    Button _arriving;
    Button _leaving;
    Button _checkAvail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUp() {;
        _arriving = (Button) findViewById(R.id.arriving);
        _leaving = (Button) findViewById(R.id.leaving);
        _checkAvail = (Button) findViewById(R.id.checkAvail);


        _arriving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent arrive = new Intent("app.sixpts.Arriving");
                startActivity(arrive);
            }
        });

        _leaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leave = new Intent("app.sixpts.Leaving");
                startActivity(leave);
            }
        });

        _checkAvail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkAvail = new Intent("app.sixpts.CheckAvail");
                startActivity(checkAvail);
            }
        });

/**
        _leaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpGet httpGet = new HttpGet(Main.this, _suggestedLot, _leaving);
                httpGet.execute();
                _leaving.setEnabled(false);
            }
        });
**/
    }
}
