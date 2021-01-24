package com.CP317.tutorloo.BoundaryClasses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.CP317.tutorloo.Database_Helper;
import com.CP317.tutorloo.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import com.CP317.tutorloo.EntityClasses.Tutor;

public class TutorRegisterActivity extends AppCompatActivity {

    Database_Helper db;
    private ImageButton mPrevious;
    private Button mSubmit;
    private EditText mEmail, mFirst, mLast, mPassword, mDOB, mConPass, mYearofStudy, mProgram, mCourse, mHourlyfee, mBio;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$*%^&+=_])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorregisteractivity);

        db = new Database_Helper(this);

        mPrevious = (ImageButton) findViewById(R.id.previous2);
        mSubmit = (Button) findViewById(R.id.submitb);
        mEmail = (EditText) findViewById(R.id.email);
        mFirst = (EditText) findViewById(R.id.firstname);
        mLast = (EditText) findViewById(R.id.lastname);
        mPassword = (EditText) findViewById(R.id.password);
        mConPass = (EditText) findViewById(R.id.confpass);
        mDOB = (EditText) findViewById(R.id.dob);

        mYearofStudy = (EditText) findViewById(R.id.yearofstudy);
        mProgram = (EditText) findViewById(R.id.Program);
        mCourse = (EditText) findViewById(R.id.Course);
        mHourlyfee = (EditText) findViewById(R.id.hourlyfee);
        mBio = (EditText) findViewById(R.id.Bio);

        mPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TutorRegisterActivity.this, RegisterActivity.class);
                startActivity(intent);
                return;
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SetValidation();
                return;
            }
        });
    }

    //Hides keyboard when clicking off edit text box
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    //-----------Validate email and password-------------
    public void SetValidation()
    {
        //set inputs to strings
        String email = mEmail.getText().toString();
        String firstname = mFirst.getText().toString();
        String lastname = mLast.getText().toString();
        String password = mPassword.getText().toString();
        String conPassword = mConPass.getText().toString();
        String program = mProgram.getText().toString();
        String course = mCourse.getText().toString();
        String bio = mBio.getText().toString();
        String yearofstudy = mYearofStudy.getText().toString();
        String hourlyfee = mHourlyfee.getText().toString();

        boolean p_entered, c_entered, y_entered, h_entered, b_entered;
        boolean isfirstnamevalid;
        boolean islastnamevalid;
        boolean isEmailValid;
        boolean isPasswordValid;
        boolean isdobValid;

        //validate date of birth
        String stringDOB = mDOB.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        java.util.Date DOB = new java.util.Date();
        try {
            DOB = format.parse(stringDOB);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final java.sql.Date finalDOB = new java.sql.Date(DOB.getTime());

        //Check for valid date of birth
        if (stringDOB.isEmpty()) {
            mDOB.setError("Field can't be empty");
            isdobValid = false;
        }
        else {
            isdobValid = true;
        }
        // Check for a valid first name
        if (firstname.isEmpty()) {
            mFirst.setError("Field can't be empty");
            isfirstnamevalid = false;
        } else  {
            isfirstnamevalid = true;
        }

        // Check for a valid last name
        if (lastname.isEmpty()) {
            mLast.setError("Field can't be empty");
            islastnamevalid = false;
        } else  {
            islastnamevalid = true;
        }

        // Check for a valid email address.
        if (email.isEmpty()) {
            mEmail.setError("Field can't be empty");
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Please enter a valid email address");
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }


        // Check for a valid password.
        if (password.isEmpty()) {
            mPassword.setError("Field can't be empty");
            isPasswordValid = false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            mPassword.setError("Must contain at least 4 characters, any letter, and any speical character");
            isPasswordValid = false;
        } else if(!conPassword.equals(password)) {
            mConPass.setError("Passwords do not match");
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }
        //Check to see if the bio was entered
        if (bio.isEmpty()) {
            mBio.setError("Field cannot be empty");
            b_entered = false;
        }
        else {
            b_entered = true;
        }


        //Check to see if the program was entered
        if (program.isEmpty()) {
            mProgram.setError("Field cannot be empty");
            p_entered = false;
        }
        else {
            p_entered = true;
        }

        //Check to see if the courses were entered (only 1 course can be entered
        if (course.isEmpty()) {
            mCourse.setError("Field cannot be empty");
            c_entered = false;
        }
        else {
            c_entered = true;
        }

        //Check to see if the year of study is empty
        if (yearofstudy.isEmpty()){
            mYearofStudy.setError("Field cannot be empty");
            y_entered = false;
        }
        else {
            y_entered = true;
        }


        //Check to see if the hourly fee has been entered
        if(hourlyfee.isEmpty()){
            mHourlyfee.setError("Field cannot be empty");
            h_entered = false;
        }
        else {
            h_entered = true;
        }

        //Check to see if the hourly fee has been entered
        if(bio.isEmpty()){
            mBio.setError("Field cannot be empty");
            b_entered = false;
        }
        else {
            b_entered = true;
        }


        if (isfirstnamevalid && isEmailValid && islastnamevalid && isPasswordValid &&
                isdobValid && p_entered && c_entered && y_entered && h_entered && b_entered) {
            //-----------------Check if user was inserted in the database------------
            Tutor tutor = new Tutor();
            tutor.setFirstName(firstname);
            tutor.setLastName(lastname);
            tutor.setEmail(email);
            tutor.setPassword(password);
            tutor.setDob(stringDOB);
            tutor.setCourses(course);
            tutor.setProgram(program);
            tutor.setYear_of_study(yearofstudy);
            tutor.setBiography(bio);
            tutor.setHourlyRate(hourlyfee);


            boolean insert = db.insertTutor(tutor);
            if (insert == true){

                //Add email and username to shared preferences
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("Registered", true);
                editor.putString("Username", email);
                editor.putString("Password", password);
                editor.putBoolean("Student", false);

                editor.apply();
                Toast.makeText(TutorRegisterActivity.this, "Successfully Created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TutorRegisterActivity.this, TutorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }

    }


}
