package com.example.tryfortawsila1;

public class Users {
    String ID, fullname, email, phonenum;
    public Users() {
    }
    public Users(String fullname, String email, String phonenumber, String password, String ID) {
    }

    public Users(String fullname, String email, String phonenum, String ID) {
        this.fullname = fullname;
        this.email = email;
        this.phonenum = phonenum;
        this.ID = ID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
