package com.viswanath.task.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viswanath.task.R;
import com.viswanath.task.adatper.LanguageAdapter;
import com.viswanath.task.db.LanguageViewModel;
import com.viswanath.task.interapi.ApiInterface;
import com.viswanath.task.model.GetLang;
import com.viswanath.task.model.Results;
import com.viswanath.task.utility.ApiClient;
import com.viswanath.task.utility.DividerVerticalItemDecoration;
import com.viswanath.task.utility.EmptyRecyclerView;
import com.viswanath.task.utility.GlobalActivity;
import com.viswanath.task.utility.ItemDecorationAlbumColumns;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Language extends GlobalActivity implements LanguageAdapter.UpdateDataClickListener {
    List<Results> resultsList;
    List<Results> selectedlist;
    EmptyRecyclerView emptyRecyclerView;
    LanguageAdapter languageAdapter;
    int resId;
    String json;
    Gson gson;
    private RelativeLayout mErrorMessage;
    private TextView mAlertTitle;
    private TextView mAlertMsg;
    LanguageViewModel languageViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        gson = new Gson();
        languageViewModel = ViewModelProviders.of(this).get(LanguageViewModel.class);
        resId = R.anim.layout_animation_fall_down;
        emptyRecyclerView = findViewById(R.id.lang_recycler);
        mErrorMessage = findViewById(R.id.error_message);
        mAlertTitle = findViewById(R.id.alert_title);
        mAlertMsg = findViewById(R.id.alert_msg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resultsList = new ArrayList<>();
        selectedlist = new ArrayList<>();
        getcontentfrom();

        final RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        emptyRecyclerView.setLayoutManager(layoutManager);
        languageAdapter = new LanguageAdapter(Language.this, resultsList,this,  0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type type = new TypeToken<List<Results>>() {}.getType();
                json = gson.toJson(selectedlist, type);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent gotonext = new Intent(getApplicationContext(), Newspapers.class );
                gotonext.putExtra("lan", json);
                startActivity(gotonext);
            }
        });
    }

    public void getcontentfrom() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        showProgressDialog(this, "loading lists...");
        Call<GetLang> call = apiInterface.getlanguages();
        call.enqueue(new Callback<GetLang>() {
            @Override
            public void onResponse(@NonNull Call<GetLang> call, @NonNull Response<GetLang> response) {
                if (response.isSuccessful()) {
                    GetLang getLang = response.body();
                    assert getLang != null;
                    if (getLang.getStatus() == 200) {
                        try {
                            List<Results> sampleData = fetchResults(response);
                            Log.d("Language_List", "proceeded");
                            if (sampleData != null && sampleData.size() > 0) {
                                resultsList.addAll(sampleData);
                            }
                            System.out.println("Language_Size-->>" + resultsList.size());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (resultsList.size() > 0) {
                            emptyRecyclerView.setAdapter(languageAdapter);
                            RecyclerView.ItemDecoration itemDecoration = new DividerVerticalItemDecoration(Language.this);
                            //emptyRecyclerView.addItemDecoration(itemDecoration);
                            emptyRecyclerView.addItemDecoration(new ItemDecorationAlbumColumns(
                                    getResources().getDimensionPixelSize(R.dimen.list_spacing),
                                    getResources().getInteger(R.integer.preview_columns)));
                            languageAdapter.setOnItemClickListener(Language.this);
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
                            emptyRecyclerView.setLayoutAnimation(animation);
                            emptyRecyclerView.setEmptyView(mErrorMessage);
                            mAlertTitle.setText(" Sorry!!! Data not available...");
                        } else {
                            //Toast.makeText(Bookings.this, "Data Not Available", Toast.LENGTH_SHORT).show();
                            mErrorMessage.setVisibility(View.VISIBLE);
                            mAlertTitle.setText(" Data Not Available");
                            mAlertMsg.setText(" Please book courier and come back");
                        }
                    }
                }
                dismissProgressDialog();
            }

            @Override
            public void onFailure(@NonNull Call<GetLang> call, @NonNull Throwable t) {
                t.printStackTrace();
                dismissProgressDialog();
            }
        });
    }

    private List<Results> fetchResults(Response<GetLang> response) {
        GetLang status = response.body();
        assert status != null;
        status.getResults().getClass().getName();
        return status.getResults();
    }

    @Override
    public void onItemCheck(int position) {
        languageAdapter.selected(position);
        selectedlist.add(resultsList.get(position));
        //Log.i("selected_id", selectedlist.)
        int listSize = selectedlist.size();

        for (int i = 0; i<listSize; i++){
            Log.i("selected_id_check", String.valueOf(selectedlist.get(i).getId()));
        }
    }

    @Override
    public void onItemUncheck(int position) {
        languageAdapter.selected(position);
        selectedlist.remove(resultsList.get(position));
        //Log.i("selected_id", selectedlist.)
        int listSize = selectedlist.size();

        for (int i = 0; i<listSize; i++){
            Log.i("selected_id_uncheck", String.valueOf(selectedlist.get(i).getId()));
        }
    }
}
