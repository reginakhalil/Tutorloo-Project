package com.CP317.tutorloo.BoundaryClasses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class ProxyActivity extends AppCompatActivity {


    //Database_Helper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //db = new Database_Helper(this);
        super.onCreate(savedInstanceState);

        Intent activityIntent;


        Boolean registered;
        Boolean student;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        registered = sharedPref.getBoolean("Registered", false);
        student = sharedPref.getBoolean("Student", false);

        if (!registered) {
            activityIntent  = new Intent(this, LoginActivity.class);
        } else {
            if (student) {
                activityIntent = new Intent(this, StudentActivity.class);
            }else {
                activityIntent = new Intent(this, TutorActivity.class);

            }
        }

        startActivity(activityIntent);
        finish();

    }


}
