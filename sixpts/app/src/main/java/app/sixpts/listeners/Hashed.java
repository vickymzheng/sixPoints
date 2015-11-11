package app.sixpts.listeners;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import java.util.*;

/**
 * Created by paulkowa on 11/11/15.
 */
public class Hashed extends AppCompatActivity implements Parcelable {
    HashMap<Integer, Integer> map;

    public Hashed() {
        map = new HashMap<Integer, Integer>();
    }

    public Hashed(Parcel in) {
        map = new HashMap<Integer, Integer>();
    }

    private void setUp() {
        getAssets().getLocales();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
