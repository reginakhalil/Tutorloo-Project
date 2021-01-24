package com.CP317.tutorloo.BoundaryClasses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.CP317.tutorloo.Database_Helper;
import com.CP317.tutorloo.EntityClasses.Student;
import com.CP317.tutorloo.R;

public class StudentProfileActivity extends AppCompatActivity {

    private Button mEditProfile;
    private ImageButton mPrevious;
    private EditText mEmail, mDOB;
    private TextView mName;
    Database_Helper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new Database_Helper(this);
        setContentView(R.layout.activity_studentprofileview);


        db = new Database_Helper(this);
        mEditProfile = (Button) findViewById(R.id.Edit);
        mPrevious = (ImageButton) findViewById(R.id.studentPrevious);
        mEmail = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        mDOB = (EditText) findViewById(R.id.editTextDate);
        mName = (TextView) findViewById(R.id.textView13);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String email = sharedPref.getString("Username", null);
        String password = sharedPref.getString("Password", null);
        //Log.e("Email",email);
        //Log.e("Password", password);
        Student student =  new Student();
//        try {
//            student = db.getStudent(email, password);
//        }catch (Exception e)
//        {
//            Log.e("Yikers", "yikes.");
//        }

        student = db.getStudent(email, password);


        mEmail.setText(student.getEmail());
        mDOB.setText(student.getdob());
        mName.setText(student.getfirstName() + " " + student.getlastName());

        mEditProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StudentProfileActivity.this, EditProfileStudentActivity.class);
                startActivity(intent);
                return;
            }
        });

        //Must be updated to correspond with the actual previous page.- Regina
        mPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StudentProfileActivity.this, StudentActivity.class);
                startActivity(intent);
                return;
            }
        });
    }
}
