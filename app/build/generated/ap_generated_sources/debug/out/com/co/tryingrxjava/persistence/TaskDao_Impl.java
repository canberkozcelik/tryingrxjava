package com.co.tryingrxjava.persistence;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTask;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllTasks;

  public TaskDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTask = new EntityInsertionAdapter<Task>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tasks`(`taskid`,`taskname`,`taskdescription`,`iscomplete`,`priority`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Task value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        final int _tmp;
        _tmp = value.isComplete() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        stmt.bindLong(5, value.getPriority());
      }
    };
    this.__preparedStmtOfDeleteAllTasks = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Tasks";
        return _query;
      }
    };
  }

  @Override
  public Completable insertTask(final Task task) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTask.insert(task);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public void deleteAllTasks() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllTasks.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllTasks.release(_stmt);
    }
  }

  @Override
  public Flowable<Task> getTask() {
    final String _sql = "SELECT * FROM Tasks LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"Tasks"}, new Callable<Task>() {
      @Override
      public Task call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfMId = CursorUtil.getColumnIndexOrThrow(_cursor, "taskid");
          final int _cursorIndexOfMName = CursorUtil.getColumnIndexOrThrow(_cursor, "taskname");
          final int _cursorIndexOfMDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "taskdescription");
          final int _cursorIndexOfMIsComplete = CursorUtil.getColumnIndexOrThrow(_cursor, "iscomplete");
          final int _cursorIndexOfMPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final Task _result;
          if(_cursor.moveToFirst()) {
            final String _tmpMId;
            _tmpMId = _cursor.getString(_cursorIndexOfMId);
            final String _tmpMName;
            _tmpMName = _cursor.getString(_cursorIndexOfMName);
            final String _tmpMDescription;
            _tmpMDescription = _cursor.getString(_cursorIndexOfMDescription);
            final boolean _tmpMIsComplete;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfMIsComplete);
            _tmpMIsComplete = _tmp != 0;
            final int _tmpMPriority;
            _tmpMPriority = _cursor.getInt(_cursorIndexOfMPriority);
            _result = new Task(_tmpMId,_tmpMName,_tmpMDescription,_tmpMIsComplete,_tmpMPriority);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
