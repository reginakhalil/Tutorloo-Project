package com.CP317.tutorloo.BoundaryClasses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.CP317.tutorloo.Database_Helper;
import com.CP317.tutorloo.EntityClasses.Tutor;
import com.CP317.tutorloo.R;

public class TutorProfileActivity extends AppCompatActivity {

    private Button mEdit;
    private TextView mBio, mCourse, mHour, mProg, mYear, mName, mEmail;
    Database_Helper db;


    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorprofileview);
        boolean logged;
        db = new Database_Helper(this);


        mEdit = (Button) findViewById(R.id.edit_profile);

        mBio = (TextView) findViewById(R.id.white_backer_bio);
        mCourse = (TextView) findViewById(R.id.white_backer_courses);
        mHour = (TextView) findViewById(R.id.white_backer_hourlyrate);
        mProg = (TextView) findViewById(R.id.tutor_program);
        mYear = (TextView) findViewById(R.id.tutor_year);
        mName = (TextView) findViewById(R.id.tutor_name);
        mEmail = (TextView) findViewById(R.id.tutor_email);


        db = new Database_Helper(this);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String email = sharedPref.getString("Username", null);
        String password = sharedPref.getString("Password", null);
        logged = sharedPref.getBoolean("Student", false);

        Tutor tutor = db.getTutor(email, password);


        mEmail.setText(tutor.getEmail());
        mEmail.setTextSize(12);
        mBio.setText("Bio: \n"+tutor.getBiography());
        mBio.setTextSize(25);
        mName.setText(tutor.getfirstName() + " " + tutor.getlastName());
        mName.setTextSize(20);
        mCourse.setText("Courses Taken: \n"+tutor.getCourse());
        mCourse.setTextSize(25);
        mHour.setText("Hourly Rate: "+tutor.getHourlyRate());
        mHour.setTextSize(25);
        mProg.setText(tutor.getProgram());
        mProg.setTextSize(12);
        mYear.setText("Year of Study: "+tutor.getYear_of_study());
        mYear.setTextSize(12);




        if (!logged){
            mEdit.setVisibility(View.VISIBLE);
            mEdit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(TutorProfileActivity.this, EditProfileTutorActivity.class);
                    startActivity(intent);
                    return;
                }
            });

        }

        //call display profile
       // displayProfile();            //Might not work since we are using another db object

    }



//    public void displayProfile()
//    {
//        Cursor cursor = db.getTutorProfileButton1();
//        int index;
//
//        //Iterate through the cursor and display info in the text boxes
//        while(cursor.moveToNext())
//        {
//            //DISPLAY THE NAME
//            index = cursor.getColumnIndexOrThrow("First_Name");
//            mName.setText(cursor.getString(index));
//
//            //DISPLAY THE LAST NAME
//            index = cursor.getColumnIndexOrThrow("Last_Name");
//            mName.setText( mName.getText() +" "+ cursor.getString(index));
//
//            //DISPLAY THE YEAR
//            index = cursor.getColumnIndexOrThrow("Year_Of_Study");
//            mYear.setText(cursor.getString(index));
//
//            //DISPLAY THE PROGRAM
//            index = cursor.getColumnIndexOrThrow("Program");
//            mProgram.setText(cursor.getString(index));
//
//            //DISPLAY THE BIO
//            index = cursor.getColumnIndexOrThrow("Biography");
//            mBio.setText(cursor.getString(index));
//
//            //DISPLAY THE HOURLY_FEE
//            index = cursor.getColumnIndexOrThrow("Hourly_Fee");
//            mRate.setText(cursor.getString(index));
//
//            //DISPLAY THE COURSES
//            index = cursor.getColumnIndexOrThrow("Courses");
//            mCourses.setText(cursor.getString(index));
//        }
//    }
}
