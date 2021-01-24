package com.CP317.tutorloo.BoundaryClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.CP317.tutorloo.R;

public class HelpViewActivity extends AppCompatActivity {
    private ImageButton mPrevious;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpview);

        mPrevious = (ImageButton) findViewById(R.id.help_previous);

        mPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HelpViewActivity.this, LoginActivity.class);
                startActivity(intent);
                return;
            }
        });
    }
}

