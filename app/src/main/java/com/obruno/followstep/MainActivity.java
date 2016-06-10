package com.obruno.followstep;

import android.content.Intent;
import android.location.Location;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.obruno.followstep.location.LocationClass;
import com.obruno.followstep.service.LocationServiceClass;

public class MainActivity extends AppCompatActivity {
    private static final String className = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LocationServiceClass.class);
        startService(intent);
    }
}
