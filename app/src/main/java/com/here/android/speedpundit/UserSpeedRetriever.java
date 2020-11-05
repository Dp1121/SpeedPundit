package com.here.android.speedpundit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.icu.math.BigDecimal;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.auth.data.model.User;


public class UserSpeedRetriever extends Activity implements GPSCallback{

private GPSManager gpsManager = null;
private double speed = 0.0;
        Boolean isGPSEnabled=false;
        LocationManager locationManager;
        double currentSpeed,kmphSpeed;
        double getSpeed, mphSpeed;
        TextView txtview;
@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        txtview=(TextView)findViewById(R.id.info);
        try {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        }
        } catch (Exception e){
        e.printStackTrace();
        }
        }
public void getCurrentSpeed(View view){
        txtview.setText(getString(R.string.info));
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        gpsManager = new GPSManager(UserSpeedRetriever.this);
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGPSEnabled) {
        gpsManager.startListening(getApplicationContext());
        gpsManager.setGPSCallback(this);
        } else {
        gpsManager.showSettingsAlert();
        }
        }

@RequiresApi(api = Build.VERSION_CODES.N)
@Override
public void onGPSUpdate(Location location) {
        speed = location.getSpeed();
        currentSpeed = round(speed,3, BigDecimal.ROUND_HALF_UP);
        kmphSpeed = round((currentSpeed*3.6),3,BigDecimal.ROUND_HALF_UP);
        mphSpeed = round((kmphSpeed*0.621371), 3, BigDecimal.ROUND_HALF_UP);
        txtview.setText(mphSpeed + "MPH");
        }

@Override
protected void onDestroy() {
        gpsManager.stopListening();
        gpsManager.setGPSCallback(null);
        gpsManager = null;
        super.onDestroy();
        }

@RequiresApi(api = Build.VERSION_CODES.N)
public static double round(double unrounded, int precision, int roundingMode) {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, roundingMode);
        return rounded.doubleValue();
        }


        }
