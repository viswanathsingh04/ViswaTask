package com.viswanath.task.model;

public class User {
    private String username, email, password, gender, birth, city, state, firstname, lastname, Mobilenum;
    private int id;
    private String user_id;

    public User(String username, String email, String password, String gender, String birth, String city, String firstname, String state, String lastname, String Mobilenum) {
        //  this.username = username;
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.city = city;
        this.state = state;
        this.firstname = firstname;
        this.lastname = lastname;
        // this.id= id;
        this.user_id = user_id;
    }


    public int getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobilenum() {
        return Mobilenum;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
