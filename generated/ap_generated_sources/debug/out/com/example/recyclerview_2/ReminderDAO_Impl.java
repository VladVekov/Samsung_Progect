package com.example.recyclerview_2;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ReminderDAO_Impl implements ReminderDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RememberListItem> __insertionAdapterOfRememberListItem;

  private final EntityDeletionOrUpdateAdapter<RememberListItem> __deletionAdapterOfRememberListItem;

  private final EntityDeletionOrUpdateAdapter<RememberListItem> __updateAdapterOfRememberListItem;

  public ReminderDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRememberListItem = new EntityInsertionAdapter<RememberListItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `RememberListItem` (`mDeadline`,`mName`,`isFinished`,`strDeadline`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RememberListItem value) {
        stmt.bindLong(1, value.mDeadline);
        if (value.mName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.mName);
        }
        stmt.bindLong(3, value.isFinished);
        if (value.strDeadline == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.strDeadline);
        }
      }
    };
    this.__deletionAdapterOfRememberListItem = new EntityDeletionOrUpdateAdapter<RememberListItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `RememberListItem` WHERE `mDeadline` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RememberListItem value) {
        stmt.bindLong(1, value.mDeadline);
      }
    };
    this.__updateAdapterOfRememberListItem = new EntityDeletionOrUpdateAdapter<RememberListItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `RememberListItem` SET `mDeadline` = ?,`mName` = ?,`isFinished` = ?,`strDeadline` = ? WHERE `mDeadline` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RememberListItem value) {
        stmt.bindLong(1, value.mDeadline);
        if (value.mName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.mName);
        }
        stmt.bindLong(3, value.isFinished);
        if (value.strDeadline == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.strDeadline);
        }
        stmt.bindLong(5, value.mDeadline);
      }
    };
  }

  @Override
  public void insert(final RememberListItem rememberListItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRememberListItem.insert(rememberListItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final RememberListItem rememberListItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfRememberListItem.handle(rememberListItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final RememberListItem rememberListItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfRememberListItem.handle(rememberListItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<RememberListItem> getAll() {
    final String _sql = "select * from rememberlistitem order by mDeadline";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "mDeadline");
      final int _cursorIndexOfMName = CursorUtil.getColumnIndexOrThrow(_cursor, "mName");
      final int _cursorIndexOfIsFinished = CursorUtil.getColumnIndexOrThrow(_cursor, "isFinished");
      final int _cursorIndexOfStrDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "strDeadline");
      final List<RememberListItem> _result = new ArrayList<RememberListItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final RememberListItem _item;
        _item = new RememberListItem();
        _item.mDeadline = _cursor.getLong(_cursorIndexOfMDeadline);
        if (_cursor.isNull(_cursorIndexOfMName)) {
          _item.mName = null;
        } else {
          _item.mName = _cursor.getString(_cursorIndexOfMName);
        }
        _item.isFinished = _cursor.getInt(_cursorIndexOfIsFinished);
        if (_cursor.isNull(_cursorIndexOfStrDeadline)) {
          _item.strDeadline = null;
        } else {
          _item.strDeadline = _cursor.getString(_cursorIndexOfStrDeadline);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public RememberListItem getFirstElem() {
    final String _sql = "select * from RememberListItem  where isFinished = 0 order by mDeadline limit 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "mDeadline");
      final int _cursorIndexOfMName = CursorUtil.getColumnIndexOrThrow(_cursor, "mName");
      final int _cursorIndexOfIsFinished = CursorUtil.getColumnIndexOrThrow(_cursor, "isFinished");
      final int _cursorIndexOfStrDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "strDeadline");
      final RememberListItem _result;
      if(_cursor.moveToFirst()) {
        _result = new RememberListItem();
        _result.mDeadline = _cursor.getLong(_cursorIndexOfMDeadline);
        if (_cursor.isNull(_cursorIndexOfMName)) {
          _result.mName = null;
        } else {
          _result.mName = _cursor.getString(_cursorIndexOfMName);
        }
        _result.isFinished = _cursor.getInt(_cursorIndexOfIsFinished);
        if (_cursor.isNull(_cursorIndexOfStrDeadline)) {
          _result.strDeadline = null;
        } else {
          _result.strDeadline = _cursor.getString(_cursorIndexOfStrDeadline);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
