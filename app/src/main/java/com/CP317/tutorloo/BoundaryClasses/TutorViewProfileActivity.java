package com.CP317.tutorloo.BoundaryClasses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.CP317.tutorloo.Database_Helper;
import com.CP317.tutorloo.EntityClasses.Tutor;
import com.CP317.tutorloo.R;


public class TutorViewProfileActivity extends AppCompatActivity {
    private Button mDone;
    private ImageView mBack;
    private TextView mBio, mCourse, mHour, mProg, mYear, mName, mEmail;
    Database_Helper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorviewprofile);
        mDone = (Button) findViewById(R.id.Done);
        mBack = (ImageView)findViewById(R.id.Back);


        mBio = (TextView) findViewById(R.id.white_backer_bio);
        mCourse = (TextView) findViewById(R.id.white_backer_courses);
        mHour = (TextView) findViewById(R.id.white_backer_hourlyrate);
        mProg = (TextView) findViewById(R.id.tutor_program);
        mYear = (TextView) findViewById(R.id.tutor_year);
        mName = (TextView) findViewById(R.id.tutor_name);
        mEmail = (EditText) findViewById(R.id.email);


        db = new Database_Helper(this);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String email = sharedPref.getString("Username", null);
        String password = sharedPref.getString("Password", null);

        Tutor tutor = db.getTutor(email, password);


        mEmail.setText(tutor.getEmail());
        mBio.setText(tutor.getBiography());
        mName.setText(tutor.getfirstName() + " " + tutor.getlastName());
        mCourse.setText(tutor.getCourse());
        mHour.setText(tutor.getHourlyRate());
        mProg.setText(tutor.getYear_of_study());
        mYear.setText(tutor.getYear_of_study());



        mDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TutorViewProfileActivity.this, EditProfileTutorActivity.class);
                startActivity(intent);
                return;
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TutorViewProfileActivity.this, TutorActivity.class);
                startActivity(intent);
                return;
            }
        });
    }
}
