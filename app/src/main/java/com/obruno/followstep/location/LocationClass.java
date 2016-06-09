package com.obruno.followstep.location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by obruno on 09/06/2016.
 */
public class LocationClass implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private String          className = "LocationClass.";
    private GoogleApiClient mGoogleApiClient;
    private Location        mlastLocation;
    private Context         context;
    private boolean         connected;

    public LocationClass(Context context) {
        setContext(context);
        connectApi();

        if (isConnected()) {
            if (mGoogleApiClient != null) {
                mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .addApi(LocationServices.API)
                        .build();
            }
        }

    }

    public Location getLocation() {
        mlastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        return mlastLocation;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("LOG", className + "onConnected()");
        setConnected(true);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", className + "onConnectionSuspended()");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("LOG", className + "onConnectionFailed()");
    }

    private void connectApi() {
        mGoogleApiClient.connect();
    }

    private void disconnectApi() {
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
