package com.CP317.tutorloo.EntityClasses;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private String password;

    //-----first name accessor and mutators-------
    public String getfirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //-------------------------------------------


    //-----last name accessor and mutators-------
    public String getlastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }
    //--------------------------------------------


    //-----Email accessor and mutators-------
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    //-------date of birth
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getdob() {
        return this.dob;
    }

    //---------------------------------------

    //-----Password accessor and mutators-------

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
    //----------------------------------------

}





