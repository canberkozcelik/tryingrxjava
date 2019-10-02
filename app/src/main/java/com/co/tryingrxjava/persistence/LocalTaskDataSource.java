package com.co.tryingrxjava.persistence;

import com.co.tryingrxjava.DataSource;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Using the Room database as a data source.
 */
public class LocalTaskDataSource implements DataSource {

    private final TaskDao mTaskDao;

    public LocalTaskDataSource(TaskDao mTaskDao) {
        this.mTaskDao = mTaskDao;
    }

    @Override
    public Flowable<Task> getTask() {
        return mTaskDao.getTask();
    }

    @Override
    public Completable insertOrUpdateTask(Task task) {
        return mTaskDao.insertTask(task);
    }

    @Override
    public void deleteAllTasks() {
        mTaskDao.deleteAllTasks();
    }
}
