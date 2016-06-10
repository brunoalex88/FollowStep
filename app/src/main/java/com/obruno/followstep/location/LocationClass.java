package com.obruno.followstep.location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;


/**
 * Created by obruno on 09/06/2016.
 */
public class LocationClass implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private String          className = "LocationClass.";
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location        mCurrentLocation;
    private String          mLastUpdateTime;
    private Context         context;
    private boolean         connected;
    private TextView        txLocation;
    private TextView        txTime;

    public LocationClass(Context context) {
        setContext(context);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        connectApi();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        startLocationUpdates();

    }

    public Location getLocation() {
        Log.i("LOG", className + "getLocation()");

        if (isConnected())
            return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        return null;
    }

    protected void startLocationUpdates() {
        Log.i("LOG", className + "starLocationUpdates()");

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, new com.google.android.gms.location.LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        mCurrentLocation = location;
                        mLastUpdateTime  = DateFormat.getTimeInstance().format(new Date());

                        Log.i("LOG", String.valueOf(mCurrentLocation.getLatitude()));
                        Log.i("LOG", String.valueOf(mCurrentLocation.getLongitude()));
                    }
                });
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("LOG", className + "onConnected()");
        setConnected(true);
        createLocationRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", className + "onConnectionSuspended()");
        setConnected(false);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("LOG", className + "onConnectionFailed()");
        setConnected(false);
    }

    private void connectApi() {
        Log.i("LOG", className + "connectApi");
        mGoogleApiClient.connect();
    }

    public Location getmCurrentLocation() {
        return mCurrentLocation;
    }

    public String getmLastUpdateTime() {
        return mLastUpdateTime;
    }

    private void disconnectApi() {
        Log.i("LOG", className + "disconnectApi()");
        mGoogleApiClient.disconnect();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

}
