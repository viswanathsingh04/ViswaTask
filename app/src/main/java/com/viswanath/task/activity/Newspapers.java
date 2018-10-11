package com.viswanath.task.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viswanath.task.R;
import com.viswanath.task.model.Results;
import com.viswanath.task.utility.GlobalActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Newspapers extends GlobalActivity {
    String tag = "LifeCycleEvents";
    List<Results> myList;
    String json;
    List<String> finali;
    Gson gson;
    Results results;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspapers);
        finali = new ArrayList<>();
        Log.d(tag, "In the onCreate() event");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gson = new Gson();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myList = new ArrayList<>();
            json = extras.getString("lan");
            Log.i("select", json);
            Type type = new TypeToken<List<Results>>() {
            }.getType();
            myList = gson.fromJson(json, type);
            //results = gson.fromJson(json, Results.class);
            System.out.println(json);
            Log.i("select", myList.toString());
            Log.i("Select", String.valueOf(myList.size()));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        for (int i = 0; i < myList.size(); i++) {
            results = myList.get(i);
            finali.add(String.valueOf(results.getId()));
            Log.i("position_selected", String.valueOf(results.getId()));
        }

        Type type = new TypeToken<List<Results>>() {}.getType();
        String json1 = gson.toJson(finali, type);
        Toast.makeText(this, json1, Toast.LENGTH_SHORT).show();
        //Base64.encodeToString(json1.getBytes());
        //String encodedString = Base64.encodeBase64().withoutPadding().encodeToString(json1.getBytes());
        Toast.makeText(this, json1, Toast.LENGTH_SHORT).show();
    }

    public void onStart() {
        super.onStart();
        Log.d(tag, "In the onStart() event");
    }

    public void onRestart() {
        super.onRestart();
        Log.d(tag, "In the onRestart() event");
    }

    public void onResume() {
        super.onResume();
        Log.d(tag, "In the onResume() event");
    }

    public void onPause() {
        super.onPause();
        Log.d(tag, "In the onPause() event");
    }

    public void onStop() {
        super.onStop();
        Log.d(tag, "In the onStop() event");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(tag, "In the onDestroy() event");
    }
}
