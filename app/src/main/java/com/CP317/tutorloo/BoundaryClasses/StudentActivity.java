package com.CP317.tutorloo.BoundaryClasses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.CP317.tutorloo.Database_Helper;
import com.CP317.tutorloo.R;

public class StudentActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    //THIS ARRAY IS ONLY FOR TESTING PURPOSES. IT MUST BE UPDATED TO PULL INFO FROM THE DB- REGINA
    private static final String[] COURSES = new String[] {
            "CP104", "CP164", "CP264", "CP216", "CP220", "CP317", "CP386"
    };

    private static final String[] PROGRAMS = new String[] {
            "Computer Science", "BBA & CS", "Psychology & CS", "Math and CS"
    };

    private ImageButton mSearch;
    private AutoCompleteTextView mName;
    private AutoCompleteTextView mCourse;
    private AutoCompleteTextView mProgram;
    public static int [] tutorID= new int[10];
    Database_Helper db;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentview);

        //FOR TESTING PURPOSED. MUST BE UPDATED TO TALK TO DB- REGINA
        AutoCompleteTextView searchCourse = findViewById(R.id.searchByCourse);
        ArrayAdapter<String> course_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, COURSES);
        searchCourse.setAdapter(course_adapter);

        //FOR TESTING PURPOSED. MUST BE UPDATED TO TALK TO DB- REGINA
        AutoCompleteTextView searchProgram = findViewById(R.id.searchByProgram);
        ArrayAdapter<String> program_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, PROGRAMS);
        searchProgram.setAdapter(program_adapter);

        //Search button functionality
        mSearch = (ImageButton) findViewById(R.id.searchButton);

        //Search boxes functionality
        mName = (AutoCompleteTextView) findViewById(R.id.searchByName);
        mCourse = (AutoCompleteTextView) findViewById(R.id.searchByCourse);
        mProgram = (AutoCompleteTextView) findViewById(R.id.searchByProgram);

        //The database
        db = new Database_Helper(this);

        //Must be updated to show error message if search field is empty. - Regina
        mSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                //Once the search button has been clicked, send all the criteria to the database
                sendCriteria();

                //

                Intent intent = new Intent(StudentActivity.this, TutorListActivity.class);
                startActivity(intent);
                return;
            }
        });

    }
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.profile_popup);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(StudentActivity.this, StudentProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                //Needs to be updated
                final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("Registered", false);
                editor.apply();

                Intent intent2 = new Intent(StudentActivity.this, LoginActivity.class);
                startActivity(intent2);
                return true;
            default:
                return false;
        }
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

    public static int[] getTutorID()
    {
        return tutorID;
    }


    /*This method will send the criteria to the DB_Helper class
      in the form on an array
      array[0] = Name
      array[1] = Course
      array[2] = Program

      Note:
      CURRENTLY, WE ARE ONLY DISPLAYING WHETHER OR NOT THE MATCHES HAVE BEEN FOUND OR NOT - Divya
      A MESSAGE SIMPLY POPS UP
      THE FUNCTIONALITY WILL BE ADDED LATER

     */
    public void sendCriteria()
    {
        //Array that will store all of the criteria
        String[] criteriaArray= new String[3];

        //Get the Strings inside the text-boxes
        final String name = mName.getText().toString();
        final String course = mCourse.getText().toString();
        final String program = mProgram.getText().toString();

        //Add the strings to the corresponding array index
        if (!name.isEmpty())
            criteriaArray[0]=name;
        if(!course.isEmpty())
            criteriaArray[1]= course;
        if(!program.isEmpty())
            criteriaArray[2]= program;

        //Get the database in here and send the criteria by doing db.findTutors(criteriaArray)
        tutorID= db.findTutors(criteriaArray);

        //If the array is not empty
        if(tutorID.length !=0)
        {
            //Display to user that matches have been found and change view to TutorList Activity
            Toast.makeText(getApplicationContext(), "Matches Found", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(StudentActivity.this, TutorListActivity.class);
            startActivity(intent);
            finish();
        }

        //If it is empty (return No tutors found)
        else{
            Toast.makeText(getApplicationContext(), "No Matches Found", Toast.LENGTH_LONG).show();
        }

        tutorID = null;
    }




}
