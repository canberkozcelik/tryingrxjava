package com.co.tryingrxjava;

import android.content.Context;

import com.co.tryingrxjava.persistence.LocalTaskDataSource;
import com.co.tryingrxjava.persistence.TasksDatabase;
import com.co.tryingrxjava.ui.ViewModelFactory;

/**
 * Enables injection of data sources.
 */
public class Injection {

    public static DataSource provideDataSource(Context context) {
        TasksDatabase database = TasksDatabase.getInstance(context);
        return new LocalTaskDataSource(database.taskDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        DataSource dataSource = provideDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}
