package com.CP317.tutorloo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.CP317.tutorloo.EntityClasses.Tutor;
import com.CP317.tutorloo.EntityClasses.Student;


public class Database_Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Users.db";


    public static final String Table_Name_Student = "Student";
    public static final String Table_Name_tutor = "tutor";
//    public static final String Table_Name_user_course = "user_course";
//    public static final String Table_Name_user_photo = "user_photo";
//    public static final String Table_Name_user_program = "user_program";
    public static final String Table_Name_courses = "courses";
    public static final String Table_Name_programs = "programs";
    public static int[] tutorIDs = new int[100];


    public Database_Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ Table_Name_Student+" (Student_id INTEGER,Last_Name VARCHAR,First_Name VARCHAR,Date_Of_Birth DATE,Email STRING,Encrypt_Pass VARCHAR) ");
        sqLiteDatabase.execSQL("CREATE TABLE "+ Table_Name_tutor+" (Tutor_id INTEGER PRIMARY KEY AUTOINCREMENT,Last_Name VARCHAR,First_Name VARCHAR,Date_Of_Birth DATE,Email STRING,Encrypt_Pass VARCHAR,Biography LONGTEXT,Year_Of_Study VARCHAR,Hourly_Fee VARCHAR,Rating INTEGER,Course VARCHAR, Program VARCHAR) ");
//        sqLiteDatabase.execSQL("CREATE TABLE "+ Table_Name_user_course+" (Course_id SMALLINT,Tutor_id SMALLINT) ");
//        sqLiteDatabase.execSQL("CREATE TABLE "+ Table_Name_user_photo+" (Photo_id SMALLINT,Tutor_id SMALLINT,Link TEXT, Time_Added TIMESTAMP, Active BOOLEAN) ");
//        sqLiteDatabase.execSQL("CREATE TABLE "+ Table_Name_user_program+" (Program_id SMALLINT,Tutor_id SMALLINT) ");
        sqLiteDatabase.execSQL("CREATE TABLE "+ Table_Name_courses+" (Course_id SMALLINT,Name VARCHAR) ");
        sqLiteDatabase.execSQL("CREATE TABLE "+ Table_Name_programs+" (Program_id SMALLINT,Name VARCHAR) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Table_Name_Student);
        onCreate(sqLiteDatabase);    }

