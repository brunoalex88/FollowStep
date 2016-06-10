package com.obruno.followstep.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.obruno.followstep.service.LocationServiceClass;

/**
 * Created by obruno on 10/06/2016.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent locationServiceIntent = new Intent(context, LocationServiceClass.class);
        context.startService(locationServiceIntent);
    }

}
