package com.viswanath.task.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.viswanath.task.model.Results;

import java.util.List;

public class LanguageViewModel extends AndroidViewModel {

    private final LiveData<List<Results>> itemAndPersonList;
    private AppDataBase appDatabase;

    public LanguageViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDataBase.getDatabase(this.getApplication());
        itemAndPersonList = appDatabase.itemAddModel().getAllItems();
    }

    public LiveData<List<Results>> getList() {
        return itemAndPersonList;
    }

    public void deleteItem(Results tracking) {
        new deleteAsyncTask(appDatabase).execute(tracking);
    }

    private static class deleteAsyncTask extends AsyncTask<Results, Void, Void> {
        private AppDataBase db;

        deleteAsyncTask(AppDataBase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Results... params) {
            db.itemAddModel().deleteModel(params[0]);
            return null;
        }

    }
}
