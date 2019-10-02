package com.co.tryingrxjava.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TaskDao {

    /**
     * Get the task from the table. Since for simplicity we only have one task in the database,
     * this query gets all tasks from the table, but limits the result to just the 1st task.
     *
     * @return the task from the table
     */
    @Query("SELECT * FROM Tasks LIMIT 1")
    Flowable<Task> getTask();

    /**
     * Insert a task in the database. If the task already exists, replace it.
     *
     * @param task the task to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertTask(Task task);

    /**
     * Delete all tasks.
     */
    @Query("DELETE FROM Tasks")
    void deleteAllTasks();
}
