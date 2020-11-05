package com.here.android.speedpundit;

import android.location.Location;

public interface GPSCallback
{
    public abstract void onGPSUpdate(Location location);
}
