package com.co.tryingrxjava.ui.main;

import androidx.lifecycle.ViewModel;

import com.co.tryingrxjava.DataSource;
import com.co.tryingrxjava.persistence.Task;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class MainViewModel extends ViewModel {

    private final DataSource mDataSource;
    private Task mTask;

    public MainViewModel(DataSource dataSource) {
        this.mDataSource = dataSource;
    }

    /**
     * Get the name of task
     *
     * @return a {@link Flowable} that will emit every time the task name has been updated.
     */
    public Flowable<String> getTaskName() {
        return mDataSource.getTask()
                // for every emission of the task, get the task name
                .map(task -> {
                    mTask = task;
                    return task.getName();
                });
    }

    /**
     * Get the description of task
     *
     * @return a {@link Flowable} that will emit every time the task description has been updated.
     */
    public Flowable<String> getTaskDescription() {
        return mDataSource.getTask()
                // for every emission of the task, get the task name
                .map(task -> {
                    mTask = task;
                    return task.getDescription();
                });
    }

    /**
     * Update the task name.
     *
     * @param taskName the new task name
     * @return a {@link Completable} that completes when the task name is updated
     */
    public Completable updateTaskName(final String taskName) {
        // if there's no task, create new task.
        // if we already have a task, then, since the task object is immutable,
        // create a new task, with the id of the previous task and the updated task name.
        mTask = mTask == null
                ? new Task(taskName)
                : new Task(mTask.getId(), taskName, mTask.getDescription(), mTask.isComplete(), mTask.getPriority());
        return mDataSource.insertOrUpdateTask(mTask);
    }
}
