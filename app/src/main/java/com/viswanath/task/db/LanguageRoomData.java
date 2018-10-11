package com.viswanath.task.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.viswanath.task.model.Results;

@Database(entities = {Results.class}, version = 1)
public abstract class LanguageRoomData extends RoomDatabase {
    public abstract LanguageDAO languageDAO();
    private static volatile LanguageRoomData INSTANCE;

    static LanguageRoomData getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LanguageRoomData.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LanguageRoomData.class, "lang_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
