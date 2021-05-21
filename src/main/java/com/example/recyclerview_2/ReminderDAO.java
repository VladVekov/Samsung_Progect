package com.example.recyclerview_2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ReminderDAO {
    @Insert
    void insert(RememberListItem rememberListItem);

    @Query("select * from rememberlistitem order by mDeadline")
    List<RememberListItem> getAll();

    @Update
    void update(RememberListItem rememberListItem);

    @Delete
    void delete(RememberListItem rememberListItem);

    @Query("select * from RememberListItem  where isFinished = 0 order by mDeadline limit 1")
    RememberListItem getFirstElem();
}
