package com.viswanath.task.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.viswanath.task.activity.MainActivity;
import com.viswanath.task.model.User;

//here for this class we are using a singleton pattern

public class SharedPreference {
    //the constants
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_ID = "keyid";
    private static final String USER_ID ="user_id";
    private static final String KEY_MOBILE ="mobile";
    private static final String STATE ="state";
    private static final String CITY ="city";
    private static final String BIRTHDATE ="birth_date";
    private static final String FIRST_NAME ="first_name";
    private static final String LAST_NAME ="last_name";
    private static final String PASSWORD ="password";
    private static SharedPreference mInstance;
    private static Context mCtx;

    private SharedPreference(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPreference getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreference(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
       // editor.putInt(KEY_ID, user.getId());

        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_GENDER, user.getGender());
        editor.putString(FIRST_NAME,user.getFirstname());
        editor.putString(LAST_NAME,user.getLastname());
        editor.putString(KEY_MOBILE,user.getMobilenum());
        editor.putString(STATE,user.getState());
        editor.putString(CITY,user.getCity());
        editor.putString(BIRTHDATE,user.getBirth());
        editor.putString(PASSWORD,user.getPassword());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(USER_ID,null),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_GENDER, null),
                sharedPreferences.getString(KEY_MOBILE,null),
                sharedPreferences.getString(FIRST_NAME,null),
                sharedPreferences.getString(LAST_NAME,null),
                sharedPreferences.getString(STATE,null),
                sharedPreferences.getString(CITY,null),
                sharedPreferences.getString(BIRTHDATE,null)


        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, MainActivity.class));
    }

}