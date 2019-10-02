package com.co.tryingrxjava;

import com.co.tryingrxjava.persistence.Task;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface DataSource {
    /**
     * Gets the task from the data source.
     * @return the tas from the data source.
     */
    Flowable<Task> getTask();

    /**
     * Inserts the task into the data source, or if task exists, updates it.
     * @param task the task to be inserted or updated
     */
    Completable insertOrUpdateTask(Task task);

    /**
     * Deletes all tasks from the data source.
     */
    void deleteAllTasks();
}
