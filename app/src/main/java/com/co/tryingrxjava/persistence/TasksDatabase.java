package com.co.tryingrxjava.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * The Room database that contains the Tasks table
 */
@Database(entities = {Task.class}, version = 1)
public abstract class TasksDatabase extends RoomDatabase {
    private static volatile TasksDatabase INSTANCE;

    public abstract TaskDao taskDao();

    public static TasksDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TasksDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TasksDatabase.class, "Tasks.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
