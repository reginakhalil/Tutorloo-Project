package com.CP317.tutorloo.BoundaryClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.CP317.tutorloo.R;

public class RegisterActivity extends AppCompatActivity {
    private ImageButton mPrevious;
    private Button mStudent, mTutor;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerview);
        mPrevious = (ImageButton) findViewById(R.id.previous);
        mStudent = (Button) findViewById((R.id.StudentRegister));
        mTutor = (Button) findViewById((R.id.TutorRegister));

        mPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                return;
            }
        });

        mStudent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, StudentRegisterActivity.class);
                startActivity(intent);
                return;
            }
        });

        mTutor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, TutorRegisterActivity.class);
                startActivity(intent);
                return;
            }
        });

    }
}
