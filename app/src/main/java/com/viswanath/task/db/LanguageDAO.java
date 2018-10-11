package com.viswanath.task.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.viswanath.task.model.Results;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface LanguageDAO {
    @Query("select * from lang_table")
    LiveData<List<Results>> getAllItems();

    @Query("select * from lang_table where id = :id")
    Results getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addModel(Results addModel);

    @Delete
    void deleteModel(Results addModel);
}
