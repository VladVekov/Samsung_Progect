package com.example.recyclerview_2;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RememberListItem.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {
    public abstract ReminderDAO reminderDAO();
}