//    public int getTutorID(Tutor tutor){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String email = tutor.getEmail();
//
//        Cursor cursor = db.rawQuery("Select * from tutor where email=?", new String[]{email});
//        int ID = cursor;
//    }


    public boolean insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("First_Name", student.getfirstName());
        contentValues.put("Last_Name", student.getlastName());
        contentValues.put("Email", student.getEmail());
        contentValues.put("Date_of_Birth", String.valueOf(student.getdob()));
        contentValues.put("Encrypt_Pass", student.getPassword());
        long result = db.insert("Student", null, contentValues);
        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertTutor(Tutor tutor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("First_Name", tutor.getfirstName());
        contentValues.put("Last_Name", tutor.getlastName());
        contentValues.put("Email", tutor.getEmail());
        contentValues.put("Date_of_Birth", String.valueOf(tutor.getdob()));
        contentValues.put("Encrypt_Pass", tutor.getPassword());
        contentValues.put("Course", tutor.getCourse());
        contentValues.put("Program", tutor.getProgram());
        contentValues.put("Year_Of_Study", tutor.getYear_of_study());
        contentValues.put("Biography", tutor.getBiography());
        contentValues.put("Hourly_Fee", tutor.getHourlyRate());
        long result = db.insert("tutor", null, contentValues);
        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }

    public Student getStudent(String email, String password){
        Log.e("MyEmail",email);
        SQLiteDatabase db = this.getReadableDatabase();
        Student student = new Student();
        String query = "Select * from Student where Email=? and Encrypt_Pass=?";
        //Log.e("Query", query);
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        int index;

        while(cursor.moveToNext()) {
            index = cursor.getColumnIndexOrThrow("First_Name");
            student.setFirstName(cursor.getString(index));
            //Log.e("First Name", cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Last_Name");
            student.setLastName(cursor.getString(index));
            //Log.e("Last Name", cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Date_Of_Birth");
            student.setDob(cursor.getString(index));
            //Log.e("DOB", cursor.getString(index));
        }
        student.setEmail(email);

        return student;
    }

    public Tutor getTutor(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Tutor tutor = new Tutor();
        String query = "Select * from tutor where Email=? and Encrypt_Pass=?";
        //Log.e("Query", query);
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        int index;

        while(cursor.moveToNext()) {
            index = cursor.getColumnIndexOrThrow("First_Name");
            tutor.setFirstName(cursor.getString(index));
            //Log.e("First Name", cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Last_Name");
            tutor.setLastName(cursor.getString(index));
            //Log.e("Last Name", cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Biography");
            tutor.setBiography(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Year_Of_Study");
            tutor.setYear_of_study(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Hourly_Fee");
            tutor.setHourlyRate(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Course");
            tutor.setCourses(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Program");
            tutor.setProgram(cursor.getString(index));
            //Log.e("DOB", cursor.getString(index));
        }
        tutor.setEmail(email);

        return tutor;
    }

    //checking if user exists
    public boolean checkStudent(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Student where Email=? and Encrypt_Pass=?", new String[]{email, password});
        int count = cursor.getCount();

        boolean exists;
        if (count > 0) {
            exists  = true;
        }
        else {
            exists = false;
        }

        return exists;
    }

    //checking if tutor exists
    public boolean checkTutor(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery("Select First_Name from tutor where Email=? and Encrypt_Pass=?", new String[]{email, password});
        int count = cursor.getCount();

        //cursor = db.rawQuery("Select Tutor_id from tutor where First_Name=?", new String[]{name});

        boolean exists;

        if (count > 0) {
            exists  = true;
        }
        else {
            exists = false;
        }

        return exists;
    }


    //Input: String of criteria array from (StudentActivity)
    //Output: Returns an array of tutorID's containing all of the matches
    //Finds the tutor id based on the criteria given
    public int[] findTutors(String[] criteriaArray) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Can only store 100 tutors


        //Depending on how the array is filled, there will be 7 SQL statements
        //Each SQL statement searches the database and returns tutorIDs
        //Note: !criteraArray[i] MAY NOT WORK TO CHECK IF IT IS NULL
        //      may have to fill the array with zeros in Student Activity and
        //      check if it is non zero here.

        String name = criteriaArray[0];
        String course = criteriaArray[1];
        String program = criteriaArray[2];

        Cursor cursor = null;

        //IF WE ARE ONLY SEARCHING BY TUTOR NAME
        if (name != null && course == null && program == null)
        {
            cursor = db.rawQuery("Select Tutor_id from tutor where First_Name=?", new String[]{name});
        }

        //IF WE ARE ONLY SEARCHING BY COURSE
        else if (name == null && course != null && program == null)
        {
            //Complete a JOIN between the User_Courses table and the tutor table
            cursor = db.rawQuery("Select Tutor_id from tutor where Course=?", new String[]{course});
        }

        //IF WE ARE ONLY SEARCHING BY TUTOR PROGRAM
        else if (name == null && course == null && program != null)
        {
            cursor = db.rawQuery("Select Tutor_id from tutor where Program=?", new String[]{program});
        }

        /*
        //IF WE ARE ONLY SEARCHING BY TUTOR NAME & COURSE //CRASHES WITH ARIA AND CP386
        else if (name != null && course != null && program==null)
        {
            cursor = db.rawQuery("Select Tutor_id from tutor where First_Name=? and Course=?", new String[]{name, course});
        }

        //IF WE ARE ONLY SEARCHING BY TUTOR NAME & PROGRAM
        else if (name != null && course == null && program != null) {
            cursor = db.rawQuery("Select Tutor_id from tutor where First_Name=? and Program=?", new String[]{name, program});
        }

        //IF WE ARE ONLY SEARCHING BY COURSE AND PROGRAM
        else if (name == null && course != null && program != null) {
            cursor = db.rawQuery("Select Tutor_id from tutor where Course=? and Program=?", new String[]{course, program});
        }

        //IF WE ARE ONLY SEARCHING BY ALL CRITERIA
        else if (name != null && course != null && program != null) {
            cursor = db.rawQuery("Select Tutor_id from Tutor where First_Name= ? and Course=? and Program=?", new String[]{name, course, program});
        }*/

        //iterate through cursor and put all results into the tutorIDs array
        int i=0;

        while (cursor.moveToNext())
        {
            //0- because we need the tutor id which is in column 0
            tutorIDs[i] = Integer.parseInt(cursor.getString(0));
            Log log = null;
            log.e("Success:",Integer.toString(tutorIDs[i]));
            i++;
        }

        Log.e("TutorId[0] in fun1",Integer.toString(tutorIDs[0])); //should print 1
        Log.e("TutorId[1] in fun1",Integer.toString(tutorIDs[1])); //should print 2

        return tutorIDs;
    }

    //Get the cursor that points to all the data that will be displayed in TutorListView
    public Cursor getTutorLCursor()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        int length = tutorIDs.length;

        String query = "Select First_Name, Last_Name, Program, Email from Tutor";

        String theLength = Integer.toString(length);

        Log.e("The length of the array",theLength);
        //SQL statement
        for(int i=0; i < length; i++)
        {
            if(tutorIDs[i]==0){
                break;
            }

            Log.e("TutorId[0] in fun2",Integer.toString(tutorIDs[0])); //should print 1
            Log.e("TutorId[1] in fun2",Integer.toString(tutorIDs[1])); //should print 2

            if(i ==0)
            {
                //"Select Tutor_id from Tutor where Name= ? and Course=? and Program=?", new String[]{name, course, program});
                String theID = " where Tutor_Id =";
                theID += Integer.toString(tutorIDs[0]);

                //Add to the query
                query+=(theID);
            }
            else {
                String theID = " or Tutor_Id=";
                theID+=Integer.toString(tutorIDs[i]);

                //Add to the query
                query+=theID;
            }

            //Because we need some way to clear it
            tutorIDs[i]=0; //******************************************************************************************************
        }

        Log.e("The SQL Statement: ", query);
        cursor = db.rawQuery(query,null); //Error maybe here?
        query = null;



        return cursor;

    }

    //Return the cursor with the associated profile
    public Cursor getTutorProfileButton1(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        String tutorID = String.valueOf("1");
        
        cursor = db.rawQuery("Select Last_Name, First_Name, Date_Of_Birth, Email, Biography, " +
                "Year_Of_Student, Hourly_Fee, Rating, Course, Program " +
                "from tutor where Tutor_Id=?", new String[]{tutorID});
        return cursor;
    }


}