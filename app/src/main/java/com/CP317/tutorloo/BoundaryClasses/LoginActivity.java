package com.CP317.tutorloo.BoundaryClasses;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class LoginActivity extends AppCompatActivity {

    private Button mLogin;
    private Button mRegister;
    private Button mTutorLogin;
    private ImageButton mInfo;
    private EditText mEmail, mPassword;
    private Button mReset;
    Database_Helper db;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginview);

        db = new Database_Helper(this);
        mLogin = (Button) findViewById(R.id.login);
        mRegister = (Button) findViewById(R.id.RegisterButton);
        mInfo = (ImageButton) findViewById(R.id.HelpButton);
        mReset = (Button) findViewById(R.id.resetpass);

        mEmail = (EditText) findViewById(R.id.Email_input);
        mPassword = (EditText) findViewById(R.id.password_input);


        //--------------Login Validation---------------------
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginValidation();
                return;
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                return;
            }
        });

        mInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, HelpViewActivity.class);
                startActivity(intent);
                return;
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
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

    //Login validation
    public void loginValidation() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        //Login validation
        boolean email_entered;
        boolean pass_entered;

        //Generate error if fields are empty
        if (email.isEmpty()) {
            mEmail.setError("Please enter an email address");
            email_entered = false;
        }
        else {
            email_entered = true;
        }

        if (password.isEmpty()) {
            mPassword.setError("Please enter your password");
            pass_entered = false;
        }
        else {
            pass_entered = true;
        }

        //If not empty: check if users are registered
        if (pass_entered && email_entered) {
            boolean user_exists = db.checkStudent(email, password);
            boolean tutor_exists = db.checkTutor(email,password);
            if (user_exists) {
                final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("Registered", true);
                editor.putString("Username", email);
                editor.putString("Password", password);
                editor.putBoolean("Student", true);

                editor.apply();

                Toast.makeText(getApplicationContext(), "Successfully login", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
                finish();
                startActivity(intent);

            }
            else if (tutor_exists){
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("Registered", true);
                editor.putString("Username", email);
                editor.putString("Password", password);
                editor.putBoolean("Student", false);

                editor.apply();

                Toast.makeText(getApplicationContext(), "Successfully login", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, TutorActivity.class);
                finish();
                startActivity(intent);


            }
            else {
                Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
            }
        }

    }



}
