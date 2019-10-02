package com.co.tryingrxjava.persistence;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Immutable model class for a Task
 */
@Entity(tableName = "tasks")
public class Task {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "taskid")
    private String mId;

    @ColumnInfo(name = "taskname")
    private String mName;

    @ColumnInfo(name = "taskdescription")
    private String mDescription;

    @ColumnInfo(name = "iscomplete")
    private boolean mIsComplete;

    @ColumnInfo(name = "priority")
    private int mPriority;

    @Ignore
    public Task(String taskName) {
        mId = UUID.randomUUID().toString();
        mName = taskName;
    }

    public Task(String id, String name, String description, boolean isComplete, int priority) {
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mIsComplete = isComplete;
        this.mPriority = priority;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    public void setId(@NonNull String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public boolean isComplete() {
        return mIsComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.mIsComplete = isComplete;
    }

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(int priority) {
        this.mPriority = priority;
    }

    public static List<Task> createTasksList() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(UUID.randomUUID().toString(), "Trash", "Take out the trash", true, 3));
        tasks.add(new Task(UUID.randomUUID().toString(), "Dog", "Walk the dog", false, 2));
        tasks.add(new Task(UUID.randomUUID().toString(), "Bed", "Make my bed", true, 1));
        tasks.add(new Task(UUID.randomUUID().toString(), "Dishwasher", "Unload the dishwasher", false, 0));
        tasks.add(new Task(UUID.randomUUID().toString(), "Dinner", "Make dinner", true, 5));
        return tasks;
    }
}
